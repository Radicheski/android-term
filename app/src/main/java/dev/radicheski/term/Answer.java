package dev.radicheski.term;

import android.graphics.Color;

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