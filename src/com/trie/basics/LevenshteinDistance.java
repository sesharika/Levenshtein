package com.trie.basics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by seshharikamannepalli on 7/19/17.
 */
public class LevenshteinDistance {

    /*
        LEVENSHTEIN DISTANCE ALGORITHM
            If the character is same,
                get the diagonal value from the matrix
            else,
                get the min from matrix[i-1][j], matrix[i-1][j-1], matrix[i][j-1] and add 1

     */
    public int levenshteinEditDistance(char[] inputChar, char[] wordChar){

        int[][] matrix = new int[inputChar.length + 1][wordChar.length + 1];

        // Add the first row and column to the matrix
        for(int i = 0; i < matrix[0].length; i++){
            matrix[0][i] = i;
        }

        for(int i = 0; i < matrix.length; i++){
            matrix[i][0] = i;
        }

        for(int i = 1; i <= inputChar.length; i++){
            for(int j = 1; j <= wordChar.length; j++){
                if(inputChar[i-1] == wordChar[j-1]){
                    matrix[i][j] = matrix[i-1][j-1];
                }
                else {
                    matrix[i][j] = minValue(matrix[i-1][j], matrix[i-1][j-1], matrix[i][j-1]) + 1;
                }
            }
        }

        return matrix[inputChar.length][wordChar.length];
    }

    public int minValue(int a, int b, int c){
        int temp = Math.min(a, b);
        return Math.min(temp, c);
    }

    public String autoCorrectByDistance(String word, String[] dictionary){
        int[] distances = new int[dictionary.length];

        char[] inputChar = word.toCharArray();
        int minValue = Integer.MAX_VALUE;
        int minIndex = 0;
        for(int i = 0; i < dictionary.length; i++){
            char[] dictChar = dictionary[i].toCharArray();

            int distance = levenshteinEditDistance(inputChar, dictChar);
            distances[i] = distance;

            if(distance < minValue){
                minValue = distance;
                minIndex = i;
            }
        }

        return dictionary[minIndex];
    }

    public static void main(String[] args){
        LevenshteinDistance ld = new LevenshteinDistance();
        String[] dict = {"trie","tries","distance","dot","ditto","because","behive","but"};
        String input = "what";

        String autoCorrect = ld.autoCorrectByDistance(input, dict);

        System.out.println("Did you mean? " + autoCorrect);
    }

}
