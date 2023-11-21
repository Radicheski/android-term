package dev.radicheski.term;

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

    public Answer checkAnswer() {
        if (cursor < letters.length) return Answer.INCOMPLETE_WORD;

        String inputWord = getInputWord();
        if (!WordRepository.checkWord(inputWord)) return Answer.INVALID_WORD; //TODO Apagar conteudo das textviews

        Answer.Case[] cases = new Answer.Case[letters.length];
        //TODO Atualizar cores das textviews
        for (int i = 0; i < word.length(); i++) {
            if (!word.contains(inputWord.substring(i, i + 1))) {
                cases[i] = Answer.Case.WRONG_LETTER;
            } else if (word.charAt(i) == inputWord.charAt(i)) {
                cases[i] = Answer.Case.RIGHT_PLACE;
            } else {
                cases[i] = Answer.Case.WRONG_PLACE;
            }
        }

        return new Answer(cases);
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
