package com.trie.basics;

import java.util.List;

/**
 * Created by seshharikamannepalli on 7/19/17.
 */
public class AutoCorrectionUsingTrie {

    AutoCompletion ac;

    AutoCorrectionUsingTrie(){
        String[] dictionary = {"trie","try","because","behive","tried"};
        ac = new AutoCompletion(dictionary);
    }

    public void findAutoCorrectWord(String input){
        for(int i = input.length() - 1 ; i >= 0 ; i--){
            List<String> list = ac.getWordsForPrefixFromTrie(input.substring(0,i+1));
            if(list.size() != 0){
               System.out.println(list.toString());
               break;
            }
        }
    }

    public static void main(String args[]){
        AutoCorrectionUsingTrie at = new AutoCorrectionUsingTrie();
        at.findAutoCorrectWord("tries");
    }


}
