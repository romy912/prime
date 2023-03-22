package com.romy.prime.biz.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.romy.prime.biz.entity.PopularTerms;
import com.romy.prime.biz.exception.ValidationException;
import com.romy.prime.biz.repository.PopularTermsRepository;

/**
 * 
 * 인기 검색어 관련 서비스
 *
 */
@Service
public class PopularTemsService {

	@Autowired
	private PopularTermsRepository popularTermsRepository;
	
	
	/**
	 * 검색어 저장
	 * @param term
	 * @return
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void savePopularTerms(@NonNull String term) {
		
		if(StringUtils.isBlank(term)) {
			throw new ValidationException("term is empty");
		}
		
		PopularTerms entity = this.popularTermsRepository.findByTerm(term);
		if(entity != null) {
			entity.setHit(entity.getHit() + 1);
		} else {
			entity = new PopularTerms();
			entity.setHit(1);
			entity.setTerm(term);
		}
		
		this.popularTermsRepository.save(entity);
	}
	
	/**
	 * 인기 검색어 조회
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<PopularTerms> getPopularTermsList() {
		
		List<PopularTerms> listResult = this.popularTermsRepository.findAll(Sort.by(Sort.Direction.DESC, "hit"));
		if(CollectionUtils.isNotEmpty(listResult) && listResult.size() > 10) {
			return listResult.subList(0, 10);
		}
		
		return listResult;
	}
	
	
}
