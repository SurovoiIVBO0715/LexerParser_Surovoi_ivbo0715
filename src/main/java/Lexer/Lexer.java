package Lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private List<Lexeme> lexemes = new ArrayList<>();

    public Lexer() {
        lexemes.add(new Lexeme("VAR", Pattern.compile("[a-zA-Z]+")));
        lexemes.add(new Lexeme("DIGIT", Pattern.compile("0|([1-9][0-9]*)")));
        lexemes.add(new Lexeme("ASSIGN", Pattern.compile("=")));
        lexemes.add(new Lexeme("OP", Pattern.compile("[+-/*]")));
        lexemes.add(new Lexeme("WS", Pattern.compile("[\t\f\r\n]+")));
    }

    public List<Token> process(String s) {

        List<Token> list = new ArrayList<>();

        StringBuilder tokenBuffer = new StringBuilder();
        for (Lexeme token : lexemes)
            tokenBuffer.append(String.format("|(?<%s>%s)", token.getType(), token.getPattern()));

        Pattern tokenPatterns = Pattern.compile(tokenBuffer.substring(1));
        Matcher matcher = tokenPatterns.matcher(s);
        while (matcher.find()) {
            if (matcher.group("WS") != null)
                continue;
            for (Lexeme lexeme : lexemes) {
                if (matcher.group(lexeme.getType()) != null)
                    list.add(new Token(lexeme, matcher.group(lexeme.getType())));
            }
        }
        return list;
    }
}