package com.network.ipmanagement.service;

import com.network.ipmanagement.repository.IpStore;

import java.util.List;

public interface StoreService {
    // save operation
    IpStore saveIpAddress(IpStore ipStore);

    //save all
    List<IpStore> saveAllIps(List<IpStore> ips);

    // read operation
    List<IpStore> fetchIpAddressList();

    // update operation
    IpStore updateIpAddress(IpStore ipStore);

    // delete operation
    void deleteIpAddressById(Long departmentId);

}
