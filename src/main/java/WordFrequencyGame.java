import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String Space = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getWordFrequency(String sentence) {
        if (sentence.split(Space).length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(Space);

                List<WordFrequency> frequencies = Arrays.stream(words)
                        .map(word -> new WordFrequency(word, 1))
                        .toList();
                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> map = getListMap(frequencies);

                frequencies = map.entrySet().stream()
                        .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                        .toList();

                return frequencies.stream()
                        .sorted((word, nextWord) -> Integer.compare(nextWord.getWordCount(), word.getWordCount()))
                        .map(w -> w.getWord() + " " + w.getWordCount())
                        .collect(Collectors.joining(LINE_BREAK));
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> inputList) {
        return inputList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }
}
