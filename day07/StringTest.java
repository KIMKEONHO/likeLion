package day07;

public class StringTest {
    public static void main(String[] args) {

        String str1 = "asd";
        String str2 = new String("asd");
        String str3 = new String("asd");
        String str4 = "asd";

        System.out.println(str1 == str4);
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str2 == str4);
    }

}
