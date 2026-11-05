package algovisualizer.sorting;

public class InsertionSort implements SortingAlgorithm {
    @Override
    public void sort(int[] array, SortObserver observer){
        for(int i = 1; i < array.length; i++){
            int key = array[i];
            int j = i - 1;

            observer.onSetKey(key);

            while(j >= 0){
                observer.onCompare(j, j + 1);

                if(array[j] > key){
                    array[j + 1] = array[j];
                    observer.onWrite(j + 1, array[j]);
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = key;
            observer.onWrite(j + 1, key);
        }
    }
}
