import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Random random = new Random();
        int[] array1 = new int[99];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = random.nextInt(100);
        }


//        sortByMerge(mergeSort, 0, mergeSort.length - 1);
//        System.out.println(System.currentTimeMillis() - start);

//        int[] array2 = Arrays.copyOf(array1, array1.length);
//        long start = System.currentTimeMillis();
//        sortBubble(array2);
//        System.out.println(System.currentTimeMillis() - start);


    }
//    private static void swapElements(int[] arr, int indexA, int indexB) {
//        int tmp = arr[indexA];
//        arr[indexA] = arr[indexB];
//        arr[indexB] = tmp;
//    }
//    public static void sortBubble(int[] arr) {
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = 0; j < arr.length - 1 - i; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    swapElements(arr, j, j + 1);
//                }
//            }
//        }
//    }
    public static void sortByMerge(Integer[] arrayInteger, int indexStart, int indexEnd) {
        if (indexEnd - indexStart >= 1) {
            int middle = (indexStart + indexEnd) / 2;
            sortByMerge(arrayInteger, indexStart, middle);
            sortByMerge(arrayInteger, middle + 1, indexEnd);

            int[] resultingArray = new int[indexEnd - indexStart + 1];
            int leftIndex = indexStart;
            int rightIndex = middle + 1;

            for (int i = indexStart; i <= indexEnd; i++) {
                if (leftIndex > middle || (rightIndex <= indexEnd && arrayInteger[leftIndex] > arrayInteger[rightIndex])) {
                    resultingArray[i - indexStart] = arrayInteger[rightIndex];
                    rightIndex++;
                } else {
                    resultingArray[i - indexStart] = arrayInteger[leftIndex];
                    leftIndex++;
                }
            }
            for (int i = indexStart; i <= indexEnd; i++) {
                arrayInteger[i] = resultingArray[i - indexStart];
            }
        }
    }
}