package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

// BEGIN
class App {

    public static Map<String, Integer> getWordCount(String sentence) {

        Map<String, Integer> wordCount = new HashMap<>();
        String[] sentenceWords = sentence.split(" ");

        if (sentence.length() == 0) {
            return wordCount;
        }

        for (int i = 0; i < sentenceWords.length; i++) {
            String currentWord = sentenceWords[i];
            int count = 0;

            for (int n = 0; n < sentenceWords.length; n++) {
                if (currentWord.equalsIgnoreCase(sentenceWords[n])) {
                    count += 1;
                }
            }
            wordCount.put(currentWord, count);
        }

        return wordCount;
    }

    public static String toString(Map<String, Integer> wordCount) {

        if (wordCount.isEmpty()) {
            return "{}";
        }

        String[] words = wordCount.keySet().toArray(new String[0]);
        StringJoiner sjString = new StringJoiner("\n", "{\n", "\n}");

        for (int i = 0; i < words.length; i++) {
            sjString.add("  " + words[i] + ": " + wordCount.get(words[i]));
        }

        return sjString.toString();
    }
}
//END
