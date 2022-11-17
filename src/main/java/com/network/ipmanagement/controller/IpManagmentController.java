package com.network.ipmanagement.controller;

import com.network.ipmanagement.dto.IpAddressDetail;
import com.network.ipmanagement.dto.IpCreate;
import com.network.ipmanagement.repository.IpStore;
import com.network.ipmanagement.service.IpManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IpManagmentController {

    private IpManagmentService ipManagmentService;

    @Autowired
    public IpManagmentController(IpManagmentService ipManagmentService){
        this.ipManagmentService = ipManagmentService;
    }

    @PostMapping("create")
    public boolean createIpForCIDRBlock(@RequestBody IpCreate ipCreate){

        ipManagmentService.createIP(ipCreate.getIpWithCIDR());

        return true;
    }

    @GetMapping("getAllIps")
    public List<IpAddressDetail> getAllIpsWithStatus(){
        return ipManagmentService.getAllIps();
    }

    @PatchMapping("acquire/{ip}")
    public boolean acquireIp(@PathVariable String ip){
        ipManagmentService.acquireIp(ip);

        return true;
    }
}
