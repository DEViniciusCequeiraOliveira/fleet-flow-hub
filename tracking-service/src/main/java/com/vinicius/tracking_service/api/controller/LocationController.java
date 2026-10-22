package com.vinicius.tracking_service.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.tracking_service.api.dto.request.LocationRequest;
import com.vinicius.tracking_service.domain.service.Location.LocationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService  locationService;
    
    @PostMapping
    public void trackingLocate(@RequestBody LocationRequest location) {
        locationService.trackingLocate(location);
    }
    
}
