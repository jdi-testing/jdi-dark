package com.epam.jdi.bookstore.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(name = "user_id", updatable = false, insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;

    @NotBlank(message = "Full name is mandatory")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank(message = "Address line 1 is mandatory")
    @Column(name = "address_line1", nullable = false)
    private String address_line1;

    @Column(name = "address_line2")
    private String address_line2;

    @NotBlank(message = "Postal is mandatory")
    @Column(name = "postal", nullable = false)
    private String postal;

    @NotBlank(message = "City is mandatory")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "Region is mandatory")
    @Column(name = "region", nullable = false)
    private String region;

    @NotBlank(message = "Country is mandatory")
    @Column(name = "country", nullable = false)
    private String country;

}
