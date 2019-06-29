package Queue;

import java.util.*;

public class Lec7 {

	public static void generateBinary(int n) {

		Queue<String> q = new LinkedList<>();
		q.offer("1");
		for (int i = 0; i < n; i++) {

			String s1 = q.poll();

			System.out.print(s1 + " ");
			q.offer(s1 + "0");
			q.offer(s1 + "1");
		}
		System.out.println();
	}

	public static void MaximumWindowK(int[] arr, int K) {

		Scanner scn = new Scanner(System.in);
		Deque<Integer> q = new ArrayDeque<>();

		int i = 0;
		for (i = 0; i < K; i++) {

			while (!q.isEmpty() && arr[i] >= arr[q.peekLast()]) {
				q.pollLast();
			}

			q.addLast(i);
		}

		for (; i < arr.length; i++) {

			System.out.print(arr[q.peek()] + " ");

			while (!q.isEmpty() && q.peek() <= i - K) {
				q.removeFirst();
			}

			while (!q.isEmpty() && arr[i] >= arr[q.peekLast()]) {
				q.pollLast();
			}

			q.addLast(i);
		}

		System.out.print(q.peek() + " ");
	}

	public static int SumKsubArray(int[] arr, int K) {

		Deque<Integer> G = new ArrayDeque<>();
		Deque<Integer> S = new ArrayDeque<>();

		int i = 0;
		for (i = 0; i < K; i++) {

			while (!G.isEmpty() && arr[i] >= arr[G.peekLast()]) {
				G.pollLast();
			}

			while (!S.isEmpty() && arr[i] <= arr[S.peekLast()]) {
				S.pollLast();
			}

			G.addLast(i);
			S.addLast(i);
		}

		int sum = 0;

		for (; i < arr.length; i++) {

			sum += arr[G.peekFirst()] + arr[S.peekFirst()];

			while (!G.isEmpty() && G.peekFirst() <= i - K) {
				G.pollFirst();
			}

			while (!S.isEmpty() && S.peekFirst() <= i - K) {
				S.pollFirst();
			}

			while (!G.isEmpty() && arr[i] >= arr[G.peekLast()]) {
				G.pollLast();
			}

			while (!S.isEmpty() && arr[i] <= arr[S.peekLast()]) {
				S.pollLast();
			}

			G.addLast(i);
			S.addLast(i);
		}

		sum += G.peekFirst() + S.peekFirst();

		return sum;
	}

}
