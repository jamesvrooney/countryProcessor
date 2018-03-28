package com.sedex.test;

/**
 * Created by jamesvrooney on 13/04/17.
 */
public class CountryRecord {
    private int countryId;
    private String name;
    private Integer population;
    private Continent continent;

    public CountryRecord(int countryId, String name, Integer population, Continent continent) {
        this.countryId = countryId;
        this.name = name;
        this.population = population;
        this.continent = continent;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
