package com.mycompany.propertymanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name="PROPERTY_TABLE")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   @Column(name="PROPERTY_TITLE", nullable = false)
    private String title;
   private String description;

    private Double price;
    private String address;


    @ManyToOne//(fetch = FetchType.LAZY) //it will not fetch the user data while fetching property
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getOwerName() {
//        return owerName;
//    }
//
//    public void setOwerName(String owerName) {
//        this.owerName = owerName;
//    }
//
//    public String getOwnerEmail() {
//        return ownerEmail;
//    }
//
//    public void setOwnerEmail(String ownerEmail) {
//        this.ownerEmail = ownerEmail;
//    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
