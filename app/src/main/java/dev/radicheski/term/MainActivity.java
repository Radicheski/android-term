package dev.radicheski.term;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

import dev.radicheski.term.words.WordRepository;

public class MainActivity extends AppCompatActivity {

    private Match match;
    private Keyboard keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createKeyboard();
        createMatch();
    }

    private void createMatch() {
        TextView[][] views = new TextView[6][5];

        for (int atempt = 0; atempt < 6; atempt++) {
            for (int letter = 0; letter < 5; letter++) {
                int id = getResources().getIdentifier("atempt" + atempt + "_letter" + letter, "id", getPackageName());
                views[atempt][letter] = findViewById(id);
            }
        }

        WordRepository.setContext(getApplicationContext());
        match = new Match(views);
    }

    private void createKeyboard() {
        Button keyBackspace = findViewById(R.id.keyBackspace);
        Button keyEnter = findViewById(R.id.keyEnter);

        char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
        Button[] letterButtons = new Button[letters.length];
        for (int i = 0; i < letters.length; i++) {
            letterButtons[i] = findViewById(getResources()
                    .getIdentifier("key" + letters[i], "id", getPackageName()));
        }

        keyboard = new Keyboard(letterButtons, keyEnter, keyBackspace);
        keyboard.setLetterListener(this::letterClick);
        keyboard.setEnterListener(this::enterClick);
        keyboard.setBackpaceListener(this::backspaceClick);
    }

    private void backspaceClick(View view) {
        if (view instanceof Button button) {
            match.deleteLetter();
        }
    }

    private void enterClick(View view) {
        if (view instanceof Button button) {
            Answer answer = match.checkAnswer();
            if (answer == Answer.INCOMPLETE_WORD) return;
            if (answer.getCases().isEmpty() && Objects.nonNull(answer.getInput())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(String.format("A palavra \"%s\" não foi encontrada.", answer.getInput()));
                builder.setPositiveButton("OK", null);
                builder.show();
                return;
            }
            keyboard.updateLayout(answer.getCases());
        }
    }

    private void letterClick(View view) {
        if (view instanceof Button button) {
            match.addLetter(button.getText());
        }
    }

}
