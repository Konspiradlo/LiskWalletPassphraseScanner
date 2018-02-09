package pl.kons;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scanner {


    public static void main(String[] args) throws IOException {
        Pattern
                patt = Pattern.compile("(?=(\\b[\\w']+\\b \\b[\\w']+\\b \\b[\\w']+\\b \\b[\\w']+\\b \\b[\\w']+\\b \\b[\\w']+\\b \\b[\\w']+\\b \\b[\\w']+\\b \\b[\\w']+\\b \\b[\\w']+\\b \\b[\\w']+\\b \\b[\\w']+\\b))");

        Map<String, String> rawFindings = new HashMap<>();
        String content = "";
        String rootDirectory = "C:\\My Web Sites";
        for (File file : new pl.kons.PathProvider().providePaths(rootDirectory)) {
            try {
                content = new java.util.Scanner(file).useDelimiter("\\Z").next();
            } catch (NoSuchElementException e) {
                continue;
            }
            content = content.replaceAll("\\s+", " ");
            content = content.replaceAll("[^\\w\\s]", "");
            Matcher m = patt.matcher(content);
            while (m.find()) {
                rawFindings.put(m.group(1), file.getAbsolutePath());

            }
        }
        Map<String, String> filtered = new Filter(new pl.kons.WordsProvider().loadAllowedWords()).filterListEnglish(rawFindings);
        filtered.entrySet().forEach(
                s -> System.out.println(s)
        );
        filtered.forEach((s, s2) -> System.out.println(s + " FILE: " + s2));
    }


}
