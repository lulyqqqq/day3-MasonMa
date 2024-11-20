import java.util.*;

public class WordFrequencyGame {
    public String getWordFrequency(String sentence) {
        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");

                List<wordFrequency> inputList = new ArrayList<>();
                for (String word : words) {
                    wordFrequency input = new wordFrequency(word, 1);
                    inputList.add(input);
                }
                //get the map for the next step of sizing the same word
                Map<String, List<wordFrequency>> map = getListMap(inputList);

                List<wordFrequency> list = new ArrayList<>();
                for (Map.Entry<String, List<wordFrequency>> entry : map.entrySet()) {
                    wordFrequency input = new wordFrequency(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputList = list;

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (wordFrequency w : inputList) {
                    String s = w.getWord() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<wordFrequency>> getListMap(List<wordFrequency> inputList) {
        Map<String, List<wordFrequency>> map = new HashMap<>();
        for (wordFrequency input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getWord(), arr);
            } else {
                map.get(input.getWord()).add(input);
            }
        }
        return map;
    }
}
