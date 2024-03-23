package com.threedumbdevs.springapi.TO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LocationTO implements Serializable {
    private Long id;
    private String name;
    private String latitude;
    private String longitude;
}
