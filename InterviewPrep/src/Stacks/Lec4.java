package Stacks;

import java.util.*;

public class Lec4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// O(N) : Normal Stack Solution
	public int calPoints(String[] str) {

		Stack<Integer> stack = new Stack<>();
		int sum = 0;

		for (int i = 0; i < str.length; i++) {

			String op = str[i];

			if (op.equals("+")) {

				int a = stack.pop();
				int b = stack.pop();
				stack.push(b);
				stack.push(a);

				int item = a + b;
				sum += item;
				stack.push(item);
			} else if (op.equals("D")) {

				int a = stack.peek();
				int item = 2 * a;
				sum += item;
				stack.push(item);
			} else if (op.equals("C")) {

				int item = stack.pop();
				sum -= item;

			} else {
				int item = Integer.parseInt(op);
				sum += item;
				stack.push(item);
			}
		}

		return sum;
	}

	// O(N) : Normal Stack Solution with StringBuilder
	public String removeOuterParentheses(String str) {

		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);

			if (ch == '(') {

				sb.append(ch);
				stack.push(ch);
			} else {

				sb.append(ch);
				stack.pop();
			}

			if (stack.isEmpty()) {

				sb.deleteCharAt(0);
				sb.deleteCharAt(sb.length() - 1);

				res.append(sb);
				sb = new StringBuilder();

			}
		}

		return res.toString();
	}

	// O(N) : Normal Stack Solution with StringBuilder
	public String removeDuplicates(String str) {

		Stack<Character> stack = new Stack<>();
		int flag = 0;
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);

			while (!stack.isEmpty() && stack.peek() == ch) {

				stack.pop();
				flag = 1;
			}

			if (flag == 0)
				stack.push(ch);

			flag = 0;
		}

		StringBuilder sb = new StringBuilder();

		while (!stack.isEmpty()) {

			sb.insert(0, stack.pop());
		}

		return sb.toString();
	}

	// O(N) : PostFix to InFix
	public int ReversePolishNotation(String[] str) {

		Stack<Integer> st = new Stack<>();

		for (int i = 0; i < str.length; i++) {

			String op = str[i];

			if (!op.equals("+") && !op.equals("-") && !op.equals("*") && !op.equals("/")) {

				st.push(Integer.valueOf(op));
				continue;
			}

			int n1 = st.pop();
			int n2 = st.pop();

			if (op.equals("+")) {

				st.push(n1 + n2);
			} else if (op.equals("-")) {
				st.push(n2 - n1);
			} else if (op.equals("*")) {
				st.push(n1 * n2);
			} else {
				st.push(n2 / n1);
			}
		}

		return st.pop();
	}

	// O(N): Decode String - One Pass
	public String decodeString(String s) {

		Stack<Integer> count = new Stack<>();
		Stack<String> resStack = new Stack<>();

		String res = "";

		for (int idx = 0; idx < s.length();) {

			char ch = s.charAt(idx);

			if (Character.isDigit(ch)) {
				int num = 0;

				while (Character.isDigit(s.charAt(idx))) {

					num = num * 10 + (s.charAt(idx) - '0');
					idx++;
				}

				count.push(num);
			} else if (ch == '[') {

				resStack.push(res);
				res = "";
				idx++;

			} else if (ch == ']') {

				StringBuilder sb = new StringBuilder(resStack.pop());
				int repeat = count.pop();

				for (int i = 0; i < repeat; i++) {
					sb.append(res);
				}

				res = sb.toString();
				idx++;
			} else {

				res += ch;
				idx++;
			}
		}

		return res;
	}

}
