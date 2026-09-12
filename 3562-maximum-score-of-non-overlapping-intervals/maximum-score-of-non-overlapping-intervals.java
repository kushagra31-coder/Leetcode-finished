class Solution {

    static class Interval {
        int left, right, weight, index;

        Interval(int left, int right, int weight, int index) {
            this.left = left;
            this.right = right;
            this.weight = weight;
            this.index = index;
        }
    }

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        List<Interval> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            ));
        }

        // Sort by starting point
        arr.sort((a, b) -> {
            if (a.left != b.left)
                return Integer.compare(a.left, b.left);

            return Integer.compare(a.right, b.right);
        });

        State[][] memo = new State[n][5];

        State ans = dp(arr, 0, 4, memo);

        return ans.indices.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private State dp(
        List<Interval> arr,
        int i,
        int k,
        State[][] memo
    ) {
        if (i == arr.size() || k == 0) {
            return new State(0, new ArrayList<>());
        }

        if (memo[i][k] != null) {
            return memo[i][k];
        }

        // Option 1: skip
        State skip = dp(arr, i + 1, k, memo);

        // Option 2: take
        Interval cur = arr.get(i);

        int next = findNext(arr, i + 1, cur.right);

        State nextState = dp(arr, next, k - 1, memo);

        List<Integer> takeIndices =
                new ArrayList<>(nextState.indices);

        takeIndices.add(cur.index);

        Collections.sort(takeIndices);

        State take = new State(
            cur.weight + nextState.score,
            takeIndices
        );
        if (take.score > skip.score) {
            memo[i][k] = take;
        } 
        else if (take.score < skip.score) {
            memo[i][k] = skip;
        } 
        else {
            if (compare(take.indices, skip.indices) < 0) {
                memo[i][k] = take;
            } else {
                memo[i][k] = skip;
            }
        }

        return memo[i][k];
    }

    private int findNext(
        List<Interval> arr,
        int start,
        int right
    ) {
        int lo = start;
        int hi = arr.size();

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr.get(mid).left > right) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private int compare(
        List<Integer> a,
        List<Integer> b
    ) {
        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}