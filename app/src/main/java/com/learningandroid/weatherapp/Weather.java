package com.learningandroid.weatherapp;

public class Weather
{
    Coord coord;
    Day[] weather;
    String base; // Internal parameter
    Main main;
    int visibility;
    Wind wind;
    clouds clouds;
    int dt; // Time of data calculation, unix, UTC
    Sys sys;
    int id; //City ID
    String name; //City name
    int cod; //Internal parameter

    //經緯度
    class Coord
    {
        double lon;//經度 longitude
        double lat;//緯度 latitude
    }

    //more info Weather condition codes
    class Day
    {
        int id; //Weather condition id
        String main; // Group of weather parameters (Rain, Snow, Extreme etc.)
        String description; //Weather condition within the group
        String icon ; //Weather icon id --> openweathermap
    }

    class Main
    {
        double temp; //Temperature 溫度
        int pressure; // Atmospheric pressure 氣壓
        int humidity; //Humidity 濕度
        double temp_min;
        double temp_max;
    }

    class Wind
    {
        double speed;
        int deg;
    }

    class clouds
    {
        int all ;
    }

    class Sys
    {
        int type;
        int id ;
        double message;
        String country;
        int sunrise;
        int sunset;
    }

}
