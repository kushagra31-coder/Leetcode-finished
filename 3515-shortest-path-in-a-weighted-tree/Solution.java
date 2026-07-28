

class Solution {

    static class Edge {
        int to;
        int w;
        Edge(int t, int w) {
            this.to = t;
            this.w = w;
        }
    }

    int timer = 0;
    int[] tin, tout, parent;
    long[] dist;
    Fenwick bit;

    public int[] treeQueries(int n, int[][] edges, int[][] queries) {

        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        Map<Long, Integer> weight = new HashMap<>();

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));

            long key = key(u, v);
            weight.put(key, w);
        }

        tin = new int[n + 1];
        tout = new int[n + 1];
        parent = new int[n + 1];
        dist = new long[n + 1];

        dfs(1, 0, 0, graph);

        bit = new Fenwick(n + 2);

        List<Integer> ans = new ArrayList<>();

        for (int[] q : queries) {

            if (q[0] == 1) {

                int u = q[1];
                int v = q[2];
                int nw = q[3];

                long k = key(u, v);
                int old = weight.get(k);

                int child = parent[u] == v ? u : v;

                int delta = nw - old;

                bit.add(tin[child], delta);
                bit.add(tout[child] + 1, -delta);

                weight.put(k, nw);

            } else {

                int x = q[1];
                long res = dist[x] + bit.query(tin[x]);
                ans.add((int) res);
            }
        }

        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);

        return res;
    }

    void dfs(int u, int p, long d, List<Edge>[] graph) {

        parent[u] = p;
        dist[u] = d;

        tin[u] = ++timer;

        for (Edge e : graph[u]) {

            if (e.to == p) continue;

            dfs(e.to, u, d + e.w, graph);
        }

        tout[u] = timer;
    }

    long key(int u, int v) {
        if (u > v) {
            int t = u;
            u = v;
            v = t;
        }
        return (((long) u) << 32) | (v & 0xffffffffL);
    }

    static class Fenwick {

        long[] bit;

        Fenwick(int n) {
            bit = new long[n + 2];
        }

        void add(int idx, long val) {
            while (idx < bit.length) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        long query(int idx) {
            long sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }
}