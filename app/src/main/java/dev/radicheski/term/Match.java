package dev.radicheski.term;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;
import java.util.Objects;

import dev.radicheski.term.words.WordRepository;

public class Match {

    private String word;
    private int cursor = 0;
    private Atempt[] atempts;
    private MainActivity activity;
    private Keyboard keyboard;

    public Match(TextView[][] views) {
        atempts = new Atempt[views.length];
        for (int i = 0; i < atempts.length; i++) {
            atempts[i] = new Atempt(views[i]);
        }

        newGame();
    }

    private void newGame() {
        this.word = WordRepository.getRandomWord();
        cursor = 0;

        for (Atempt atempt: atempts) {
            atempt.clear();
        }

        if (Objects.nonNull(keyboard)) keyboard.reset();
    }

    public void checkAnswer() {
        if (atempts[cursor].isIncomplete()) return;

        String input = atempts[cursor].getInput();
        Answer answer = Answer.of(getNormalizedWord(), input);

        if (answer.getRightPlace().size() == word.length()) {
            String message = String.format("A palavra é \"%s\".", word);
            showAlert("Você acertou", message, this::newGame);
        }

        if (answer == Answer.INVALID_INPUT) {
            showAlert("Palavra inválida", String.format("A palavra \"%s\" não foi encontrada.", input), atempts[cursor]::clear);
        } else {
            for(Map.Entry<Integer, Integer> entry: answer.getAsd().entrySet()) {
                atempts[cursor].setLetterColor(entry.getKey(), entry.getValue());
            }

            cursor++;
            updateKeyboard(answer);
            if (cursor == atempts.length) {
                String message = String.format("A palavra é \"%s\".", word);
                showAlert("Você errou", message, this::newGame);
            }
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

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    private void showAlert(String title, String message, Runnable onCompletion) {
        if (Objects.nonNull(activity)) activity.showAlert(title, message, onCompletion);
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    private void updateKeyboard(Answer answer) {
        if (Objects.nonNull(keyboard)) keyboard.updateLayout(answer);
    }

    private String getNormalizedWord() {
        return WordRepository.normalize(word);
    }
}
