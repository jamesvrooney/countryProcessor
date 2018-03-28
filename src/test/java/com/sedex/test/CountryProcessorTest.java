package com.sedex.test;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CountryProcessorTest {

    @Test
    public void testGroupCountriesByContinent() throws FileNotFoundException {
        String filename = "src/test/resources/countries.csv";

        Map countriesByContinent = CountryProcessor.groupFilteredCountriesByContinent(openFile(filename));

        int expectedNumberOfContinents = 6;

        int expectedNumberOfEuropeanCountries = 44;
        int expectedNumberOfAsianCountries = 46;
        int expectedNumberOfAustralianCountries = 7;
        int expectedNumberOfSouthAmericanCountries = 12;
        int expectedNumberOfNorthAmericanCountries = 16;
        int expectedNumberOfAfricanCountries = 48;

        // TODO: 
        List europeanCountries = null;
        List asianCountries = null;
        List australianCountries = null;
        List southAmericanCountries = null;
        List northAmericanCountries = null;
        List africanCountries = null;

        assertThat(countriesByContinent.size(), is(expectedNumberOfContinents));

        assertThat(europeanCountries.size(), is(expectedNumberOfEuropeanCountries));
        assertThat(asianCountries.size(), is(expectedNumberOfAsianCountries));
        assertThat(australianCountries.size(), is(expectedNumberOfAustralianCountries));
        assertThat(southAmericanCountries.size(), is(expectedNumberOfSouthAmericanCountries));
        assertThat(northAmericanCountries.size(), is(expectedNumberOfNorthAmericanCountries));
        assertThat(africanCountries.size(), is(expectedNumberOfAfricanCountries));
    }

    @Test
    public void testPopulationByContinent() throws FileNotFoundException {
        String filename = "src/test/resources/countries.csv";

        Map populationByContinent = CountryProcessor.populationByContinent(openFile(filename));

        int expectedNumberOfContinents = 6;

        int expectedPopulationOfEuropeanCountries = 465630364;
        int expectedPopulationOfAsianCountries = 478970302;
        int expectedPopulationOfAustralianCountries = 76328943;
        int expectedPopulationOfSouthAmericanCountries = 147141372;
        int expectedPopulationOfNorthAmericanCountries = 143735472;
        int expectedPopulationOfAfricanCountries = 506334209;

        Integer europeanCountriesPopulation = null;
        Integer asianCountriesPopulation = null;
        Integer australianCountriesPopulation = null;
        Integer southAmericanCountriesPopulation = null;
        Integer northAmericanCountriesPopulation = null;
        Integer africanCountriesPopulation = null;

        assertThat(europeanCountriesPopulation, is(expectedPopulationOfEuropeanCountries));
        assertThat(asianCountriesPopulation, is(expectedPopulationOfAsianCountries));
        assertThat(australianCountriesPopulation, is(expectedPopulationOfAustralianCountries));
        assertThat(southAmericanCountriesPopulation, is(expectedPopulationOfSouthAmericanCountries));
        assertThat(northAmericanCountriesPopulation, is(expectedPopulationOfNorthAmericanCountries));
        assertThat(africanCountriesPopulation, is(expectedPopulationOfAfricanCountries));
    }

    @Test
    public void testFilteredCountriesByContinent() throws FileNotFoundException {
        String filename = "src/test/resources/countries.csv";
        
        Map filteredCountriesByContinent = CountryProcessor.groupFilteredCountriesByContinent(openFile(filename));

        int expectedNumberOfContinents = 5;

        int expectedNumberOfFilteredEuropeanCountries = 4;
        int expectedNumberOfFilteredAsianCountries = 3;
        int expectedNumberOfFilteredAustralianCountries = 1;
        int expectedNumberOfFilteredNorthAmericanCountries = 1;
        int expectedNumberOfFilteredAfricanCountries = 7;

        List filteredEuropeanCountries = null;
        List filteredAsianCountries = null;
        List filteredAustralianCountries = null;
        List filteredSouthAmericanCountries = null;
        List filteredNorthAmericanCountries = null;
        List filteredAfricanCountries = null;

        assertThat(filteredCountriesByContinent.size(), is(expectedNumberOfContinents));

        assertThat(filteredEuropeanCountries.size(), is(expectedNumberOfFilteredEuropeanCountries));
        assertThat(filteredAsianCountries.size(), is(expectedNumberOfFilteredAsianCountries));
        assertThat(filteredAustralianCountries.size(), is(expectedNumberOfFilteredAustralianCountries));
        assertThat(filteredNorthAmericanCountries.size(), is(expectedNumberOfFilteredNorthAmericanCountries));
        assertThat(filteredAfricanCountries.size(), is(expectedNumberOfFilteredAfricanCountries));
    }

    @Test
    public void testMostPopulatedCountryByContinent() throws FileNotFoundException {
        String filename = "src/test/resources/countries.csv";

        Map mostPopulationCountryByContinent = CountryProcessor.getMostPopulatedCountryByContinent(openFile(filename));

        int expectedNumberOfContinents = 6;

        String expectedMostPopulatedEuropeanCountry = "Poland";
        String expectedMostPopulatedAsianCountry = "Philippines";
        String expectedMostPopulatedAustralianCountry = "Micronesia";
        String expectedMostPopulatedSouthAmericanCountry = "Jamaica";
        String expectedMostPopulatedNorthAmericanCountry = "Colombia";
        String expectedMostPopulatedAfricanCountry = "Uganda";


        String actualMostPopulatedEuropeanCountry = null;
        String actualMostPopulatedAsianCountry = null;
        String actualMostPopulatedAustralianCountry = null;
        String actualMostPopulatedNorthAmericanCountry = null;
        String actualMostPopulatedSouthAmericanCountry = null;
        String actualMostPopulatedAfricanCountry = null;


        assertThat(actualMostPopulatedEuropeanCountry, is(expectedMostPopulatedEuropeanCountry));
        assertThat(actualMostPopulatedAsianCountry, is(expectedMostPopulatedAsianCountry));
        assertThat(actualMostPopulatedAustralianCountry, is(expectedMostPopulatedAustralianCountry));
        assertThat(actualMostPopulatedNorthAmericanCountry, is(expectedMostPopulatedSouthAmericanCountry));
        assertThat(actualMostPopulatedSouthAmericanCountry, is(expectedMostPopulatedNorthAmericanCountry));
        assertThat(actualMostPopulatedAfricanCountry, is(expectedMostPopulatedAfricanCountry));
    }

    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }
}
