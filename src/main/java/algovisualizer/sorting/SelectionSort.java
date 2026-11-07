package algovisualizer.sorting;

public class SelectionSort implements SortingAlgorithm{
    @Override
    public void sort(int[] array, SortObserver observer){
        for(int i = 0; i < array.length - 1; i++){
            int minIndex = i;

            for(int j = i + 1; j < array.length; j++){
                observer.onCompare(j, minIndex);

                if(array[j] < array[minIndex]){
                    minIndex = j;
                }
            }

            if(minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;

                observer.onSwap(i, minIndex);
            }
        }
    }
}
