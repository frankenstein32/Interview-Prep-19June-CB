package Greedy;

import java.util.*;

public class Lec10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean lemonadeChange(int[] bills) {
		int coin5 = 0;
		int coin10 = 0;

		for (int i = 0; i < bills.length; i++) {

			if (bills[i] == 5)
				coin5++;
			else if (bills[i] == 10) {
				coin5--;
				coin10++;
			} else if (coin10 > 0) {
				coin5--;
				coin10--;
			} else
				coin5 -= 3;

			if (coin5 < 0) {
				return false;
			}
		}

		return true;
	}

	public int findContentChildren(int[] g, int[] s) {

		Arrays.sort(g);
		Arrays.sort(s);

		int i = 0, j = 0;
		int res = 0;
		while (i < g.length && j < s.length) {

			if (g[i] <= s[j]) {
				i++;
				res++;
			}
			j++;
		}

		return res;
	}

	public int minDeletionSize(String[] A) {

		int res = 0;

		for (int col = 0; col < A[0].length(); col++) {
			for (int row = 0; row < A.length - 1; row++) {
				if (A[row].charAt(col) > A[row + 1].charAt(col)) {
					res++;
					break;
				}
			}
		}

		return res;

	}

	public int largestSumAfterKNegations(int[] A, int k) {

		Arrays.sort(A);
		int minPos = Integer.MAX_VALUE;
		int sum = 0;
		int cnt = 0;

		for (int num : A) {

			if (num < 0 && ++cnt <= k) {

				num = -num;
			}

			sum += num;
			minPos = Math.min(minPos, num);
		}

		if (cnt > k || (k - cnt) % 2 == 0) {

			return sum;
		} else {

			return sum - minPos * 2;
		}

	}

	public int twoCitySchedCost(int[][] costs) {
		Arrays.sort(costs, new Comparator<int[]>() {

			@Override
			public int compare(final int[] entry1, final int[] entry2) {
				return (entry1[1] - entry1[0]) - (entry2[1] - entry2[0]);
			}
		});

		for (int[] arr : costs) {
			for (int val : arr) {
				System.out.print(val + " ");
			}
			System.out.println();
		}

		int sum = 0;
		int n = costs.length;
		for (int i = 0; i < costs.length / 2; i++) {

			sum += costs[i][1] + costs[n - i - 1][0];
		}

		return sum;
	}

	public int canCompleteCircuit(int[] gas, int[] cost) {

		int tank = 0;

		for (int i = 0; i < gas.length; i++)
			tank += gas[i] - cost[i];

		if (tank < 0)
			return -1;

		int accumulate = 0;
		int start = 0;

		for (int i = 0; i < gas.length; i++) {

			int curGain = gas[i] - cost[i];

			if (accumulate + curGain < 0) {
				accumulate = 0;
				curGain = 0;
				start = i + 1;
			} else {
				accumulate += curGain;
			}
		}

		return start;
	}

	public boolean isSubsequence(String s, String t) {
		if (s.length() == 0)
			return true;
		int indexS = 0, indexT = 0;
		while (indexT < t.length()) {
			if (t.charAt(indexT) == s.charAt(indexS)) {
				indexS++;
				if (indexS == s.length())
					return true;
			}
			indexT++;
		}
		return false;
	}

	public int[][] reconstructQueue(int[][] people) {

		Arrays.sort(people, new Comparator<int[]>() {

			@Override
			public int compare(int[] a, int[] b) {

				return a[0] != b[0] ? a[0] - b[0] : b[1] - a[1];
			}
		});

		ArrayList<Integer> rem = new ArrayList<>();

		int[][] ans = new int[people.length][2];
		for (int i = 0; i < people.length; i++) {

			rem.add(i);
		}

		for (int i = 0; i < people.length; i++) {

			int index = rem.get(people[i][1]);
			ans[index][0] = people[i][0];
			ans[index][1] = people[i][1];

			rem.remove(people[i][1]);
		}

		return ans;
	}

	public int eraseOverlapIntervals(int[][] intervals) {

		if (intervals.length == 0)
			return 0;

		Arrays.sort(intervals, new Comparator<int[]>() {

			@Override
			public int compare(int[] a, int[] b) {

				return a[1] - b[1];
			}
		});

		int count = 0;
		int end = intervals[0][1];

		for (int i = 1; i < intervals.length; i++) {

			if (intervals[i][0] >= end) {
				end = intervals[i][1];
				count++;
			}
		}

		return intervals.length - count - 1;
	}

	public int findMinArrowShots(int[][] points) {

		if (points.length == 0) {
			return 0;
		}

		Arrays.sort(points, new Comparator<int[]>() {

			@Override
			public int compare(int[] a, int[] b) {

				return a[1] - b[1];
			}
		});

		int curPos = points[0][1];
		int count = 1;

		for (int i = 1; i < points.length; i++) {

			if (curPos >= points[i][0]) {
				continue;
			}

			count++;
			curPos = points[i][1];
		}

		return count;
	}

	public boolean carPooling(int[][] trips, int capacity) {

		int[] stops = new int[1001];

		for (int i = 0; i < trips.length; i++) {

			stops[trips[i][1]] += trips[i][0];
			stops[trips[i][2]] -= trips[i][0];
		}

		for (int i = 0; i < 1001 && capacity >= 0; i++) {

			capacity -= stops[i];
		}

		return capacity >= 0;
	}

	public List<Integer> splitIntoFibonacci(String s) {

		ArrayList<Integer> ans = new ArrayList<>();
		helper(s, 0, ans);

		return ans;
	}

	public static boolean helper(String s, int idx, ArrayList<Integer> ans) {

		if (idx == s.length() && ans.size() >= 3)
			return true;

		for (int i = idx; i < s.length(); i++) {

			if (s.charAt(idx) == '0' && i > idx)
				break;

			long num = Long.parseLong(s.substring(idx, i + 1));

			if (num > Integer.MAX_VALUE) {
				break;
			}
			if (ans.size() >= 2 && num > ans.get(ans.size() - 1) + ans.get(ans.size() - 2)) {
				break;
			}

			if (ans.size() <= 1 || ans.get(ans.size() - 1) + ans.get(ans.size() - 2) == num) {

				ans.add((int) num);

				if (helper(s, i + 1, ans))
					return true;

				ans.remove(ans.size() - 1);
			}
		}

		return false;
	}

	public int monotoneIncreasingDigits(int N) {

		char[] ch = Integer.toString(N).toCharArray();

		int i = 0, n = ch.length;

		while (i < n - 1 && ch[i] <= ch[i + 1]) {
			i++;
		}

		if (i == n - 1)
			return N;
		while (i > 0 && ch[i - 1] == ch[i])
			i--;

		ch[i++]--;

		while (i < n)
			ch[i++] = '9';

		return Integer.valueOf(new String(ch));
	}

	public int matrixScore(int[][] A) {

		int R = A.length, C = A[0].length;
		int ans = 0;
		for (int c = 0; c < C; c++) {

			int col_sum = 0;
			for (int r = 0; r < R; r++) {

				col_sum = col_sum + (A[r][c] ^ A[r][0]);
			}

			ans += Math.max(col_sum, R - col_sum) * (1 << C - 1 - c);
		}

		return ans;
	}

	public int bagOfTokensScore(int[] tokens, int P) {

		Arrays.sort(tokens);

		int lo = 0;
		int hi = tokens.length - 1;
		int ans = 0, points = 0;

		while (lo <= hi && (P >= tokens[lo] || points > 0)) {

			while (lo <= hi && P >= tokens[lo]) {
				points++;
				P -= tokens[lo++];
			}

			ans = Math.max(ans, points);

			if (lo <= hi && points > 0) {
				points--;
				P += tokens[hi--];
			}
		}

		return ans;
	}
}
