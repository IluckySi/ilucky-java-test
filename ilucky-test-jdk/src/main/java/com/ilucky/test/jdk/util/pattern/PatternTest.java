package com.ilucky.test.jdk.util.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        String urlPattern = "this\\\s+is\s+text";
        Pattern p=Pattern.compile(urlPattern);
        String[] uriArray = new String[]{"this is text", "this    is     text"};
        for(String uri : uriArray) {
            Matcher m=p.matcher(uri.toString());
            if (m.find()){
                System.out.println(true);
            } else {
                System.out.println(false);
            }  
        }        
    }

    private static void test1() {
        String urlPattern = "this is text";
        Pattern p=Pattern.compile(urlPattern);
        String[] uriArray = new String[]{"this is text", "this    is     text"};
        for(String uri : uriArray) {
            Matcher m=p.matcher(uri.toString());
            if (m.find()){
                System.out.println(true);
            } else {
                System.out.println(false);
            }  
        }
    }

    private static void test() {
        String uri = "/test/a.mvc";
        String urlPattern = ".*";
        Pattern p=Pattern.compile(urlPattern);
        Matcher m=p.matcher(uri.toString());
        if (m.find()){
            System.out.println(true);
        } else {
            System.out.println(false);
        }        
    }
}
