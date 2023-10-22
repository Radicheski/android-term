package dev.radicheski.term;

import android.widget.TextView;

public class Match {

    private int cursor = 0;
    private Atempt[] atempt;

    public Match(String word, TextView[][] views) {
        atempt = new Atempt[views.length];
        for (int i = 0; i < atempt.length; i++) {
            atempt[i] = new Atempt(word, views[i]);
        }
    }

    public void checkAnswer() {
        atempt[cursor].checkAnswer();
    }

    public void deleteLetter() {
        atempt[cursor].deleteLetter();
    }

    public void addLetter(CharSequence letter) {
        atempt[cursor].addLetter(letter);
    }

}