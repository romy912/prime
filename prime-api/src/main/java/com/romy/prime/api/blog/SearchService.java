package com.romy.prime.api.blog;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romy.prime.api.blog.model.BlogInfo;
import com.romy.prime.api.blog.model.BlogResult;
import com.romy.prime.api.model.kakao.KakaoBlogResult;
import com.romy.prime.api.model.kakao.KakaoDocument;
import com.romy.prime.api.model.naver.NaverBlogResult;
import com.romy.prime.api.model.naver.NaverItem;
import com.romy.prime.biz.entity.ServerInfo;
import com.romy.prime.biz.service.ExternalApiService;
import com.romy.prime.biz.service.PopularTemsService;
import com.romy.prime.biz.service.ServerInfoService;

import net.sf.json.JSONObject;

/**
 * 검색 서비스
 */
@Service
public class SearchService {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private ServerInfoService serverInfoService;

	@Autowired
	private ExternalApiService externalApiService;

	@Autowired
	private PopularTemsService popularTemsService;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

	/**
	 * 서버 구분
	 */
	public enum Server {
		KAKAO, NAVER;
	}

	/**
	 * 블로그 검색 
	 * @param query
	 * @param size
	 * @return
	 */
	public List<BlogResult> getBlogSearchList(@NonNull String query, String sort, int page, int size) {

		List<BlogResult> listResult = new ArrayList<>();
		
		// 검색어 저장
		this.popularTemsService.savePopularTerms(query);
		
		// 서버 조회
		List<ServerInfo> listServer = this.serverInfoService.findServerInfoList(null, null);

		for (ServerInfo serverInfo : listServer) {
			Object result = this.callBlogSearchApi(serverInfo, query, sort, page, size);
			
			BlogResult blogResult = this.makeBlogSearchResult(result, size);
			listResult.add(blogResult);
		}

		return listResult;
	}

	/**
	 * 블로그 검색 API 호출
	 * @param serverInfo
	 * @param query
	 * @return
	 */
	private Object callBlogSearchApi(ServerInfo serverInfo, String query, String sort, int page, int size) {

		Map<String, Object> params = new HashMap<>();
		params.put("query", query);

		String serverNm = serverInfo.getServerNm();
		String serverHost = serverInfo.getServerHost();
		String apiUri = serverInfo.getApiUri();
		JSONObject serverHeaders = (JSONObject) serverInfo.getServerHeaders();

		try {
			this.setParameters(serverNm, sort, page, size, params);
			
			Object result = this.externalApiService.callExternalGetApi(serverHost + apiUri, 
					serverHeaders, params);
			
			if(Server.KAKAO.toString().equals(serverNm)) {
				return this.mapper.convertValue(result, KakaoBlogResult.class);
			}

			// server error 일 경우 대체 서버로 조회
		} catch (ServerErrorException e) {

			serverNm = serverInfo.getAlternativeServerNm();
			serverHost = serverInfo.getAlternativeServerHost();
			apiUri = serverInfo.getAlternativeApiUri();
			serverHeaders = (JSONObject) serverInfo.getAlternativeServerHeaders();

			this.setParameters(serverNm, sort, page, size, params);
			Object result = this.externalApiService.callExternalGetApi(serverHost + apiUri, 
					serverHeaders, params);
			
			if(Server.NAVER.toString().equals(serverNm)) {
				return this.mapper.convertValue(result, NaverBlogResult.class);
			}
		}
		
		return null;
	}
	
	/**
	 * 파라미터 셋팅
	 * @param serverNm
	 * @param sort
	 * @param page
	 * @param size
	 * @param params
	 */
	private void setParameters(String serverNm, String sort, int page, int size, Map<String, Object> params) {
		
		if(Server.KAKAO.toString().equals(serverNm)) {
			params.put("sort", "A".equals(sort) ? "accuracy" : "recency");
			params.put("page", page);
			params.put("size", size);
		} else if(Server.NAVER.toString().equals(serverNm)) {
			params.put("sort", "A".equals(sort) ? "sim" : "date");
			params.put("start", page);
			params.put("display", size);
		}
	}
	
	/**
	 * 결과값 컨버트
	 * @param searchResult
	 * @param size
	 * @return
	 */
	private BlogResult makeBlogSearchResult(Object searchResult, int size) {
		
		BlogResult result = new BlogResult();
		
		if (searchResult instanceof KakaoBlogResult) {
			result.setServerNm(Server.KAKAO.toString());
			
			KakaoBlogResult kakaoResult = (KakaoBlogResult) searchResult;
			
			long totalCount = kakaoResult.getMeta().getTotalCount();
			int pageCount = (int) (Math.floorDiv(totalCount, size) + (Math.floorMod(totalCount, size) == 0 ? 0 : 1));
			
			result.setTotalCount(totalCount);
			result.setPageCount(pageCount);
			
			List<KakaoDocument> listDoc = kakaoResult.getDocuments();
			if(CollectionUtils.isNotEmpty(listDoc)) {
				List<BlogInfo> listData = new ArrayList<>();
				
				listDoc.stream().forEach(doc -> {
					
					BlogInfo blog = new BlogInfo();
					blog.setTitle(doc.getTitle());
					blog.setBlogname(doc.getBlogname());
					blog.setContents(doc.getContents());
					blog.setUrl(doc.getUrl());
					blog.setRegDate(doc.getDatetime().toLocalDateTime().toLocalDate());
					
					listData.add(blog);
				});
				
				result.setList(listData);
			}
		} else if (searchResult instanceof NaverBlogResult) {
			result.setServerNm(Server.NAVER.toString());
			
			NaverBlogResult naverResult = (NaverBlogResult) searchResult;
			
			long totalCount = naverResult.getTotal();
			int pageCount = (int) (Math.floorDiv(totalCount, size) + (Math.floorMod(totalCount, size) == 0 ? 0 : 1));
			
			result.setTotalCount(totalCount);
			result.setPageCount(pageCount);
			
			List<NaverItem> listItem = naverResult.getItems();
			if(CollectionUtils.isNotEmpty(listItem)) {
				List<BlogInfo> listData = new ArrayList<>();
				
				listItem.stream().forEach(item -> {
					
					BlogInfo blog = new BlogInfo();
					blog.setTitle(item.getTitle());
					blog.setBlogname(item.getBloggername());
					blog.setContents(item.getDescription());
					blog.setUrl(item.getLink());
					blog.setRegDate(LocalDate.parse(item.getPostdate(), this.formatter));
					
					listData.add(blog);
				});
				
				result.setList(listData);
			}
		}		
		return result;
	}
}
