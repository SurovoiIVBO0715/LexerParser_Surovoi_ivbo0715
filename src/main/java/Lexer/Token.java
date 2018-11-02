package Lexer;

public class Token {

    private String type;
    private String value;

    Token(Lexeme lexeme, String value) {
        this.type = lexeme.getType();
        this.value = value;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
