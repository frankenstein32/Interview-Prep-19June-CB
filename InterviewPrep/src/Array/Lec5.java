package Array;

import java.util.*;

public class Lec5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// O(N2) : Array Nesting Brute Force
	public static int arrayNesting(int[] nums) {

		int res = 0;

		for (int i = 0; i < nums.length; i++) {

			int start = nums[i];
			int cnt = 0;

			do {
				start = nums[start];
				cnt++;
			} while (start != nums[i]);

			res = Math.max(cnt, res);
		}

		return res;
	}

	// O(N) : Space O(N) : Enhanced Approach
	public static int arrayNesting_en(int[] nums) {

		boolean[] visited = new boolean[nums.length];

		int res = 0;

		for (int i = 0; i < nums.length; i++) {

			if (!visited[i]) {
				int start = nums[i];
				int cnt = 0;

				do {
					start = nums[start];
					visited[start] = true;
					cnt++;
				} while (start != nums[i]);

				res = Math.max(cnt, res);
			}
		}

		return res;

	}

	public static int SubarraySumDivK(int[] nums, int K) {

		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);

		int sum = 0;
		int cnt = 0;
		for (int i = 0; i < nums.length; i++) {

			sum = (sum + nums[i]) % K;
			if (sum < 0)
				sum = sum + K;
			cnt += map.getOrDefault(sum, 0);
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}

		return cnt;
	}

	public static int SubarraySumDivK_arr(int[] nums, int K) {

		int[] map = new int[K];

		map[0] = 1;
		int sum = 0, cnt = 0;
		for (int i = 0; i < nums.length; i++) {

			sum = (sum + nums[i]) % K;
			if (sum < 0)
				sum = sum + K;

			cnt += map[sum];
			map[sum]++;
		}
		return cnt;
	}

	public static int subArrayProdLessthanK(int[] nums, int K) {

		int left = 0;
		int res = 0;
		int curProd = 1;
		for (int right = 0; right < nums.length; right++) {

			curProd *= nums[right];

			while (curProd >= K) {
				curProd /= nums[left++];
			}

			res += right - left + 1;

		}

		return res;
	}

	// O(N) : Sliding Window Approach
	public static int MaxWidthRamp(int[] nums) {

		int[] rmax = new int[nums.length];

		rmax[rmax.length - 1] = nums[nums.length - 1];

		for (int i = rmax.length - 2; i >= 0; i--) {

			rmax[i] = Math.max(rmax[i + 1], nums[i]);
		}

		int left = 0, right = 0, ans = 0;

		while (right < nums.length) {

			while (left < right && nums[left] > rmax[right]) {
				left++;
			}

			ans = Math.max(ans, right - left);
			right++;
		}

		return ans;

	}

	// O(NLogN) : Sorting Approach
	public static int MaxWidthRamp_(int[] nums) {

		Integer[] temp = new Integer[nums.length];

		for (int i = 0; i < nums.length; i++) {
			temp[i] = i;
		}

		java.util.Arrays.sort(temp, (i, j) -> ((Integer) nums[i]).compareTo(nums[j]));

		int res = 0;
		int min = nums.length - 1;

		for (int i = 0; i < nums.length; i++) {

			res = Math.max(res, temp[i] - min);
			min = Math.min(temp[i], min);
		}

		return res;
	}

	public static int minFlips(String s) {

		int[] prefix = new int[s.length() + 1];

		for (int i = 0; i < s.length(); i++) {

			prefix[i + 1] = prefix[i] + (s.charAt(i) == '1' ? 1 : 0);
		}

		int min = Integer.MAX_VALUE;
		int N = s.length();
		for (int i = 0; i <= s.length(); i++) {

			min = Math.min(min, prefix[i] + (N - i) - (prefix[i] - prefix[i]));
		}

		return min;
	}

	// O(N) : Just Like the Previous Approach
	public static int BestTimeToSellBuyTransaction(int[] prices, int fee) {

		if (prices.length < 2) {
			return 0;
		}

		int profit = 0;
		int min = prices[0];

		for (int i = 0; i < prices.length; i++) {

			if (prices[i] < min) {
				min = prices[i];
			} else if (prices[i] > min + fee) {

				profit += prices[i] - min - fee;
				min = prices[i] - fee;
			}

		}

		return profit;
	}

	// O(N) : Easy Approach S : O(N)
	public static int maxTurbulenceSize(int[] nums) {

		boolean up = true;
		boolean down = true;
		int cnt = 1;
		int maxLen = 1;

		for (int i = 1; i < nums.length; i++) {

			if (nums[i - 1] > nums[i] && up) {
				up = false;
				down = true;
				cnt++;
			} else if (nums[i - 1] < nums[i] && down) {

				down = false;
				up = true;
				cnt++;
			} else {

				cnt = 2;

				if (nums[i - 1] > nums[i]) {
					down = true;
					up = false;
				} else if (nums[i - 1] < nums[i]) {
					up = true;
					down = false;
				} else {

					cnt = 1;
					up = true;
					down = true;

				}

			}

			if (cnt > maxLen) {
				maxLen = cnt;
			}

		}

		return maxLen;
	}

	// O(N) : Prefix Array Approach
	public static int minFlipsMonoIncr(String S) {

		int[] p = new int[S.length() + 1];

		for (int i = 0; i < S.length(); i++) {

			p[i + 1] = p[i] + (S.charAt(i) == '1' ? 1 : 0);
		}

		int ans = Integer.MAX_VALUE;

		for (int j = 0; j <= S.length(); j++) {

			ans = Math.min(ans, p[j] + (S.length() - j) - (p[S.length()] - p[j]));
		}

		return ans;
	}

	// O(LogN) : Binary Search with Duplicates
	public static int findMin_Dup(int[] nums) {

		int N = nums.length;
		int lo = 0;
		int hi = N - 1;
		int rmax = nums[hi];

		while (lo < hi) {

			int mid = (lo + hi) / 2;

			if (nums[mid] > rmax) {
				lo = mid + 1;
			} else if (nums[mid] < rmax) {
				hi = mid;
			} else {

				if (nums[hi - 1] == nums[hi]) {
					hi--;
				} else {

					lo = mid + 1;
				}
			}

		}

		return nums[lo];
	}

}
