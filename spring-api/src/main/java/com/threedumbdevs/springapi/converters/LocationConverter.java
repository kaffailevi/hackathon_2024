package com.threedumbdevs.springapi.converters;

import com.threedumbdevs.springapi.TO.LocationTO;
import com.threedumbdevs.springapi.entities.Location;

public class LocationConverter {

    public static LocationTO convertLocationToTO(Location location) {
        return new LocationTO(location.getId(), location.getName(), location.getLatitude(), location.getLongitude());
    }

    public static Location convertTOToLocation(LocationTO locationTO) {
        return new Location(locationTO.getId(), locationTO.getName(), locationTO.getLatitude(), locationTO.getLongitude());
    }
}
