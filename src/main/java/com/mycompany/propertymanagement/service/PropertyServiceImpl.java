package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.Exception.BusinessException;
import com.mycompany.propertymanagement.Exception.ErrorModel;
import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService{

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PropertyDTO SaveProperty(PropertyDTO propertyDTO) {

      Optional<UserEntity> optUe =  userRepository.findById(propertyDTO.getUserId());
      if (optUe.isPresent()) {
          PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
           pe.setUserEntity(optUe.get());  //getting userEntity data(id) and saving it here
          pe = propertyRepository.save(pe);

          propertyDTO = propertyConverter.convertEntityToDTO(pe);
      }else {
          List<ErrorModel> errorModelList = new ArrayList<>();
          ErrorModel errorModel = new ErrorModel();
          errorModel.setCode("User Id Not Exist");
          errorModel.setMessage("User Not Exist");
          errorModelList.add(errorModel);

          throw new BusinessException(errorModelList);
      }
        return propertyDTO;
    }

//    v-25
    @Override
    public List<PropertyDTO> getAllProperties() {

       List<PropertyEntity>  listOfProp = (List<PropertyEntity>) propertyRepository.findAll();
       List<PropertyDTO> propList = new ArrayList<>();

        for(PropertyEntity pe : listOfProp) {
            PropertyDTO dto=    propertyConverter.convertEntityToDTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public List<PropertyDTO> getAllPropertiesForUser(Long userId) {

        List<PropertyEntity>  listOfProp = propertyRepository.findAllByUserEntityId(userId);
        List<PropertyDTO> propList = new ArrayList<>();

        for(PropertyEntity pe : listOfProp) {
            PropertyDTO dto=    propertyConverter.convertEntityToDTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {

      Optional<PropertyEntity> optEn =propertyRepository.findById(propertyId);
       PropertyDTO dto = null;
        if (optEn.isPresent()){

            PropertyEntity pe = optEn.get(); //getting data from dataBase

            pe.setTitle(propertyDTO.getTitle());
            pe.setDescription (propertyDTO.getDescription());
            pe.setAddress(propertyDTO.getAddress());
//            pe.setOwerName(propertyDTO.getOwerName());
//            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setPrice(propertyDTO.getPrice());

            dto = propertyConverter.convertEntityToDTO(pe);

            propertyRepository.save(pe); // this object contains updated data fields
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn =propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (optEn.isPresent()){

            PropertyEntity pe = optEn.get(); //getting data from dataBase
            pe.setDescription (propertyDTO.getDescription());
            dto = propertyConverter.convertEntityToDTO(pe);

            propertyRepository.save(pe);       // this object contains updated data fields
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (optEn.isPresent()){

       PropertyEntity pe = optEn.get();
       pe.setPrice(propertyDTO.getPrice());
      dto = propertyConverter.convertEntityToDTO(pe);

       propertyRepository.save(pe);
    }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}
