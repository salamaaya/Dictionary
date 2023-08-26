//Aya Salama
//Sections: C, RB
//I pledge my honor that I have abided by the Stevens honor system.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class Dictionary {
    private ArrayList<String> wordList;
    private ArrayList<DictionaryItem> dictArrayList;

    /**
     * constructor which initializes wordList and dictArrayList.
     */
    public Dictionary() {
        wordList = new ArrayList<String>(1300);
        dictArrayList = new ArrayList<DictionaryItem>(1300);
        readFile("ionDictionary.txt");
    }

    /**
     * constructor
     *
     * @param filename
     */
    public Dictionary(String filename) {
        wordList = new ArrayList<String>(1300);
        dictArrayList = new ArrayList<DictionaryItem>(1300);
        readFile(filename);
    }

    /**
     * prints the menu until program is quit by user
     */
    public void printMenu() {
        Scanner sc = new Scanner(System.in);
        int userInput = 0;
        System.out.println("Welcome to the Ion Dictionary! This dictionary is created from the book Ion by Plato!");
        System.out.println("Please choose one of the following menu items indicated with 1-3");
        System.out.println("1: To print all the words in the dictionary, choose 1");
        System.out.println("2: To search a word in the dictionary, choose 2");
        System.out.println("3: To quit the program, choose 3");
        try {
            userInput = sc.nextInt();
        }
        catch(InputMismatchException e){
        }
        while (processMenuItem(userInput, sc)) {
            System.out.println("Please choose one of the following menu items indicated with 1-3");
            System.out.println("1: To print all the words in the dictionary, choose 1");
            System.out.println("2: To search a word in the dictionary, choose 2");
            System.out.println("3: To quit the program, choose 3");
            try {
                userInput = sc.nextInt();
            }
            catch(InputMismatchException e){
                userInput = 0;
            }
        }
        System.out.println("Thanks for using Ion Dictionary! Bye!");
    }

    /**
     * helper method to printMenu() which calls associated methods to menu options
     *
     * @param menuItem
     * @param scan
     * @return
     */
    private boolean processMenuItem(int menuItem, Scanner scan) {
        if (menuItem == 1) {
            System.out.println("All the words mentioned in the Ion book!");
            System.out.println("Words");
            System.out.println("-----");
            printDictionary();
            return true;
        }
        else if (menuItem == 2) {
            String searchWord;
            System.out.println("Please enter the word you would like to search: ");
            searchWord = scan.next();
            if (searchDictionary(searchWord) != 0) {
                System.out.println("The word '" + searchWord + "' occurred " + searchDictionary(searchWord) + " times in the book!");
            } else {
                System.out.println("The word '" + searchWord + "' does not exist in the Ion dictionary!");
            }
            return true;
        }
        else if (menuItem > 3 || menuItem < 1) {
            System.out.println("ERROR! Please enter a number between 1 and 3.");
            scan.nextLine();
            return true;
        }
        return false;
    }

    /**
     * checks if file is there, if it is, reads through it and updates ArrayList values.
     *
     * @param fileName
     */
    public void readFile(String fileName) {
        File f = new File(fileName);
        Scanner c;
        try {
            c = new Scanner(f);
            c.nextLine();
            c.nextLine();
            c.nextLine();
            while (c.hasNextLine()) {
                c.nextLine();
                try {
                    wordList.add(c.next());
                } catch (NoSuchElementException e) {
                    break;
                }
            }
            Scanner splitLine = new Scanner(f);
            splitStoreLine(splitLine);
        } catch (FileNotFoundException e) {
            System.out.println("Error! File Not Found!!!");
        }
    }

    /**
     * helper method to readFile()
     *
     * @param scan
     */
    private void splitStoreLine(Scanner scan) {
        scan.nextLine();
        scan.nextLine();
        scan.nextLine();
        while (scan.hasNextLine()) {
            scan.nextLine();
            try {
                String word = scan.next();
                scan.next();
                dictArrayList.add(new DictionaryItem(word, Integer.parseInt(scan.next())));
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }

    /**
     * prints the dictionary
     */
    public void printDictionary() {
        for (int i = 0; i < wordList.size(); i++) {
            System.out.println(wordList.get(i));
        }
    }

    /**
     * returns the count of word in the dictionary
     * @param word
     * @return
     */
    public int searchDictionary(String word) {
        if (binarySearch(word, 0, wordList.size() - 1) != -1) {
            return dictArrayList.get(binarySearch(word, 0, wordList.size())).getCount();
        }
        return 0;
    }

    /**
     * @param word
     * @param low
     * @param high
     * @return
     */
    private int binarySearch(String word, int low, int high) {
        int middle = (low + high) / 2;
        if (wordList.get(middle).equals(word)) {
            return middle;
        } else if (word.compareTo(wordList.get(middle)) < 0 && middle > low) {
            middle = (low + high) / 2;
            return binarySearch(word, low, middle - 1);
        } else if (word.compareTo(wordList.get(middle)) > 0 && middle < high) {
            middle = (low + high) / 2;
            return binarySearch(word, middle + 1, high);
        } else {
            return -1;
        }
    }
}

