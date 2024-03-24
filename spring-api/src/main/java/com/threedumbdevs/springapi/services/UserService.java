package com.threedumbdevs.springapi.services;

import com.threedumbdevs.springapi.TO.UserCTO;
import com.threedumbdevs.springapi.TO.UserTO;
import com.threedumbdevs.springapi.converters.UserConverter;
import com.threedumbdevs.springapi.entities.Pet;
import com.threedumbdevs.springapi.entities.User;
import com.threedumbdevs.springapi.exceptions.InternalErrorException;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User save(UserCTO userCTO) {
        User user = new User();
        user.setFirstName(userCTO.getFirstName());
        user.setLastName(userCTO.getLastName());
        user.setEmail(userCTO.getEmail());
        user.setAge(userCTO.getAge());
        user.setAvailableForHire(userCTO.isAvailableForHire());
        user.setRating(0);
        user.setProfilePictureUrl("default.jpg");
        String password = PasswordService.hash(userCTO.getPassword());
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            return user.get();
        } else throw new NotFoundException("User not found");
    }

    public Optional<User> find(Long id) {
        return userRepository.findById(id);
    }

//    public List<Pet> findPets(User user){
//        return
//    }


//    public UserTO findById(Long id) {
//        Optional<User> user = userRepository.findById(id);
//        if(user.isPresent()) {
//            return UserConverter.convertUserToTO(user.get());
//        }
//        return null;
//    }

//    public UserTO update(UserTO userTO) {
//        Optional<User> user = userRepository.findById(userTO.getId());
//        if(user.isPresent()) {
//            User updatedUser = user.get();
//            updatedUser.setFirstName(userTO.getFirstName());
//            updatedUser.setLastName(userTO.getLastName());
//            updatedUser.setAge(userTO.getAge());
//            updatedUser.setAvailableForHire(userTO.isAvailableForHire());
//            updatedUser.setRating(userTO.getRating());
//            return UserConverter.convertUserToTO(userRepository.save(updatedUser));
//        } else throw new NotFoundException("User not found");
//    }

//    public UserTO delete(Long id) {
//        Optional<User> user = userRepository.findById(id);
//        if(user.isPresent()) {
//            try {
//                userRepository.delete(user.get());
//                return UserConverter.convertUserToTO(user.get());
//            } catch (Exception e) {
//                throw new InternalErrorException(e.getMessage());
//            }
//        } else throw new NotFoundException("User not found");
//    }
}
