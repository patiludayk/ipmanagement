package com.network.ipmanagement.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "IP_STORE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IpStore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "ipaddress")
    private String ipaddress;
    private String status;
    private int cidr;
}
