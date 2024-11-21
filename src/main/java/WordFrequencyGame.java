import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String SPACE = " ";

    public String getWordFrequency(String sentence) {
        try {
            return Arrays.stream(sentence.split(REGEX))
                    .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                    .entrySet().stream()
                    .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().intValue()))
                    .sorted(Comparator.comparingInt(WordFrequency::getCount).reversed())
                    .map(wordFrequency -> wordFrequency.getWord() + SPACE + wordFrequency.getCount()) // todo no use object
                    .collect(Collectors.joining(LINE_BREAK));
        } catch (Exception e) {
            return CALCULATE_ERROR + e;
        }
    }
}
