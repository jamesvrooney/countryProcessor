package com.sedex.test;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CountryProcessorTest {

    @Test
    public void testGroupCountriesByContinent() throws FileNotFoundException {
        String filename = "src/test/resources/countries.csv";

        Predicate<CountryRecord> noFilteringCondition = null;

        Map<Continent, List<CountryRecord>> countriesByContinent = CountryProcessor.groupFilteredCountriesByContinent(openFile(filename), noFilteringCondition);

        int expectedNumberOfContinents = 6;

        int expectedNumberOfEuropeanCountries = 44;
        int expectedNumberOfAsianCountries = 46;
        int expectedNumberOfAustralianCountries = 7;
        int expectedNumberOfSouthAmericanCountries = 12;
        int expectedNumberOfNorthAmericanCountries = 16;
        int expectedNumberOfAfricanCountries = 48;

        List<CountryRecord> europeanCountries = countriesByContinent.get(Continent.EUROPE);
        List<CountryRecord> asianCountries = countriesByContinent.get(Continent.ASIA);
        List<CountryRecord> australianCountries = countriesByContinent.get(Continent.AUSTRALIA);
        List<CountryRecord> southAmericanCountries = countriesByContinent.get(Continent.SOUTH_AMERICA);
        List<CountryRecord> northAmericanCountries = countriesByContinent.get(Continent.NORTH_AMERICA);
        List<CountryRecord> africanCountries = countriesByContinent.get(Continent.AFRICA);

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

        Map<Continent, Integer> populationByContinent = CountryProcessor.populationByContinent(openFile(filename));

        int expectedNumberOfContinents = 6;

        int expectedPopulationOfEuropeanCountries = 465630364;
        int expectedPopulationOfAsianCountries = 478970302;
        int expectedPopulationOfAustralianCountries = 76328943;
        int expectedPopulationOfSouthAmericanCountries = 147141372;
        int expectedPopulationOfNorthAmericanCountries = 143735472;
        int expectedPopulationOfAfricanCountries = 506334209;

        Integer europeanCountriesPopulation = populationByContinent.get(Continent.EUROPE);
        Integer asianCountriesPopulation = populationByContinent.get(Continent.ASIA);
        Integer australianCountriesPopulation = populationByContinent.get(Continent.AUSTRALIA);
        Integer southAmericanCountriesPopulation = populationByContinent.get(Continent.SOUTH_AMERICA);
        Integer northAmericanCountriesPopulation = populationByContinent.get(Continent.NORTH_AMERICA);
        Integer africanCountriesPopulation = populationByContinent.get(Continent.AFRICA);

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

        Predicate<CountryRecord> filterByNamesBeginningWithM = countryRecord -> countryRecord.getName().startsWith("M");

        Map<Continent, List<CountryRecord>> filteredCountriesByContinent = CountryProcessor.groupFilteredCountriesByContinent(openFile(filename), filterByNamesBeginningWithM);

        int expectedNumberOfContinents = 5;

        int expectedNumberOfFilteredEuropeanCountries = 4;
        int expectedNumberOfFilteredAsianCountries = 3;
        int expectedNumberOfFilteredAustralianCountries = 1;
        int expectedNumberOfFilteredNorthAmericanCountries = 1;
        int expectedNumberOfFilteredAfricanCountries = 7;

        List<CountryRecord> filteredEuropeanCountries = filteredCountriesByContinent.get(Continent.EUROPE);
        List<CountryRecord> filteredAsianCountries = filteredCountriesByContinent.get(Continent.ASIA);
        List<CountryRecord> filteredAustralianCountries = filteredCountriesByContinent.get(Continent.AUSTRALIA);
        List<CountryRecord> filteredSouthAmericanCountries = filteredCountriesByContinent.get(Continent.SOUTH_AMERICA);
        List<CountryRecord> filteredNorthAmericanCountries = filteredCountriesByContinent.get(Continent.NORTH_AMERICA);
        List<CountryRecord> filteredAfricanCountries = filteredCountriesByContinent.get(Continent.AFRICA);

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

        Map<Continent, Optional<CountryRecord>> mostPopulationCountryByContinent = CountryProcessor.getMostPopulatedCountryByContinent(openFile(filename));

        int expectedNumberOfContinents = 6;

        String expectedMostPopulatedEuropeanCountry = "Poland";
        String expectedMostPopulatedAsianCountry = "Philippines";
        String expectedMostPopulatedAustralianCountry = "Micronesia";
        String expectedMostPopulatedSouthAmericanCountry = "Jamaica";
        String expectedMostPopulatedNorthAmericanCountry = "Colombia";
        String expectedMostPopulatedAfricanCountry = "Uganda";


        String actualMostPopulatedEuropeanCountry = mostPopulationCountryByContinent.get(Continent.EUROPE).get().getName();
        String actualMostPopulatedAsianCountry = mostPopulationCountryByContinent.get(Continent.ASIA).get().getName();
        String actualMostPopulatedAustralianCountry = mostPopulationCountryByContinent.get(Continent.AUSTRALIA).get().getName();
        String actualMostPopulatedNorthAmericanCountry = mostPopulationCountryByContinent.get(Continent.NORTH_AMERICA).get().getName();
        String actualMostPopulatedSouthAmericanCountry = mostPopulationCountryByContinent.get(Continent.SOUTH_AMERICA).get().getName();
        String actualMostPopulatedAfricanCountry = mostPopulationCountryByContinent.get(Continent.AFRICA).get().getName();


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
