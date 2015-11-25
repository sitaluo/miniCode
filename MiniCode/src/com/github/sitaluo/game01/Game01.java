package com.github.sitaluo.game01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Game01 {

	public static void main(String[] args) {
		// n = 3时：int[] nums = new int[]{1,2,3,4,5,6,7,8,9};
		gameRun(3);
		/* 打印符合条件的结果
			[2, 7, 6, 9, 5, 1, 4, 3, 8]
			[2, 9, 4, 7, 5, 3, 6, 1, 8]
			[4, 3, 8, 9, 5, 1, 2, 7, 6]
			[4, 9, 2, 3, 5, 7, 8, 1, 6]
			[6, 1, 8, 7, 5, 3, 2, 9, 4]
			[6, 7, 2, 1, 5, 9, 8, 3, 4]
			[8, 3, 4, 1, 5, 9, 6, 7, 2]
			[8, 1, 6, 3, 5, 7, 4, 9, 2]
			
			也就是第一组数
			2, 7, 6, 
			9, 5, 1, 
			4, 3, 8   这样横竖左右以及对角线加起来的和都是相等的，后面的也是一样
		*/
		// n = 5时： 这里就太耗时间了，根本没有耐心等到结果出来
		// int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
		// 那么算法需要怎么优化呢?
		
	}
	
	public static void gameRun(int n){
		
		if(n % 2 != 1){
			System.out.println("Error! The parameter n must be odd number.");
			return;
		}
		int maxNum = n*n;
		int[] nums = new int[maxNum];
		for (int i = 0; i < maxNum; i++) {
			nums[i] = i + 1;
		}
		//int[] nums = new int[]{1,2,3,4,5,6,7,8,9};
		
		int[][]  datas = PermulationAndCombination.getAllArray(nums);
		for (int i = 0; i < datas.length; i++) {
			int[] temp = datas[i];
			boolean flag = check(temp);
			if(flag){
				//System.out.println("yes");
				System.out.println(Arrays.toString( temp));//符合要求的一组数
			}else{
				//System.out.println("no");
			}
		}
	}
	
	/**
	 * 检查当前数组组成的一组数是否符合条件，比如
	 * [2, 7, 6, 9, 5, 1, 4, 3, 8]，应该返回true,对应组成的9宫格上下左右以及对角线的和都是相等的
	 * @param nums
	 * @return
	 */
	public static boolean check(int[] nums){
		
		int size = nums.length;
		int bianChang = (int) Math.sqrt(size);
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		Integer first = 0;
		for(int i=0; i< bianChang; i++){
			first = first + nums[i];
		}
		map.put(first, null);
		Integer sumTemp = 0;
		Integer sumTemp2 = 0;
		int[][] subs = new int[bianChang][bianChang];
		for (int i = 0; i < nums.length; i++) {
			int x = i/bianChang;
			int y = i%bianChang;
			subs[x][y] = nums[i];
		}
		
		for (int i = 0; i < bianChang; i++) {
			sumTemp = 0;
			sumTemp2 = 0;
			for (int j = 0; j < bianChang; j++) {
				sumTemp = sumTemp + subs[i][j];
				sumTemp2 = sumTemp2 + subs[j][i];
			}
			if(!map.containsKey(sumTemp) || !map.containsKey(sumTemp2)){
				return false;
			}
		}
		
		int startIndex3 = 0;
		int startIndex4_x = 0;
		int startIndex4_y = bianChang -1;
		int sumTemp3 = 0;
		int sumTemp4 = 0;
		for (int i = 0; i < bianChang; i++) {
			sumTemp3 = sumTemp3 + subs[startIndex3][startIndex3];
			startIndex3 ++;
			sumTemp4 = sumTemp4 + subs[startIndex4_y][startIndex4_x];
			startIndex4_x ++;
			startIndex4_y --;
		}
//		System.out.println(sumTemp3);
//		System.out.println(sumTemp4);
		 if(!map.containsKey(sumTemp3)){
				return false;
		 }
		 if(!map.containsKey(sumTemp4)){
				return false;
		 }
		return true;
	}
	
}
