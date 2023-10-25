package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PropertyService {
    public PropertyDTO SaveProperty(PropertyDTO propertyDTO);

    public List<PropertyDTO> getAllProperties();

    public List<PropertyDTO> getAllPropertiesForUser(Long userId);


    PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId);

   PropertyDTO updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, Long propertyId);

    PropertyDTO updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, Long propertyId);

    void deleteProperty(Long propertyId);

}
