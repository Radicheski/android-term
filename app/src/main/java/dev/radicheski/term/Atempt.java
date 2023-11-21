package dev.radicheski.term;

import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import dev.radicheski.term.words.WordRepository;

class Atempt {

    private String word;
    private int cursor = 0;
    private TextView[] letters;

    Atempt(String word, TextView[] views) {
        this.word = WordRepository.normalize(word);
        this.letters = views;
    }

    public Answer checkAnswer() {
        if (cursor < letters.length) return Answer.INCOMPLETE_WORD;

        String inputWord = getInputWord();
        if (!WordRepository.checkWord(inputWord)) {
            clear();
            return Answer.INVALID_WORD;
        }

        Map<Character, Answer.Case> cases = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            if (!word.contains(inputWord.substring(i, i + 1))) {
                cases.put(inputWord.charAt(i), Answer.Case.WRONG_LETTER);
                setColor(letters[i], Answer.Case.WRONG_LETTER);
            } else if (word.charAt(i) == inputWord.charAt(i)) {
                cases.put(inputWord.charAt(i), Answer.Case.RIGHT_PLACE);
                setColor(letters[i], Answer.Case.RIGHT_PLACE);
            } else {
                cases.put(inputWord.charAt(i), Answer.Case.WRONG_PLACE);
                setColor(letters[i], Answer.Case.WRONG_PLACE);
            }
        }

        return new Answer(cases);
    }

    private void setColor(TextView view, Answer.Case _case){
        view.setTextColor(_case.getTextColor());
        view.setBackgroundColor(_case.getBackgroundColor());
    }

    private void clear() {
        for (TextView view: letters) {
            view.setText("");
        }
        cursor = 0;
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
