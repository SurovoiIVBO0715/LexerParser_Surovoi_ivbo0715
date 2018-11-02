package Parser;

import Lexer.Token;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Parser {

    private Queue<Token> tokens = new LinkedList<>();

    private Token next() {
        return tokens.peek();
    }

    private boolean next(String type) {
        assert tokens.peek() != null;
        return tokens.peek().getType().equals(type);
    }

    public String parse(List<Token> tokens) {
        this.tokens = new LinkedList<>(tokens);
        lang();
        return "Syntax is OK";
    }

    private void lang() {
        expr();
    }

    private void expr() {
        if (next("VAR")) {
            tokens.poll();
            if (next("ASSIGN"))
                tokens.poll();
            else
                throw new IllegalArgumentException("Error. Expected type ASSIGN, but " +
                        next().getType() + " was found.");
            value_expr();
        } else
            throw new IllegalArgumentException("Error. Expected type VAR, but " +
                    next().getType() + " was found.");
    }

    private void value_expr() {
        value();
        while (next() != null) {
            if (next("OP")) {
                tokens.poll();
                value();
            } else
                throw new IllegalArgumentException("Error. Expected type OP, but " +
                        next().getType() + " was found.");
        }
    }

    private void value() {
        if (next("VAR") | next("DIGIT"))
            tokens.poll();
        else
            throw new IllegalArgumentException("Error. Expected type VAR or DIGIT, but " +
                    next().getType() + " was found.");
    }
}