package dev.radicheski.term;

import java.util.Map;

public class Answer {

    public static final Answer INVALID_WORD = new Answer(Map.of());
    public static final Answer INCOMPLETE_WORD = new Answer(Map.of());

    private final Map<Character, Case> cases;

    public Answer(Map<Character, Case> cases) {
        this.cases = cases;
    }

    public Map<Character, Case> getCases() {
        return cases;
    }

    public enum Case {
        WRONG_LETTER,
        WRONG_PLACE,
        RIGHT_PLACE;
    }
}