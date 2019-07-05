package Recursion;

import java.util.*;

public class Lec9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<String> restoreIpAddresses(String s) {

		List<String> ans = new ArrayList<>();

		int len = s.length();

		for (int i = 1; i <= 3; i++) {

			if (len - i > 9) {
				System.out.println(i);
				continue;
			}

			for (int j = i + 1; j <= i + 3; j++) {

				if (len - j > 6) {
					continue;
				}

				for (int k = j + 1; k < len && k <= j + 3; k++) {

					int a, b, c, d;
					a = Integer.parseInt(s.substring(0, i));
					b = Integer.parseInt(s.substring(i, j));
					c = Integer.parseInt(s.substring(j, k));
					d = Integer.parseInt(s.substring(k));

					if (a > 255 || b > 255 || c > 255 || d > 255) {

						continue;
					}

					String ip = a + "." + b + "." + c + "." + d;

					if (ip.length() < len + 3) {

						continue;
					}

					ans.add(ip);
				}
			}
		}

		return ans;
	}

	public List<List<Integer>> combinationSum2(int[] nums, int target) {

		Arrays.sort(nums);
		List<List<Integer>> ans = new ArrayList<>();
		combinationSum2(nums, 0, target, new ArrayList<>(), ans);
		return ans;
	}

	public static void combinationSum2(int[] nums, int vidx, int target, List<Integer> curr, List<List<Integer>> ans) {

		if (target == 0) {
			ans.add(new ArrayList<>(curr));
			return;
		}
		for (int i = vidx; i < nums.length; i++) {

			if (target - nums[i] >= 0 && (i == vidx || nums[i] != nums[i - 1])) {
				curr.add(nums[i]);
				combinationSum2(nums, i + 1, target - nums[i], curr, ans);
				curr.remove(curr.size() - 1);
			}
		}
	}

	public List<List<Integer>> subsetsWithDup(int[] nums) {

		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		subset2(nums, 0, new ArrayList<>(), res);

		return res;

	}

	public static void subset2(int[] nums, int vidx, List<Integer> curr, List<List<Integer>> res) {

		res.add(new ArrayList<>(curr));

		for (int i = vidx; i < nums.length; i++) {

			if (i == vidx || nums[i - 1] != nums[i]) {
				curr.add(nums[i]);
				subset2(nums, i + 1, curr, res);
				curr.remove(curr.size() - 1);
			}
		}
	}

	public boolean isScramble(String s1, String s2) {

		if (s1.length() != s2.length()) {
			return false;
		}

		if (s1 == s2) {
			return true;
		}

		if (s1.length() == 1) {

			return s1.charAt(0) == s2.charAt(0);
		}

		char[] dict = new char[256];
		for (int i = 0; i < s1.length(); i++) {

			dict[s1.charAt(i)]++;
			dict[s2.charAt(i)]--;
		}

		for (int i = 0; i < 256; i++) {

			if (dict[i] != 0)
				return false;
		}

		for (int i = 1; i < s2.length(); i++) {

			String s11 = s1.substring(0, i);
			String s21 = s2.substring(0, i);
			String s22 = s2.substring(i);
			String s12 = s1.substring(i);

			if (isScramble(s11, s21) && isScramble(s12, s22)) {
				return true;
			}

			s22 = s2.substring(0, s1.length() - i);
			s21 = s2.substring(s1.length() - i);

			if (isScramble(s11, s21) && isScramble(s12, s22)) {
				return true;
			}
		}

		return false;

	}

	static final int[] watch = { 1, 2, 4, 8, 1, 2, 4, 8, 16, 32 };

	public List<String> readBinaryWatch(int num) {

		List<String> ans = new ArrayList<>();

		if (num >= 0) {

			dfs(num, 0, ans, 0, 0);
		}

		return ans;

	}

	public static void dfs(int num, int start, List<String> list, int hour, int min) {

		if (num <= 0) {

			if (hour < 12 && min < 60) {

				if (min < 10) {
					list.add(hour + ":0" + min);
				} else {
					list.add(hour + ":" + min);
				}

				return;
			}

		}

		for (int i = start; i < watch.length; i++) {

			if (i < 4) {
				dfs(num - 1, i + 1, list, hour + watch[i], min);
			} else {
				dfs(num - 1, i + 1, list, hour, min + watch[i]);
			}
		}

	}

	public List<String> letterCasePermutation(String str) {
		List<String> ans = new ArrayList<>();

		return dfs(str.toCharArray(), 0, ans);

	}

	public List<String> dfs(char[] str, int vidx, List<String> ans) {

		ans.add(String.valueOf(str));

		if (vidx >= str.length) {
			return ans;
		}

		for (int i = vidx; i < str.length; i++) {

			if (Character.isAlphabetic(str[i])) {

				str[i] ^= 32;
				dfs(str, i + 1, ans);
				str[i] ^= 32;
			}
		}

		return ans;

	}

	public List<List<Integer>> subsets(int[] arr) {

		List<List<Integer>> ans = new ArrayList<>();

		subset_dfs(arr, 0, new ArrayList<>(), ans);

		return ans;
	}

	public static void subset_dfs(int[] arr, int vidx, List<Integer> curr, List<List<Integer>> ans) {

		ans.add(new ArrayList<>(curr));

		for (int i = vidx; i < arr.length; i++) {

			curr.add(arr[i]);
			subset_dfs(arr, i + 1, curr, ans);
			curr.remove(curr.size() - 1);
		}
	}

	public List<List<Integer>> permute(int[] nums) {

		List<List<Integer>> ans = new ArrayList<>();
		helper(nums, ans, new ArrayList<>());

		return ans;
	}

	public void helper(int[] nums, List<List<Integer>> ans, List<Integer> temp) {

		if (temp.size() == nums.length) {
			ans.add(new ArrayList<>(temp));
			return;
		}

		for (int i = 0; i < nums.length; i++) {

			if (temp.contains(nums[i]))
				continue;

			temp.add(nums[i]);
			helper(nums, ans, temp);
			temp.remove(temp.size() - 1);
		}
	}

	public List<List<Integer>> permuteUnique(int[] nums) {

		List<List<Integer>> ans = new ArrayList<>();
		Arrays.sort(nums);
		helperRepeat(nums, ans, new ArrayList<>(), new boolean[nums.length]);
		return ans;
	}

	public static void helperRepeat(int[] nums, List<List<Integer>> ans, List<Integer> temp, boolean[] used) {

		if (temp.size() == nums.length) {

			ans.add(new ArrayList<>(temp));
			return;

		}

		for (int i = 0; i < nums.length; i++) {

			if (used[i] || i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
				continue;
			}

			used[i] = true;
			temp.add(nums[i]);
			helperRepeat(nums, ans, temp, used);
			temp.remove(temp.size() - 1);
			used[i] = false;
		}
	}

	public String getPermutation(int n, int k) {

		List<Integer> numbers = new ArrayList<>();
		int[] fact = new int[n + 1];
		StringBuilder sb = new StringBuilder();

		int sum = 1;
		fact[0] = 1;

		for (int i = 1; i <= n; i++) {

			sum *= i;
			fact[i] = sum;
		}

		for (int i = 1; i <= n; i++) {
			numbers.add(i);
		}

		k--;

		for (int i = 1; i <= n; i++) {

			int idx = k / (fact[n - i]);
			sb.append(String.valueOf(numbers.get(idx)));
			numbers.remove(idx);

			k -= (idx) * fact[n - i];
		}

		return String.valueOf(sb);
	}

	public List<List<Integer>> combine(int n, int k) {

		List<List<Integer>> combs = new ArrayList<>();
		dfs(combs, new ArrayList<>(), 1, n, k);

		return combs;
	}

	public static void dfs(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {

		if (k == 0) {
			combs.add(new ArrayList<>(comb));
		} else {

			for (int i = start; i <= n; i++) {

				comb.add(i);
				dfs(combs, comb, i + 1, n, k - 1);
				comb.remove(comb.size() - 1);
			}

		}
	}
}
