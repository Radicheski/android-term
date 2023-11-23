package dev.radicheski.term;

import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class Keyboard {

    private Button[] letters;
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
        //TODO
    }

    public void reset() {
        //TODO
    }

}
