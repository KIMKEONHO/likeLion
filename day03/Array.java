package day03;

public class Array {
    public static void main(String[] args) {

        // 고정 길이 배열 선언 및 할당
        int [] arr = new int[10];
        int arr2[] = new int[10];
        int []arr3[] = new int[10][10];
        int [][] arr4 = new int[10][10];
        int [] arr5 = {1,2,3,4,5,6,7,8,9};

        // 데이터 삽입
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
    }
}
