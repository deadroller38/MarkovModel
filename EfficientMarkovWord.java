
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {

    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> followsMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        followsMap = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }
    
    private void buildMap() {
        for (int i = 0; i<=myText.length-myOrder; i++) {
            WordGram kGram = new WordGram(myText, i, myOrder);;
            if(!followsMap.containsKey(kGram)) {
                followsMap.put(kGram, new ArrayList<String>());
            }
            if(followsMap.containsKey(kGram) && i+myOrder!=myText.length) {
                ArrayList<String> follows = followsMap.get(kGram);
                String next = myText[i+myOrder];
                follows.add(next);
                followsMap.put(kGram, follows);
            }
        }
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
        return followsMap.get(kGram);
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
    
    public void printHashMapInfo() {
        System.out.println("Number of keys " + followsMap.size());
        int max = 0;
        ArrayList<String> maxFollows = new ArrayList<String>();
        for(WordGram key : followsMap.keySet()) {
            int curr = followsMap.get(key).size();
            if (max < curr) {
                max = curr;
                maxFollows.clear();
                maxFollows.add(key.toString());
            } else if (max == curr) {
                maxFollows.add(key.toString());
            }
        }
        System.out.println("Maximum value " + max);
        System.out.println("Set of keys with max " + maxFollows);
    }

        
    
}
