package dev.radicheski.term;

import android.graphics.Color;
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

    public boolean isIncomplete() {
        return cursor < letters.length;
    }


    public void clear() {
        for (TextView view: letters) {
            AnswerColor.BLANK.setTextViewColor(view);
            view.setText("");
        }

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

    public void setLetterColor(int index, int color) {
        switch (color) {
            case Answer.WRONG_LETTER -> AnswerColor.WRONG_LETTER.setTextViewColor(letters[index]);
            case Answer.WRONG_PLACE -> AnswerColor.WRONG_PLACE.setTextViewColor(letters[index]);
            case Answer.RIGHT_PLACE -> AnswerColor.RIGHT_PLACE.setTextViewColor(letters[index]);
        }
    }

    private enum AnswerColor {
        BLANK("#FFFFFF", "#CCCCCC"),
        RIGHT_PLACE("#006100", "#C6EFCE"),
        WRONG_PLACE("#9C5700", "#FFEB9C"),
        WRONG_LETTER("#9C0006", "#FFC7CE");

        private final int text;
        private final int background;

        AnswerColor(String text, String background) {
            this.text = Color.parseColor(text);
            this.background = Color.parseColor(background);
        }

        public void setTextViewColor(TextView textView) {
            textView.setTextColor(text);
            textView.setBackgroundColor(background);
        }
    }

}
