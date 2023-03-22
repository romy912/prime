package com.romy.prime.biz.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.romy.prime.biz.entity.PopularTerms;

/**
 * 
 * 인기 검색어 Repository
 *
 */
@Repository
public interface PopularTermsRepository extends JpaRepository<PopularTerms, Long> {

	PopularTerms findByTerm(String term);

	List<PopularTerms> findAll(Sort sort);
}
