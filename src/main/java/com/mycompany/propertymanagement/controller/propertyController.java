package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class propertyController {

    @GetMapping("/om")
    public String helloWorld(){
        return "Om Guruve Namaha";
    }

    @Value("${pms.dummy}")
    private String dummy;


    @Autowired
    private PropertyService propertyService;

    //localhost:8080/api/v1/properties
    @PostMapping("/AddingNewProperties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO){

    propertyDTO =   propertyService.SaveProperty(propertyDTO);

    ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/getAll-propertyList")
    public  ResponseEntity<List<PropertyDTO>> getAllProperties(){

        System.out.println(dummy);
        List<PropertyDTO> propertyList = propertyService.getAllProperties();
        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }

    @GetMapping("/getAll-propertyList/user/{userId}")
    public  ResponseEntity<List<PropertyDTO>> getAllPropertiesForUser(@PathVariable("userId") Long userId){


        List<PropertyDTO> propertyList = propertyService.getAllPropertiesForUser(userId);
        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }



    @PutMapping("UpdateProperty/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        propertyDTO=  propertyService.updateProperty(propertyDTO, propertyId);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping("/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
       propertyDTO = propertyService.updatePropertyDescription(propertyDTO, propertyId);
       ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
       return responseEntity;
    }

    @PatchMapping("/properties/update-price/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
      propertyDTO =  propertyService.updatePropertyPrice(propertyDTO, propertyId);
      ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
      return responseEntity;

    }
//    localhost:8080/api/v1/properties/delete/252
    @DeleteMapping("properties/delete/{propertyId}")
    public ResponseEntity deletePropertyById(@PathVariable Long propertyId){
        propertyService.deleteProperty(propertyId);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return responseEntity;
    }



}
