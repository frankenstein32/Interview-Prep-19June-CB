package DP;

import java.util.Arrays;

public class Lec11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean JumpGame(int[] arr, int vidx) {

		if (vidx >= arr.length - 1) {
			return true;
		}

		for (int step = 1; step <= arr[vidx]; step++) {

			boolean rr = JumpGame(arr, vidx + step);

			if (rr)
				return true;
		}

		return false;

	}

	public boolean canJump(int[] nums) {

		boolean[] strg = new boolean[nums.length];

		for (int i = strg.length - 1; i >= 0; i--) {

			if (i == strg.length - 1) {
				strg[i] = true;
				continue;
			}

			int steps = nums[i];
			for (int step = 1; step <= steps; step++) {

				if (i + step < strg.length && strg[i + step]) {
					strg[i] = true;
					break;
				}
			}

		}

		return strg[0];
	}

	// Greeedy
	public boolean canJump_greedy(int[] nums) {
		int lastPos = nums.length - 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (i + nums[i] >= lastPos) {
				lastPos = i;
			}
		}
		return lastPos == 0;
	}

	public int minCostClimbingStairs(int[] cost) {

		return helper_stairsTD(cost, 0, new int[cost.length]);
	}

	public static int helper_stairsTD(int[] cost, int vidx, int[] strg) {

		if (vidx >= cost.length - 1) {
			return 0;
		}

		if (strg[vidx] != 0) {
			return strg[vidx];
		}

		int profit_1 = cost[vidx] + helper_stairsTD(cost, vidx + 1, strg);
		int profit_2 = cost[vidx + 1] + helper_stairsTD(cost, vidx + 2, strg);

		strg[vidx] = Math.min(profit_1, profit_2);
		return strg[vidx];

	}

	public int minCostClimbingStairsBU(int[] nums) {

		int[] cost = new int[nums.length + 1];

		for (int i = nums.length - 1; i >= 0; i--) {

			if (i >= nums.length - 1) {

				cost[i] = 0;
				continue;
			}

			cost[i] = Math.min(nums[i] + cost[i + 1], nums[i + 1] + cost[i + 2]);
		}

		return cost[0];

	}

	public int minCostClimbingStairsBUSE(int[] nums) {

		int one = 0;
		int two = 0;
		for (int i = nums.length - 2; i >= 0; i--) {

			int fstair = nums[i] + one;
			int Sstair = nums[i + 1] + two;

			two = one;
			one = Math.min(fstair, Sstair);

		}

		return one;
	}

	public int jump2(int[] nums) {

		int[] strg = new int[nums.length];

		for (int i = strg.length - 2; i >= 0; i--) {

			int steps = nums[i];
			int ans = Integer.MAX_VALUE;
			for (int step = 1; step <= steps; step++) {

				if (i + step < nums.length) {
					ans = Math.min(ans, strg[i + step]);
				}
			}
			if (ans != Integer.MAX_VALUE)
				strg[i] = ans + 1;
			else
				strg[i] = ans;

		}

		return strg[0];
	}

	public static int jump2TD(int[] nums, int vidx, int[] strg) {

		if (vidx >= nums.length) {
			return Integer.MAX_VALUE;
		}

		if (vidx == nums.length - 1) {
			return 0;
		}

		if (strg[vidx] != 0) {
			return strg[vidx];
		}

		int res = Integer.MAX_VALUE;
		int steps = nums[vidx];

		for (int step = 1; step <= steps; step++) {

			if (vidx + step < nums.length)
				res = Math.min(res, jump2TD(nums, vidx + step, strg));
		}

		if (res != Integer.MAX_VALUE) {
			strg[vidx] = res + 1;
			return res + 1;
		} else {
			strg[vidx] = res;
			return res;
		}
	}

	public int minPathSum(int[][] grid) {

		return helperMinPathTD(grid, 0, 0, new int[grid.length][grid[0].length]);
	}

	public static int helperMinPathTD(int[][] grid, int cr, int cc, int[][] strg) {

		if (cr == grid.length - 1 && cc == grid[0].length - 1) {
			return grid[cr][cc];
		}

		if (cr >= grid.length || cc >= grid[0].length) {
			return 100000;
		}

		if (strg[cr][cc] != 0) {
			return strg[cr][cc];
		}

		int c1 = helperMinPathTD(grid, cr + 1, cc, strg);
		int c2 = helperMinPathTD(grid, cr, cc + 1, strg);

		return strg[cr][cc] = Math.min(c1, c2) + grid[cr][cc];
	}

	public static int helperMinPathBU(int[][] grid) {

		int[][] strg = new int[grid.length + 1][grid[0].length + 1];

		for (int cr = grid.length; cr >= 0; cr--) {
			for (int cc = grid[0].length; cc >= 0; cc--) {

				if (cr == grid.length - 1 && cc == grid[0].length - 1) {

					strg[cr][cc] = grid[cr][cc];
					continue;
				}

				if (cr == grid.length || cc == grid[0].length) {
					strg[cr][cc] = 100000;
					continue;
				}

				strg[cr][cc] = Integer.min(strg[cr + 1][cc], strg[cr][cc + 1]) + grid[cr][cc];

			}
		}

		return strg[0][0];

	}

	public static int helperMinPathBUSE(int[][] grid) {

		int[] strg = new int[grid[0].length + 1];

		for (int cr = grid.length; cr >= 0; cr--) {
			for (int cc = grid[0].length; cc >= 0; cc--) {

				if (cr == grid.length - 1 && cc == grid[0].length - 1) {

					strg[cc] = grid[cr][cc];
					continue;
				}

				if (cr == grid.length || cc == grid[0].length) {
					strg[cc] = 100000;
					continue;
				}

				strg[cc] = Integer.min(strg[cc], strg[cc + 1]) + grid[cr][cc];
			}
		}

		return strg[0];
	}

	public int uniquePathsWithObstacles(int[][] arr) {

		if (arr.length == 1 && arr[0].length == 1) {

			if (arr[0][0] == 0) {
				return 1;
			} else {
				return 0;
			}
		}

		return helperTD(arr, 0, 0, new int[arr.length][arr[0].length]);
	}

	public int helperTD(int[][] arr, int cr, int cc, int[][] strg) {

		if (cr >= arr.length || cc >= arr[0].length || arr[cr][cc] == 1)
			return 0;

		if (cr == arr.length - 1 && cc == arr[0].length - 1) {
			return 1;
		}

		if (strg[cr][cc] != 0)
			return strg[cr][cc];

		int cnt = 0;

		cnt += helperTD(arr, cr + 1, cc, strg);
		cnt += helperTD(arr, cr, cc + 1, strg);

		strg[cr][cc] = cnt;
		return cnt;

	}

	public int uniquePathsWithObstaclesBU(int[][] arr) {

		int[][] strg = new int[arr.length + 1][arr[0].length + 1];

		for (int i = arr.length - 1; i >= 0; i--) {

			for (int j = arr[0].length - 1; j >= 0; j--) {

				if (arr[i][j] == 1) {
					continue;
				}

				if (i == arr.length - 1 && j == arr[0].length - 1) {
					strg[i][j] = 1;
					continue;
				}

				strg[i][j] = strg[i + 1][j] + strg[i][j + 1];
			}
		}

		return strg[0][0];

	}

	public int uniquePathsWithObstaclesBUSE(int[][] arr) {

		int[] strg = new int[arr[0].length + 1];

		for (int i = arr.length - 1; i >= 0; i--) {

			for (int j = arr[0].length - 1; j >= 0; j--) {

				if (arr[i][j] == 1) {
					strg[j] = 0;
					continue;
				}

				if (i == arr.length - 1 && j == arr[0].length - 1) {
					strg[j] = 1;

					continue;
				}

				strg[j] = strg[j] + strg[j + 1];
			}

		}

		return strg[0];
	}

	public int rob(int[] nums) {

		int[] strg = new int[nums.length];
		Arrays.fill(strg, -1);
		return houseRobber(nums, 0, strg);
	}

	public static int houseRobber(int[] amt, int vidx, int[] strg) {

		if (vidx >= amt.length) {
			return 0;
		}

		if (strg[vidx] != -1)
			return strg[vidx];

		int spared = houseRobber(amt, vidx + 1, strg);
		int robbed = houseRobber(amt, vidx + 2, strg) + amt[vidx];

		return strg[vidx] = Math.max(spared, robbed);
	}

	public static int helperBU(int[] nums) {

		int n = nums.length;
		int[] strg = new int[n + 2];

		for (int i = n - 1; i >= 0; i--) {

			if (i == n - 1) {
				strg[i] = nums[i];
				continue;
			}

			strg[i] = Math.max(strg[i + 2] + nums[i], strg[i + 3] + nums[i + 1]);
		}

		return strg[0];
	}

	public int robBU(int[] amt) {

		int[] strg = new int[amt.length + 2];

		for (int i = amt.length - 1; i >= 0; i--) {

			int spared = strg[i + 1];
			int robbed = strg[i + 2] + amt[i];

			strg[i] = Math.max(spared, robbed);
		}

		return strg[0];
	}

	public boolean stoneGame(int[] piles) {

		return gameOnIntTD(piles, 0, piles.length - 1, true, new int[piles.length][piles.length]) > 0;
	}

	public static int gameOnIntTD(int[] piles, int si, int ei, boolean turn, int[][] strg) {

		if (si > ei) {
			return 0;
		}

		if (strg[si][ei] > 0) {
			return strg[si][ei];
		}

		if (turn) {

			int rr1 = gameOnIntTD(piles, si + 1, ei, false, strg) + piles[si];
			int rr2 = gameOnIntTD(piles, si, ei - 1, false, strg) + piles[ei];

			strg[si][ei] = Math.max(rr1, rr2);
			return strg[si][ei];
		}

		int rr1 = gameOnIntTD(piles, si + 1, ei, true, strg) - piles[si];
		int rr2 = gameOnIntTD(piles, si, ei - 1, true, strg) - piles[ei];

		strg[si][ei] = Math.min(rr1, rr2);
		return strg[si][ei];

	}

	public static int gameOnIntBU(int[] piles) {

		int[][] strg = new int[piles.length][piles.length];
		boolean turn = false;

		for (int slide = 0; slide < piles.length; slide++) {

			for (int si = 0; si < piles.length - slide; si++) {

				int ei = slide + si;

				if (si == ei) {
					strg[si][ei] = -piles[si];
					continue;
				}

				if (turn) {

					strg[si][ei] = Math.max(strg[si + 1][ei] + piles[si], strg[si][ei - 1] + piles[ei]);
				} else {

					strg[si][ei] = Math.min(strg[si + 1][ei] - piles[si], strg[si][ei - 1] - piles[ei]);
				}
			}

			turn = !turn;
		}
		return strg[0][strg.length - 1];

	}

	public static int gameOnIntBUSE(int[] piles) {

		int[] strg = new int[piles.length];

		strg[piles.length - 1] = piles[piles.length - 1];

		for (int si = piles.length - 1; si >= 0; si--) {

			boolean turn = false;

			for (int ei = si; ei < piles.length; ei++) {

				if (si == ei) {

					strg[ei] = piles[ei];
					turn = !turn;
					continue;
				}

				if (turn) {

					strg[ei] = Math.max(strg[ei] + piles[ei], strg[ei - 1] + piles[ei - 1]);
				} else {

					strg[ei] = Math.min(strg[ei] - piles[ei], strg[ei - 1] - piles[ei - 1]);
				}

				turn = !turn;
			}

		}

		return strg[strg.length - 1];
	}

	public int minFallingPathSum(int[][] matrix) {

		int ans = Integer.MAX_VALUE;

		int[][] strg = new int[matrix.length][matrix[0].length];
		for (int col = 0; col < matrix[0].length; col++) {

			ans = Math.min(ans, helperTD(matrix, 0, col, strg));
		}
		return ans;
	}

	public static int FallSumTD(int[][] matrix, int row, int col, int[][] strg) {

		if (row >= matrix.length || col >= matrix[0].length || col < 0) {
			return Integer.MAX_VALUE;
		}

		if (row == matrix.length - 1) {
			return matrix[row][col];
		}

		if (strg[row][col] != 0) {
			return strg[row][col];
		}

		int res = 0;

		int prev = 0, curr = 0, next = 0;
		prev = FallSumTD(matrix, row + 1, col - 1, strg);
		curr = FallSumTD(matrix, row + 1, col, strg);
		next = FallSumTD(matrix, row + 1, col + 1, strg);
		res = Math.min(prev, Math.min(curr, next)) + matrix[row][col];

		strg[row][col] = res;
		return res;

	}

	public static int FallSumBU(int[][] matrix) {

		int[][] strg = new int[matrix.length][matrix[0].length];

		int res = Integer.MAX_VALUE;

		for (int row = matrix.length - 1; row >= 0; row--) {

			for (int col = matrix[0].length - 1; col >= 0; col--) {

				if (row == matrix.length - 1) {
					strg[row][col] = matrix[row][col];
					continue;
				}

				int ans = Integer.MAX_VALUE;
				if (col - 1 >= 0) {
					ans = Math.min(ans, strg[row + 1][col - 1]);
				}

				ans = Math.min(ans, strg[row + 1][col]);

				if (col + 1 < matrix[0].length) {
					ans = Math.min(ans, strg[row + 1][col + 1]);
				}

				strg[row][col] = ans + matrix[row][col];

			}
		}

		for (int col = 0; col < matrix[0].length; col++) {

			res = Math.min(res, strg[0][col]);
		}
		return res;

	}

	public static int FallSumTDBUSE(int[][] matrix) {

		int[] strg = new int[matrix[0].length];

		for (int row = matrix.length - 1; row >= 0; row--) {

			int temp = strg[matrix[0].length - 1];
			for (int col = matrix[0].length - 1; col >= 0; col--) {

				if (row == matrix.length - 1) {
					strg[col] = matrix[row][col];
					continue;
				}

				int prev = Integer.MAX_VALUE;
				int curr = Integer.MAX_VALUE;
				int next = temp;

				if (col - 1 >= 0) {

					prev = strg[col - 1];
				}

				curr = strg[col];

				if (col < strg.length) {
					next = temp;
					temp = strg[col];
				}

				strg[col] = Math.min(curr, Math.min(next, prev)) + matrix[row][col];
			}

		}

		int res = Integer.MAX_VALUE;

		for (int col = 0; col < matrix[0].length; col++) {

			res = Math.min(res, strg[col]);
		}

		return res;

	}

	public int minimumDeleteSum(String s1, String s2) {

		return minimumDeleteSumTD(s1, s2, new int[s1.length() + 1][s2.length() + 1]);
	}

	public static int minimumDeleteSumTD(String s1, String s2, int[][] strg) {

		if (s1.length() == 0 || s2.length() == 0) {

			int sum = 0;

			for (int i = 0; i < s1.length(); i++) {

				sum += s1.charAt(i);
			}

			for (int i = 0; i < s2.length(); i++) {
				sum += s2.charAt(i);
			}

			return sum;

		}

		if (strg[s1.length()][s2.length()] != 0) {
			return strg[s1.length()][s2.length()];
		}

		if (s1.charAt(0) == s2.charAt(0)) {

			strg[s1.length()][s2.length()] = minimumDeleteSumTD(s1.substring(1), s2.substring(1), strg);
			return strg[s1.length()][s2.length()];
		} else {

			int firstPart = minimumDeleteSumTD(s1.substring(1), s2, strg) + s1.charAt(0);
			int secondPart = minimumDeleteSumTD(s1, s2.substring(1), strg) + s2.charAt(0);

			strg[s1.length()][s2.length()] = Math.min(firstPart, secondPart);
			return strg[s1.length()][s2.length()];
		}

	}

	public static int minimumDeleteSumBU(String s1, String s2) {

		int[][] strg = new int[s1.length() + 1][s2.length() + 1];

		for (int i = s1.length(); i >= 0; i--) {

			for (int j = s2.length(); j >= 0; j--) {

				if (i == s1.length() || j == s2.length()) {

					int sum = 0;

					for (int k = i; k < s1.length(); k++) {

						sum += s1.charAt(k);
					}

					for (int k = j; k < s2.length(); k++) {
						sum += s2.charAt(k);
					}

					strg[i][j] = sum;
					continue;
				}

				if (s1.charAt(i) == s2.charAt(j)) {

					strg[i][j] = strg[i + 1][j + 1];
				} else {

					int firstPart = strg[i + 1][j] + s1.charAt(i);
					int secondPart = strg[i][j + 1] + s2.charAt(j);

					strg[i][j] = Math.min(firstPart, secondPart);
				}

			}
		}

		return strg[0][0];
	}

	public static int minimumDeleteSumBUSE(String s1, String s2) {

		int[] strg = new int[s2.length() + 1];

		for (int i = s1.length(); i >= 0; i--) {

			int save = 0;
			for (int j = s2.length(); j >= 0; j--) {

				if (i == s1.length() || j == s2.length()) {

					int sum = 0;

					for (int k = i; k < s1.length(); k++) {

						sum += s1.charAt(k);
					}

					for (int k = j; k < s2.length(); k++) {
						sum += s2.charAt(k);
					}

					save = strg[j];
					strg[j] = sum;
					continue;
				}

				if (s1.charAt(i) == s2.charAt(j)) {

					int temp = save;
					save = strg[j];
					strg[j] = temp;
				} else {

					int firstPart = strg[j] + s1.charAt(i);
					int secondPart = strg[j + 1] + s2.charAt(j);

					save = strg[j];
					strg[j] = Math.min(firstPart, secondPart);
				}
			}
		}

		return strg[0];
	}

	public int DungeonG_TD(int[][] dungeon, int cr, int cc, int[][] strg) {

		if (cr == dungeon.length - 1 && cc == dungeon[0].length - 1) {
			return Math.max(1, 1 - dungeon[cr][cc]);
		}

		if (cr >= dungeon.length || cc >= dungeon[0].length) {
			return Integer.MAX_VALUE;
		}

		if (strg[cr][cc] != -1) {
			return strg[cr][cc];
		}

		int minVertical = DungeonG_TD(dungeon, cr + 1, cc, strg);
		int minHorizontal = DungeonG_TD(dungeon, cr, cc + 1, strg);

		return strg[cr][cc] = Math.max(1, Math.min(minVertical, minHorizontal) - dungeon[cr][cc]);

	}

	public int DungeonG_BU(int[][] dungeon) {

		int[][] strg = new int[dungeon.length + 1][dungeon[0].length + 1];

		for (int i = dungeon.length - 1; i >= 0; i--) {

			for (int j = dungeon[0].length - 1; j >= 0; j--) {

				if (i == dungeon.length - 1 && j == dungeon[0].length - 1) {
					strg[i][j] = Math.max(1, 1 - dungeon[i][j]);
					continue;
				}

				if (i == dungeon.length - 1) {
					strg[i][j] = Math.max(1, strg[i][j + 1] - dungeon[i][j]);
					continue;

				}

				if (j == dungeon[0].length - 1) {
					strg[i][j] = Math.max(1, strg[i + 1][j] - dungeon[i][j]);
					continue;
				}

				strg[i][j] = Math.max(1, Math.min(strg[i + 1][j], strg[i][j + 1]) - dungeon[i][j]);
			}
		}

		return strg[0][0];
	}

	public int DungeonG_BUSE(int[][] dungeon) {

		int[] strg = new int[dungeon[0].length + 1];

		for (int i = dungeon.length - 1; i >= 0; i--) {

			for (int j = dungeon[0].length - 1; j >= 0; j--) {

				if (i == dungeon.length - 1 && j == dungeon[0].length - 1) {
					strg[j] = Math.max(1, 1 - dungeon[i][j]);
					continue;
				}

				if (i == dungeon.length - 1) {
					strg[j] = Math.max(1, strg[j + 1] - dungeon[i][j]);
					continue;

				}

				if (j == dungeon[0].length - 1) {
					strg[j] = Math.max(1, strg[j] - dungeon[i][j]);
					continue;
				}

				strg[j] = Math.max(1, Math.min(strg[j], strg[j + 1]) - dungeon[i][j]);
			}
		}

		return strg[0];
	}

	public int findPaths(int er, int ec, int moves, int cr, int cc) {

		long[][][] strg = new long[moves + 1][er][ec];

		for (long[][] arr : strg) {
			for (long[] item : arr)
				Arrays.fill(item, -1);
		}

		return (int) boundaryPath(cr, cc, er, ec, moves, strg);
	}

	public static long boundaryPath(int cr, int cc, int er, int ec, int moves, long[][][] strg) {

		if (cr < 0 || cc < 0 || cr >= er || cc >= ec) {

			return 1;
		}

		if (moves == 0) {
			return 0;
		}

		if (strg[moves][cr][cc] != -1) {

			return strg[moves][cr][cc];
		}

		long cnt = 0;
		cnt += boundaryPath(cr + 1, cc, er, ec, moves - 1, strg);
		cnt += boundaryPath(cr - 1, cc, er, ec, moves - 1, strg);
		cnt += boundaryPath(cr, cc + 1, er, ec, moves - 1, strg);
		cnt += boundaryPath(cr, cc - 1, er, ec, moves - 1, strg);

		return strg[moves][cr][cc] = (cnt % 1_000_000_007L);
		// return cnt;

	}

	public static long boundaryPath_SE(int srow, int scol, int er, int ec, int moves) {

		long[][] strg = new long[er][ec];
		long m = 1_000_000_007L;

		for (int move = 1; move <= moves; move++) {

			long[][] temp = new long[er][ec];

			for (int cr = er - 1; cr >= 0; cr--) {

				for (int cc = ec - 1; cc >= 0; cc--) {

					long ans = 0;

					if (cr + 1 >= er) {
						ans = (ans + 1) % m;
					} else {
						ans = (ans + strg[cr + 1][cc]) % m;
					}

					if (cr - 1 < 0) {
						ans = (ans + 1) % m;
					} else {
						ans = (ans + strg[cr - 1][cc]) % m;
					}

					if (cc + 1 >= ec) {
						ans = (ans + 1) % m;
					} else {
						ans = (ans + strg[cr][cc + 1]) % m;
					}

					if (cc - 1 < 0) {
						ans = (ans + 1) % m;
					} else {
						ans = (ans + strg[cr][cc - 1]) % m;
					}

					temp[cr][cc] = ans;

				}
			}

			strg = temp;
		}

		return strg[srow][scol];
	}

	public static long boundaryPath_SE2(int srow, int scol, int er, int ec, int moves) {

		long[][] strg = new long[er][ec];
		long m = 1_000_000_007L;
		int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		for (int move = 1; move <= moves; move++) {

			long[][] temp = new long[er][ec];

			for (int cr = er - 1; cr >= 0; cr--) {

				for (int cc = ec - 1; cc >= 0; cc--) {

					long res = 0;
					for (int[] d : dirs) {

						int r = cr + d[0];
						int c = cc + d[1];

						if (r < 0 || r >= er || c < 0 || c >= ec) {
							res++;
						} else {
							temp[cr][cc] = (temp[cr][cc] + strg[r][c]) % m;
						}
					}
					temp[cr][cc] = (temp[cr][cc] + res) % m;

				}
			}

			strg = temp;
		}

		return strg[srow][scol];
	}

}
