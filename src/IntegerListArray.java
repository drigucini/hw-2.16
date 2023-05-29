import java.util.Arrays;

public class IntegerListArray implements IntegerList {
    private Integer[] arrayInteger;
    private int size;

    public IntegerListArray() {
        arrayInteger = new Integer[10];
    }
    public IntegerListArray(int size) {
        arrayInteger = new Integer[size];
    }

    @Override
    public Integer add(Integer item) {
        growIfNeeded();
        validateItem(item);
        grow();
        arrayInteger[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item){
        growIfNeeded();
        validateItem(item);
        validateIndex(index);

        if (index == size) {
            arrayInteger[size++] = item;
            return item;
        }
        System.arraycopy(arrayInteger, index, arrayInteger, index + 1, size - index);
        size++;
        arrayInteger[index] = item;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateItem(item);
        validateIndex(index);
        arrayInteger[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);

        int index = indexOf(item);

        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);

        Integer item = arrayInteger[index];

        if (index != size) {
            System.arraycopy(arrayInteger, index + 1, arrayInteger, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        validateItem(item);
        sort(arrayInteger);
        return binarySearch(arrayInteger, item);
    }

    @Override
    public int indexOf(Integer item) {
        validateItem(item);
        for (int i = 0; i < size; i++) {
            if (arrayInteger[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        validateItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (arrayInteger[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return arrayInteger[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(arrayInteger, size);
    }


    private void validateItem (Integer item ) {
        if (item == null) {
            throw new NullItemException();
        }
    }
    private void growIfNeeded() {
        if (size >= arrayInteger.length) {
            grow();
        }
    }

    private void grow() {
        Arrays.copyOf(arrayInteger, size + size / 2);
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }

    private void sort(Integer[] arrayInteger) {
        sortByMerge(arrayInteger, 0, arrayInteger.length - 1);
    }
    private static void sortByMerge(Integer[] arrayInteger, int indexStart, int indexEnd) {
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
    private static boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }


            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
