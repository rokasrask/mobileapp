package com.example.moksleivis.mobileapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static final String VALID_CREDENTIALS_REGEX ="^[A-Za-z]{1,50}";
    private static final String VALID_NUMERIS_REGEX = "^[0-9]{1,9}$";
    private static final String VALID_DATA_REGEX = "^((19|200|201[0-9])[0-9]{2})$";


    public static boolean isValidCredentials(String credentials){
        Pattern credentialsPattern = Pattern.compile(VALID_CREDENTIALS_REGEX);
        Matcher credentialsMatcher = credentialsPattern.matcher(credentials);
        return credentialsMatcher.find();
    }

    public static boolean isValidNumeris(String numeris){
        Pattern numerisPattern = Pattern.compile(VALID_NUMERIS_REGEX);
        Matcher numerisMatcher = numerisPattern.matcher(numeris);
        return numerisMatcher.find();
    }
    public static boolean isValidData(String credentials){
        Pattern credentialsPattern = Pattern.compile(VALID_DATA_REGEX);
        Matcher credentialsMatcher = credentialsPattern.matcher(credentials);
        return credentialsMatcher.find();
    }
}
