package dev.radicheski.term;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dev.radicheski.term.words.WordRepository;

public class Match {

    private String word;
    private int cursor = 0;
    private Atempt[] atempts;

    public Match(TextView[][] views) {
        atempts = new Atempt[views.length];
        for (int i = 0; i < atempts.length; i++) {
            atempts[i] = new Atempt(views[i]);
        }

        newGame();
    }

    private void newGame() {
        this.word = WordRepository.getRandomWord();

        for (Atempt atempt: atempts) {
            atempt.clear();
        }
    }

    public void checkAnswer() {
        if (!atempts[cursor].isComplete()) return;

        String input = atempts[cursor].getInput();
        Answer answer = Answer.of(word, input);

        if (answer == Answer.INVALID_INPUT) {
            //TODO AlertDialog
            atempts[cursor].clear();
            return;
        }


    }

    public void deleteLetter() {
        atempts[cursor].deleteLetter();
    }

    public void addLetter(View view) {
        if (view instanceof Button button) {
            atempts[cursor].addLetter(button.getText());
        }
    }

}
