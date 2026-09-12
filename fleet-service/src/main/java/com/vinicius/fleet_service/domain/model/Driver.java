package com.vinicius.fleet_service.domain.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Driver
 */
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Setter 
    private String name;
    @Setter 
    private String document;
    @Setter 
    private String driverLicense;
    @Setter 
    private String licenseCategory;
    private String status;

    public static Driver create(String name, String document, String driverLicense, String licenseCategory) {
        Driver driver = new Driver();
        driver.id = UUID.randomUUID();
        driver.name = name;
        driver.document = document;
        driver.driverLicense = driverLicense;
        driver.licenseCategory = licenseCategory;
        driver.status = "A";
        return driver;
    }
}