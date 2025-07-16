import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class TaxCalculatorTest {

    /**
     * 测试 calculateTax 方法：
     * 假设起征点为 5000，税率表为：
     * 级数1：0-3000（税率 3%）
     * 级数2：3000-12000（税率 10%）
     * 当应纳税所得额为 10000（即总收入-5000）时：
     * 第一段：3000 * 0.03 = 90
     * 剩余7000在第二段：7000 * 0.10 = 700
     * 总税额应为：90 + 700 = 790
     */
    @Test
    public void testCalculateTax() {
        List<TaxBracket> brackets = new ArrayList<>();
        brackets.add(new TaxBracket(3000.0, 0.03));
        brackets.add(new TaxBracket(12000.0, 0.10));
        brackets.add(new TaxBracket(25000.0, 0.20));
        brackets.add(new TaxBracket(35000.0, 0.25));
        brackets.add(new TaxBracket(55000.0, 0.30));
        brackets.add(new TaxBracket(80000.0, 0.35));
        brackets.add(new TaxBracket(null, 0.45));

        TaxCalculator calculator = new TaxCalculator(5000, brackets);
        double taxableIncome = 10000;  // 模拟应纳税所得额（总收入 - 起征点）
        double tax = calculator.calculateTax(taxableIncome);
        assertEquals(790, tax, 0.01);
    }
}
