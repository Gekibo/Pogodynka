package org.weatherapp.util;

import org.weatherapp.model.Forecast;
import org.weatherapp.model.WeatherToForecastMapper;
import org.weatherapp.webclient.WeatherApiClient;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Util {
    public static Forecast userInput(WeatherApiClient weatherApiClient, WeatherToForecastMapper weatherToForecastMapper) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj miasto: ");
        String cityScanner = scanner.nextLine();

        final HttpResponse<String> responseScanner = weatherApiClient.getWeather(String.valueOf(cityScanner));
        Forecast forecastScanner = weatherToForecastMapper.mapToForecast(responseScanner.body());

        System.out.println(forecastScanner.toString());
        return forecastScanner;
    }
}
