package com.mj;

import java.nio.charset.Charset;
import java.util.*;

public class Main3 {
    public static void main(String[] args) {
        List<String> list = permutation("ABCD");

        System.out.println(list.size());


    }


    public static ArrayList<String> permutation(String str){

        ArrayList<String> list = new ArrayList<String>();
        if(str!=null && str.length()>0){
            PermutationHelper(str.toCharArray(),0,list);
            Collections.sort(list);
        }
        return list;
    }
//    private static void PermutationHelper(char[] chars,int i,ArrayList<String> list){
//        if(i == chars.length-1){
//            list.add(String.valueOf(chars));
//        }else{
//            Set<Character> charSet = new HashSet<Character>();
//            for(int j=i;j<chars.length;++j){
//                if(j==i || !charSet.contains(chars[j])){
//                    charSet.add(chars[j]);
//                    swap(chars,i,j);
//                    PermutationHelper(chars,i+1,list);
//                    swap(chars,j,i);
//                }
//            }
//        }
//    }

    private static void PermutationHelper(char[] chars, int i, ArrayList<String> result) {
        if (i == chars.length - 1){
            result.add(new String(chars));
        } else {
            Set charset = new HashSet();
            for (int j = i; j < chars.length; j++) {
                if (!charset.contains(chars[j])) {
                    charset.add(chars[j]);
                    swap(chars,i,j);
                    PermutationHelper(chars,i+1,result);
                    swap(chars,i,j);
                }
            }
        }
    }



    private static void swap(char[] cs,int i,int j){
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }
}
