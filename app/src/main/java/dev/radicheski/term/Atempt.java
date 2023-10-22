package dev.radicheski.term;

import android.util.Log;

import java.util.Arrays;

class Atempt {

    private String word;
    private int cursor = 0;
    private Character[] letters = new Character[5];

    Atempt(String word) {
        this.word = word;
    }

    public void checkAnswer() {
        Log.i(getClass().getName(), Boolean.toString(word.equalsIgnoreCase(Arrays.toString(letters))));
    }

    public void deleteLetter() {
        cursor--;
        letters[cursor] = null;
    }

    public void addLetter(CharSequence letter) {
        letters[cursor] = letter.charAt(0);
        cursor++;
    }

}
