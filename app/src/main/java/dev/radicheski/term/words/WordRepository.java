package dev.radicheski.term.words;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import dev.radicheski.term.R;

public class WordRepository {

    private static WordRepository instance = new WordRepository();
    private static Context context;

    private List<String> words = new ArrayList<>();
    private Set<String> normalizedWords = new HashSet<>();

    private WordRepository() {
        if (Objects.isNull(context)) return;

        try (Reader r = new InputStreamReader(context.getResources().openRawResource(R.raw.words));
             BufferedReader reader = new BufferedReader(r)) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.toUpperCase();
                words.add(line);
                normalizedWords.add(normalize(line));
            }

        } catch (Exception e) {
            Log.e(getClass().getName(), "Failed to load words.", e);
        }
    }

    public static void setContext(Context context) {
        WordRepository.context = context;
        instance = new WordRepository();
    }

    public static boolean checkWord(String word) {
        String normalized = normalize(word);
        return instance.normalizedWords.contains(normalized);
    }

    public static String normalize(String word) {
        if (Objects.isNull(word)) return null;
        if (Normalizer.isNormalized(word, Normalizer.Form.NFKD)) return word;

        String normalized = Normalizer.normalize(word, Normalizer.Form.NFKD);
        normalized = normalized.replaceAll("[^A-Z]", "");

        return normalized;
    }

    public static String getRandomWord() {
        Random random = new Random();
        return instance.words.get(random.nextInt(instance.words.size()));
    }
}
