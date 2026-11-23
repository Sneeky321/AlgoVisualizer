package algovisualizer.searching;

public class BinarySearch implements SearchAlgorithm{

    @Override
    public int search(int[] array, int target, SearchObserver observer) {

        int left = 0;
        int right = array.length - 1;

        while(left <= right){
            int middle = left + (right - left) / 2;

            observer.onCheck(middle);

            if(array[middle] == target){
                observer.onFound(middle);
                observer.onFinish();
                return middle;
            }

            if(array[middle] < target){
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        observer.onFinish();
        return -1;
    }
}
