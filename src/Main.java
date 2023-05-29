import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[] array1 = new int[9999];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = random.nextInt(100);
        }

        long start1 = System.currentTimeMillis();
        System.out.print("Sort by merge takes: ");
        sortByMerge(array1, 0, array1.length - 1);
        System.out.println(System.currentTimeMillis() - start1);

        int[] array2 = Arrays.copyOf(array1, array1.length);
        long start2 = System.currentTimeMillis();
        System.out.print("Sort by bubble takes: ");
        sortBubble(array2);
        System.out.println(System.currentTimeMillis() - start2);

        int[] array3 = Arrays.copyOf(array1, array1.length);
        long start3 = System.currentTimeMillis();
        System.out.print("Sort by insertion takes: ");
        sortInsertion(array3);
        System.out.println(System.currentTimeMillis() - start3);

        int[] array4 = Arrays.copyOf(array1, array1.length);
        long start4 = System.currentTimeMillis();
        System.out.print("Sort by selection takes: ");
        sortSelection(array4);
        System.out.println(System.currentTimeMillis() - start4);

    }
    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }
    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }
    public static void sortByMerge(int[] arrayInteger, int indexStart, int indexEnd) {
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