package com.romy.prime.biz.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.romy.prime.biz.entity.converter.JpaConverterJsonObject;

import lombok.Data;
import lombok.Generated;

/**
 * 
 * 서버 정보 Entity
 *
 */
@Data
@Generated
@Entity(name = "SERVER_INFO")
public class ServerInfo {

	@Id
	@Column(name = "SERVER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long serverId;
	
	@Column(name = "SERVER_NM")
	private String serverNm;
	
	@Column(name = "SERVER_HOST")
	private String serverHost;
	
	@Column(name = "API_URI")
	private String apiUri;
	
	@Column(name = "SERVER_HEADERS")
	@Convert(converter = JpaConverterJsonObject.class)
	private Object serverHeaders;
	
	@Column(name = "ALTERNATIVE_SERVER_NM")
	private String alternativeServerNm;
	
	@Column(name = "ALTERNATIVE_SERVER_HOST")
	private String alternativeServerHost;
	
	@Column(name = "ALTERNATIVE_API_URI")
	private String alternativeApiUri;
	
	@Column(name = "ALTERNATIVE_SERVER_HEADERS")
	@Convert(converter = JpaConverterJsonObject.class)
	private Object alternativeServerHeaders;
	
	@Column(name = "SORT_ORDER")
	private int sortOrder;
}
