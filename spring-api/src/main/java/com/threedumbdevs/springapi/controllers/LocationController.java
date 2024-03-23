package com.threedumbdevs.springapi.controllers;

import com.threedumbdevs.springapi.TO.LocationTO;
import com.threedumbdevs.springapi.converters.LocationConverter;
import com.threedumbdevs.springapi.entities.Location;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.services.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/v1/location")
@AllArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping(path = "all")
    public List<LocationTO> getAllLocations() {
        return locationService.findAll();
    }

    @GetMapping(path = "find/{id}")
    public LocationTO getLocationById(Long id) {
        Optional<Location> opLocation = locationService.findById(id);
        if(opLocation.isEmpty()) {
            throw new NotFoundException("Location not found");
        }
        return LocationConverter.convertLocationToTO(opLocation.get());
    }

    @PostMapping(path = "add")
    public LocationTO addLocation(LocationTO locationTO) {
        Location newLocation = LocationConverter.convertTOToLocation(locationTO);
        return LocationConverter.convertLocationToTO(locationService.save(newLocation));
    }

    @PostMapping(path = "update")
    public LocationTO updateLocation(LocationTO locationTO) {
        Location location = LocationConverter.convertTOToLocation(locationTO);
        try {
            return LocationConverter.convertLocationToTO(locationService.update(location));
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("delete/{location_id}")
    public LocationTO deleteLocation(@PathVariable Long location_id) {
        Location location = locationService.delete(location_id);
        return LocationConverter.convertLocationToTO(location);
    }
}
