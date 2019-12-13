package com.example.guide.java8.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * @author Jason
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String line = processFile((BufferedReader reader) -> reader.readLine());
        System.out.println(line);

        String lines = processFile((BufferedReader reader) -> reader.readLine() + "\t" + reader.readLine());
        System.out.println(lines);

        String result = test("first string",
                "second string",
                (String firstString, String secondString) -> firstString + " " + secondString);

        System.out.println(result);

        result = test("first string",
                "second string",
                (String firstString, String secondString) -> firstString.substring(0, 3) + secondString.substring(3, 5));

        System.out.println(result);


        List<String> list = Arrays.asList("this", "is", "a", "test", "string");
        uppercase(list, (String s) -> System.out.print(s.toUpperCase() + " "));

        System.out.println("");

        result = combine("a", "b", (String a, String b) -> a + b);
        System.out.println(result);
    }

    public static String processFile(BufferedReadProcessor processor) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            return processor.process(reader);
        }
    }

    public static String test(String firstString, String secondString, TestInterface testInterface) {
        return testInterface.test(firstString, secondString);
    }

    public static void uppercase(List<String> list, Consumer<String> consumer) {
        for (String s : list) {
            consumer.accept(s);
        }
    }

    public static String combine(String a, String b, BiFunction<String, String, String> function){
        return function.apply(a, b);
    }
}
