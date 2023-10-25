package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.Exception.BusinessException;
import com.mycompany.propertymanagement.Exception.ErrorModel;
import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.AddressEntity;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.repository.AddressRepository;
import com.mycompany.propertymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> optUse = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());

        if (optUse.isPresent()) {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("Email already exist");
            errorModel.setMessage("The Email with which your trying to register already Exist");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }

        UserEntity userEntity = userConverter.ConvertDTOtoEntity(userDTO);  //Adding address info to userTable
        userEntity = userRepository.save(userEntity);
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setHouseNo(userDTO.getHouseNo());
        addressEntity.setStreet(userDTO.getStreet());
        addressEntity.setCity(userDTO.getCity());
        addressEntity.setPin_code(userDTO.getPin_code());
        addressEntity.setCountry(userDTO.getCountry());

        addressEntity.setUserEntity(userEntity);

        addressRepository.save(addressEntity);

        userDTO = userConverter.ConvertEntityToDTO(userEntity);

        return userDTO;
    }

    @Override
    public UserDTO login(String ownerEmail, String password) {
        UserDTO userDTO = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(ownerEmail, password);
        if (optionalUserEntity.isPresent()) {
            userDTO = userConverter.ConvertEntityToDTO(optionalUserEntity.get());
        } else {

            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("Invalid_Login");
            errorModel.setMessage("Incorrect Email/password");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        return userDTO;
    }

    @Override
    public UserDTO FindDetailsByOwnerName(String name) {
        UserDTO userDTO = null;

        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerName(name);
        if (optionalUserEntity.isPresent()) {
            userDTO = userConverter.ConvertEntityToDTO(optionalUserEntity.get());
        } else {

            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("owner name not found");
            errorModel.setMessage("Please enter the valid user name");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        return userDTO;
    }
}






