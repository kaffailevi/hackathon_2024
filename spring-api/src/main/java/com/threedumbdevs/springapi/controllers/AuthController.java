package com.threedumbdevs.springapi.controllers;

import com.threedumbdevs.springapi.TO.UserCTO;
import com.threedumbdevs.springapi.TO.UserTO;
import com.threedumbdevs.springapi.converters.UserConverter;
import com.threedumbdevs.springapi.entities.User;
import com.threedumbdevs.springapi.exceptions.NotAllowedException;
import com.threedumbdevs.springapi.services.PasswordService;
import com.threedumbdevs.springapi.services.TokenService;
import com.threedumbdevs.springapi.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")

public class AuthController {
    private UserService userService;
    private TokenService tokenService;
    @PostMapping("/register")
    public UserTO register(@RequestBody UserCTO userCTO) {

        return UserConverter.convertUserToUserTO(userService.save(userCTO));

    }




    @PostMapping("/login")
    public String login(@RequestHeader Map<String, String> headers) {
        String decodedAuthHeader = new String(Base64.decodeBase64(headers.get("authorization").substring(6)));
        String email = decodedAuthHeader.substring(0, decodedAuthHeader.indexOf(":"));
        String pw = decodedAuthHeader.substring(decodedAuthHeader.indexOf(":") + 1);

        User user = userService.findByEmail(email);
        if(PasswordService.check(pw, user.getPassword())) {
            return tokenService.createToken(user.getFirstName()+user.getLastName(), user.getEmail(), user.getId());
        } else {
            throw new NotAllowedException("Invalid credentials");
        }
    }

}
