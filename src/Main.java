import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("№1 : \n" + essay(7, "hello my name is Bessie and this is my essay"));
        System.out.println("№2 -> " + split("((()))(())()()(()())"));
        System.out.println("№3 : \n" + toCamelCase("hello_edabit") + "\n" + toSnakeCase("helloEdabit"));
        System.out.println("№4 -> " + overTime(new float[]{13.25f, 15, 30, 1.5f}));
        System.out.println("№5 -> " + BMI("205 pounds", "73 inches"));
        System.out.println("№6 -> " + bugger(39));
        System.out.println("№7 -> " + toStarShorthand("77777geff"));
        System.out.println("№8 -> " + doesRhyme("Sam I am!", "Green eggs and hAm."));
        System.out.println("№9 -> " + trouble(451999277, 41177722899L));
        System.out.println("№10 -> " + countUniqueBooks("ZZABCDEF", 'Z'));

    }

    /** Функция принимающая строку и выводящая её в формате эссе **/
    public static StringBuilder essay(int len, String string) {
        StringBuilder stringBuilder = new StringBuilder();
        int countCh = len;
        for (String str : string.split(" ")) {
            if (str.length() <= countCh) {
                stringBuilder.append(str).append(" ");
                countCh = countCh - str.length();
            }
            else {
                stringBuilder.append("\n").append(str).append(" ");
                countCh = len - str.length();
            }
        }
        return stringBuilder;
    }

    /** Функция, группирующая строку в кластер скобок **/
    public static ArrayList<String> split(String string) {
        ArrayList<String> cluster = new ArrayList<>();
        int count = 0;
        StringBuilder build = new StringBuilder();
        for (char chr : string.toCharArray()) {
            if (chr == '(') count++;
            else if (chr == ')') count--;
            build.append(chr);
            if (count == 0 && chr == ')') {
                cluster.add(build.toString());
                build.setLength(0);
            }
        }
        return cluster;
    }

    /** Функция, преобразующая строку из формата snake_case в формат camelCase **/
    public static String toCamelCase(String string) {
        String[] strings = string.split("_");
        StringBuilder camelCase = new StringBuilder(strings[0]);
        for (int i = 1; i < strings.length; i++) {
            camelCase.append(strings[i].substring(0,1).toUpperCase());
            camelCase.append(strings[i].substring(1));
        }
        return camelCase.toString();
    }

    /** Функция преобразующая строку из формата camelCase в формат snake_case **/
    public static String toSnakeCase(String string) {
        StringBuilder snakeCase = new StringBuilder();
        for (char chr : string.toCharArray()) {
            if(Character.isUpperCase(chr)) {
                snakeCase.append('_');
                snakeCase.append(Character.toLowerCase(chr));
            }
            else snakeCase.append(chr);
        }
        return snakeCase.toString();
    }
    /** Функция высчитывающая сверхурочную работу и оплату, связанную с ней **/
    public static String overTime(float[] data) {
        float start = data[0];
        float finish = data[1];
        float hourlyPay = data[2];
        float ratio = data[3];
        float payment = ((int) start - start) * hourlyPay;

        for (float hour = start; hour < finish; hour++) {
            if (hour >= 17) payment += hourlyPay * ratio;
            else payment += hourlyPay;
        }

        return "$" + String.format("%.2f", payment);
    }

    /** Функция возвращающая индекс массы тела **/
    public static String BMI(String weight, String height) {
        double meaningWeight = Double.parseDouble(weight.split(" ")[0]);
        if (weight.split(" ")[1].equals("pounds")) meaningWeight *= 0.45;
        double meaningHeight = Double.parseDouble(height.split(" ")[0]);
        if (height.split(" ")[1].equals("inches")) meaningHeight *= 0.0254;

        double BMI = Math.ceil(meaningWeight / Math.pow(meaningHeight, 2));

        if (BMI < 18.5) return BMI + " Underweight";
        else if (BMI >= 18.5 && BMI < 25) return BMI + " Normal weight";
        else return BMI + " Overweight";
    }

    /** Функция возвращающая мультипликативное постоянство введённого числа **/
    public static int bugger(int number) {
        int count = 0;
        int newNum = number;
        while (newNum > 9) {
            int temp = 1;
            while (newNum > 0) {
                temp *= newNum % 10;
                newNum /= 10;
            }
            newNum = temp;
            count++;
        }
        return count;
    }

    /** Функция, которая преобразует строку в звездную стенографию **/
    public static String toStarShorthand(String string) {
        if(string.isEmpty()) return string;

        char[] chars = string.toCharArray();
        StringBuilder builder = new StringBuilder();

        for (char chr : chars) {
            if (builder.indexOf(chr+"") == -1) {
                int n = countChar(string, chr);
                if (n >= 2) {
                    builder.append(chr).append("*").append(n);
                }
                else  builder.append(chr);
            }
        }
        return builder.toString();
    }
    private static int countChar(String string, char chr){
        int count = 0;
        for (int i=0; i < string.length(); i++) {
            if (string.charAt(i) == chr) count++;
        }
        return count;
    }

    /** Функция, определяющая рифмуются ли две введённые строки **/
    public static boolean doesRhyme(String firstString, String secondString) {
        String first = firstString.split(" ")[firstString.split(" ").length -1];
        String second = secondString.split(" ")[secondString.split(" ").length -1];
        StringBuilder firthVowels = new StringBuilder();
        StringBuilder secondVowels = new StringBuilder();

        for (char chr : first.toCharArray()) {
            if ("aeyuio".contains((chr+"").toLowerCase())) firthVowels.append((chr+"").toLowerCase());
        }
        for (char chr : second.toCharArray()) {
            if ("aeyuio".contains((chr+"").toLowerCase())) secondVowels.append((chr+"").toLowerCase());
        }

        return firthVowels.compareTo(secondVowels) == 0;
    }

    /** Функция, определяющая повторяется ли число трижды в num1 и дважды в num2 **/
    public static boolean trouble(long num1, long num2) {
        String string1 = num1 + "";
        String string2 = num2 + "";

        for (char chr1 : string1.toCharArray()) {
            for (char chr2 : string2.toCharArray()) {
                if (chr1 == chr2 && countChar(string1, chr1) == 3 && countChar(string2, chr2) == 2) return true;
            }
        }
        return false;
    }

    /** Функция, возвращающая общее колличество уникальных символов между всеми парами концов "книг" **/
    public static int countUniqueBooks(String stringSequence, char bookEnd) {
        List<Character> list = new ArrayList<>();
        boolean flag = false;
        for (char chr : stringSequence.toCharArray()) {
            if (chr == bookEnd) {
                flag = !flag;
            }
            else if (flag) {
                if (!list.contains(chr)) {
                    list.add(chr);
                }
            }
        }
        return list.size();
    }
}