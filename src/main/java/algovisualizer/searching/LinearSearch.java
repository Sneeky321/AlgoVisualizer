package algovisualizer.searching;

import algovisualizer.sorting.SortObserver;

public class LinearSearch implements SearchAlgorithm{
    @Override
    public int search(int[] array, int target, SearchObserver observer){

        for(int i = 0; i < array.length; i++){
            observer.onCheck(i);

            if(array[i] == target){
                observer.onFound(i);
                observer.onFinish();
                return i;
            }
        }

        observer.onFinish();
        return -1;
    }
}
