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

    public Answer checkAnswer() {
        Answer answer = atempts[cursor].checkAnswer(getNormalizedWord());

        if (!answer.getCases().isEmpty()) cursor += 1;

        return answer;
    }

    public void deleteLetter() {
        atempts[cursor].deleteLetter();
    }

    public void addLetter(View view) {
        if (view instanceof Button button) {
            atempts[cursor].addLetter(button.getText());
        }
    }

    private String getNormalizedWord() {
        return WordRepository.normalize(word);
    }

}
