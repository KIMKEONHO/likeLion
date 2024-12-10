package day07;

public class StringTest2 {

    public static void main(String[] args) {
        String greeting = "Hello World";
        System.out.println(greeting.length());

        char firstChar = greeting.charAt(0);
        System.out.println(firstChar);

    }

    public boolean find(String data, char findChar){
        for(int i = 0; i < data.length(); i++){
            if(data.charAt(i) == findChar){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
}
