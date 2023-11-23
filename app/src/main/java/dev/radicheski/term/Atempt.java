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
            view.setText("");
            view.setTextColor(Color.BLACK);
            view.setBackgroundColor(Color.WHITE);
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
        AnswerColor answerColor;

        switch (color) {
            case Answer.WRONG_LETTER -> answerColor = AnswerColor.WRONG_LETTER;
            case Answer.WRONG_PLACE -> answerColor = AnswerColor.WRONG_PLACE;
            case Answer.RIGHT_PLACE -> answerColor = AnswerColor.RIGHT_PLACE;
            default -> { return; }
        }

        letters[index].setTextColor(answerColor.text);
        letters[index].setBackgroundColor(answerColor.background);
    }

    private enum AnswerColor {
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
