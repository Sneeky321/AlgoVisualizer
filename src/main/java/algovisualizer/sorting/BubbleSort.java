package algovisualizer.sorting;

public class BubbleSort {
    public void sort(int[] array, SortObserver observer){
        for(int i = 0; i < array.length - 1; i++){
            for(int j = 0; j < array.length - 1 - i; j++){

                observer.onCompare(j, j + 1);

                if(array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    observer.onSwap(j, j + 1);
                }
            }
        }
    }
}
