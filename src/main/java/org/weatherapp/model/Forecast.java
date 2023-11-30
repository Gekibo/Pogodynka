package org.weatherapp.model;

import java.util.UUID;

public class Forecast {

    private UUID id;
    private String city;
    private String country;
    private Integer temperature;
    private String weatherDescription;
    private Integer humidity;
    private Integer pressure;
    private Integer windSpeed;
    private String windDirection;

    public Forecast(final String city,
                    final String country,
                    final Integer temperature,
                    final String weatherDescription,
                    final Integer humidity,
                    final Integer pressure,
                    final Integer windSpeed,
                    final String windDirection) {
        this.id = createUuid(country, city);
        this.city = city;
        this.country = country;
        this.temperature = temperature;
        this.weatherDescription = weatherDescription;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public UUID getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    private UUID createUuid(String country, String city) {
        return UUID.nameUUIDFromBytes((country + city).getBytes());
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", temperature=" + temperature +
                ", weatherDescription='" + weatherDescription + '\'' +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", windSpeed=" + windSpeed +
                ", windDirection='" + windDirection + '\'' +
                '}';
    }
}
