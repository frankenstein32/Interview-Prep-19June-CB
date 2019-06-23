package Array;

import java.util.*;

public class Lec3 {

	// O(n) : Sliding Window
	public double maximumAverageSub1(int[] nums, int k) {

		double currSum = 0;

		for (int i = 0; i < k; i++) {

			currSum += nums[i];
		}

		double res = currSum;

		for (int i = k; i < nums.length; i++) {

			currSum = currSum + nums[i] - nums[i - k];
			res = Math.max(res, currSum);
		}

		return res / k;

	}

	// O(n) : Easy and Sorting one also Exist
	public int maximumProduct(int[] nums) {

		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		int max3 = Integer.MIN_VALUE;

		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;

		for (int num : nums) {

			if (num > max1) {

				max3 = max2;
				max2 = max1;
				max1 = num;
			} else if (num > max2) {

				max3 = max2;
				max2 = num;
			} else if (num > max3) {

				max3 = num;
			}

			if (num < min1) {
				min2 = min1;
				min1 = num;
			} else if (num < min2) {

				min2 = num;
			}
		}

		return Math.max(max3 * max2 * max1, min1 * min2 * max3);
	}

	// O(n) : Maximum Sum Subarray : Kadane's handle for Neg. Left

	// O(n) : Two Pointer Approach
	public void mergeTwoSortedArray(int[] nums1, int m, int[] nums2, int n) {

		int i = nums1.length - 1;
		m--;
		n--;

		while (m >= 0 && n >= 0) {

			if (nums1[m] > nums2[n]) {
				nums1[i--] = nums1[m--];
			} else {
				nums1[i--] = nums2[n--];
			}
		}

		while (n >= 0) {
			nums1[i--] = nums2[n--];
		}
	}

	public int MissingNumber(int[] nums) {

		int missing = nums.length;

		for (int i = 0; i < nums.length; i++) {

			missing = missing ^ i ^ nums[i];
		}

		return missing;
	}

	// O(n) : Must Know
	public int MissingNumber_sum(int[] nums) {

		int n = nums.length;
		int Computed = 0;
		for (int num : nums)
			Computed += num;

		int Orig = n * (n + 1) / 2;

		return Orig - Computed;

	}

	// O(n) : Two Pointers
	public void MoveZeros(int[] nums) {

		int i = 0;
		int j = 0;

		while (j < nums.length) {

			if (nums[j] != 0) {

				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;

				i++;
			}
			j++;
		}

	}

	// O(n) : Time and O(n) : Space
	public int PairsOfSongs(int[] time) {

		int[] c = new int[60];
		int res = 0;

		for (int t : time) {

			res += c[(600 - t) % 60];
			c[t % 60] += 1;
		}

		return res;
	}

	// O(n) : Two Pointer Approach
	public int removeElement(int[] arr, int item) {

		int i = 0;
		int j = 0;

		while (j < arr.length) {

			if (arr[j] != item) {

				arr[i] = arr[j];
				i++;
			}
			j++;
		}

		return i;
	}

	// O(logn) : Binary Search
	public int SearchInsertPos(int[] nums, int item) {

		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {

			int mid = (right + left) / 2;

			if (nums[mid] < item) {
				left = mid + 1;
			} else if (nums[mid] > item) {
				right = mid - 1;
			} else {
				return mid;
			}
		}

		return left;
	}

	// O(n) : Typical to Teach
	public int ShortestUnsortedSubarray(int[] A) {

		int n = A.length, beg = -1, end = -2, min = A[n - 1], max = A[0];

		for (int i = 1; i < A.length; i++) {

			max = Math.max(max, A[i]);
			min = Math.min(min, A[n - i - 1]);

			if (A[i] < max)
				end = i;
			if (A[n - i - 1] > min)
				beg = n - i - 1;
		}
		return end - beg + 1;

	}

	// O(n) : Two Pointer Approach
	public static int[] sortByParity(int[] A) {

		int i = 0;
		int j = 0;

		while (j < A.length) {

			if (A[j] % 2 == 0) {

				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;

				i++;
			}

			j++;
		}

		return A;
	}

	// O(N) : Two Pointer Approach
	public int[] sortArrayByParityII(int[] A) {

		int i = 0;
		int j = 0;

		while (j < A.length) {

			if (i % 2 == 0 && A[j] % 2 == 0) {

				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;

				i++;
			} else if (i % 2 == 1 && A[j] % 2 == 1) {

				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;

				i++;
			} else {

				j++;
			}
		}

		return A;
	}

	// O(m + n) : MergeTwoSortedArrays
	public int[] sortedSquares(int[] nums) {

		int[] ans = new int[nums.length];

		int j = 0;
		while (j < nums.length) {

			if (nums[j] >= 0) {
				break;
			}

			j++;
		}

		int i = j - 1;
		int k = 0;

		while (i >= 0 && j < nums.length) {

			if (sq(nums[i]) > sq(nums[j])) {
				ans[k++] = sq(nums[j++]);
			} else {
				ans[k++] = sq(nums[i--]);
			}
		}

		while (j < nums.length) {
			ans[k++] = sq(nums[j++]);
		}

		while (i >= 0) {
			ans[k++] = sq(nums[i--]);
		}

		return ans;
	}

	// helper Function : Square of Number
	public static int sq(int num) {
		return num * num;
	}

