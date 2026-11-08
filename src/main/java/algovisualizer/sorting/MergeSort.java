package algovisualizer.sorting;

public class MergeSort implements SortingAlgorithm{
    public void sort(int[] array, SortObserver observer){
        mergeSort(array, 0, array.length - 1, observer);
    }

    private void merge(int[] array, int left, int middle, int right, SortObserver observer){
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for(int i = 0; i < n1; i++){
            leftArray[i] = array[left + i];
        }

        for(int j = 0; j < n2; j++){
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while(i < n1 && j < n2){
            observer.onCompare(left + i, middle + 1 + j);

            if(leftArray[i] <= rightArray[j]){
                array[k] = leftArray[i];
                observer.onWrite(k, leftArray[i]);
                i++;
            } else{
                array[k] = rightArray[j];
                observer.onWrite(k, rightArray[j]);
                j++;
            }

            k++;
        }

        while(i < n1){
            array[k] = leftArray[i];
            observer.onWrite(k, leftArray[i]);
            i++;
            k++;
        }

        while(j < n2){
            array[k] = rightArray[j];
            observer.onWrite(k, rightArray[j]);
            j++;
            k++;
        }
    }

    private void mergeSort(int[] array, int left, int right, SortObserver observer){
        if(left < right){
            int middle = left + (right - left) / 2;

            mergeSort(array, left, middle, observer);
            mergeSort(array, middle + 1, right, observer);

            merge(array, left, middle, right, observer);
        }
    }
}
