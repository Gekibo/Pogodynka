package org.weatherapp.webclient;

import org.weatherapp.model.Forecast;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriterExample {

    public void writeToFile(Forecast forecastScanner) {
        // Ścieżka do pliku, do którego chcesz zapisać dane
        String filePath = "src/main/resources/CurrentWeather.txt";

        // Dane do zapisania
        String dataToWrite = forecastScanner.toString();

        try {
            // Tworzenie obiektu BufferedWriter dla efektywnego zapisu
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(filePath, true ));

            // Zapis danych do pliku
            writer.write(dataToWrite);
            writer.newLine();

            // Zamknięcie writer'a, aby zwolnić zasoby
            writer.close();

            System.out.println("Dane zostały pomyślnie zapisane do pliku.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