	// O(n) : Two Pointer Approach
	public static int[] twoSum(int[] nums, int target) {

		int i = 0;
		int j = nums.length - 1;

		while (i < j) {

			int sum = nums[i] + nums[j];

			if (sum > target) {
				j--;
			} else if (sum < target) {
				i++;
			} else {
				return new int[] { i + 1, j + 1 };
			}
		}

		return new int[] {};
	}

	// O(n) : GCD, HashMap Space : O(n)
	public static boolean hasGroupSizeX(int[] deck) {

		HashMap<Integer, Integer> map = new HashMap<>();

		for (int card : deck)
			map.put(card, map.getOrDefault(card, 0) + 1);

		int ideal = -1;
		for (int key : map.keySet()) {

			if (map.get(key) < 2) {
				return false;
			}

			if (ideal == -1) {
				ideal = map.get(key);
			} else {

				ideal = gcd(ideal, map.get(key));
			}
		}

		return ideal >= 2;

	}

	// Helper Function : GCD
	public static int gcd(int a, int b) {

		if (a == 0) {
			return b;
		}

		return gcd(a % b, a);

	}

	// O(N) : New Approach Application
	public int[] SetMismatch(int[] nums) {

		int[] res = new int[2];
		for (int i = 0; i < nums.length; i++) {

			int val = Math.abs(nums[i]) - 1;

			if (nums[val] > 0) {
				nums[val] = -nums[val];
			} else {
				res[0] = val + 1;
			}
		}

		for (int i = 0; i < nums.length; i++) {

			if (nums[i] > 0) {
				res[1] = i + 1;
				return res;
			}
		}

		return res;
	}

	// O(N) : One Parse + Smart Work
	public boolean canThreePartsEqualSum(int[] A) {
		int sum = 0;
		// calculate overall sum
		for (int n : A)
			sum += n;
		// check if it's divisible by 3
		if (sum % 3 != 0)
			return false;
		// exact sum of each segment
		sum /= 3;

		int curSum = 0;
		int numOfSegments = 0;
		for (int i = 0; i < A.length; i++) {
			curSum += A[i];
			// check if we can form a segment
			if (curSum == sum && numOfSegments <= 1) {
				numOfSegments++;
				curSum = 0;
			}
		}
		// if we have 2 segments formed greedily and sum of leftover is also 1/3 of
		// overall sum
		return (numOfSegments == 2 && curSum == sum);
	}

	/* ------------ ------------MEDIUM SECTION ------------ ------------ */

	// O(N^2) : Along with Sorting NLogN
	public List<List<Integer>> threeSum(int[] nums) {

		Arrays.sort(nums);
		List<List<Integer>> ans = new ArrayList<>();

		for (int i = 0; i < nums.length; i++) {

			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}

			List<Integer> pair = new ArrayList<>();
			int j = i + 1;
			int k = nums.length - 1;

			while (j < k) {

				int sum = nums[i] + nums[j] + nums[k];

				if (sum < 0) {

					j++;
				} else if (sum > 0) {
					k--;
				} else {

					pair.add(nums[i]);
					pair.add(nums[j]);
					pair.add(nums[k]);
					ans.add(pair);
					j++;
					k--;
					pair = new ArrayList<>();
					while (j < k && nums[j - 1] == nums[j]) {
						j++;
					}

					while (j < k && nums[k + 1] == nums[k]) {
						k--;
					}
				}

			}
		}

		return ans;
	}

	// O(N^2) : Along with Sorting (Similar Approach)
	public int threeSumClosest(int[] nums, int target) {

		if (nums == null || nums.length < 3) {
			return 0;
		}

		Arrays.sort(nums);
		int Mindiff = Integer.MAX_VALUE;
		int resSum = 0;

		for (int i = 0; i < nums.length; i++) {

			int j = i + 1;
			int k = nums.length - 1;

			while (j < k) {

				int sum = nums[i] + nums[j] + nums[k];
				int diff = Math.abs(target - sum);

				if (diff == 0) {
					return sum;
				}

				if (diff < Mindiff) {
					Mindiff = diff;
					resSum = sum;
				}

				if (sum > target) {
					k--;
				} else {
					j++;
				}
			}
		}

		return resSum;
	}

	// O(LogN) : Smart Search
	public int search(int[] nums, int target) {

		int lo = 0;
		int hi = nums.length - 1;

		while (lo <= hi) {

			int mid = lo + ((hi - lo) / 2);

			if (nums[mid] == target) {
				return mid;
			}

			if (nums[mid] >= nums[lo]) {

				if (target >= nums[lo] && target < nums[mid]) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else {

				if (target > nums[mid] && target <= nums[hi]) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
		}

		return -1;
	}

	// O(N) : Along with Smart Work
	public static void nextPermutation(int[] nums) {

		if (nums == null || nums.length < 2) {
			return;
		}

		int idx1 = -1;
		int idx2 = -1;

		for (int i = nums.length - 2; i >= 0; i--) {

			if (nums[i] < nums[i + 1]) {
				idx1 = i;
				break;
			}
		}

		if (idx1 == -1) {
			reverse(nums, 0, nums.length - 1);
			return;
		}

		for (int i = nums.length - 1; i >= 0; i--) {

			if (nums[i] > nums[idx1]) {
				idx2 = i;
				break;
			}
		}

		int temp = nums[idx1];
		nums[idx1] = nums[idx2];
		nums[idx2] = temp;

		if (idx1 != nums.length - 1)
			reverse(nums, idx1 + 1, nums.length - 1);

	}

	// Helper Function : For Next Permutation
	public static void reverse(int[] nums, int i, int j) {

		while (i < j) {

			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;

			i++;
			j--;
		}
	}
}
