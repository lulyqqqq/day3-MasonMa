public class wordFrequency {
    private String word;
    private int count;

    public wordFrequency(String w, int i){
        this.word =w;
        this.count =i;
    }

    public String getWord() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }
}
