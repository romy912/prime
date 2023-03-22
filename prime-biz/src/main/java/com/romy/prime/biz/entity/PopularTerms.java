package com.romy.prime.biz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Generated;

/**
 * 
 * 인기 검색어 Entity
 *
 */
@Data
@Generated
@Entity(name = "POPULAR_TERMS")
public class PopularTerms {

	@Id
	@JsonIgnore
	@Column(name = "TERM_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long termId;
	
	@Column(name = "TERM", unique = true)
	private String term;
	
	@Column(name = "HIT")
	private long hit;
	
}
