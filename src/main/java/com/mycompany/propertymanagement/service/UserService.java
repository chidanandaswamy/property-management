package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.UserDTO;

public interface UserService {

    public UserDTO register(UserDTO userDTO);

    UserDTO login( String email, String password);

    UserDTO FindDetailsByOwnerName(String name);
}
