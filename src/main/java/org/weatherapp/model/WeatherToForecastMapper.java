package org.weatherapp.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherToForecastMapper implements ForecastMapper {

    @Override
    public Forecast mapToForecast(final String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonNode = objectMapper.readTree(json);

        Forecast forecast = new Forecast(
                jsonNode.get("location").get("name").asText(),
                jsonNode.get("location").get("country").asText(),
                jsonNode.get("current").get("temperature").asInt(),
                jsonNode.get("current").get("weather_descriptions").get(0).asText(),
                jsonNode.get("current").get("humidity").asInt(),
                jsonNode.get("current").get("pressure").asInt(),
                jsonNode.get("current").get("wind_speed").asInt(),
                jsonNode.get("current").get("wind_dir").asText());
        return forecast;
    }
}
