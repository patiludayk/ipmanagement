package com.network.ipmanagement.service;

import com.network.ipmanagement.dto.IpAddressDetail;
import com.network.ipmanagement.repository.IpStore;
import org.apache.commons.net.util.SubnetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.network.ipmanagement.constants.StringConstants.AVAILABLE;

@Service
public class IpManagmentService {

    private StoreServiceImpl storeServiceImpl;
    private CustomIpService customIpService;

    @Autowired
    public IpManagmentService(StoreServiceImpl storeServiceImpl, CustomIpService customIpService) {
        this.storeServiceImpl = storeServiceImpl;
        this.customIpService = customIpService;
    }

    public void createIP(String ipWithCIDR) {
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
        storeServiceImpl.saveAllIps(ipStoreList);
    }

    public List<IpAddressDetail> getAllIps() {
        final List<IpStore> ipStores = storeServiceImpl.fetchIpAddressList();

        return ipStores.stream().map(ipStore -> IpAddressDetail.builder()
                                                                    .ipAddress(ipStore.getIpaddress())
                                                                    .status(ipStore.getStatus())
                                                                    .build())
                                                                .collect(Collectors.toList());
    }

    public void acquireIp(String ip) {
        customIpService.updateIpStatus(ip);
    }
}
