package Stacks;

import java.util.Stack;

import Stacks.Lec5.pair;

public class Lec6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// O(N) : Next Greater ELement
	public int[] dailyTemperature(int[] nums) {

		int[] res = new int[nums.length];

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < nums.length; i++) {

			while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {

				int prevDay = stack.pop();
				int curDay = i;
				res[i] = curDay - prevDay;
			}

			stack.push(i);
		}

		while (!stack.isEmpty()) {

			res[stack.pop()] = 0;
		}

		return res;
	}

	// O(N) : Branching Statement
	public int[] asteroidCollision(int[] asteroids) {

		Stack<Integer> stack = new Stack<>();

		for (int ast : asteroids) {

			collisions: {
				while (!stack.isEmpty() && ast < 0 && stack.peek() > 0) {

					if (stack.peek() < -ast) {
						stack.pop();
						continue;
					} else if (stack.peek() == -ast) {
						stack.pop();
					}

					break collisions;
				}

				stack.push(ast);
			}
		}

		int n = stack.size();
		int[] res = new int[n];

		while (!stack.isEmpty()) {
			res[--n] = stack.pop();
		}

		return res;

	}

	// O(N) : Circular Array
	public int[] nextGreater2(int[] nums) {

		int[] res = new int[nums.length];

		Stack<Integer> stack = new Stack<>();

		for (int i = 2 * nums.length - 1; i >= 0; i--) {

			while (!stack.isEmpty() && nums[stack.peek()] <= nums[i % nums.length]) {
				stack.pop();
			}

			res[i % nums.length] = (stack.isEmpty() ? -1 : nums[stack.peek()]);
			stack.push(i % nums.length);
		}

		return res;

	}

	// Helper class for FindPattern Problem
	public static class pair {

		int min;
		int max;

		public pair(int min, int max) {
			this.min = min;
			this.max = max;
		}
	}

	// O(N) : Generic Stack
	public static boolean findPattern132(int[] nums) {

		Stack<pair> stack = new Stack<>();

		for (int i = 0; i < nums.length; i++) {

			if (stack.isEmpty() || stack.peek().min > nums[i]) {
				stack.push(new pair(nums[i], nums[i]));
			} else if (stack.peek().min < nums[i]) {

				pair max = stack.pop();

				if (nums[i] < max.max)
					return true;

				max.max = nums[i];

				while (!stack.isEmpty() && nums[i] >= stack.peek().max) {
					stack.pop();
				}

				if (!stack.isEmpty() && stack.peek().min < nums[i]) {
					return true;
				}

				stack.push(max);
			}
		}

		return false;

	}

	// O(N) : Greedy + Stack
	public static String removeKdigits(String str, int K) {

		if (K == str.length())
			return "0";

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {

			while (!stack.isEmpty() && stack.peek() > str.charAt(i) - '0' && K > 0) {

				stack.pop();
				K--;
			}

			stack.push(str.charAt(i) - '0');
		}

		while (!stack.isEmpty() && K-- > 0) {
			stack.pop();
		}

		StringBuilder sb = new StringBuilder();

		while (!stack.isEmpty()) {
			sb.insert(0, stack.pop() + "");
		}

		while (sb.length() > 1 && sb.charAt(0) == '0') {
			sb.deleteCharAt(0);
		}

		return sb.toString();
	}

	// O(N)
	public static boolean isValidSerializable(String preorder) {

		String[] str = preorder.split(",");

		Stack<String> stack = new Stack<>();

		for (int i = 0; i < str.length; i++) {

			String check = str[i];

			if (stack.isEmpty() || !check.equals("#")) {
				stack.push(check);
			} else {

				while (!stack.isEmpty() && stack.peek().equals("#")) {
					stack.pop();

					if (stack.isEmpty())
						return false;
					else
						stack.pop();

				}

				stack.push("#");
			}
		}

		return stack.size() == 1 && stack.peek().equals("#");
	}

	// Helper Function : Divide and Conquer
	public static int scoreOfParenthesis(String S) {

		return helper_score(S, 0, S.length());
	}

	// O(N^2) : Divide and Conquer Approach
	public static int helper_score(String S, int i, int j) {

		int ans = 0;
		int bal = 0;

		for (int k = i; k < j; k++) {

			bal += (S.charAt(k) == '(' ? 1 : -1);

			if (bal == 0) {

				if (k - i == 1)
					ans++;
				else
					ans += 2 * helper_score(S, i + 1, k);

				i = k + 1;

			}
		}

		return ans;

	}

	// O(N) : Stack Solution
	public static int scoreOfParenthesis_stack(String S) {

		Stack<Integer> stack = new Stack<>();

		stack.add(0);

		for (int i = 0; i < S.length(); i++) {

			if (S.charAt(i) == '(') {
				stack.push(0);
			} else {

				int v = stack.pop();
				int w = stack.pop();

				w = w + Math.max(2 * v, 1);
				stack.push(w);
			}
		}

		return stack.pop();
	}

	// O(N) : Stack + Math
	public static int scoreOfParenthesis_eff(String S) {

		int ans = 0, bal = 0;

		for (int i = 0; i < S.length(); i++) {

			char ch = S.charAt(i);
			if (ch == '(') {
				bal++;
			} else {
				bal--;

				if (S.charAt(i - 1) == '(') {
					ans += 1 << bal;
				}
			}
		}

		return ans;
	}

	// O(N)
	public static boolean validateStackSeq(int[] pushed, int[] popped) {

		int j = 0;
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < pushed.length; i++) {

			stack.push(pushed[i]);

			while (!stack.isEmpty() && stack.peek() == popped[j]) {
				stack.pop();
				j++;
			}
		}

		return stack.isEmpty();
	}

	// O(N)
	public static int basic_calculator(String str) {

		int sign = 1;
		int ans = 0;

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);

			if (Character.isDigit(ch)) {

				int sum = ch - '0';

				while (i + 1 < str.length() && Character.isDigit(str.charAt(i + 1))) {

					sum = sum * 10 + str.charAt(i + 1) - '0';
					i++;
				}

				ans += sign * sum;

			} else if (ch == '+') {
				sign = 1;
			} else if (ch == '-') {
				sign = -1;
			} else if (ch == '(') {
				stack.push(ans);
				stack.push(sign);
				ans = 0;
				sign = 1;
			} else if (ch == ')') {

				ans = ans * stack.pop() + stack.pop();
			}
		}

		return ans;
	}

	// O(N) : Space O(N)
	public String decodeAtIndex(String S, int K) {

		long size = 0;

		for (int i = 0; i < S.length(); i++) {

			if (Character.isDigit(S.charAt(i))) {

				size *= S.charAt(i) - '0';
			} else {
				size++;
			}

		}

		for (int i = S.length() - 1; i >= 0; i--) {

			char ch = S.charAt(i);
			K %= size;

			if (K == 0 && !Character.isDigit(ch)) {
				return Character.toString(ch);
			}

			if (Character.isDigit(ch)) {

				size /= ch - '0';
			} else {
				size--;
			}
		}

		return "Yo";
	}

}
