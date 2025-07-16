import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Main 类是个人所得税计算器的入口程序。
 * 提供用户交互界面，允许计算税款、调整起征点以及修改税率表。
 */
public class Main {
    /**
     * 程序入口。
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 初始化计算器，起征点为 5000，使用默认税率表
        TaxCalculator calculator = new TaxCalculator(5000, getDefaultTaxBrackets());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = readIntInput(scanner, "请选择（1-4）：");
            switch (choice) {
                case 1:
                    calculateTax(scanner, calculator);
                    break;
                case 2:
                    adjustThreshold(scanner, calculator);
                    break;
                case 3:
                    adjustTaxBrackets(scanner, calculator);
                    break;
                case 4:
                    exitProgram(scanner);
                    break;
                default:
                    System.out.println("无效的选项，请重新输入。");
            }
        }
    }

    /**
     * 打印主菜单。
     */
    private static void printMenu() {
        System.out.println("\n欢迎使用个人所得税计算器：");
        System.out.println("1. 计算个人所得税");
        System.out.println("2. 调整起征点");
        System.out.println("3. 调整税率");
        System.out.println("4. 退出");
    }

    /**
     * 安全退出程序，并关闭 Scanner。
     * 
     * @param scanner Scanner 对象
     */
    private static void exitProgram(Scanner scanner) {
        System.out.println("退出程序。");
        scanner.close();
        System.exit(0);
    }

    /**
     * 获取默认税率表，基于中国 2018 年税率标准。
     * 
     * @return 包含多个 TaxBracket 对象的列表
     */
    private static List<TaxBracket> getDefaultTaxBrackets() {
        List<TaxBracket> brackets = new ArrayList<>();
        brackets.add(new TaxBracket(3000.0, 0.03));
        brackets.add(new TaxBracket(12000.0, 0.10));
        brackets.add(new TaxBracket(25000.0, 0.20));
        brackets.add(new TaxBracket(35000.0, 0.25));
        brackets.add(new TaxBracket(55000.0, 0.30));
        brackets.add(new TaxBracket(80000.0, 0.35));
        brackets.add(new TaxBracket(null, 0.45));
        return brackets;
    }

    /**
     * 根据用户输入计算税款，并输出结果。
     * 
     * @param scanner    Scanner 对象，用于读取用户输入
     * @param calculator TaxCalculator 对象
     */
    private static void calculateTax(Scanner scanner, TaxCalculator calculator) {
        System.out.print("请输入月薪总额：");
        double income = readDoubleInput(scanner, "");
        double taxableIncome = income - calculator.getThreshold();

        if (taxableIncome <= 0) {
            System.out.println("应纳税所得额小于等于0.00，个人所得税为0.00元。");
        } else {
            double tax = calculator.calculateTax(taxableIncome);
            System.out.printf("应缴纳的个人所得税为：%.2f元\n", tax);
        }
    }

    /**
     * 调整起征点。
     * 
     * @param scanner    Scanner 对象，用于读取用户输入
     * @param calculator TaxCalculator 对象
     */
    private static void adjustThreshold(Scanner scanner, TaxCalculator calculator) {
        System.out.print("请输入新的起征点：");
        double newThreshold = readDoubleInput(scanner, "");
        if (newThreshold < 0) {
            System.out.println("起征点不能为负数。");
            return;
        }
        calculator.setThreshold(newThreshold);
        System.out.println("起征点已更新为：" + newThreshold);
    }

    /**
     * 通过用户交互调整税率表中的各级税率和上限。
     * 
     * @param scanner    Scanner 对象
     * @param calculator TaxCalculator 对象
     */
    private static void adjustTaxBrackets(Scanner scanner, TaxCalculator calculator) {
        List<TaxBracket> taxBrackets = calculator.getTaxBrackets();
        while (true) {
            printCurrentTaxBrackets(taxBrackets);
            int level = readIntInput(scanner, "请输入要修改的级数（1-7），或输入0返回上级菜单：");
            if (level == 0)
                return;
            if (!validateTaxBracketLevel(level))
                continue;

            TaxBracket currentBracket = taxBrackets.get(level - 1);
            if (level == 7) {
                adjustTaxRateOnly(scanner, currentBracket);
            } else {
                adjustTaxBracket(scanner, taxBrackets, level, currentBracket);
            }
        }
    }

