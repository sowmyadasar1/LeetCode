class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);

            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());

            graph.get(a).put(b, values[i]);
            graph.get(b).put(a, 1.0 / values[i]);
        }

        double[] ans = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dst = queries.get(i).get(1);

            if (!graph.containsKey(src) || !graph.containsKey(dst)) {
                ans[i] = -1.0;
            } else if (src.equals(dst)) {
                ans[i] = 1.0;
            } else {
                ans[i] = dfs(src, dst, 1.0, graph, new HashSet<>());
            }
        }

        return ans;
    }

    private double dfs(String curr, String target, double product,
                       Map<String, Map<String, Double>> graph,
                       Set<String> visited) {
        if (curr.equals(target)) return product;

        visited.add(curr);

        for (Map.Entry<String, Double> next : graph.get(curr).entrySet()) {
            if (!visited.contains(next.getKey())) {
                double res = dfs(next.getKey(), target,
                                 product * next.getValue(),
                                 graph, visited);
                if (res != -1.0) return res;
            }
        }

        return -1.0;
    }
}