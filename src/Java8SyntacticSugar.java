import java.util.*;
import java.util.stream.Collectors;

public class Java8SyntacticSugar {
    private static final String EMPTY_STRING = "";

    public static void main(String[] args) {
        System.out.println("使用Java7:");
        List<String> stringList = Arrays.asList("abc", "", "xyz", "jqk", "", "mid", "length", "size");
        System.out.println("原字符串列表：" + stringList);
        long count = getCountEmptyStringUsingJava7(stringList);
        System.out.println("空字符串数量：" + count);
        count = getCountLength3UsingJava7(stringList);
        System.out.println("字符串长度为3数量：" + count);
        List<String> notEmptyList = deleteEmptyStringUsingJava7(stringList);
        System.out.println("删除空字符串后列表：" + notEmptyList);
        String mergedStr = getMergedStringUsingJava7(notEmptyList, ",");
        System.out.println("整理为字符串后：" + mergedStr);

        List<Integer> integerList = Arrays.asList(1, 3, 5, 12, 7, -7, 0);
        System.out.println("原数字列表：" + integerList);
        System.out.println("最大值：" + getMax(integerList));
        System.out.println("最小值：" + getMin(integerList));
        System.out.println("和：" + getSum(integerList));
        System.out.println("平均值：" + getAvg(integerList));

        System.out.println("随机数：");
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt());
        }

        System.out.println("使用Java8:");
        System.out.println("原字符串列表：" + stringList);
        count = stringList.stream().filter( i -> i.isEmpty()).count();
        System.out.println("空字符串数量：" + count);
        count = stringList.stream().filter( i -> i.length() == 3).count();
        System.out.println("长度为3字符串数量：" + count);
        notEmptyList = stringList.stream().filter( i -> !i.isEmpty()).collect(Collectors.toList());
        System.out.println("删除空字符串后列表：" + notEmptyList);
        mergedStr = stringList.stream().filter( i -> !i.isEmpty()).collect(Collectors.joining(","));
        System.out.println("整理为字符串后" + mergedStr);

        System.out.println("原数字列表:" + integerList);
        List<Integer> squaresList = integerList.stream().map( i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("值平方后列表：" + squaresList);

        IntSummaryStatistics stat = integerList.stream().mapToInt( (x) -> x).summaryStatistics();

        System.out.println("最大值：" + stat.getMax());
        System.out.println("最小值：" + stat.getMin());
        System.out.println("和：" + stat.getSum());
        System.out.println("平均值：" + stat.getAverage());
        System.out.println("随机数排序后：");
        random.ints().limit(10).sorted().forEach(System.out::println);

        // 并行处理
        count = stringList.parallelStream().filter( i -> i.isEmpty()).count();
        System.out.println("空字符串数量：" + count);

    }

    public static int getCountEmptyStringUsingJava7(List<String> stringList) {
        int count = 0;
        for (String item : stringList) {
            if (EMPTY_STRING.equals(item)) count++;
        }
        return count;
    }

    public static int getCountLength3UsingJava7(List<String> stringList) {
        int count = 0;
        for (String item : stringList) {
            if (item.length() == 3) count++;
        }
        return count;
    }

    public static List<String> deleteEmptyStringUsingJava7(List<String> stringList) {
        List<String> notEmptyList = new ArrayList<>();
        for (String item : stringList) {
            if (!EMPTY_STRING.equals(item)) notEmptyList.add(item);
        }
        return notEmptyList;
    }

    public static String getMergedStringUsingJava7(List<String> stringList, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : stringList) {
            stringBuilder.append(item);
            stringBuilder.append(separator);
        }
        String rst = stringBuilder.toString();
        return rst.substring(0, rst.length() - 1);
    }


    public static List<Integer> getSquares(List<Integer> integerList) {
        List<Integer> rstList = new ArrayList<>();
        for (Integer item : integerList) {
            int rst = item.intValue() * item.intValue();
            if (!rstList.contains(rst)) {
                rstList.add(rst);
            }
        }
        return rstList;
    }

    public static int getMax(List<Integer> integerList) {
        int max = 0;
        for (Integer item : integerList) {
            if (item.intValue() > max) max = item.intValue();
        }
        return max;
    }

    public static int getMin(List<Integer> integerList) {
        int min = integerList.get(0);
        for (int i = 1; i < integerList.size(); i++) {
            if (min > integerList.get(i).intValue()) min = integerList.get(i).intValue();
        }
        return min;
    }

    public static int getSum(List integerList) {
        int sum = (int) integerList.get(0);
        for (int i = 1; i < integerList.size(); i++) {
            sum += (int)integerList.get(i);
        }
        return sum;
    }

    public static int getAvg(List integerList) {
        return getSum(integerList) / integerList.size();
    }
}
