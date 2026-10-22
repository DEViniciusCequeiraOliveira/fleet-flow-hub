package com.vinicius.tracking_service.domain.service.Location;

import org.springframework.stereotype.Service;

import com.vinicius.tracking_service.api.dto.request.LocationRequest;
import com.vinicius.tracking_service.domain.model.Location;
import com.vinicius.tracking_service.domain.repository.LocationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public void trackingLocate(LocationRequest locationRequest) {
        Location location = Location.create(
                locationRequest.getVehicleId(),
                locationRequest.getLatitude(),
                locationRequest.getLongitude(),
                locationRequest.getSpeed());

        locationRepository.save(location);
    }

}
