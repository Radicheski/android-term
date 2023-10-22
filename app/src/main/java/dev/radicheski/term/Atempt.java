package dev.radicheski.term;

import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;

class Atempt {

    private String word;
    private int cursor = 0;
    private TextView[] letters;

    Atempt(String word, TextView[] views) {
        this.word = word;
        this.letters = views;
    }

    public void checkAnswer() {
        Log.i(getClass().getName(), Boolean.toString(word.equalsIgnoreCase(Arrays.toString(letters))));
    }

    public void deleteLetter() {
        if (cursor == 0) return;

        cursor--;
        letters[cursor].setText(null);
    }

    public void addLetter(CharSequence letter) {
        if (cursor == letters.length) return;

        letters[cursor].setText(letter);
        cursor++;
    }

}
