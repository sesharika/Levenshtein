package com.trie.basics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by seshharikamannepalli on 7/18/17.
 */
public class AutoCompletion {

    private class Node {
        String word;
        HashMap<Character, Node> child;
        boolean isWord;

        private Node(String word){
            this.word = word;
            this.child = new HashMap<Character, Node>();
        }
    }

    private Node trie;

    public AutoCompletion(String[] dictionary){
        trie = new Node("");

        for(String each : dictionary){
            insertWord(each);
        }
    }

    private void insertWord(String word){
        Node current = trie;

        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);

            // If the word prefix is not already there add it
            if(!current.child.containsKey(ch)){
                current.child.put(ch, new Node(word.substring(0, i+1)));
            }
            current = current.child.get(ch);

            // If it is the last character of the word, then the isWord flag should be set to true
            if(i == word.length() - 1){
                current.isWord = true;
            }
        }
    }

    public List<String> getWordsForPrefixFromTrie(String prefix){
        List<String> list = new LinkedList<String>();

        Node current = trie;
        for(char ch : prefix.toCharArray()){
            if(current.child.containsKey(ch)){
                current = current.child.get(ch);
            } else {
                return list;
            }
        }

        findWordsWithPrefix(current, list);
        return list;
    }

    public void findWordsWithPrefix(Node current, List<String> list){
        if(current.isWord){
            list.add(current.word);
        }

        for(char ch : current.child.keySet()){
            findWordsWithPrefix(current.child.get(ch), list);
        }
    }

    public static void main(String[] args){
        String[] dictionary = new String[5];
        dictionary[0] = "trie";
        dictionary[1] = "try";
        dictionary[2] = "tire";
        dictionary[3] = "tries";
        dictionary[4] = "tim";

        AutoCompletion ac = new AutoCompletion(dictionary);
        List<String> result = ac.getWordsForPrefixFromTrie("ti");

        System.out.println(result.toString());

    }


}
