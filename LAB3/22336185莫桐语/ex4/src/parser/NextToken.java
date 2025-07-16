package parser;

import exceptions.*;
import scanner.token.*;

public interface NextToken {
    public Token nextToken() throws java.io.IOException, OberonException;
}
