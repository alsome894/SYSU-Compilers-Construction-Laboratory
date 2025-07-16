import java.io.*;

public class Benchmark {
    public static void main(String[] args) throws IOException {
        int[] testSizes = {100, 1_000, 10_000, 50_000, 100_000, 500_000, 1_000_000};
        
        System.out.println("数据长度\t递归版(ms)\t循环版(ms)");
        for (int size : testSizes) {
            // 准备测试数据
            String filename = "test_" + size + ".txt";
            FileInputStream input = new FileInputStream(filename);
            
            // 测试递归版本
            long start = System.currentTimeMillis();
            new RecursiveParser(input).parse();
            long recursiveTime = System.currentTimeMillis() - start;
            
            // 重置输入流
            input = new FileInputStream(filename);
            
            // 测试循环版本
            start = System.currentTimeMillis();
            new LoopParser(input).parse();
            long loopTime = System.currentTimeMillis() - start;
            
            System.out.printf("%d\t\t%d\t\t%d\n", size, recursiveTime, loopTime);
        }
    }
}