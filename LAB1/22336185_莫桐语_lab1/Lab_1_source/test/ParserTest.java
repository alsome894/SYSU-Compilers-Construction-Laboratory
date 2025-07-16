import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class ParserTest {

    @Test
    public void testValidSingleDigit() {
        Parser parser = new Parser("1");
        parser.parse();
        assertEquals("1", parser.getOutput());
        assertTrue(parser.getErrors().isEmpty());
    }

    @Test
    public void testValidExpression() {
        Parser parser = new Parser("9-5+2");
        parser.parse();
        assertEquals("95-2+", parser.getOutput());
        assertTrue(parser.getErrors().isEmpty());
    }

    @Test
    public void testMissingOperand() {
        Parser parser = new Parser("1+");
        parser.parse();
        List<ParseError> errors = parser.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Missing operand after operator '+'", errors.get(0).message);
    }

    @Test
    public void testUnexpectedOperator() {
        Parser parser = new Parser("+12");
        parser.parse();
        List<ParseError> errors = parser.getErrors();
        assertTrue(errors.size() >= 1);
        assertEquals("Missing digit before operator '+'", errors.get(0).message);
    }

    @Test
    public void testMultipleErrors() {
        Parser parser = new Parser("1++2-");
        parser.parse();
        List<ParseError> errors = parser.getErrors();
        assertEquals(2, errors.size());
        assertEquals("Missing digit before operator '+'", errors.get(0).message);
        assertEquals("Missing operand after operator '-'", errors.get(1).message);
    }

    @Test
    public void testWhitespaceHandling() {
        Parser parser = new Parser("1 + 2 ");
        parser.parse();
        assertEquals("12+", parser.getOutput());
        assertEquals(3, parser.getErrors().size()); // 检测到3个空格
    }
}