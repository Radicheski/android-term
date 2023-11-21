package dev.radicheski.term;

import android.widget.TextView;

import java.util.Map;
import java.util.Objects;

import dev.radicheski.term.words.WordRepository;

public class Match {

    private int cursor = 0;
    private Atempt[] atempt;

    public Match(TextView[][] views) {
        String word = WordRepository.getRandomWord();

        atempt = new Atempt[views.length];
        for (int i = 0; i < atempt.length; i++) {
            atempt[i] = new Atempt(word, views[i]);
        }
    }

    public Answer checkAnswer() {
        Answer answer = atempt[cursor].checkAnswer();

        if (!answer.getCases().isEmpty()) cursor += 1;

        return answer;
    }

    public void deleteLetter() {
        atempt[cursor].deleteLetter();
    }

    public void addLetter(CharSequence letter) {
        atempt[cursor].addLetter(letter);
    }

}
