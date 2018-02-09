package pl.kons;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Filter {

    private Set<String> allowedWords;

    public Filter(Set<String> allowedWords) {
        this.allowedWords = allowedWords;
    }

    public Map<String, String> filterListEnglish(Map<String, String> rawFindings) {
        Map<String, String> filtered = new HashMap<>();
        for (Map.Entry<String, String> entry : rawFindings.entrySet()) {
            if (test(entry)) {
                filtered.put(entry.getKey(), entry.getValue());
            }
        }
        return filtered;
    }

    private boolean test(Map.Entry<String, String> entry) {
        String[] words = entry.getKey().split("\\s");
        for (String word : words) {
            if (!allowedWords.contains(word)) {
                return false;
            }
        }
        return true;
    }
}
