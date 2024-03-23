package com.threedumbdevs.springapi.converters;

import com.threedumbdevs.springapi.TO.UserTO;
import com.threedumbdevs.springapi.entities.User;

public class UserConverter {

    public static UserTO convertUserToUserTO(User user) {
        return new UserTO(user.getId(), user.getFirstName(), user.getLastName(), user.getAge(), user.isAvailableForHire(), user.getRating(), user.getProfilePictureUrl());
    }

    public static User convertUserTOToUser(UserTO userTO) {
        return new User(userTO.getId(), userTO.getFirstName(), userTO.getLastName(), userTO.getAge(), userTO.isAvailableForHire(), userTO.getRating(), userTO.getProfilePictureUrl());
    }
}
