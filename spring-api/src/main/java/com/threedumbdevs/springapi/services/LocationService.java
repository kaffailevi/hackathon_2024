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

    public LocationTO findById(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.map(LocationConverter::convertLocationToTO).orElse(null);
    }

    /*public LocationTO save(LocationTO locationTO) {
        Location newLocation = LocationConverter.convertTOToLocation(locationTO);
        return LocationConverter.convertLocationToTO(locationRepository.save(newLocation));
    }*/

    public LocationTO update(LocationTO locationTO) {
        Optional<Location> location = locationRepository.findById(locationTO.getId());
        if (location.isPresent()) {
            Location updatedLocation = location.get();
            updatedLocation.setLatitude(locationTO.getLatitude());
            updatedLocation.setLongitude(locationTO.getLongitude());
            updatedLocation.setName(locationTO.getName());
            return LocationConverter.convertLocationToTO(locationRepository.save(updatedLocation));
        } else throw new NotFoundException("Location not found");
    }
}
