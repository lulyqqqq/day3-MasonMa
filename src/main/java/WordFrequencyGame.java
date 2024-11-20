import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getWordFrequency(String sentence) {
        try {
            return Arrays.stream(sentence.split(SPACE))
                    .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                    .entrySet().stream()
                    .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().intValue()))
                    .sorted(Comparator.comparingInt(WordFrequency::getWordCount).reversed())
                    .map(w -> w.getWord() + " " + w.getWordCount())
                    .collect(Collectors.joining(LINE_BREAK));
        } catch (Exception e) {
            return CALCULATE_ERROR + e;
        }
    }
}
