package dev.radicheski.term;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dev.radicheski.term.words.WordRepository;

public class Answer {
    
    public static final Answer INVALID_INPUT = new Answer();

    public final static int WRONG_LETTER = 0;
    public final static int WRONG_PLACE = 1;
    public final static int RIGHT_PLACE = 2;

    private final Map<Integer, Integer> asd;

    private final List<String> rightPlace;
    private final List<String> wrongPlace;
    private final List<String> wrongLetter;
    
    private Answer() {
        rightPlace = List.of();
        wrongPlace = List.of();
        wrongLetter = List.of();
        asd = Map.of();
    }
    
    private Answer(CharSequence word, CharSequence guess) {
        rightPlace = new ArrayList<>();
        wrongPlace = new ArrayList<>();
        wrongLetter = new ArrayList<>();
        asd = new HashMap<>();

        List<Character> letters = new ArrayList<>();
        Set<Integer> indices = new HashSet<>();

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.charAt(i)) {
                rightPlace.add(String.valueOf(guess.charAt(i)));
                asd.put(i, RIGHT_PLACE);
            } else {
                letters.add(word.charAt(i));
                indices.add(i);
            }
        }

        for (int i: indices) {
            int firstIndex = letters.indexOf(guess.charAt(i));
            if (firstIndex == -1) {
                wrongLetter.add(String.valueOf(guess.charAt(i)));
                asd.put(i, WRONG_LETTER);
            } else {
                wrongPlace.add(String.valueOf(guess.charAt(i)));
                letters.remove(firstIndex);
                asd.put(i, WRONG_PLACE);
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

    public Map<Integer, Integer> getAsd() {
        return Map.copyOf(asd);
    }
}