package dev.radicheski.term;

public class Match {

    private Atempt atempt;

    public Match(String word) {
        atempt = new Atempt(word);
    }

    public void checkAnswer() {
        atempt.checkAnswer();
    }

    public void deleteLetter() {
        atempt.deleteLetter();
    }

    public void addLetter(CharSequence letter) {
        atempt.addLetter(letter);
    }

}
