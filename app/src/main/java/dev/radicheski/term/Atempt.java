package dev.radicheski.term;

import android.util.Log;
import android.widget.TextView;

import dev.radicheski.term.words.WordRepository;

class Atempt {

    private String word;
    private int cursor = 0;
    private TextView[] letters;

    Atempt(String word, TextView[] views) {
        this.word = WordRepository.normalize(word);
        this.letters = views;
    }

    public void checkAnswer() {
        if (cursor < letters.length) return;

        String inputWord = getInputWord();

        if (!WordRepository.checkWord(inputWord)) {
            Log.e(getClass().getName(), "Invalid word.");
            return;
        }

        Log.i(getClass().getName(), Boolean.toString(word.equalsIgnoreCase(inputWord)));
    }

    public void deleteLetter() {
        if (cursor == 0) return;

        cursor--;
        letters[cursor].setText(null);
    }

    public void addLetter(CharSequence letter) {
        if (cursor == letters.length) return;

        letters[cursor].setText(letter);
        cursor++;
    }

    private String getInputWord() {
        StringBuilder word = new StringBuilder();
        for (TextView letter : letters) {
            word.append(letter.getText());
        }
        return WordRepository.normalize(word.toString());
    }
}
