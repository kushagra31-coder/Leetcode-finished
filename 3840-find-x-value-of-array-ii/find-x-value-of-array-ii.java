class Solution {

    static class Node {
        int product;
        long[] count;

        Node(int k) {
            count = new long[k];
            product = 1 % k;
        }
    }

    int n;
    int k;
    int[] nums;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.nums = nums;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Persistent update
            nums[index] = value;
            update(1, 0, n - 1, index, value);

            // Query nums[start ... n-1]
            Node result = query(1, 0, n - 1, start, n - 1);

            answer[q] = (int) result.count[x];
        }

        return answer;
    }

    // Build segment tree
    private void build(int node, int left, int right) {
        if (left == right) {
            tree[node] = makeLeaf(nums[left]);
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Create a leaf for one number
    private Node makeLeaf(int value) {
        Node node = new Node(k);

        int rem = value % k;

        node.product = rem;
        node.count[rem] = 1;

        return node;
    }

    // Point update
    private void update(
        int node,
        int left,
        int right,
        int index,
        int value
    ) {
        if (left == right) {
            tree[node] = makeLeaf(value);
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Range query
    private Node query(
        int node,
        int left,
        int right,
        int ql,
        int qr
    ) {
        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        if (qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }

        Node a = query(node * 2, left, mid, ql, qr);
        Node b = query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(a, b);
    }

    // Merge A followed by B
    private Node merge(Node a, Node b) {
        Node result = new Node(k);

        // Prefixes completely inside A
        for (int r = 0; r < k; r++) {
            result.count[r] += a.count[r];
        }

        // Prefixes that use all of A and then a prefix of B
        for (int r = 0; r < k; r++) {
            int newRemainder =
                (int) ((long) a.product * r % k);

            result.count[newRemainder] += b.count[r];
        }

        // Product of the whole segment
        result.product =
            (int) ((long) a.product * b.product % k);

        return result;
    }
}