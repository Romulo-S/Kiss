import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String END_WORD = "ay";

    public static void main(String[] args) {
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        String word = in.nextLine();

        translateToPigLatin(word);
    }

    /**
     * Converts this sentence into pig latam language
     * @param sentence
     */
    public static void translateToPigLatin(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder sentenceTranslated = new StringBuilder();


        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String wordTranslated = convert(word);
            sentenceTranslated.append(wordTranslated).append(" ");
        }

        System.out.println(sentenceTranslated);
    }

    /**
     * @param word
     * @return
     */
    private static String convert(String word) {

        if (!hasLetters(word)) {
            return word;
        }

        String stem;
        if (isVowel(word.charAt(0)) == true) {
            stem = word + END_WORD;
            return stem;
        }

        String punctuation = "";
        int length = word.length();

        for (int i = 0; i < length; i++) {

            if (isVowel(word.charAt(i)) == true) {

                if (containsPunctuation(word.substring(word.length() - 1))) {
                    punctuation = word.substring(word.length() - 1);
                    word = word.substring(0,word.length() - 1);
                }

                if (Character.isUpperCase(word.charAt(0))) {
                    //convert the first letter to lower case
                    String lowerCase = word.substring(0, 1).toLowerCase() + word.substring(1);
                    String translatedWord = translateToPigLatin(lowerCase, i);
                    return translatedWord.substring(0, 1).toUpperCase() + translatedWord.substring(1) + punctuation;
                } else {
                    String translatedWord = translateToPigLatin(word, i)+ punctuation;
                    return translatedWord;
                }
            }
        }
        return word;
    }

    private static boolean containsPunctuation(String word) {
        String punctuations = ".,:;!";
        if (punctuations.contains(word)) {
            return true;
        }
        return false;
    }

    private static String translateToPigLatin(String word, int vowelIndex) {
        String stem;
        String prefix;
        prefix = word.substring(0, vowelIndex);
        stem = word.substring(vowelIndex);
        String translatedWord = stem + prefix + END_WORD;
        return translatedWord;
    }

    private static boolean hasLetters(String word) {
        String regexLetters = ".*[a-zA-Z].*";
        if (word.matches(regexLetters)) {
            return true;
        }
        return false;
    }

    public static boolean isVowel(char c) {
        return "AEIOUaeiouy".indexOf(c) != -1;
    }
}