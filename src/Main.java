import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Random random = new Random();
        Integer[] mergeSort = new Integer[99];
        for (int i = 0; i < mergeSort.length; i++) {
            mergeSort[i] = random.nextInt(100);
        }

        long start = System.currentTimeMillis();
        sortByMerge(mergeSort, 0, mergeSort.length - 1);
        System.out.println(System.currentTimeMillis() - start);
    }

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