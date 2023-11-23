package dev.radicheski.term;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dev.radicheski.term.words.WordRepository;

public class Answer {
    
    public static final Answer INVALID_INPUT = new Answer();
    
    private final List<String> rightPlace;
    private final List<String> wrongPlace;
    private final List<String> wrongLetter;
    
    private Answer() {
        rightPlace = List.of();
        wrongPlace = List.of();
        wrongLetter = List.of();
    }
    
    private Answer(CharSequence word, CharSequence guess) {
        rightPlace = new ArrayList<>();
        wrongPlace = new ArrayList<>();
        wrongLetter = new ArrayList<>();

        List<Character> letters = new ArrayList<>();
        Set<Integer> indices = new HashSet<>();

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.charAt(i)) {
                rightPlace.add(String.valueOf(guess.charAt(i)));
            } else {
                letters.add(word.charAt(i));
                indices.add(i);
            }
        }

        for (int i: indices) {
            int firstIndex = letters.indexOf(guess.charAt(i));
            if (firstIndex == -1) {
                wrongLetter.add(String.valueOf(guess.charAt(i)));
            } else {
                wrongPlace.add(String.valueOf(guess.charAt(i)));
                letters.remove(firstIndex);
            }
        }
    }

    public static Answer of(CharSequence word, CharSequence guess) {
        if (!WordRepository.checkWord(guess.toString())) return INVALID_INPUT;
        return new Answer(word, guess);
    }

    public List<String> getRightPlace() {
        return List.copyOf(rightPlace);
    }

    public List<String> getWrongLetter() {
        return List.copyOf(wrongLetter);
    }

    public List<String> getWrongPlace() {
        return List.copyOf(wrongPlace);
    }
}