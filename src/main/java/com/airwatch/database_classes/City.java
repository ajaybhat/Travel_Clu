package com.airwatch.database_classes;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    @Column(name = "Name")
    private String cityName;
    @Column(name = "CountryCode")
    private String countryCode;
    @Column(name = "District")
    private String district;
    @Column(name = "Population")
    private long population;

    public long getPopulation() {
        return this.population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String toString() {
        return "City Name :" + this.cityName + "\nDistrict : " + this.district + "\nCountry Code : " + this.countryCode + "\nPopulation : " + this.population;
    }
}
