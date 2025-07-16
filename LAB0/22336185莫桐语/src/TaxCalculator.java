import java.util.List;

/**
 * TaxCalculator 类用于计算个人所得税。
 * 该类通过设置一个起征点和一个税率表来计算超额累进税制下应缴的税款。
 */
public class TaxCalculator {
    /**
     * 个人所得税的起征点。
     */
    private double threshold;
    /**
     * 税率表，包含多个 TaxBracket 对象，必须按应纳税所得额上限升序排列。
     */
    private List<TaxBracket> taxBrackets;

    /**
     * 构造一个 TaxCalculator 对象。
     * 
     * @param threshold   个人所得税起征点
     * @param taxBrackets 税率表，要求上限按从小到大排序
     */
    public TaxCalculator(double threshold, List<TaxBracket> taxBrackets) {
        this.threshold = threshold;
        this.taxBrackets = taxBrackets;
    }

    /**
     * 获取个人所得税的起征点。
     * 
     * @return 当前起征点
     */
    public double getThreshold() {
        return threshold;
    }

    /**
     * 设置个人所得税的起征点。
     * 
     * @param threshold 新的起征点
     */
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    /**
     * 获取税率表。
     * 
     * @return 包含多个 TaxBracket 的列表
     */
    public List<TaxBracket> getTaxBrackets() {
        return taxBrackets;
    }

    /**
     * 设置税率表。
     * 
     * @param taxBrackets 新的税率表列表，要求上限按从小到大排序
     */
    public void setTaxBrackets(List<TaxBracket> taxBrackets) {
        this.taxBrackets = taxBrackets;
    }

    /**
     * 计算应缴纳的个人所得税。
     * 根据传入的应纳税所得额（总收入减去起征点），使用超额累进算法计算税款。
     * 
     * @param taxableIncome 应纳税所得额
     * @return 应缴纳的税款金额
     * @throws IllegalStateException 如果税率表上限顺序错误
     */
    public double calculateTax(double taxableIncome) {
        double tax = 0.0;
        double remaining = taxableIncome;
        double prevUpperBound = 0.0;

        for (TaxBracket bracket : taxBrackets) {
            if (remaining <= 0)
                break;

            Double currentUpperBound = bracket.getUpperBound();
            double rate = bracket.getRate();
            double bracketSize;

            if (currentUpperBound == null) {
                // 最后一级无上限，直接计算剩余部分
                bracketSize = remaining;
            } else {
                bracketSize = currentUpperBound - prevUpperBound;
                if (bracketSize < 0) {
                    throw new IllegalStateException("税率表的上限顺序错误。");
                }
                bracketSize = Math.min(bracketSize, remaining);
            }

            tax += bracketSize * rate;
            remaining -= bracketSize;

            if (currentUpperBound != null) {
                prevUpperBound = currentUpperBound;
            }
        }
        return tax;
    }
}
