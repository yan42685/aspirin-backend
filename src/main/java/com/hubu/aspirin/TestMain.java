package com.hubu.aspirin;


public class TestMain {
    public static void main(String[] args) {
        String a = "12345";
        String[] abcs = a.split("abc");
        System.out.println(abcs.length);
        System.out.println(abcs[0]);
    }
}
