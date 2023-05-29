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
        validateSize();
        validateItem(item);
        arrayInteger[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item){
        validateItem(item);
        validateIndex(index);
        validateSize();

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
        return indexOf(item) != -1;
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
    private void validateSize () {
        if (size >= arrayInteger.length) {
            throw new StorageIsFullException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }

}
