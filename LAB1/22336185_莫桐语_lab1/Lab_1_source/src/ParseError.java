import java.util.*;

/**
 * 表示解析过程中出现的语法错误，记录错误位置、描述和类型信息。
 * <p>
 * 本类用于在语法分析过程中收集错误信息，支持多错误记录机制。</p>
 */
public class ParseError {
    /**
     * 错误发生的位置1-based索引，面向用户可读的位置编号
     */
    public final int position;
    
    /**
     * 可读的错误描述信息
     */
    public final String message;
    
    /**
     * 错误分类标识
     */
    public final String errorType;

    /**
     * 构造新的错误记录对象
     * @param pos 错误在输入字符串中的位置1-based
     * @param msg 具体错误描述
     * @param type 错误类型标识
     * @throws IllegalArgumentException 当位置值小于1时抛出
     */
    public ParseError(int pos, String msg, String type) {
        if (pos < 1) throw new IllegalArgumentException("Position must be 1-based");
        this.position = pos;
        this.message = Objects.requireNonNull(msg);
        this.errorType = Objects.requireNonNull(type);
    }

    /**
     * 生成标准化的错误信息字符串
     * @return 格式化字符串，包含位置、类型和描述信息
     */
    @Override
    public String toString() {
        return String.format("Error at position %d: [%s] %s", 
                           position, errorType, message);
    }
}