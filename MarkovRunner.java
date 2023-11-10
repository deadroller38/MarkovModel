
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        int size = 150;
        int seed = 643;
        MarkovWord mw = new MarkovWord(3); 
        runModel(mw, st, size, seed); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

    public void teshHashMap() {
        String st = "this is a test yes this is really a test";
        EfficientMarkovWord emw = new EfficientMarkovWord(2);
        emw.setRandom(42);
        emw.setTraining(st);
    }
    
    public void compareTwoMarkov() {
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        int size = 100;
        int seed = 65;
        
        //MarkovWord mw = new MarkovWord(5); 
        //runModel(mw, st, size, seed); 
        
        EfficientMarkovWord emw = new EfficientMarkovWord(2); 
        runModel(emw, st, size, seed);         
    }
    
}
