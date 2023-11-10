
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel {

    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);
        /*for (int i = index; i<=myOrder; i++) {
            System.out.println("Key is ");
            System.out.print(myText[i] + " ");
        }
        */
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int index = indexOf(myText, kGram, 0);
        while (index != -1) {
         follows.add(myText[kGram.length()+index]);
         index = indexOf(myText, kGram, index+1);
        }
        return follows;
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        int l = target.length();
        for (int index = start; index<words.length-l; index++) {
            WordGram wg = new WordGram(words, index, l);
            if (target.equals(wg)) {
                return index;
            }
        }
        return -1;   
    }
    

        
    
}
