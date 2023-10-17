package dev.radicheski.term;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Match match = new Match("AAAAA");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button keyBackspace = findViewById(R.id.keyBackspace);
        keyBackspace.setOnClickListener(this::backspaceClick);

        Button keyEnter = findViewById(R.id.keyEnter);
        keyEnter.setOnClickListener(this::enterClick);

        char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        for (char c : letters) {
            Button letter = findViewById(getResources()
                    .getIdentifier("key" + c, "id", getPackageName()));
            letter.setOnClickListener(this::letterClick);
        }
    }

    private void backspaceClick(View view) {
        if (view instanceof Button button) {
            match.deleteLetter();
        }
    }

    private void enterClick(View view) {
        if (view instanceof Button button) {
            match.checkAnswer();
        }
    }

    private void letterClick(View view) {
        if (view instanceof Button button) {
            match.addLetter(button.getText());
        }
    }

}