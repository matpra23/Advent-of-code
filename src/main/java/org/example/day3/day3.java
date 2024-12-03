package org.example.day3;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day3 {
    private static final String filePath = "/Users/mateuszpraski/Documents/java/Advent-of-code-2024/src/main/java/org/example/day3/data03.txt";
    private static final Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
    public static void main(String[] args) {
        BigInteger result = BigInteger.ZERO;
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                Matcher m = pattern.matcher(line);
                while (m.find()) {
                    int val1 = Integer.parseInt(m.group(1));
                    int val2 = Integer.parseInt(m.group(2));

                    result = result.add(BigInteger.valueOf(val1).multiply(BigInteger.valueOf(val2)));
                }
            }
            System.out.println(result);

        } catch (IOException e) {
            System.out.println("Error while loading in file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
