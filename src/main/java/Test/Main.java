package Test;

import Lexer.*;
import Parser.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String string = "x = someValue/946 - y + 31*z";

        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.process(string);

        System.out.println("Source expression:");
        System.out.println(string);

        System.out.println();

        System.out.println("Lexer:");
        for (Token token : tokens) {
            System.out.println(token);
        }

        System.out.println();

        Parser parser = new Parser();
        System.out.println("Parser:");
        try {
            System.out.println(parser.parse(tokens));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}