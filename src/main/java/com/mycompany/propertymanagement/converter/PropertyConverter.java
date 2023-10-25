package com.mycompany.propertymanagement.converter;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {

    //The below problem is done using adapter-design pattern. here we are converting entity to DTO and can be visa-versa
    public PropertyEntity convertDTOtoEntity(PropertyDTO propertyDTO){

      PropertyEntity pe = new PropertyEntity();

        pe.setTitle(propertyDTO.getTitle());
        pe.setDescription (propertyDTO.getDescription());
        pe.setAddress(propertyDTO.getAddress());
        pe.setPrice(propertyDTO.getPrice());

        return pe;
    }

    public PropertyDTO convertEntityToDTO(PropertyEntity propertyEntity){

        PropertyDTO pdto = new PropertyDTO();

        pdto.setId(propertyEntity.getId());
        pdto.setTitle(propertyEntity.getTitle());
        pdto.setDescription (propertyEntity.getDescription());
        pdto.setAddress(propertyEntity.getAddress());
        pdto.setPrice(propertyEntity.getPrice());
        pdto.setUserId(propertyEntity.getUserEntity().getId());

        return pdto;
    }
}