    /**
     * 打印当前税率表信息。
     * 
     * @param taxBrackets 税率表列表
     */
    private static void printCurrentTaxBrackets(List<TaxBracket> taxBrackets) {
        System.out.println("\n当前税率表：");
        for (int i = 0; i < taxBrackets.size(); i++) {
            TaxBracket bracket = taxBrackets.get(i);
            String upperBoundStr = (bracket.getUpperBound() == null) ? "无"
                    : String.format("%.2f元", bracket.getUpperBound());
            System.out.printf("%d. 应纳税所得额上限：%s，税率：%.2f%%\n", i + 1, upperBoundStr, bracket.getRate() * 100);
        }
    }

    /**
     * 校验用户输入的级数是否在 1 到 7 之间。
     * 
     * @param level 用户输入的级数
     * @return 合法返回 true，否则返回 false
     */
    private static boolean validateTaxBracketLevel(int level) {
        if (level < 1 || level > 7) {
            System.out.println("无效的级数。");
            return false;
        }
        return true;
    }

    /**
     * 仅修改最后一级税率的税率值。
     * 
     * @param scanner        Scanner 对象
     * @param currentBracket 最后一级的 TaxBracket 对象
     */
    private static void adjustTaxRateOnly(Scanner scanner, TaxBracket currentBracket) {
        System.out.print("请输入新的税率（当前为" + currentBracket.getRate() * 100 + "%）：");
        double newRate = readDoubleInput(scanner, "");
        if (newRate < 0 || newRate > 100) {
            System.out.println("税率必须在0-100之间。");
            return;
        }
        currentBracket.setRate(newRate / 100);
        System.out.println("税率已更新。");
    }

    /**
     * 修改非最后一级税率级别的信息，包括上限和税率。
     * 
     * @param scanner        Scanner 对象
     * @param taxBrackets    税率表列表
     * @param level          当前修改的级数
     * @param currentBracket 当前级的 TaxBracket 对象
     */
    private static void adjustTaxBracket(Scanner scanner, List<TaxBracket> taxBrackets, int level,
            TaxBracket currentBracket) {
        Double currentUpperBound = currentBracket.getUpperBound();
        double currentRate = currentBracket.getRate();
        System.out.print("请输入新的应纳税所得额上限（当前为" + currentUpperBound + "元，输入-1保持不变）：");
        double newUpperBoundInput = readDoubleInput(scanner, "");
        Double newUpperBound = (newUpperBoundInput == -1) ? currentUpperBound : newUpperBoundInput;
        System.out.print("请输入新的税率（当前为" + currentRate * 100 + "%），输入-1保持不变：");
        double newRateInput = readDoubleInput(scanner, "");
        double newRate = (newRateInput == -1) ? currentRate : newRateInput / 100;
        if (newUpperBound <= 0) {
            System.out.println("上限必须大于0。");
            return;
        }
        if (level > 1) {
            TaxBracket prevBracket = taxBrackets.get(level - 2);
            if (newUpperBound <= prevBracket.getUpperBound()) {
                System.out.println("新的上限必须大于前一级的上限" + prevBracket.getUpperBound());
                return;
            }
        }
        if (level < 7) {
            TaxBracket nextBracket = taxBrackets.get(level);
            Double nextUpperBound = nextBracket.getUpperBound();
            if (nextUpperBound != null && newUpperBound >= nextUpperBound) {
                System.out.println("新的上限必须小于后一级的上限" + nextUpperBound);
                return;
            }
        }
        if (newRate < 0 || newRate > 1) {
            System.out.println("税率必须在0-100%之间。");
            return;
        }
        currentBracket.setUpperBound(newUpperBound);
        currentBracket.setRate(newRate);
        System.out.println("级数" + level + "已更新。");
    }

    /**
     * 读取整数输入，带有异常处理。
     * 
     * @param scanner Scanner 对象
     * @param prompt  提示信息
     * @return 用户输入的整数
     */
    private static int readIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("请输入有效的数字。");
                scanner.next(); // 清除无效输入
            }
        }
    }

    /**
     * 读取浮点数输入，带有异常处理。
     * 
     * @param scanner Scanner 对象
     * @param prompt  提示信息
     * @return 用户输入的浮点数
     */
    private static double readDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("请输入有效的数字。");
                scanner.next(); // 清除无效输入
            }
        }
    }
}
