package com.epam.jdi.bookstore.model.user;

import com.epam.jdi.tools.DataClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "address")
public class Address extends DataClass<Address> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    public User user;

    @Column(name = "user_id", updatable = false, insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long userId;

    @NotBlank(message = "Full name is mandatory")
    @Column(name = "full_name", nullable = false)
    public String fullName;

    @NotBlank(message = "Address line 1 is mandatory")
    @Column(name = "address_line1", nullable = false)
    public String address_line1;

    @Column(name = "address_line2")
    public String address_line2;

    @NotBlank(message = "Postal is mandatory")
    @Column(name = "postal", nullable = false)
    public String postal;

    @NotBlank(message = "City is mandatory")
    @Column(name = "city", nullable = false)
    public String city;

    @NotBlank(message = "Region is mandatory")
    @Column(name = "region", nullable = false)
    public String region;

    @NotBlank(message = "Country is mandatory")
    @Column(name = "country", nullable = false)
    public String country;

}
