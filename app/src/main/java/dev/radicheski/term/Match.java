package dev.radicheski.term;

import android.util.Log;

public class Match {

    private String word;
    private StringBuilder atempt = new StringBuilder();

    public Match(String word) {
        this.word = word;
    }

    public void checkAnswer() {
        Log.i(getClass().getName(), Boolean.toString(word.equalsIgnoreCase(atempt.toString())));
    }

    public void deleteLetter() {
        atempt.deleteCharAt(atempt.length() - 1);
    }

    public void addLetter(CharSequence letter) {
        atempt.append(letter);
    }

}
