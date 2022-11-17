package com.network.ipmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpRepository extends CrudRepository<IpStore, Long> {

}
