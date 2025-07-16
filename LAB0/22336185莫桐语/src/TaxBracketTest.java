import static org.junit.Assert.*;
import org.junit.Test;

public class TaxBracketTest {

    /**
     * 测试 TaxBracket 的 getter 和 setter 方法
     */
    @Test
    public void testGetterSetter() {
        // 创建对象，设置上限为5000，税率为10%
        TaxBracket bracket = new TaxBracket(5000.0, 0.10);
        assertEquals(5000.0, bracket.getUpperBound(), 0.001);
        assertEquals(0.10, bracket.getRate(), 0.001);
        
        // 修改上限和税率
        bracket.setUpperBound(6000.0);
        bracket.setRate(0.15);
        assertEquals(6000.0, bracket.getUpperBound(), 0.001);
        assertEquals(0.15, bracket.getRate(), 0.001);
    }
}
