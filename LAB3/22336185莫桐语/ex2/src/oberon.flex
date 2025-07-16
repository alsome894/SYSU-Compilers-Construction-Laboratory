package scanner;

import exceptions.*;
import java.lang.Integer;

%%

%public

%class OberonScanner
%type Token
%yylexthrow LexicalException

%cup                                    
%line                                   
%column                                 
%ignorecase

%eofval{
    return Symbol.eof(yyline + 1, yycolumn + 1);
%eofval}

%{
%}

/* Regular definitions */

MyInteger     = 0[0-7]* | [1-9]+[0-9]*
Identifier    = [a-zA-Z][a-zA-Z0-9]*
Whitespace    = [ \t\n\r] | \r\n
Comment       = "(*" ~ "*)"             

/* Exception definitions */

IllegalComment = "(*" ([^\*] | "*"+[^\)])+ | ([^\(]|"("+[^\*])+"*)"
IllegalOctal = 0[0-7]* [8|9|"."]+ [0-9]*
IllegalInteger 	= {MyInteger}+{Identifier}+

%%

/* Reservedwords/Keywords */

<YYINITIAL> {
    "BOOLEAN"       { return Keyword.typeBool(yyline + 1, yycolumn + 1); }
    "INTEGER"       { return Keyword.typeInt(yyline + 1, yycolumn + 1); }
    "RECORD"        { return Keyword.typeRecord(yyline + 1, yycolumn + 1); }
    "ARRAY"         { return Keyword.typeArray(yyline + 1, yycolumn + 1); }
    "OF"            { return Keyword.symOf(yyline + 1, yycolumn + 1); }

    "DO"            { return Keyword.symDo(yyline + 1, yycolumn + 1); }
    "WHILE"         { return Keyword.symWhile(yyline + 1, yycolumn + 1); }
    "IF"            { return Keyword.symIf(yyline + 1, yycolumn + 1); }
    "ELSE"          { return Keyword.symElse(yyline + 1, yycolumn + 1); }
    "ELSIF"         { return Keyword.symElif(yyline + 1, yycolumn + 1); }
    "THEN"          { return Keyword.symThen(yyline + 1, yycolumn + 1); }

    "CONST"         { return Keyword.symConst(yyline + 1, yycolumn + 1); }
    "VAR"           { return Keyword.symVar(yyline + 1, yycolumn + 1); }
    "TYPE"          { return Keyword.symType(yyline + 1, yycolumn + 1); }

    "MODULE"        { return Keyword.symModule(yyline + 1, yycolumn + 1); }
    "PROCEDURE"     { return Keyword.symProcedure(yyline + 1, yycolumn + 1); }
    "BEGIN"         { return Keyword.symBegin(yyline + 1, yycolumn + 1); }
    "END"           { return Keyword.symEnd(yyline + 1, yycolumn + 1); }

    "DIV"           { return Operator.div(yyline + 1, yycolumn + 1); }
    "MOD"           { return Operator.mod(yyline + 1, yycolumn + 1); }
    "OR"            { return Operator.or(yyline + 1, yycolumn + 1); }

    "READ"		    { return Keyword.symRead(yyline + 1, yycolumn + 1); }
    "WRITE"		    { return Keyword.symWrite(yyline + 1, yycolumn + 1); }
    "WRITELN"		{ return Keyword.symWriteln(yyline + 1, yycolumn + 1); }
}

/* Other tokens*/

<YYINITIAL> {
    {Whitespace}    { /* Skip white spaces */ }

    {Comment}       { /* Skip comments */ }

    {MyInteger}       {
                        /* Parse integer */

                        // Validate integer
                        if (!yytext().matches("[0-9]+")) {
                            throw new LexicalException("Invalid integer");
                        }

                        // Check integer length
                        if (yytext().length() > 12) {
                            throw new IllegalIntegerRangeException("Too long integer");
                        }

                        // Parse integer value
                        if (yytext().startsWith("0")) {
                            // Parse octal integer
                            try {
                                return new MyInteger(yyline + 1, yycolumn + 1, Integer.parseInt(yytext(), 8));
                            } catch (NumberFormatException e) {
                                throw new IllegalOctalException("Invalid octal integer");
                            }
                        } else {
                            // Parse decimal integer
                            try {
                                return new MyInteger(yyline + 1, yycolumn + 1, Integer.parseInt(yytext()));
                            } catch (NumberFormatException e) {
                                throw new IllegalIntegerException("Invalid decimal integer");
                            }
                        }
                    }
    
    {Identifier}    { 
                        /* Parse Identifier */

                        if (yylength() > 24) {
							throw new IllegalIdentifierLengthException("Too long identifier");
                        }

                        return new Identifier(yyline + 1, yycolumn + 1, yytext()); 
                    }

    ":="            { return Operator.assign(yyline + 1, yycolumn + 1); }
    
    "+"             { return Operator.plus(yyline + 1, yycolumn + 1); }
    "-"             { return Operator.minus(yyline + 1, yycolumn + 1); }
    "*"             { return Operator.mult(yyline + 1, yycolumn + 1); }
    
    "&"             { return Operator.and(yyline + 1, yycolumn + 1); }
    "~"             { return Operator.not(yyline + 1, yycolumn + 1); }
    
    "="             { return Operator.eq(yyline + 1, yycolumn + 1); }
    "#"             { return Operator.neq(yyline + 1, yycolumn + 1); }

    "<"             { return Operator.lt(yyline + 1, yycolumn + 1); }
    "<="            { return Operator.lteq(yyline + 1, yycolumn + 1); }
    ">"             { return Operator.gt(yyline + 1, yycolumn + 1); }
    ">="            { return Operator.gteq(yyline + 1, yycolumn + 1); }

    "("             { return Symbol.lpar(yyline + 1, yycolumn + 1); }
    ")"             { return Symbol.rpar(yyline + 1, yycolumn + 1); }
    "["             { return Symbol.lbrack(yyline + 1, yycolumn + 1); }
    "]"             { return Symbol.rbrack(yyline + 1, yycolumn + 1); }
    ":"             { return Symbol.colon(yyline + 1, yycolumn + 1); }
    ";"             { return Symbol.semicol(yyline + 1, yycolumn + 1); }
    ","             { return Symbol.comma(yyline + 1, yycolumn + 1); }
    "."             { return Symbol.dot(yyline + 1, yycolumn + 1); }

    {IllegalComment}    {throw new MismatchedCommentException("Open comment error");}
    {IllegalOctal}      {throw new IllegalOctalException("Invalid octal integer");}
	{IllegalInteger}    {throw new IllegalIntegerException("Invalid decimal integer");}
}

/* Error fallback */

[^]                 {
                        /* Unknown symbol, read one character */
                        throw new IllegalSymbolException("Unknown symbol");
                    }
