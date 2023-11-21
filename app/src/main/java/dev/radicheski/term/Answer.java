package dev.radicheski.term;

public class Answer {

    public static final Answer INVALID_WORD = new Answer(new Case[0]);
    public static final Answer INCOMPLETE_WORD = new Answer(new Case[0]);

    private final Case[] cases;

    public Answer(Case[] cases) {
        this.cases = cases;
    }

    public Case[] getCases() {
        return cases;
    }

    public enum Case {
        WRONG_LETTER,
        WRONG_PLACE,
        RIGHT_PLACE;
    }
}