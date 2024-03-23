package com.threedumbdevs.springapi.services;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class PasswordService {

    private static final String HASH_ALGORITHM = "SHA3-256";
    private static final Logger LOG = LoggerFactory.getLogger(PasswordService.class.getName());

    public static String hash(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] result = md.digest(password.getBytes());

            StringBuilder strBuilder = new StringBuilder();
            for (byte x : result){
                strBuilder.append(String.format("%02x",x));
            }

            return strBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }

    }

    public static boolean check(String password, String stored){
        try{
            return hash(password).equals(stored);
        }catch (IllegalArgumentException e)
        {
            LOG.error("Something went wrong during hash procedure");
            return false;
        }
    }


}