package com.network.ipmanagement.service;

import com.network.ipmanagement.dto.IpAddressDetail;
import com.network.ipmanagement.repository.CustomIpService;
import com.network.ipmanagement.repository.IpStore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.util.SubnetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.network.ipmanagement.constants.StringConstants.AVAILABLE;

@Service
@Slf4j
public class IpManagementService {

    private StoreServiceImpl storeServiceImpl;
    private CustomIpService customIpService;

    @Autowired
    public IpManagementService(StoreServiceImpl storeServiceImpl, CustomIpService customIpService) {
        this.storeServiceImpl = storeServiceImpl;
        this.customIpService = customIpService;
    }

    /**
     * take in a CIDR block (e.g. 10.0.0.1/24) and add all IP addresses within that block to the data store with status “available”
     * @param ipWithCIDR
     * @return boolean status
     */
    public boolean createIP(String ipWithCIDR) {
        final String[] ipWithCidrBlock = ipWithCIDR.split("/");
        String ipAddress = ipWithCidrBlock[0];
        String cidr = ipWithCidrBlock[1];

        SubnetUtils utils = new SubnetUtils(ipWithCIDR);
        String[] ipAddresses = utils.getInfo().getAllAddresses();
        final List<IpStore> ipStoreList = Arrays.stream(ipAddresses).map(ip -> IpStore.builder()
                                                                            .ipaddress(ip)
                                                                            .status(AVAILABLE)
                                                                            .cidr(Integer.parseInt(cidr))
                                                                            .build())
                                                                    .collect(Collectors.toList());
        try {
            final int size = storeServiceImpl.saveAllIps(ipStoreList).size();
            return size == ipAddresses.length ? true : false;
        } catch (Exception e){
            log.error("error saving ip to database.", e);
            throw new RuntimeException("IP address not saved. error:", e);
        }
    }

    /**
     * return all IP addresses in the system with their current status
     * @return list
     */
    public List<IpAddressDetail> getAllIps() {
        final List<IpStore> ipStores = storeServiceImpl.fetchIpAddressList();

        return ipStores.stream().map(ipStore -> IpAddressDetail.builder()
                                                                    .ipAddress(ipStore.getIpaddress())
                                                                    .status(ipStore.getStatus())
                                                                    .build())
                                                                .collect(Collectors.toList());
    }

    /**
     * Acquire an IP - set the status of a certain IP to “acquired”
     * @param ip address
     * @return acquire status
     */
    public boolean acquireIp(String ip) {
        try {
            customIpService.updateIpStatus(ip);
            return true;
        } catch (Exception e){
            log.error("error acquiring ip.", e);
            throw new RuntimeException("IP address not acquired. error:", e);
        }
    }
}
