package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lec2 {

	public static void main(String[] args) {

		System.out.println();
	}

	// O(N) : New Approach
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> ans = new ArrayList<>();

		for (int i = 0; i < nums.length; i++) {

			int val = Math.abs(nums[i]) - 1;

			if (nums[val] > 0) {
				nums[val] = -nums[val];
			}
		}

		for (int i = 0; i < nums.length; i++) {

			if (nums[i] > 0) {
				ans.add(i + 1);
			}
		}

		return ans;
	}

	// O(n) -> Array size 26
	public static List<String> commonCharacters(String[] A) {

		int[] count = new int[26];
		java.util.Arrays.fill(count, Integer.MAX_VALUE);

		for (String a : A) {

			int[] tempCnt = new int[26];
			for (char ch : a.toCharArray()) {
				tempCnt[ch - 'a']++;
			}

			for (int i = 0; i < 26; i++) {
				count[i] = Math.min(count[i], tempCnt[i]);
			}
		}

		List<String> ans = new ArrayList<>();

		for (int i = 0; i < 26; i++) {

			while (count[i] != 0) {

				ans.add((char) (i + 'a') + "");
				count[i]--;
			}
		}

		return ans;

	}

	// O(n) and Space O(1) -> PreCalculation Technique
	public int pivotIndex(int[] nums) {

		int rightSum = 0;
		for (int num : nums)
			rightSum += num;

		int leftSum = 0;

		for (int i = 0; i < nums.length; i++) {

			rightSum -= nums[i];

			if (leftSum == rightSum) {

				return i;
			}

			leftSum += nums[i];
		}

		return -1;
	}

	// O(nlogn) : Sorting Technique
	public int HeightChecker(int[] heights) {

		int[] temp = heights.clone();
		java.util.Arrays.sort(temp);
		int cnt = 0;

		for (int i = 0; i < temp.length; i++) {

			if (temp[i] != heights[i]) {
				cnt++;
			}
		}

		return cnt;
	}

	// O(n) : Count Sort : Space O(n)
	public int HeightChecker_Eff(int[] height) {

		int[] Freq = new int[101];

		for (int h : height)
			Freq[h]++;

		int result = 0;
		int curHeight = 0;
		for (int i = 0; i < height.length; i++) {

			while (Freq[curHeight] == 0) {
				curHeight++;
			}

			if (curHeight != height[i]) {
				result++;
			}

			Freq[curHeight]--;
		}
		return result;
	}

	// O(nlogn) -> Sorting -> constant Space
	public int findPairs(int[] nums, int k) {

		java.util.Arrays.sort(nums);
		int left = 0;
		int right = 1;
		int count = 0;

		while (left < nums.length && right < nums.length) {

			if (left == 0 || nums[left] != nums[left - 1]) {

				int diff = nums[right] - nums[left];

				if (diff == k) {
					count++;
					left++;
				} else if (diff < k) {
					right++;
				} else {
					left++;
				}
			} else {
				left++;
			}

			if (left == right) {
				right++;
			}
		}

		return count;

	}

	// O(n) -> HashMap Space O(n)
	public int findPairs_eff(int[] nums, int k) {

		HashMap<Integer, Integer> count = new HashMap<>();

		for (int num : nums) {
			count.put(num, count.getOrDefault(num, 0) + 1);
		}

		int res = 0;

		for (Map.Entry<Integer, Integer> entry : count.entrySet()) {

			if (k == 0) {

				if (entry.getValue() >= 2) {
					res++;
				}
			} else {

				if (count.containsKey(entry.getKey() + k)) {
					res++;
				}
			}
		}

		return res;

	}

	// O(n) -> Normal Loop
	public int LengthOfContinuousIncrSubeq(int[] nums) {

		if (nums == null || nums.length < 2) {
			return nums.length;
		}
		int count = 1;
		int max = 0;
		for (int i = 1; i < nums.length; i++) {

			if (nums[i - 1] >= nums[i]) {
				count = 1;
			} else {
				count++;
			}

			max = Math.max(count, max);
		}

		return max;
	}

	// Sorting -> HashMap -> Divide and Conquer
	// O(n) : Majority Vote Algorithm
	public int MajorityElement(int[] nums) {

		int digit = 0;
		int freq = -1;

		for (int i = 0; i < nums.length; i++) {

			if (digit != nums[i]) {
				freq--;
			} else {
				freq++;
			}

			if (freq < 0) {
				freq = 1;
				digit = nums[i];
			}
		}

		return digit;
	}

	// Helper Function for Majority Element
	public int countInRange(int[] nums, int num, int lo, int hi) {
		int count = 0;
		for (int i = lo; i <= hi; i++) {
			if (nums[i] == num) {
				count++;
			}
		}
		return count;
	}

	// Divide and Conquer for Majority Element
	public int majorityElementRec(int[] nums, int lo, int hi) {
		// base case; the only element in an array of size 1 is the majority
		// element.
		if (lo == hi) {
			return nums[lo];
		}

		// recurse on left and right halves of this slice.
		int mid = (hi - lo) / 2 + lo;
		int left = majorityElementRec(nums, lo, mid);
		int right = majorityElementRec(nums, mid + 1, hi);

		// if the two halves agree on the majority element, return it.
		if (left == right) {
			return left;
		}

		// otherwise, count each element and return the "winner".
		int leftCount = countInRange(nums, left, lo, hi);
		int rightCount = countInRange(nums, right, lo, hi);

		return leftCount > rightCount ? left : right;
	}

	// O(n) : Simple Loop
	public int MaxConseqOnes(int[] nums) {

		int count = 0;
		int ans = 0;
		for (int num : nums) {

			if (num == 1) {
				count++;
			} else {
				count = 0;
			}

			ans = Math.max(count, ans);
		}

		return ans;
	}

	// O(n) : Space O(n) : Left - Right Array Approach
	public int maximizeDistToClosest(int[] seats) {

		int[] left = new int[seats.length];
		int[] right = new int[seats.length];
		int N = seats.length;

		for (int i = 0; i < left.length; i++) {

			left[i] = seats[i] == 1 ? 0 : i > 0 ? left[i - 1] + 1 : N;
		}

		for (int i = right.length - 1; i >= 0; i--) {

			right[i] = seats[i] == 1 ? 0 : i < right.length - 1 ? right[i + 1] + 1 : N;
		}

		int ans = 0;

		for (int i = 0; i < seats.length; i++) {

			ans = Math.max(ans, Math.min(left[i], right[i]));
		}

		return ans;

	}

	// O(n) : Math Space O(1)
	public int maximizeDistToClosest_EFF(int[] seats) {

		int k = 0;
		int N = seats.length;
		int ans = 0;

		for (int i = 0; i < seats.length; i++) {

			if (seats[i] == 1) {
				k = 0;
			} else {
				k++;
			}

			ans = Math.max(ans, k);
		}

		for (int i = 0; i < seats.length; i++) {

			if (seats[i] == 1) {
				ans = Math.max(i, ans);
				break;
			}
		}

		for (int i = seats.length - 1; i >= 0; i--) {

			if (seats[i] == 1) {

				ans = Math.max(ans, N - i - 1);
				break;
			}
		}

		return ans;
	}

}
