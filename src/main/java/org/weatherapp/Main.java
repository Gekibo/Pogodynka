package org.weatherapp;

import org.weatherapp.model.Forecast;
import org.weatherapp.model.WeatherToForecastMapper;
import org.weatherapp.webclient.FileWriterExample;
import org.weatherapp.webclient.WeatherApiClient;

import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        WeatherApiClient weatherApiClient = new WeatherApiClient();
        WeatherToForecastMapper weatherToForecastMapper = new WeatherToForecastMapper();
        final HttpResponse<String> response = weatherApiClient.getWeather("Gdynia");
        Forecast forecast = weatherToForecastMapper.mapToForecast(response.body());
//        System.out.println("To jest nasz obiekt:");
//        System.out.println(forecast.toString());
        FileWriterExample fileWriter = new FileWriterExample();

//        System.out.println("To jest JSON: ");
//        System.out.println(response.body());
//        final HttpResponse<String> historicalForecast = weatherApiClient.getHistoricalWeather("Gdynia", "2015-01-21");
//        System.out.println("To jest nasz historical forecast:");
//        System.out.println(historicalForecast.body());
//        userInput(weatherApiClient, weatherToForecastMapper);

//        fileWriter.writeToFile(userInput(weatherApiClient, weatherToForecastMapper));

        System.out.println(forecast.getWindDirection());

    }

    private static Forecast userInput(WeatherApiClient weatherApiClient, WeatherToForecastMapper weatherToForecastMapper) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj miasto: ");
        String cityScanner = scanner.nextLine();

        final HttpResponse<String> responseScanner = weatherApiClient.getWeather(String.valueOf(cityScanner));
        Forecast forecastScanner = weatherToForecastMapper.mapToForecast(responseScanner.body());

        System.out.println(forecastScanner.toString());
        return forecastScanner;
    }
}