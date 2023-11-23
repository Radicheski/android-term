package dev.radicheski.term;

import android.widget.TextView;

class Atempt {

    private int cursor = 0;
    private TextView[] letters;

    Atempt(TextView[] views) {
        this.letters = views;
    }

    public String getInput() {
        StringBuilder builder = new StringBuilder();
        for (TextView view: letters) {
            builder.append(view.getText());
        }
        return builder.toString();
    }

    public boolean isComplete() {
        return cursor >= letters.length;
    }


    public void clear() {
        for (TextView view: letters) view.setText("");
        cursor = 0;
    }

    public void addLetter(CharSequence letter) {
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
