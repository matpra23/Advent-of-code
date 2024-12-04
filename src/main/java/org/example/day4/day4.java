package org.example.day4;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class day4 {

    private static final String filePath = "/Users/mateuszpraski/Documents/java/Advent-of-code-2024/src/main/java/org/example/day4/data04.txt";

    public static void main(String[] args) throws Exception {
        final List<String> fullInput = Files.readAllLines(Paths.get(filePath));

        final char[][] puzzle = new char[fullInput.size()][];
        for (int i = 0; i < fullInput.size(); i++) {
            puzzle[i] = fullInput.get(i).toCharArray();
        }

        wordSearch("XMAS", puzzle);
        wordSearchX("MAS", puzzle);
    }

    private static void wordSearch(final String word, final char[][] puzzle) {
        int wordCount = 0;

        for (int y = 0; y < puzzle.length; y++) {
            for (int x = 0; x < puzzle[y].length; x++) {
                if (puzzle[y][x] == word.charAt(0)) {
                    wordCount += countIf(isWordMatch(word, puzzle, x, y, -1, -1))
                            + countIf(isWordMatch(word, puzzle, x, y, -1, 0))
                            + countIf(isWordMatch(word, puzzle, x, y, -1, 1))
                            + countIf(isWordMatch(word, puzzle, x, y, 0, -1))
                            + countIf(isWordMatch(word, puzzle, x, y, 0, 1))
                            + countIf(isWordMatch(word, puzzle, x, y, 1, -1))
                            + countIf(isWordMatch(word, puzzle, x, y, 1, 0))
                            + countIf(isWordMatch(word, puzzle, x, y, 1, 1));
                }
            }
        }

        System.out.printf("Found %s %d times%n", word, wordCount);
    }

    private static void wordSearchX(final String word, final char[][] puzzle) {
        final int middlePosition = word.length() / 2;
        final char middleLetter = word.charAt(middlePosition);

        int wordCount = 0;
        for (int y = 0; y < puzzle.length; y++) {
            for (int x = 0; x < puzzle[y].length; x++) {
                if (puzzle[y][x] == middleLetter) {
                    wordCount += countIf(
                            (isWordMatch(word, puzzle, x - middlePosition, y - middlePosition, 1, 1) || isWordMatch(word, puzzle, x + middlePosition, y + middlePosition, -1, -1))
                                    && (isWordMatch(word, puzzle, x + middlePosition, y - middlePosition, -1, 1) || isWordMatch(word, puzzle, x - middlePosition , y + middlePosition, 1, -1))
                    );
                }
            }
        }

        System.out.printf("X-Found %s %d times%n", word, wordCount);
    }

    private static boolean isWordMatch(
            final String word, final char[][] puzzle,
            final int x, final int y,
            final int xDir, final int yDir) {

        if (word.isEmpty()) {
            return true;
        }

        if (!locationInBound(puzzle, x, y) || word.charAt(0) != puzzle[y][x]) {
            return false;
        }

        return isWordMatch(word.substring(1), puzzle, x + xDir, y + yDir, xDir, yDir);
    }

    private static int countIf(boolean count) {
        return count ? 1 : 0;
    }

    private static boolean locationInBound(final char[][] puzzle, int x, int y) {
        return x >= 0 && x < puzzle[0].length && y >= 0 && y < puzzle.length;
    }
}
