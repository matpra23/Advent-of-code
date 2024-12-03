package org.example.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class day2part2 {
    private static final String filePath = "/Users/mateuszpraski/Documents/java/Advent-of-code-2024/src/main/java/org/example/day3/data03.txt";
    public static void main(String[] args) {

        try {
            List<String> reports = Files.readAllLines(Paths.get(filePath));

            int safeCount = 0;

            for (String report : reports) {
                if (isSafe(report) || canBeMadeSafe(report)) {
                    safeCount++;
                }
            }

            System.out.println("Liczba bezpiecznych raportów (z uwzględnieniem usunięcia jednego poziomu): " + safeCount);

        } catch (IOException e) {
            System.out.println("Błąd podczas wczytywania pliku: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Funkcja sprawdzająca, czy raport jest bezpieczny
    public static boolean isSafe(String report) {
        String[] parts = report.split(" ");
        int[] levels = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 1; i < levels.length; i++) {
            int diff = levels[i] - levels[i - 1];

            if (diff < -3 || diff > 3 || diff == 0) {
                return false; // Różnica spoza zakresu lub brak zmiany
            }

            if (diff > 0) {
                decreasing = false; // Jeśli jest wzrost, przestaje być malejący
            } else if (diff < 0) {
                increasing = false; // Jeśli jest spadek, przestaje być rosnący
            }
        }

        return increasing || decreasing; // Musi być tylko jeden kierunek
    }

    // Funkcja sprawdzająca, czy usunięcie jednego poziomu uczyni raport bezpiecznym
    public static boolean canBeMadeSafe(String report) {
        String[] parts = report.split(" ");
        int[] levels = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < levels.length; i++) {
            // Stwórz nową tablicę, pomijając poziom o indeksie i
            int[] modifiedLevels = removeLevel(levels, i);

            // Jeśli raport po usunięciu jednego poziomu jest bezpieczny, zwróć true
            if (isSafeArray(modifiedLevels)) {
                return true;
            }
        }

        return false; // Żadne usunięcie nie sprawia, że raport jest bezpieczny
    }

    // Funkcja pomocnicza do usunięcia elementu z tablicy
    public static int[] removeLevel(int[] levels, int index) {
        int[] result = new int[levels.length - 1];
        int pos = 0;
        for (int i = 0; i < levels.length; i++) {
            if (i != index) {
                result[pos++] = levels[i];
            }
        }
        return result;
    }

    // Funkcja sprawdzająca, czy tablica jest bezpieczna
    public static boolean isSafeArray(int[] levels) {
        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 1; i < levels.length; i++) {
            int diff = levels[i] - levels[i - 1];

            if (diff < -3 || diff > 3 || diff == 0) {
                return false; // Różnica spoza zakresu lub brak zmiany
            }

            if (diff > 0) {
                decreasing = false; // Jeśli jest wzrost, przestaje być malejący
            } else if (diff < 0) {
                increasing = false; // Jeśli jest spadek, przestaje być rosnący
            }
        }

        return increasing || decreasing; // Musi być tylko jeden kierunek
    }
}
