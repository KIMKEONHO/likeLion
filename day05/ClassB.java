package day05;

public class ClassB {
    public static int sField;
    public int iField;

    public static void sMethode(){
        System.out.println(sField);
        //System.out.println(iField);
    }
    public void iMethod(){
        System.out.println(sField);
        System.out.println(iField);
    }
}
