package pl.kons;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class WordsProvider {

    public Set<String> loadAllowedWords() throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("Liskenglish.txt");
        BufferedReader r = new BufferedReader(new FileReader(url.getFile()));
        String line;
        Set<String> allowedWords = new HashSet<String>();
        while ((line = r.readLine()) != null) {
            allowedWords.add(line.trim());
        }
        return allowedWords;
    }
}
