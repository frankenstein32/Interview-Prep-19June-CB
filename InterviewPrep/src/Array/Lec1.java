package Array;

import java.util.*;

public class Lec1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Simple and Sober -> 100 %
	public static boolean isOneBitCharacter(int[] bits) {

		int category = 1;

		int i = 0;
		while (i < bits.length) {

			if (bits[i] == 0) {
				category = 1;
				i++;
			} else {
				category = 2;
				i = i + 2;
			}
		}

		return category == 1;
	}

	// O(nlogn) -> Sorting Technique
	public static int ArrayPartition(int[] nums) {

		java.util.Arrays.sort(nums);

		int sum = 0;
		for (int i = 0; i < nums.length; i += 2) {

			sum += nums[i];
		}

		return sum;
	}

	// O(n) : exist Array O(n)
	public static int ArrayPartition_Eff(int[] nums) {

		int[] exist = new int[20001];

		for (int num : nums)
			exist[num + 10000]++;

		boolean odd = true;
		int sum = 0;

		for (int i = 0; i < exist.length; i++) {

			while (exist[i] > 0) {

				if (odd) {

					sum += i - 10000;
				}

				odd = !odd;
				exist[i]--;
			}

		}

		return sum;
	}

	//O(n2) : Normal Looping
	public static int CapturesForRook(char[][] board) {

		int x0 = 0, y0 = 0, res = 0;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {

				if (board[i][j] == 'R') {
					x0 = i;
					y0 = j;
				}
			}
		}

		for (int[] d : new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } }) {

			for (int x = x0 + d[0], y = y0 + d[1]; x >= 0 && x < board.length && y >= 0
					&& y < board.length; x += d[0], y += d[1]) {
				if (board[x][y] == 'p')
					res++;
				if (board[x][y] != '.')
					break;
			}
		}

		return res;

	}

	// O(n) with Space O(n) : Left Right Array Approach
	public static int BestTimeToSellStock(int[] prices) {

		int[] leftMin = new int[prices.length];
		int[] rightMax = new int[prices.length];

		leftMin[0] = prices[0];

		for (int i = 1; i < leftMin.length; i++)
			leftMin[i] = Math.min(leftMin[i - 1], prices[i]);

		rightMax[rightMax.length - 1] = prices[prices.length - 1];

		for (int i = rightMax.length - 2; i >= 0; i--)
			rightMax[i] = Math.max(rightMax[i + 1], prices[i]);

		int diff = 0;

		for (int i = 0; i < prices.length; i++) {

			diff = Math.max(diff, rightMax[i] - leftMin[i]);
		}

		return diff;
	}

	// O(n) : Diff Approach
	public static int BestTimeToSellStock_eff(int[] prices) {

		int max_diff = 0;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < prices.length; i++) {

			if (prices[i] < min) {
				min = prices[i];
			} else {

				max_diff = Math.max(max_diff, prices[i] - min);
			}
		}

		return max_diff;
	}

	// O(n) : Different Approach
	public static int BestTimeToSellStock2(int[] prices) {

		int maxProfit = 0;

		for (int i = 1; i < prices.length; i++) {

			if (prices[i] > prices[i - 1]) {
				maxProfit += prices[i] - prices[i - 1];
			}
		}

		return maxProfit;
	}

	//O(n) : Normal lookUp Algo
	public static boolean canPlaceFlowers(int[] nums, int n) {

		int i = 0, count = 0;

		while (i < nums.length) {

			if (nums[i] == 0 && (i == 0 || nums[i - 1] == 0) && (i == nums.length - 1 || nums[i + 1] == 0)) {
				count++;
				nums[i] = 1;
			}

			if (count >= n) {
				return true;
			}

			i++;
		}

		return false;
	}

	// O(n) and O(nlogn) -> Sorting
	public static boolean containsDuplicate(int[] nums) {

		if (nums == null || nums.length <= 1) {
			return false;
		}

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int num : nums) {

			if (num < min) {
				min = num;
			}

			if (num > max) {
				max = num;
			}
		}

		boolean[] arr = new boolean[max - min + 1];

		for (int num : nums) {

			if (arr[num - min]) {
				return true;
			}

			arr[num - min] = true;
		}

		return false;
	}

	// HashMap O(n)
	public static boolean containsDuplicate2(int[] nums, int k) {

		HashMap<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {

			if (map.containsKey(nums[i])) {

				if (i - map.get(nums[i]) <= k) {
					return true;
				}
			}

			map.put(nums[i], i);
		}

		return false;
	}

	// Sliding Window O(n)
	public static boolean containsDuplicate_set(int[] nums, int k) {

		if (nums == null || nums.length <= 1) {
			return false;
		}
		HashSet<Integer> set = new HashSet<>();

		for (int i = 0; i <= k && i < nums.length; i++) {

			if (!set.add(nums[i])) {
				return true;
			}
		}

		for (int i = k + 1; i < nums.length; i++) {

			set.remove(nums[i - k - 1]);

			if (!set.add(nums[i])) {
				return true;
			}
		}
		return false;
	}

	// Two Pass O(n) : Three HashMap
	public static int DegreeArray(int[] nums) {

		HashMap<Integer, Integer> count = new HashMap<>();
		HashMap<Integer, Integer> left = new HashMap<>();
		HashMap<Integer, Integer> right = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {

			if (!left.containsKey(nums[i])) {
				left.put(nums[i], i);
			}

			right.put(nums[i], i);

			count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
		}

		int maxDegree = Collections.max(count.values());
		int maxLen = nums.length;

		for (int key : count.keySet()) {

			if (count.get(key) == maxDegree) {

				maxLen = Math.min(right.get(key) - left.get(key) + 1, maxLen);
			}
		}

		return maxLen;

	}

	// One Pass O(n): Two HashMap
	public static int DegreeArray_Eff(int[] nums) {

		HashMap<Integer, Integer> count = new HashMap<>();
		HashMap<Integer, Integer> left = new HashMap<>();

		int res = nums.length;
		int degree = 0;

		for (int i = 0; i < nums.length; i++) {

			left.putIfAbsent(nums[i], i);
			count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);

			if (count.get(nums[i]) > degree) {

				degree = count.get(nums[i]);
				res = i - left.get(nums[i]) + 1;
			} else if (count.get(nums[i]) == degree) {

				res = Math.min(res, i - left.get(nums[i]) + 1);
			}
		}

		return res;

	}

	// O(n) -> Mathematic + HashSet
	public static int[] FairCandySwap(int[] A, int[] B) {

		int sa = 0;
		int sb = 0;

		for (int a : A)
			sa += a;
		for (int b : B)
			sb += b;

		int delta = (sb - sa) / 2;
		Set<Integer> setB = new HashSet<>();

		for (int b : B)
			setB.add(b);

		for (int a : A) {

			if (setB.contains(a + delta)) {
				return new int[] { a, a + delta };
			}
		}

		return new int[] {};
	}

}
