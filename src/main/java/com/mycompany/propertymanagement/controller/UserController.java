package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

//    http://localhost:8080//api/v1/user/register
    @PostMapping("/register")
    @ApiOperation(value = "register", notes = "this method is used for registration")
    public ResponseEntity<UserDTO> register(@ApiParam(                            //these below lines are for swagger implementation
            name = "userDTO",
            type = "UserDTO",
            value = "User data",
            example = "user information",
            required = true) @Valid @RequestBody UserDTO userDTO){
      userDTO = userService.register(userDTO);
      ResponseEntity<UserDTO> responseEntity = new ResponseEntity<>(userDTO, HttpStatus.CREATED);

      return responseEntity;
    }

    //localhost:8080/api/v1/user/login
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserDTO userDTO){
       userDTO = userService.login(userDTO.getOwnerEmail(), userDTO.getPassword());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    //localhost:8080/api/v1/user/userName
    @PostMapping("/userName")  //getting
    public ResponseEntity<UserDTO> DetailsOfTheOwner(@RequestBody UserDTO userDTO){
      userDTO = userService.FindDetailsByOwnerName(userDTO.getOwnerName());
     return  new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
