package Samples;

import Base.helpers.MySeleniumMethods;

public class klasaaaaa  {


    public static void main(String[] args) {
        System.out.println("Hello!");
        MySeleniumMethods.readFromTextFile("src\\test\\resources\\Numbers.txt").stream().forEach(System.out::println);


    }


}
