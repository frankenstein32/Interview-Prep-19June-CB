package Trees;

import java.util.*;

public class Lec8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public class Node {
		int val;
		Node left;
		Node right;
		Node next;

		Node(int x) {
			val = x;
		}
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		if (root == null) {
			return null;
		}

		if (root.val > p.val && root.val > q.val) {

			return lowestCommonAncestor(root.left, p, q);
		}

		if (root.val < p.val && root.val < q.val) {
			return lowestCommonAncestor(root.right, p, q);
		}

		return root;
	}

	TreeNode Prev = null;
	int minDiff = Integer.MAX_VALUE;

	public int getMinimumDifference(TreeNode root) {

		helper_minDiff(root);
		return minDiff;
	}

	public void helper_minDiff(TreeNode root) {

		if (root == null)
			return;

		helper_minDiff(root.left);

		if (Prev != null) {

			minDiff = Math.min(minDiff, root.val - Prev.val);
		}

		Prev = root;
		helper_minDiff(root.right);
	}

	public boolean findTarget(TreeNode root, int target) {
		List<Integer> ans = new ArrayList<>();
		Inorder(root, ans);

		int i = 0, j = ans.size() - 1;

		while (i < j) {

			int sum = ans.get(i) + ans.get(j);

			if (target == sum) {
				return true;
			} else if (target > sum) {
				i++;
			} else {
				j--;
			}
		}

		return false;
	}

	public void Inorder(TreeNode root, List<Integer> ans) {

		if (root == null)
			return;

		Inorder(root.left, ans);
		ans.add(root.val);
		Inorder(root.right, ans);

	}

	public int minDiffInBST(TreeNode root) {

		minDiffBST(root);
		return min;

	}

	TreeNode prev = null;
	int min = Integer.MAX_VALUE;

	public void minDiffBST(TreeNode root) {

		if (root == null) {
			return;
		}

		minDiffBST(root.left);

		if (prev != null) {

			min = Math.min(min, root.val - prev.val);
		}

		prev = root;

		minDiffBST(root.right);
	}

	public boolean leafSimilar(TreeNode root1, TreeNode root2) {
		Stack<TreeNode> s1 = new Stack<>(), s2 = new Stack<>();
		s1.push(root1);
		s2.push(root2);
		while (!s1.empty() && !s2.empty())
			if (dfs(s1) != dfs(s2))
				return false;
		return s1.empty() && s2.empty();
	}

	public int dfs(Stack<TreeNode> s) {
		while (true) {
			TreeNode node = s.pop();
			if (node.right != null)
				s.push(node.right);
			if (node.left != null)
				s.push(node.left);
			if (node.left == null && node.right == null)
				return node.val;
		}
	}

	public int sumRootToLeaf(TreeNode root) {

		return dfs(root, 0);
	}

	public int dfs(TreeNode root, int val) {

		if (root == null)
			return 0;

		val = 2 * val + root.val;

		return (root.left == root.right ? val : dfs(root.left, val) + dfs(root.right, val));
	}

	public boolean isCousins(TreeNode root, int x, int y) {

//	     }
		dfs_cousins(root, root, x, y, 0);

		return (xParent != yParent) && (xDepth == yDepth);

	}

	TreeNode xParent, yParent;
	int xDepth, yDepth;

	public void dfs_cousins(TreeNode root, TreeNode parent, int x, int y, int depth) {

		if (root == null)
			return;

		if (root.val == x) {
			xParent = parent;
			xDepth = depth;
		}

		if (root.val == y) {
			yParent = parent;
			yDepth = depth;
		}

		dfs_cousins(root.left, root, x, y, depth + 1);
		dfs_cousins(root.right, root, x, y, depth + 1);
	}

	public void flatten(TreeNode node) {
		if (node == null)
			return;

		flatten(node.right);
		flatten(node.left);

		node.right = prev;
		node.left = null;

		prev = node;
	}

	public Node connect(Node root) {

		if (root == null)
			return null;

		Queue<Node> q = new LinkedList<>();
		Queue<Node> helper = new LinkedList<>();

		q.offer(root);

		while (!q.isEmpty()) {

			Node rp = q.poll();

			if (!q.isEmpty()) {

				rp.next = q.peek();
			} else {
				rp.next = null;
			}

			if (rp.left != null) {
				helper.offer(rp.left);
			}

			if (rp.right != null) {
				helper.offer(rp.right);
			}

			if (q.isEmpty()) {
				q = helper;
				helper = new LinkedList<>();
			}
		}

		return root;
	}

	public List<Integer> rightSideView(TreeNode root) {

		if (root == null)
			return new ArrayList<>();

		List<Integer> ans = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		Queue<TreeNode> helper = new LinkedList<>();
		TreeNode save = null;
		q.add(root);

		while (!q.isEmpty()) {

			TreeNode rp = q.poll();
			save = rp;

			if (rp.left != null)
				helper.add(rp.left);
			if (rp.right != null)
				helper.add(rp.right);

			if (q.isEmpty()) {
				System.out.println(save.val);
				ans.add(save.val);
				q = helper;
				helper = new LinkedList<>();
			}
		}

		return ans;
	}

	public int kthSmallest(TreeNode root, int k) {

		if (root == null) {
			return -1;
		}

		List<Integer> res = new ArrayList<>();
		dfsKthSmallest(root, res);
		return res.get(k - 1);
	}

	public void dfsKthSmallest(TreeNode root, List<Integer> res) {

		if (root == null)
			return;

		dfsKthSmallest(root.left, res);
		res.add(root.val);
		dfsKthSmallest(root.right, res);
	}

	public TreeNode deleteNode(TreeNode root, int key) {

		if (root == null) {
			return root;
		}

		return delete(root, key);

	}

	public TreeNode max(TreeNode node) {

		if (node.right != null) {
			return max(node.right);
		}

		return node;
	}

	public TreeNode delete(TreeNode root, int key) {

		if (root == null) {
			return root;
		}

		if (root.val < key) {
			root.right = delete(root.right, key);
		} else if (root.val > key) {
			root.left = delete(root.left, key);
		} else {

			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			}

			TreeNode temp = max(root.left);
			root.val = temp.val;
			root.left = delete(root.left, root.val);

		}

		return root;
	}

	public int findBottomLeftValue(TreeNode root) {
		if (root == null) {
			return -1;
		}

		Queue<TreeNode> q = new LinkedList<>();

		q.add(root);
		while (!q.isEmpty()) {

			root = q.poll();

			if (root.right != null) {
				q.add(root.right);
			}

			if (root.left != null) {
				q.add(root.left);
			}

		}

		return root.val;
	}

	int max = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {

		helper(root);
		return max;

	}

	public int helper(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int LSum = Math.max(0, helper(root.left));
		int RSum = Math.max(0, helper(root.right));
		int selfSum = LSum + RSum + root.val;

		max = Math.max(selfSum, max);

		return Math.max(LSum, RSum) + root.val;
	}

	public TreeNode subtreeWithAllDeepest(TreeNode root) {

		return dfs(root).node;
	}

	class Result {

		int dist;
		TreeNode node;

		public Result(TreeNode n, int d) {
			this.dist = d;
			this.node = n;
		}
	}

	public Result dfs(TreeNode root) {

		if (root == null) {
			return new Result(null, -1);
		}

		Result L = dfs(root.left);
		Result R = dfs(root.right);

		if (L.dist > R.dist) {
			return new Result(L.node, L.dist + 1);
		}

		if (L.dist < R.dist) {
			return new Result(R.node, R.dist + 1);
		}

		return new Result(root, L.dist + 1);
	}

	public boolean flipEquiv(TreeNode root1, TreeNode root2) {

		List<Integer> ans1 = new ArrayList<>();
		List<Integer> ans2 = new ArrayList<>();

		flipEquiv_dfs(root1, ans1);
		flipEquiv_dfs(root2, ans2);

		return ans1.equals(ans2);
	}

	public void flipEquiv_dfs(TreeNode root, List<Integer> ans) {

		if (root != null) {
			ans.add(root.val);

			int L = (root.left == null ? -1 : root.left.val);
			int R = (root.right == null ? -1 : root.right.val);

			if (L < R) {
				flipEquiv_dfs(root.left, ans);
				flipEquiv_dfs(root.right, ans);
			} else {

				flipEquiv_dfs(root.right, ans);
				flipEquiv_dfs(root.left, ans);
			}

			ans.add(null);
		}
	}

	public boolean isCompleteTree(TreeNode root) {

		Queue<TreeNode> bfs = new LinkedList<>();

		bfs.offer(root);

		while (bfs.peek() != null) {

			TreeNode rp = bfs.poll();

			bfs.offer(rp.left);
			bfs.offer(rp.right);
		}

		while (!bfs.isEmpty() && bfs.peek() == null) {
			bfs.poll();
		}

		return bfs.isEmpty();
	}

	public int maxAncestorDiff(TreeNode root) {
		return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public int dfs(TreeNode node, int max, int min) {

		if (node == null) {
			return max - min;
		}

		max = Math.max(node.val, max);
		min = Math.min(node.val, min);

		return Math.max(dfs(node.left, max, min), dfs(node.right, max, min));
	}

	public TreeNode constructMaximumBinaryTree(int[] nums) {
		return construct(nums, 0, nums.length - 1);
	}

	public TreeNode construct(int[] nums, int nlo, int nhi) {

		if (nlo > nhi) {
			return null;
		}

		int idx = -1;
		int max = Integer.MIN_VALUE;
		for (int i = nlo; i <= nhi; i++) {

			if (nums[i] > max) {
				max = nums[i];
				idx = i;
			}
		}

		TreeNode nn = new TreeNode(nums[idx]);

		nn.left = construct(nums, nlo, idx - 1);
		nn.right = construct(nums, idx + 1, nhi);

		return nn;
	}

	int i = 0;
	List<Integer> ans = new ArrayList<>();

	public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {

		return (dfs(root, voyage) ? ans : Arrays.asList(-1));
	}

	public boolean dfs(TreeNode root, int[] voyage) {

		if (root == null)
			return true;
		if (root.val != voyage[i++])
			return false;

		if (root.left != null && root.left.val != voyage[i]) {

			ans.add(root.val);

			return dfs(root.right, voyage) && dfs(root.left, voyage);
		}

		return dfs(root.left, voyage) && dfs(root.right, voyage);
	}

	TreeNode fidx;
	TreeNode sidx;

	/*
	 * Uncomment for below code TreeNode prev = new TreeNode(Integer.MIN_VALUE);
	 */
	public void recoverTree(TreeNode root) {

		if (root == null) {
			return;
		}

		inorder(root);

		int temp = fidx.val;
		fidx.val = sidx.val;
		sidx.val = temp;

	}

	public void inorder(TreeNode root) {

		if (root == null) {
			return;
		}

		inorder(root.left);
		if (fidx == null && prev.val > root.val) {
			fidx = prev;
		}

		if (fidx != null && prev.val > root.val) {
			sidx = root;
		}

		prev = root;

		inorder(root.right);
	}

	public List<Integer> largestValues(TreeNode root) {

		if (root == null) {
			return new ArrayList<>();
		}

		Queue<TreeNode> q = new LinkedList<>();
		Queue<TreeNode> h = new LinkedList<>();
		int i = 0;
		List<Integer> res = new ArrayList<>();

		q.add(root);
		while (!q.isEmpty()) {

			TreeNode rp = q.poll();

			if (res.size() == i) {
				res.add(rp.val);
			} else {
				res.set(i, Math.max(res.get(i), rp.val));
			}

			if (rp.left != null) {
				h.add(rp.left);
			}

			if (rp.right != null) {
				h.add(rp.right);
			}

			if (q.isEmpty()) {

				q = h;
				h = new LinkedList<>();
				i++;
			}

		}

		return res;
	}
}
