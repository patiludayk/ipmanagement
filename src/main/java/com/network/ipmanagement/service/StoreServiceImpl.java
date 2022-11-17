package com.network.ipmanagement.service;

import com.network.ipmanagement.repository.IpRepository;
import com.network.ipmanagement.repository.IpStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private IpRepository ipRepository;

    @Autowired
    public StoreServiceImpl(IpRepository ipRepository){
        this.ipRepository = ipRepository;
    }


    @Override
    public IpStore saveIpAddress(IpStore ipStore) {
        return ipRepository.save(ipStore);
    }

    @Override
    public List<IpStore> saveAllIps(List<IpStore> ips){
        return (List<IpStore>) ipRepository.saveAll(ips);
    }

    @Override
    public List<IpStore> fetchIpAddressList() {
        return (List<IpStore>) ipRepository.findAll();
    }

    @Override
    public IpStore updateIpAddress(IpStore ipStore) {
        return null;
    }

    @Override
    public void deleteIpAddressById(Long departmentId) {

    }

}
