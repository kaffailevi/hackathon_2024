package com.threedumbdevs.springapi.services;

import com.threedumbdevs.springapi.entities.User;
import com.threedumbdevs.springapi.exceptions.InternalErrorException;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserConverter::convertUserToTO).toList();
    }

    /*public User save(UserTO userTO) {
        User newUser = UserConverter.convertTOToUser(userTO);
    }*/

    public UserTO findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return UserConverter.convertUserToTO(user.get());
        }
        return null;
    }

    public UserTO update(UserTO userTO) {
        Optional<User> user = userRepository.findById(userTO.getId());
        if(user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setFirstName(userTO.getFirstName());
            updatedUser.setLastName(userTO.getLastName());
            updatedUser.setAge(userTO.getAge());
            updatedUser.setAvailableForHire(userTO.isAvailableForHire());
            updatedUser.setRating(userTO.getRating());
            return UserConverter.convertUserToTO(userRepository.save(updatedUser));
        } else throw new NotFoundException("User not found");
    }

    public UserTO delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            try {
                userRepository.delete(user.get());
                return UserConverter.convertUserToTO(user.get());
            } catch (Exception e) {
                throw new InternalErrorException(e.getMessage());
            }
        } else throw new NotFoundException("User not found");
    }
}
