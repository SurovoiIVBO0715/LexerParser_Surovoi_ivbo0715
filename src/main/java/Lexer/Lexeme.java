package Lexer;

import java.util.regex.Pattern;

class Lexeme {

    private String type;
    private Pattern pattern;

    Lexeme(String type, Pattern pattern) {
        this.type = type;
        this.pattern = pattern;
    }

    String getType() {
        return type;
    }

    Pattern getPattern() {
        return pattern;
    }
}
