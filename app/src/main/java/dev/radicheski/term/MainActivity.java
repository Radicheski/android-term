package dev.radicheski.term;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        createMatch();
        createKeyboard();

        keyboard.setLetterListener(match::addLetter);
        keyboard.setEnterListener(match::checkAnswer);
        keyboard.setBackpaceListener(match::deleteLetter);
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
    }

    public void showAlert(String title, String message, Runnable onCompletion){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, which) -> {
            if (Objects.nonNull(onCompletion)) onCompletion.run();
        });
        builder.show();
    }

}
