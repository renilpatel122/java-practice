import java.util.Arrays;

public class MoutainArrCode {
//    public static int findMoutainArrIndex(int target, MountainArr mountainArr) {
//
//        int start = 0;
//        int end = mountainArr.length() - 1;
//
//        return mountainArr.get(target);
//    }
//    static int binarySearch(int start, int end, int target, MountainArr mountainArr) {
//        while(start < end) {
//            int mid = start + (end - start) / 2;
//            if (mountainArr.get(mid) == target) {
//                return mid;
//            }
//            if (mountainArr.get(mid) < target) {
//                start = mid-1;
//            } else if (mountainArr.get(mid) > target){
//                end = mid + 1;
//            } else {
//                return mid;
//            }
//        }
//        return -1;
//    }

    static int pivotIndexElement(int[] nums) {

        int start = 0;
        int end = nums.length - 1;
        while(start < end) {
            int mid = start + (end - start) / 2;
            if (nums[start] < nums[end])  {
                return end;
            } else {
                return 0;
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        int pivotIndex = pivotIndexElement(arr);
        System.out.println(pivotIndex);
    }
}

//interface MountainArr {
//
//    int get(int index);
//    int length();
//}
//
//class ABC implements MountainArr {
//
//    int[] arr = {1,2,3,4,5,6,5,4,1};
//
//    @Override
//    public int get(int index) {
//        return this.arr[index];
//    }
//
//    @Override
//    public int length() {
//        return this.arr.length;
//    }
//}