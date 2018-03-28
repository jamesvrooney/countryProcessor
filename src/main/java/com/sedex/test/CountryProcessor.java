package com.sedex.test;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class CountryProcessor {

    public static Map<Continent, List<CountryRecord>> groupFilteredCountriesByContinent(Reader source, Predicate<CountryRecord> filteringCondition) {
        List<CountryRecord> countries = createRecordsFromFile(source);


        Map<Continent, List<CountryRecord>> countriesByContinent = countries.stream()
                .filter(filteringCondition != null ? filteringCondition : (countryRecord -> true))
                .collect(groupingBy(CountryRecord::getContinent));

        return countriesByContinent;
    }

    public static Map<Continent, Integer> populationByContinent(Reader source) {
        List<CountryRecord> countries = createRecordsFromFile(source);

        Map<Continent, Integer> populationByContinent = countries.stream()
                .collect(groupingBy(CountryRecord::getContinent, summingInt(CountryRecord::getPopulation)));

        return populationByContinent;
    }

    public static Map<Continent, Optional<CountryRecord>> getMostPopulatedCountryByContinent(Reader source) {
        List<CountryRecord> countries = createRecordsFromFile(source);

        Map<Continent, Optional<CountryRecord>> mostPopulatedCountryByContinent = countries.stream()
                .collect(groupingBy(CountryRecord::getContinent, maxBy(comparingInt(CountryRecord::getPopulation))));

        return mostPopulatedCountryByContinent;
    }

    private static List<CountryRecord> createRecordsFromFile(Reader source) {
        BufferedReader br = new BufferedReader(source);

        List<CountryRecord> countries = new ArrayList<>();

        try {
            // skip 1st line
            br.readLine();

            countries = br.lines()
                    .map(convertCountryEntryStringToObject)
                    .collect(Collectors.toList());

            countries.removeAll(Collections.singleton(null));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return countries;
    }

    private static final String COMMA_SEPARATOR = ",";

    private static Function<String, CountryRecord> convertCountryEntryStringToObject = line -> {
        String[] countryData = line.split(COMMA_SEPARATOR);

        int countryId;
        String name;
        Integer population;
        Continent continent;

        CountryRecord countryRecord = null;

        try {
            countryId = Integer.parseInt(countryData[0]);
            name = countryData[1];
            population = Integer.parseInt(countryData[2]);
            continent = Continent.valueOf(countryData[3]);

            countryRecord = new CountryRecord(countryId, name, population, continent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return countryRecord;
    };
}
