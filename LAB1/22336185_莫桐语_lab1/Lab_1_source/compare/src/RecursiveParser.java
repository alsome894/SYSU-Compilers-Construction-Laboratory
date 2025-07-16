import java.io.*;

public class RecursiveParser {
    private int lookahead;
    private final InputStream input;

    public RecursiveParser(InputStream input) throws IOException {
        this.input = input;
        lookahead = input.read();
    }

    public void parse() throws IOException {
        expr();
    }

    private void expr() throws IOException {
        term();
        rest();
    }

    private void rest() throws IOException {
        if (lookahead == '+' || lookahead == '-') {
            int op = lookahead;
            match(op);
            term();
            //System.out.write(op);
            rest();
        }
    }

    private void term() throws IOException {
        if (Character.isDigit((char)lookahead)) {
            //System.out.write((char)lookahead);
            match(lookahead);
        } else {
            throw new Error("syntax error");
        }
    }

    private void match(int t) throws IOException {
        if (lookahead == t) {
            lookahead = input.read();
        } else {
            throw new Error("syntax error");
        }
    }
}


