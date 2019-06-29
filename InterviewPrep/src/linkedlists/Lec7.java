package linkedlists;

import java.util.*;

public class Lec7 {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scn = new Scanner(System.in);

		int n = scn.nextInt();

		System.out.println(n);
	}

	public ListNode deleteDuplicates(ListNode head) {

		if (head == null || head.next == null) {
			return head;
		}

		ListNode temp = head;
		ListNode prev = head;
		ListNode curr = prev.next;

		while (curr != null) {

			if (prev.val != curr.val) {
				prev.next = curr;
				prev = curr;
			}

			curr = curr.next;
		}

		prev.next = curr;

		return temp;
	}

	public ListNode removeElements(ListNode head, int val) {

		if (head == null) {
			return head;
		}

		ListNode temp = new ListNode(0);
		temp.next = head;
		ListNode prev = temp;
		ListNode curr = head;

		while (curr != null) {

			if (curr.val != val) {

				prev.next = curr;
				prev = curr;
			}

			curr = curr.next;
		}

		prev.next = curr;

		return temp.next;
	}

	public void deleteNode(ListNode node) {

		node.val = node.next.val;
		node.next = node.next.next;
	}

	public ListNode swapPairs(ListNode head) {

		if (head == null) {
			return head;
		}

		ListNode prev = head;
		ListNode curr = prev.next;

		while (curr != null) {

			ListNode next = curr.next;

			int temp = prev.val;
			prev.val = curr.val;
			curr.val = temp;

			prev = next;

			if (next != null) {
				curr = next.next;
			} else {
				curr = null;
			}
		}

		return head;
	}

	public ListNode rotateRight(ListNode head, int k) {

		if (head == null) {
			return head;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode fast = dummy;
		ListNode slow = dummy;

		int i = 0;
		for (; fast.next != null; i++) {

			fast = fast.next;
		}

		for (int j = i - k % i; j > 0; j--) {

			slow = slow.next;
		}

		fast.next = dummy.next;
		dummy.next = slow.next;
		slow.next = null;

		return dummy.next;
	}

	public ListNode deleteDuplicates2(ListNode head) {

		if (head == null || head.next == null) {
			return head;
		}

		ListNode Fakehead = new ListNode(0);
		Fakehead.next = head;

		ListNode prev = Fakehead;
		ListNode curr = head;

		while (curr != null) {

			while (curr.next != null && curr.val == curr.next.val) {
				curr = curr.next;
			}

			if (prev.next == curr) {
				prev = prev.next;
			} else {

				prev.next = curr.next;
			}

			curr = curr.next;
		}

		return Fakehead.next;
	}

	public ListNode partition(ListNode head, int x) {

		if (head == null || head.next == null) {
			return head;
		}

		ListNode shorter = new ListNode(0);
		ListNode longer = new ListNode(0);

		ListNode headS = shorter;
		ListNode headL = longer;

		while (head != null) {

			if (head.val < x) {

				headS.next = head;
				headS = headS.next;
			} else {

				headL.next = head;
				headL = headL.next;
			}

			head = head.next;
		}

		headL.next = null;
		headS.next = longer.next;

		return shorter.next;
	}

	public ListNode oddEvenList(ListNode head) {

		if (head == null || head.next == null) {
			return head;
		}

		ListNode odd = head, even = head.next, evenHead = even;

		while (even != null && even.next != null) {

			odd.next = even.next;
			odd = odd.next;
			even.next = odd.next;
			even = even.next;
		}

		odd.next = evenHead;

		return head;
	}

}
