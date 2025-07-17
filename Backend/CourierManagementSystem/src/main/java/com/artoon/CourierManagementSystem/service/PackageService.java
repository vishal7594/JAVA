package com.artoon.CourierManagementSystem.service;

import com.artoon.CourierManagementSystem.model.dto.response.PackageResponse;
import com.artoon.CourierManagementSystem.repository.PackageRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    @Autowired
    PackageRepository packageRepository;
    public List<PackageResponse> getMyPackages(Authentication authentication) {



    }
}
