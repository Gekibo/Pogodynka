package org.weatherapp.webclient;

import org.weatherapp.model.Forecast;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriterExample {

    public void writeToFile(Forecast forecast) {
        // Ścieżka do pliku, do którego chcesz zapisać dane
        String filePath = "src/main/resources/CurrentWeather.txt";

        // Dane do zapisania
        String dataToWrite = forecast.toString();

        try {
            // Sprawdzenie, czy plik istnieje
            if (!Files.exists(Paths.get(filePath))) {
                // Jeżeli plik nie istnieje, to tworzymy nowy
                Files.createFile(Paths.get(filePath));
            } else {
                // Jeżeli plik istnieje, sprawdzamy, czy zawiera już dane dla danej lokalizacji
                if (containsLocationData(filePath, forecast.getCity())) {
                    // Jeżeli zawiera, to zastępujemy nowymi danymi
                    replaceLocationData(filePath, forecast.getCity(), dataToWrite);
                    System.out.println("Dane dla lokalizacji " + forecast.getCity() + " zostały zaktualizowane.");
                    return;
                }
            }

            // Tworzenie obiektu BufferedWriter dla efektywnego zapisu
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

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

    private boolean containsLocationData(String filePath, String location) throws IOException {
        // Otwarcie pliku i sprawdzenie, czy zawiera dane dla danej lokalizacji
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(location)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void replaceLocationData(String filePath, String location, String newData) throws IOException {
        // Otwarcie pliku i zastąpienie danych dla danej lokalizacji
        File inputFile = new File(filePath);
        File tempFile = new File("src/main/resources/TempWeather.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(location)) {
                    // Zastąpienie starych danych nowymi danymi
                    writer.write(newData);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
        }

        // Zamiana plików - stary na nowy
        Files.delete(Paths.get(filePath));
        tempFile.renameTo(new File(filePath));
    }
}