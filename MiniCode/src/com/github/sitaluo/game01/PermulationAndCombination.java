package com.github.sitaluo.game01;
import java.util.ArrayList;

/**
 * 对一个数组进行排列组合，结果存入一个集合中
 * 例如[1,2,3]，对应的组合有 123,132,213,231,312,321共6种
 */
import java.util.List;
public class PermulationAndCombination {
    static List<int[]> allSorts = new ArrayList<int[]>();
         
    public static void permutation(int[] nums, int start, int end) {
        if (start == end) { // 当只要求对数组中一个数字进行全排列时，只要就按该数组输出即可
            int[] newNums = new int[nums.length]; // 为新的排列创建一个数组容器
            for (int i=0; i<=end; i++) {
                newNums[i] = nums[i];
            }
             allSorts.add(newNums); // 将新的排列组合存放起来
        } else {
            for (int i=start; i<=end; i++) {
                int temp = nums[start]; // 交换数组第一个元素与后续的元素
                nums[start] = nums[i];
                nums[i] = temp;
                permutation(nums, start + 1, end); // 后续元素递归全排列
                nums[i] = nums[start]; // 将交换后的数组还原
                nums[start] = temp;
            }
        }
    }
         
    public static int[][] getAllArray(int[] numArray){
    	permutation(numArray, 0, numArray.length - 1);
        int[][] a = new int[allSorts.size()][]; 
        allSorts.toArray(a);
        return a;
    }
    public static void main(String[] args) {
        int[] numArray = {1, 2, 3, 4, 5, 6};
        permutation(numArray, 0, numArray.length - 1);
        int[][] a = new int[allSorts.size()][]; // 你要的二维数组a
        allSorts.toArray(a);
             
        // 打印验证
        for (int i=0; i<a.length; i++) {
            int[] nums = a[i];
            for (int j=0; j<nums.length; j++) {
                System.out.print(nums[j]);
            }
            System.out.println();
        }
        System.out.println(a.length);
    }
}