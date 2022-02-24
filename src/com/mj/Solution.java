package com.mj;



import java.util.*;
public class Solution {
    ArrayList<String> res = new ArrayList<String>();
    HashSet<String> set = new HashSet<String>();
    int length;
    HashMap<Character, Integer> sta = new HashMap<Character, Integer>();
    char [] array;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.init("ABC");
        solution.Permutation("ABC");
    }
    public ArrayList<String> Permutation(String str) {
        set = new HashSet<String>();
        res = new ArrayList<String>();
        if(str.length() == 0) return new ArrayList<String>();
        init(str);
        int k = 0;
        ArrayList<Character> touse = new ArrayList<Character>();
        for(char c : array) {
            touse.add(c);
        }
        next(k, touse);
        res = new ArrayList<String>(set);
        Collections.sort(res);
        return res;
    }
 
    public void init(String str) {
        length = str.length();
        array = str.toCharArray();
        Arrays.sort(array);
        for (char c : array) {
            if (sta.containsKey(c))
                sta.put(c, sta.get(c) + 1);
            else
                sta.put(c, 1);
        }
    }
 
    public void next(int k, ArrayList<Character> touse) {
 
        if (k == length) {
            String s = new String(array);
            if(!set.contains(s)) set.add(new String(array));
        }
        else {
            for (int i=0; i<touse.size(); i++) {
                char c = touse.get(i);
                ArrayList<Character> to_use = new ArrayList<Character>(touse);
                to_use.remove(i);
                array[k] = c;
                next(k + 1, to_use);
            }
        }
    }
}