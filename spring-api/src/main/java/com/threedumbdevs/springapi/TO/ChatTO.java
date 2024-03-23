package com.threedumbdevs.springapi.TO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ChatTO implements Serializable {
    private Long id;
    private String message;
    private Long sendUserId;
    private Long receiveUserId;
    private String date;
}
