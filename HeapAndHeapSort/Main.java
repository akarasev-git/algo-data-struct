package HeapAndHeapSort;

import java.util.ArrayList;
import java.util.Arrays;

class Main{
    public static void main(String args[]){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(44, 12, 88, 32, 67, 48, 100, 0, -200, 666, 10000000));
        System.out.println(list);
        Heap.heapSort (list);

        System.out.println(list);
    }
}