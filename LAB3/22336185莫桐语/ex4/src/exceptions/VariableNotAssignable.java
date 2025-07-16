package exceptions;

import parser.env.Declaration;
import scanner.token.Identifier;

public class VariableNotAssignable extends SyntaxException {
    public VariableNotAssignable(Declaration decl, Identifier id) {
        super("Variable '" + id.getLexeme() + "' at line " + id.getLine() + ", column " + id.getColumn() + " is not assignable. First declared at line " + decl.getLine() + ", column " + decl.getColumn() + ".");
    }
}
