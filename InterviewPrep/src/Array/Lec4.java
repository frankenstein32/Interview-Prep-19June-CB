package Array;

import java.util.*;

public class Lec4 {

	// O(LogN) : Binary Search in Again and Again
	public int[] FirstAndLastIndex(int[] nums, int target) {

		int idx = BinarySearch(nums, 0, nums.length - 1, target);

		if (idx == -1)
			return new int[] { -1, -1 };

		int start = idx;
		int end = idx;

		int lo = 0;
		int hi = idx - 1;

		while (true) {

			int temp = BinarySearch(nums, lo, hi, target);

			if (temp != -1) {

				start = temp;
				lo = 0;
				hi = start - 1;

			} else {
				break;
			}
		}

		lo = idx + 1;
		hi = nums.length - 1;

		while (true) {

			int temp = BinarySearch(nums, lo, hi, target);

			if (temp != -1) {

				end = temp;
				lo = temp + 1;
				hi = nums.length - 1;
			} else {
				break;
			}
		}

		return new int[] { start, end };

	}

	// O(LogN) : Helper Function for First and Last Index
	public static int BinarySearch(int[] nums, int lo, int hi, int target) {

		while (lo <= hi) {

			int mid = lo + ((hi - lo) / 2);

			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] > target) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		return -1;
	}

	// O(N2) : TransPose + Reverse : No Extra Space
	public static void Rotate90(int[][] matrix) {

		transpose(matrix);

		for (int i = 0; i < matrix.length; i++) {
			for (int ColStart = 0, ColEnd = matrix[0].length - 1; ColStart < ColEnd; ColStart++, ColEnd--) {

				int temp = matrix[i][ColStart];
				matrix[i][ColStart] = matrix[i][ColEnd];
				matrix[i][ColEnd] = temp;

			}
		}
	}

	// O(N2) : NO Extra Space
	public static void transpose(int[][] matrix) {

		for (int i = 0; i < matrix.length; i++) {
			for (int j = i; j < matrix[0].length; j++) {

				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;

			}
		}
	}

	// O(N) : Two Pointer Approach
	public static int containedMostWater(int[] height) {

		int lo = 0;
		int hi = height.length - 1;
		int maxArea = 0;

		while (lo < hi) {

			int area = Math.min(height[lo], height[hi]) * (hi - lo);
			maxArea = Math.max(area, maxArea);

			if (height[lo] < height[hi]) {
				lo++;
			} else {
				hi--;
			}

		}

		return maxArea;

	}

	// O(N2) : Simple Using Set
	public static void SetMatrixToZeros(int[][] matrix) {

		Set<Integer> row = new HashSet<>();
		Set<Integer> col = new HashSet<>();

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {

				if (matrix[i][j] == 0) {
					row.add(i);
					col.add(j);
				}
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {

				if (row.contains(i) || col.contains(j)) {
					matrix[i][j] = 0;
				}
			}
		}

	}

	// O(N) : Two Array Approach
	public static int maxProduct(int[] nums) {

		// Handle Corner Test Case

		int[] max = new int[nums.length];
		int[] min = new int[nums.length];

		max[0] = min[0] = nums[0];
		int res = nums[0];

		for (int i = 1; i < nums.length; i++) {

			if (nums[i] > 0) {
				max[i] = Math.max(nums[i], max[i - 1] * nums[i]);
				min[i] = Math.min(nums[i], min[i - 1] * nums[i]);
			} else {
				max[i] = Math.max(nums[i], min[i - 1] * nums[i]);
				min[i] = Math.min(nums[i], max[i - 1] * nums[i]);
			}

			res = Math.max(res, max[i]);
		}

		return res;
	}

	// O(N) : Space Efficient
	public static int maxProduct_SE(int[] nums) {

		int max = nums[0];
		int min = nums[0];
		int res = nums[0];

		for (int i = 1; i < nums.length; i++) {

			int tempmin, tempmax;

			if (nums[i] > 0) {

				tempmax = Math.max(nums[i], max * nums[i]);
				tempmin = Math.min(nums[i], min * nums[i]);

			} else {
				tempmax = Math.max(nums[i], min * nums[i]);
				tempmin = Math.min(nums[i], max * nums[i]);

			}

			min = tempmin;
			max = tempmax;

			res = Math.max(res, max);

		}

		return res;
	}

	// O(LogN) : Binary Search
	public static int findMin(int[] nums) {

		int lo = 0;
		int hi = nums.length - 1;

		while (lo <= hi) {

			if (nums[lo] <= nums[hi]) {
				return nums[lo];
			}
			int mid = lo + (hi - lo) / 2;

			if (nums[lo] < mid) {
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}

		return -1;

	}

	// O(LogN) : Binary Search
	public static int findPeakElement(int[] nums) {

		int lo = 0;
		int hi = nums.length - 1;

		while (lo < hi) {

			int mid = lo + (hi - lo) / 2;

			if (nums[mid] > nums[mid + 1]) {
				hi = mid;
			} else {
				lo = mid + 1;
			}

		}

		return lo;
	}

	// O(N) : Two Pointer
	public List<String> summaryRanges(int[] nums) {

		if (nums == null || nums.length < 1) {
			return new ArrayList<>();
		}

		int i = 0;
		int j = 1;
		int count = 1;

		List<String> ans = new ArrayList<>();

		while (j < nums.length) {

			if (nums[i] + count != nums[j]) {

				String s = "";
				if (i == j - 1) {
					s += nums[i];
				} else {

					s = nums[i] + "->" + nums[j - 1];
				}

				ans.add(s);
				i = j;
				count = 0;
				j++;

			}
			j++;
			count++;
		}

		String s = "";
		if (i == j - 1) {
			s += nums[i];
		} else {

			s = nums[i] + "->" + nums[j - 1];
		}

		ans.add(s);

		return ans;

	}

	// O(N) : Two Array Approach
	public int[] productExceptItself(int[] nums) {

		int[] left = new int[nums.length];
		int[] right = new int[nums.length];

		for (int i = 1; i < nums.length; i++) {

			left[i] = left[i - 1] * nums[i - 1];
		}

		for (int j = nums.length - 2; j >= 0; j--) {

			right[j] = right[j + 1] * nums[j + 1];
		}

		int[] res = new int[nums.length];

		for (int i = 0; i < res.length; i++) {
			res[i] = left[i] * right[i];
		}

		return res;

	}

	public int[] productExceptItself_SE(int[] nums) {

//		int[] right = new int[]
		return null;
	}

	// Find Duplicate Code is Same as Discussed in Previous Questions

	// O(N) : Using HashMap
	public int SubArraySum(int[] nums, int target) {

		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		int sum = 0;
		int count = 0;
		for (int i = 0; i < nums.length; i++) {

			sum += nums[i];
			count += map.getOrDefault(sum - target, 0);
			map.put(sum, map.getOrDefault(sum, 0) + 1);

		}

		return count;
	}

	// O(N2) : Sorting
	public int traingleNumber(int[] nums) {

		java.util.Arrays.sort(nums);

		int res = 0;
		for (int i = 0; i < nums.length; i++) {

			int k = i + 2;
			for (int j = i + 1; j < nums.length && nums[i] != 0; j++) {

				while (k < nums.length && nums[i] + nums[j] > nums[k]) {
					k++;
				}

				res += k - j - 1;
			}

		}
		return res;
	}

	// O(N) : Space O(N)
	public int maximumSwap(int num) {

		char[] charray = Integer.toString(num).toCharArray();

		int[] arr = new int[10];

		for (int i = 0; i < charray.length; i++) {

			arr[charray[i] - '0'] = i;
		}

		for (int i = 0; i < charray.length; i++) {

			for (int d = 9; d > charray[i] - '0'; d--) {

				if (arr[d] > i) {

					char temp = charray[i];
					charray[i] = charray[arr[d]];
					charray[arr[d]] = temp;

					return Integer.valueOf(new String(charray));
				}
			}
		}
		return num;

	}

	// O(N) : Stack
	public int maxChunksToSorted(int[] nums) {

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < nums.length; i++) {

			if (stack.isEmpty() || stack.peek() < nums[i]) {
				stack.push(nums[i]);
			} else {

				int max = stack.pop();

				while (!stack.isEmpty() && stack.peek() > nums[i]) {
					stack.pop();
				}

				stack.push(max);
			}
		}

		return stack.size();
	}

	// O(N) : Brute
	public int numMatchingSubseq(String S, String[] words) {

		HashMap<String, Integer> map = new HashMap<>();

		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}

		int count = 0;
		for (String key : map.keySet()) {

			if (substr(S, key)) {
				count += map.get(key);
			}
		}
		return count;

	}

	// Helper Function
	public static boolean substr(String S, String word) {

		if (word.length() > S.length()) {
			return false;
		}

		int i = 0;
		int j = 0;

		while (i < S.length() && j < word.length()) {

			if (S.charAt(i) == word.charAt(j)) {
				i++;
				j++;
			} else {
				i++;
			}
		}

		return j == word.length();
	}

	// HashMap
	public int subarraysDivByK(int[] A, int k) {
		int[] map = new int[k];

		map[0] = 1;
		int sum = 0, count = 0;

		for (int a : A) {
			sum = (sum + a) % k;

			if (sum < 0) {
				sum += k;
			}

			count += map[sum];
			map[sum]++;
		}

		return count;
	}
}
