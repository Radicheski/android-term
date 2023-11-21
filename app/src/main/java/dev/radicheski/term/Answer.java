package dev.radicheski.term;

import android.graphics.Color;

import java.util.Map;

public class Answer {

    public static final Answer INCOMPLETE_WORD = new Answer(Map.of(), null);

    private final Map<Character, Case> cases;
    private final String input;

    public Answer(Map<Character, Case> cases, String input) {
        this.cases = cases;
        this.input = input;
    }

    public Map<Character, Case> getCases() {
        return cases;
    }

    public String getInput() {
        return input;
    }

    public enum Case {
        WRONG_LETTER,
        WRONG_PLACE,
        RIGHT_PLACE;

        public int getTextColor() {
            return switch (this) {
                case RIGHT_PLACE -> Color.parseColor("#006100");
                case WRONG_PLACE -> Color.parseColor("#9C5700");
                case WRONG_LETTER -> Color.parseColor("#9C0006");
            };
        }

        public int getBackgroundColor() {
            return switch (this) {
                case RIGHT_PLACE -> Color.parseColor("#C6EFCE");
                case WRONG_PLACE -> Color.parseColor("#FFEB9C");
                case WRONG_LETTER -> Color.parseColor("#FFC7CE");
            };
        }
    }
}