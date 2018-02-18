/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    ArrayList<String> wordList = new ArrayList<>();
    HashSet<String> wordSet = new HashSet<>();
    HashMap<String,ArrayList<String>> letersToWord = new HashMap<>();


    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
        }
        for(int i=0;i<wordList.size();i++)
        {
            ArrayList<String> temp = new ArrayList<>();
            String string = sortWord(wordList.get(i));
            if(letersToWord.containsKey(string)){
                temp=letersToWord.get(string);
                temp.add(wordList.get(i));
                letersToWord.put(string,temp);
            }
            else {
                temp.add(wordList.get(i));
                letersToWord.put(string, temp);
            }

        }

        Log.e("Check", letersToWord.toString());


    }
    public String sortWord(String s)
    {
        char [] word = s.toCharArray();
        Arrays.sort(word);
        String result = new String(word);
        return result;
    }
    public boolean checkEqual(String w,String o)
    {
        int m=0,flag=0;
        for (int i=0;i<w.length();i++)
        {
            if(o.charAt(m)==w.charAt(i))
            {
                m++;
                if(m==o.length()) {
                    flag=1;
                    break;
                }
            }
            else m=0;
        }
        if(flag==1) return false;
        return true;
    }
    public boolean isGoodWord(String word, String base) {


        return checkEqual(word,base);
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> checkG  = new ArrayList<>();
        for(char i='a';i<='z';i++)
        {
            String newWord=word+i;
            Log.e("getAnagramsWith: ",newWord);
            newWord=sortWord(newWord);
            if(letersToWord.containsKey(newWord))
            {
                checkG=letersToWord.get(newWord);
                for (String w:checkG) {
                  if(isGoodWord(w,word))  result.add(w);
                }
            }
                //result.addAll();

        }
        Log.e("Array", result.toString() );

        return result;
    }

    public String pickGoodStarterWord() {
        Random random = new Random();
        int n=random.nextInt(wordList.size());
         return wordList.get(n);
    }
}
