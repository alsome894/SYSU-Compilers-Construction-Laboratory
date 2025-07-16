import java.io.*;
import java.util.Random;

public class DataGenerator {
    public static void main(String[] args) throws IOException {
        int[] lengths = {100, 1_000, 10_000, 50_000, 100_000, 500_000, 1000_000}; // 测试数据长度
        for (int len : lengths) {
            generateExpression(len);
        }
    }

    private static void generateExpression(int length) throws IOException {
        try (FileWriter fw = new FileWriter("test_"+length+".txt")) {
            Random rand = new Random();
            boolean needDigit = true;
            
            for (int i = 0; i < length; ) {
                if (needDigit) {
                    fw.write(rand.nextInt(10) + '0');
                    i++;
                    needDigit = false;
                } else {
                    fw.write(rand.nextBoolean() ? '+' : '-');
                    i++;
                    needDigit = true;
                }
            }
            fw.write(rand.nextInt(10) + '0');
        }
    }
}