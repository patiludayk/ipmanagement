package com.network.ipmanagement.util;

import org.apache.commons.net.util.SubnetUtils;

import java.util.Arrays;

public class LocalUtils {
    public static void main(String[] args) {
        final String cidrNotation = "192.168.1.0/24";
        final String cidrNotation1 = "192.168.1.0/30";
        SubnetUtils utils = new SubnetUtils(cidrNotation1);
        String[] allIps = utils.getInfo().getAllAddresses();

        Arrays.stream(allIps).forEach(ip -> System.out.println(ip));
    }
}
