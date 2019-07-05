package Trees;

import java.util.*;

public class Lec7 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {

		List<List<Integer>> ans = new ArrayList<>();

		DFS(ans, root, 0);
		return ans;

	}

	public void DFS(List<List<Integer>> ans, TreeNode root, int level) {

		if (root == null) {
			return;
		}

		if (level >= ans.size()) {
			ans.add(0, new ArrayList<>());
		}

		DFS(ans, root.left, level + 1);
		DFS(ans, root.right, level + 1);

		ans.get(ans.size() - level - 1).add(root.val);
	}

	public int minDepth(TreeNode root) {

		if (root == null) {
			return 0;
		}

		return helper(root);
	}

	public int helper(TreeNode root) {

		if (root == null) {
			return Integer.MAX_VALUE;
		}

		int lh = helper(root.left);
		int rh = helper(root.right);

		if (lh == Integer.MAX_VALUE && rh == Integer.MAX_VALUE) {

			return 1;
		} else {

			return Math.min(lh, rh) + 1;
		}
	}

	public List<String> binaryTreePaths(TreeNode root) {

		List<String> res = new ArrayList<>();

		if (root != null)
			helper(root, res, "");

		return res;
	}

	public void helper(TreeNode root, List<String> res, String sb) {

		if (root.left == null && root.right == null)
			res.add(sb + root.val);

		if (root.left != null)
			helper(root.left, res, sb + root.val + "->");
		if (root.right != null)
			helper(root.right, res, sb + root.val + "->");
	}

	public TreeNode invertTree(TreeNode root) {

		if (root == null) {
			return root;
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {

			TreeNode rp = q.poll();

			TreeNode left = rp.left;
			rp.left = rp.right;
			rp.right = left;

			if (rp.left != null) {
				q.add(rp.left);
			}

			if (rp.right != null) {
				q.add(rp.right);
			}
		}

		return root;
	}

	public int pathSum(TreeNode root, int sum) {
		if (root == null)
			return 0;

		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);

		return backtrack(root, 0, sum, map);
	}

	public int backtrack(TreeNode root, int sum, int target, HashMap<Integer, Integer> map) {

		if (root == null) {
			return 0;
		}

		sum += root.val;

		int res = map.getOrDefault(sum - target, 0);
		map.put(sum, map.getOrDefault(sum, 0) + 1);

		res += backtrack(root.left, sum, target, map) + backtrack(root.right, sum, target, map);
		map.put(sum, map.get(sum) - 1);

		return res;
	}

	public int sumOfLeftLeaves(TreeNode root) {

		if (root == null) {
			return 0;
		}

		int ans = 0;

		if (root.left != null) {

			if (root.left.left == null && root.left.right == null)
				ans += root.left.val;
			else
				ans += sumOfLeftLeaves(root.left);
		}

		return sumOfLeftLeaves(root.right) + ans;
	}

	public int[] findMode(TreeNode root) {

		if (root == null) {
			return new int[0];
		}

		List<Integer> res = new ArrayList<>();
		ListFill(root, res);

		int[] arr = new int[res.size()];

		for (int i = 0; i < arr.length; i++) {

			arr[i] = res.get(i);
		}

		return arr;
	}

	TreeNode prev = null;
	int count = 1;
	int max = 0;

	public void ListFill(TreeNode root, List<Integer> res) {

		if (root == null) {
			return;
		}

		ListFill(root.left, res);

		if (prev != null) {

			if (prev.val == root.val) {
				count++;
			} else {
				count = 1;
			}
		}

		if (count > max) {

			max = count;
			res.clear();
			res.add(root.val);
		} else if (count == max) {
			res.add(root.val);
		}

		prev = root;
		ListFill(root.right, res);
	}

	public int findTilt(TreeNode root) {

		HeapMover hm = new HeapMover();

		return findTilt(root, hm).tilt;
	}

	class HeapMover {
		int sum = 0;
		int tilt = 0;
	}

	public HeapMover findTilt(TreeNode root, HeapMover mover) {

		if (root == null) {
			return new HeapMover();
		}

		HeapMover lp = findTilt(root.left, mover);
		HeapMover rp = findTilt(root.right, mover);

		HeapMover sp = new HeapMover();
		sp.sum = lp.sum + rp.sum + root.val;
		sp.tilt = Math.abs(lp.sum - rp.sum) + lp.tilt + rp.tilt;

		return sp;
	}

	public String tree2str(TreeNode t) {

		if (t == null)
			return "";

		if (t.left == null && t.right == null) {

			return t.val + "";
		}

		if (t.right == null) {
			return t.val + "(" + tree2str(t.left) + ")";
		}

		return t.val + "(" + tree2str(t.left) + ")" + "(" + tree2str(t.right) + ")";
	}

	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> res = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			long sum = 0, count = 0;
			Queue<TreeNode> temp = new LinkedList<>();
			while (!queue.isEmpty()) {
				TreeNode n = queue.remove();
				sum += n.val;
				count++;
				if (n.left != null)
					temp.add(n.left);
				if (n.right != null)
					temp.add(n.right);
			}
			queue = temp;
			res.add(sum * 1.0 / count);
		}
		return res;
	}

	public int findSecondMinimumValue(TreeNode root) {

		if (root == null) {
			return -1;
		}

		if (root.left == null && root.right == null) {
			return -1;
		}

		int left = root.left.val;
		int right = root.right.val;

		if (root.val == root.left.val) {
			left = findSecondMinimumValue(root.left);
		}

		if (root.val == root.right.val) {
			right = findSecondMinimumValue(root.right);
		}

		if (left != -1 && right != -1) {
			return Math.min(left, right);
		} else if (left != -1) {
			return left;
		} else {
			return right;
		}
	}

	int ans = 0;

	public int longestUnivaluePath(TreeNode root) {

		longestPath(root);
		return ans;
	}

	public int longestPath(TreeNode root) {

		if (root == null) {
			return 0;
		}

		int lmax = longestPath(root.left);
		int rmax = longestPath(root.right);

		int tempLeft = 0;
		int tempRight = 0;
		if (root.left != null && root.left.val == root.val) {

			tempLeft += lmax + 1;
		}

		if (root.right != null && root.right.val == root.val) {

			tempRight += rmax + 1;
		}

		ans = Math.max(ans, tempRight + tempLeft);

		return Math.max(tempLeft, tempRight);

	}

	
}
