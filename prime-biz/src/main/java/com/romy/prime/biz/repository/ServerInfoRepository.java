package com.romy.prime.biz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.romy.prime.biz.entity.ServerInfo;

/**
 * 
 * 서버 정보 Repository
 *
 */
@Repository
public interface ServerInfoRepository extends JpaRepository<ServerInfo, Long> {

}
