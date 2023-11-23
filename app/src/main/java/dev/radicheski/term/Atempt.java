package dev.radicheski.term;

import android.widget.TextView;

class Atempt {

    private int cursor = 0;
    private TextView[] letters;

    Atempt(TextView[] views) {
        this.letters = views;
    }

    public Answer checkAnswer(String word) {
        if (cursor < letters.length) return Answer.INCOMPLETE_WORD;

        return null; //TODO
    }

    public void clear() {
        for (TextView view: letters) view.setText("");
        cursor = 0;
    }

    public void addLetter(String letter) {
        if (cursor == letters.length) return;

        letters[cursor].setText(letter);
        cursor++;
    }

    public void deleteLetter() {
        if (cursor == 0) return;

        cursor--;
        letters[cursor].setText(null);
    }

}
