import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getWordFrequency(String sentence) {
        if (sentence.split(SPACE).length == 1) {
            return sentence + " 1";
        } else {
            try {
                List<WordFrequency> frequencies = getWordFrequencies(sentence);

                frequencies = getWordFrequencyList(frequencies);

                return getResult(frequencies);
            } catch (Exception e) {
                return CALCULATE_ERROR + e;
            }
        }
    }

    private static List<WordFrequency> getWordFrequencies(String sentence) {
        String[] words = sentence.split(SPACE);

        return Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .toList();
    }

    private List<WordFrequency> getWordFrequencyList(List<WordFrequency> frequencies) {
        Map<String, List<WordFrequency>> map = getListMap(frequencies);

        return map.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .toList();
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> inputList) {
        return inputList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }

    private static String getResult(List<WordFrequency> frequencies) {
        return frequencies.stream()
                .sorted((word, nextWord) -> Integer.compare(nextWord.getWordCount(), word.getWordCount()))
                .map(w -> w.getWord() + " " + w.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }
}
