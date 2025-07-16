/**
 * TaxBracket 类用于表示单个税率级别。
 * 每个 TaxBracket 包含一个应纳税所得额上限和对应的税率。
 * 当 upperBound 为 null 时，表示该级别无上限。
 */
public class TaxBracket {
    /**
     * 应纳税所得额上限，null 表示无限上限。
     */
    private Double upperBound;

    /**
     * 税率，取值范围为 0 到 1 之间（例如 3% 对应 0.03）。
     */
    private double rate;

    /**
     * 构造一个 TaxBracket 对象。
     * 
     * @param upperBound 应纳税所得额上限（null 表示无上限）
     * @param rate       税率（0 到 1 之间的数值）
     */
    public TaxBracket(Double upperBound, double rate) {
        this.upperBound = upperBound;
        this.rate = rate;
    }

    /**
     * 获取应纳税所得额上限。
     * 
     * @return 上限值，如果为 null 则表示无限上限
     */
    public Double getUpperBound() {
        return upperBound;
    }

    /**
     * 设置应纳税所得额上限。
     * 
     * @param upperBound 新的上限值（null 表示无上限）
     */
    public void setUpperBound(Double upperBound) {
        this.upperBound = upperBound;
    }

    /**
     * 获取税率。
     * 
     * @return 税率，值在 0 到 1 之间
     */
    public double getRate() {
        return rate;
    }

    /**
     * 设置税率。
     * 
     * @param rate 新的税率，值应在 0 到 1 之间
     */
    public void setRate(double rate) {
        this.rate = rate;
    }
}
