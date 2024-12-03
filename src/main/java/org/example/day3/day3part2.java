package org.example.day3;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day3part2 {
    public static void main(String[] args) {
        String filePath = "/Users/mateuszpraski/Documents/java/Advent-of-code-2024/src/main/java/org/example/day3/data03.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            Pattern pattern = Pattern.compile(
                    "(do\\(\\))|(don't\\(\\))|(mul\\((\\d+),(\\d+)\\))"
            );
            BigInteger result = BigInteger.ZERO;
            boolean enable = true;
            for (String line : lines) {
                Matcher m = pattern.matcher(line);

                while (m.find()) {
                    if (m.group(1) != null) {
                        enable = true;
                    }
                    if (m.group(2) != null) {
                        enable = false;
                    }
                    if (m.group(3) != null && enable) {
                        int val1 = Integer.parseInt(m.group(4));
                        int val2 = Integer.parseInt(m.group(5));
                        result = result.add(BigInteger.valueOf(val1).multiply(BigInteger.valueOf(val2)));
                    }
                }
            }
            System.out.println(result);

        } catch (IOException e) {
            System.out.println("Error while loading in file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
