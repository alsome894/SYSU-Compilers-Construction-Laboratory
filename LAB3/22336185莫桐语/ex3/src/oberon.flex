import java.io.*;
import exceptions.*;
import java_cup.runtime.*;
import java.lang.Integer;

%%

%public

%class OberonScanner
%type java_cup.runtime.Symbol
%yylexthrow LexicalException

%cupsym Symbol
%cup
%line
%column
%ignorecase

%eofval{
	return symbol(Symbol.EOF);
%eofval}

%{
  int getLine(){	return yyline;}
  int getColumn(){	return yycolumn;}

StringBuffer string = new StringBuffer();
  private java_cup.runtime.Symbol symbol(int type) {
    return new java_cup.runtime.Symbol(type, yyline, yycolumn);
  }
  private java_cup.runtime.Symbol symbol(int type, Object value) {
    return new java_cup.runtime.Symbol(type, yyline, yycolumn, value);
  }
%}

/* Regular definitions */

MyInteger = 0[0-7]*  |  [1-9][0-9]*
Identifier = [:jletter:][:jletterdigit:]*
WhiteSpace = " " | \r | \n | \r\n | [ \t\f]
Comment = "(*" ~ "*)"

/* Exception definitions */

IllegalComment = "(*" ([^\*] | "*"+[^\)])* | ([^\(]|"("+[^\*])* "*)"
IllegalOctal = 0[0-7]* [8|9|"."]+ [0-9]*
IllegalInteger = {MyInteger} + {Identifier}+

%%

/* Reservedwords/Keywords */

<YYINITIAL> {

  "MODULE"                       { return symbol(Symbol.MODULE); }
  "BEGIN"                        { return symbol(Symbol.BEGIN); }
  "END"                          { return symbol(Symbol.END); }
  "CONST"                        { return symbol(Symbol.CONST); }
  "TYPE"                         { return symbol(Symbol.TYPE); }
  "VAR"                          { return symbol(Symbol.VAR); }
  "PROCEDURE"                    { return symbol(Symbol.PROCEDURE); }
  "RECORD"                       { return symbol(Symbol.RECORD); }
  "ARRAY"                        { return symbol(Symbol.ARRAY); }
  "OF"                           { return symbol(Symbol.OF); }
  "WHILE"                        { return symbol(Symbol.WHILE); }
  "DO"                           { return symbol(Symbol.DO); }
  "IF"                           { return symbol(Symbol.IF); }
  "ELSE"                         { return symbol(Symbol.ELSE); }
  "ELSIF"                        { return symbol(Symbol.ELSIF); }
  "THEN"                         { return symbol(Symbol.THEN); }
  "BOOLEAN"                      { return symbol(Symbol.BOOLEAN); }
  "INTEGER"                      { return symbol(Symbol.INTEGER); }
  "TRUE"                         { return symbol(Symbol.TRUE); }
  "FALSE"                        { return symbol(Symbol.FALSE); }

  "DIV"                          { return symbol(Symbol.DIV); }
  "MOD"                          { return symbol(Symbol.MOD); }
  "OR"                           { return symbol(Symbol.OR); }

  "READ"		                     { return symbol(Symbol.READ);}
  "WRITE"		                     { return symbol(Symbol.WRITE);}
  "WRITELN"		                   { return symbol(Symbol.WRITELN);}

}

/* Other tokens*/

<YYINITIAL> {
  {WhiteSpace}                   { /* Skip white spaces */ }

  {Comment}                      { /* Skip comments */ }

  {MyInteger}                    {
                                  if(yylength() > 12) throw new IllegalIntegerRangeException();
                                  else return  symbol(Symbol.IDENTIFIER,yytext());
                                 }

  {Identifier}                   {
                                  if(yylength() > 24) throw new IllegalIdentifierLengthException();
                                  else return  symbol(Symbol.IDENTIFIER,yytext());
                                 }

  ";"                            { return symbol(Symbol.SEMICOLON); }
  "."                            { return symbol(Symbol.POINT); }
  ":"                            { return symbol(Symbol.COLON); }
  "("                            { return symbol(Symbol.LeftParenthesis); }
  ")"                            { return symbol(Symbol.RightParenthesis); }
  "["                            { return symbol(Symbol.LeftBracket); }
  "]"                            { return symbol(Symbol.RightBracket); }
  ","                            { return symbol(Symbol.COMMA); }
  "="                            { return symbol(Symbol.EQUAL); }
  ":="                           { return symbol(Symbol.ASSIGNMENT); }
  ">"                            { return symbol(Symbol.GreatThan); }
  "<"                            { return symbol(Symbol.LessThan); }
  "~"                            { return symbol(Symbol.NOT); }
  "<="                           { return symbol(Symbol.LessThanOrEqual); }
  ">="                           { return symbol(Symbol.GreatThanOrEqual); }
  "#"                            { return symbol(Symbol.NOTEQUAL); }
  "&"                            { return symbol(Symbol.AND); }
  "+"                            { return symbol(Symbol.PLUS); }
  "-"                            { return symbol(Symbol.MINUS); }
  "*"                            { return symbol(Symbol.TIMES ); }

/* Error fallback */

  {IllegalOctal}                 { throw new IllegalOctalException(); }
  {IllegalInteger}               { throw new IllegalIntegerException(); }
  {IllegalComment}               { throw new MismatchedCommentException(); }
  .                              { throw new IllegalSymbolException(); }
}