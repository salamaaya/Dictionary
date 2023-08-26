//Aya Salama
//Sections: C, RB
//I pledge my honor that I have abided by the Stevens honor system.

import java.util.NoSuchElementException;

public class DictionaryItem {
    private String word;
    private int count;

    /**
     *
     * @param word
     * @param count
     */
    public DictionaryItem(String word, int count){
        if(count < 0){
            throw new NoSuchElementException("Count must be greater than 0.");
        }
        this.word = word;
        this.count = count;
    }

    /**
     * returns the word of given DictionaryItem
     * @return
     */
    public String getWord(){
        return word;
    }

    /**
     * returns the count of the given DictionaryItem
     * @return
     */
    public int getCount(){
        return count;
    }

    /**
     * changes the value of word
     * @param newWord
     */
    public void setWord(String newWord){
        word = newWord;
    }

    /**
     * changes the value of count
     * @param newCount
     */
    public void setCount(int newCount){
        count = newCount;
    }

}
