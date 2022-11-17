package com.network.ipmanagement.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IpAddressDetail {
    private String ipAddress;
    private String status;
}
