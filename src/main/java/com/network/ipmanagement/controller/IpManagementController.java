package com.network.ipmanagement.controller;

import com.network.ipmanagement.dto.IpAddressDetail;
import com.network.ipmanagement.dto.IpCreate;
import com.network.ipmanagement.service.IpManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IpManagementController {

    private IpManagementService ipManagementService;

    @Autowired
    public IpManagementController(IpManagementService ipManagementService){
        this.ipManagementService = ipManagementService;
    }

    @PostMapping("create")
    public boolean createIpForCIDRBlock(@RequestBody IpCreate ipCreate){
        return ipManagementService.createIP(ipCreate.getIpWithCIDR());
    }

    @GetMapping("getAllIps")
    public List<IpAddressDetail> getAllIpsWithStatus(){
        return ipManagementService.getAllIps();
    }

    @PatchMapping("acquire/{ip}")
    public boolean acquireIp(@PathVariable String ip){
        return ipManagementService.acquireIp(ip);
    }
}
