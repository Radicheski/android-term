package dev.radicheski.term;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Keyboard {

    private Button[] letters;
    private Map<String, AnswerColor> colors;
    private Button enter;
    private Button backspace;

    private View.OnClickListener letterListener;
    private Runnable enterListener;
    private Runnable backspaceListener;

    Keyboard(Button[] letters, Button enter, Button backspace) {
        this.letters = letters;
        for (Button letter : this.letters) {
            letter.setOnClickListener(this::onLetterClick);
        }

        this.colors = new HashMap<>();

        this.enter = enter;
        this.enter.setOnClickListener(this::onEnterClick);

        this.backspace = backspace;
        this.backspace.setOnClickListener(this::onBackspaceClick);
    }

    private void onLetterClick(View view) {
        if (Objects.nonNull(letterListener)) letterListener.onClick(view);
    }

    private void onEnterClick(View view) {
        if (Objects.nonNull(enterListener)) enterListener.run();
    }

    private void onBackspaceClick(View view) {
        if (Objects.nonNull(backspaceListener)) backspaceListener.run();
    }

    public void setLetterListener(View.OnClickListener listener) {
        this.letterListener = listener;
    }

    public void setEnterListener(Runnable listener) {
        this.enterListener = listener;
    }

    public void setBackpaceListener(Runnable listener) {
        this.backspaceListener = listener;
    }

    public void updateLayout(Answer answer) {
        for (String letter: answer.getRightPlace()) {
            for (Button button: letters) {
                if (button.getText().equals(letter)) {
                    AnswerColor.RIGHT_PLACE.setButtonColor(button);
                    colors.put(letter, AnswerColor.RIGHT_PLACE);
                }
            }
        }

        for (String letter: answer.getWrongPlace()) {
            for (Button button: letters) {
                if (button.getText().equals(letter)) {
                    AnswerColor.WRONG_PLACE.setButtonColor(button);
                    colors.put(letter, AnswerColor.WRONG_PLACE);
                }
            }
        }

        for (String letter: answer.getWrongLetter()) {
            for (Button button: letters) {
                if (button.getText().equals(letter) && !colors.containsKey(letter)) {
                    AnswerColor.WRONG_LETTER.setButtonColor(button);
                    button.setEnabled(false);
                    button.setAlpha(0.5f);
                }
            }
        }
    }

    public void reset() {
        for (Button letter : letters) {
            AnswerColor.NONE.setButtonColor(letter);
            letter.setEnabled(true);
            letter.setAlpha(1.0f);
        }

        this.colors = new HashMap<>();
    }

    private enum AnswerColor {
        RIGHT_PLACE("#006100", "#C6EFCE"),
        WRONG_PLACE("#9C5700", "#FFEB9C"),
        WRONG_LETTER("#9C0006", "#FFC7CE"),
        NONE("#FFFFFF", "#6750A4");

        private final int text;
        private final int background;

        AnswerColor(String text, String background) {
            this.text = Color.parseColor(text);
            this.background = Color.parseColor(background);
        }

        public void setButtonColor(Button button) {
            button.setTextColor(text);
            button.setBackgroundColor(background);
        }
    }

}
