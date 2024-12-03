package org.example.day2;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class day2 {

    public static void main(String[] args) {
        String filePath = "/Users/mateuszpraski/Documents/java/Advent-of-code-2024/src/main/java/org/example/day2/data02.txt"; // Zmień na właściwą ścieżkę

        try {
            List<String> reports = Files.readAllLines(Paths.get(filePath));

            int safeCount = 0;

            for (String report : reports) {
                if (isSafe(report)) {
                    safeCount++;
                }
            }

            System.out.println("Liczba bezpiecznych raportów: " + safeCount);

        } catch (IOException e) {
            System.out.println("Błąd podczas wczytywania pliku: " + e.getMessage());
            e.printStackTrace();
        }
    }
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
}
