package com.network.ipmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomIpService extends JpaRepository<IpStore, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update IP_STORE set status='acquired' WHERE ipaddress = :ip", nativeQuery=true)
    void updateIpStatus(@Param("ip") String ipAddress);
}
