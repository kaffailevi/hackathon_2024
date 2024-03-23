package com.threedumbdevs.springapi.services;

import com.threedumbdevs.springapi.TO.LocationTO;
import com.threedumbdevs.springapi.converters.LocationConverter;
import com.threedumbdevs.springapi.entities.Location;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.repositories.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationService {

    private LocationRepository locationRepository;

    public List<LocationTO> findAll() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream().map(LocationConverter::convertLocationToTO).toList();
    }

    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public Location update(Location location) {
        Optional<Location> opLocation = locationRepository.findById(location.getId());
        if (opLocation.isEmpty()) {
            throw new NotFoundException("Location not found");
        }
        Location updatedLocation = opLocation.get();
        updatedLocation.setLatitude(location.getLatitude());
        updatedLocation.setLongitude(location.getLongitude());
        updatedLocation.setName(location.getName());
        return locationRepository.save(updatedLocation);
    }


    public Location delete(Long locationId) {
        Optional<Location> opLocation = locationRepository.findById(locationId);
        locationRepository.delete(opLocation.orElseThrow(() -> new NotFoundException("Location not found")));
        return opLocation.get();
    }
}
