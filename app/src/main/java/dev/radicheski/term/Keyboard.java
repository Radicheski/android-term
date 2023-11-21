package dev.radicheski.term;

import android.view.View;
import android.widget.Button;

import java.util.Map;
import java.util.Objects;

public class Keyboard {

    private Button[] letters;
    private Button enter;
    private Button backspace;

    private View.OnClickListener letterListener;
    private View.OnClickListener enterListener;
    private View.OnClickListener backspaceListener;

    Keyboard(Button[] letters, Button enter, Button backspace) {
        this.letters = letters;
        for (Button letter : this.letters) {
            letter.setOnClickListener(this::onLetterClick);
        }

        this.enter = enter;
        this.enter.setOnClickListener(this::onEnterClick);

        this.backspace = backspace;
        this.backspace.setOnClickListener(this::onBackspaceClick);
    }

    private void onLetterClick(View view) {
        if (Objects.nonNull(letterListener)) letterListener.onClick(view);
    }

    private void onEnterClick(View view) {
        if (Objects.nonNull(enterListener)) enterListener.onClick(view);
    }

    private void onBackspaceClick(View view) {
        if (Objects.nonNull(backspaceListener)) backspaceListener.onClick(view);
    }

    public void setLetterListener(View.OnClickListener listener) {
        this.letterListener = listener;
    }

    public void setEnterListener(View.OnClickListener listener) {
        this.enterListener = listener;
    }

    public void setBackpaceListener(View.OnClickListener listener) {
        this.backspaceListener = listener;
    }

    public void updateLayout(Map<Character, Answer.Case> answer) {
        for (Button letter: letters) {
            Answer.Case answerCase = answer.get(letter.getText().charAt(0));
            if (Objects.isNull(answerCase)) continue;

            letter.setTextColor(answerCase.getTextColor());
            letter.setBackgroundColor(answerCase.getBackgroundColor());

            if (answerCase == Answer.Case.WRONG_LETTER) {
                letter.setAlpha(0.1f);
                letter.setEnabled(false);
            }
        }
    }

}