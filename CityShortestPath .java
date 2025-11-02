import java.util.*;
public class CityShortestPath {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static class Graph {
        int V;
        List<List<Edge>> adj;
        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++)
                adj.add(new ArrayList<>());
        }
        void addEdge(int from, int to, int weight) {
            adj.get(from).add(new Edge(to, weight));
            adj.get(to).add(new Edge(from, weight)); // undirected graph
        }
        void dijkstra(int src, String[] cities) {
            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            pq.add(new int[]{src, 0});
            while (!pq.isEmpty()) {
                int[] node = pq.poll();
                int u = node[0];
                for (Edge edge : adj.get(u)) {
                    int v = edge.to;
                    int weight = edge.weight;
                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pq.add(new int[]{v, dist[v]});
                    }
                }
            }
            System.out.println("\nShortest distances from " + cities[src] + ":");
            for (int i = 0; i < V; i++)
                System.out.println(cities[src] + " → " + cities[i] + " = " + dist[i] + " km");
        }
    }
    public static void main(String[] args) {
        String[] cities = {"Pune", "Mumbai", "Nashik", "Nagpur", "Kolhapur"};
        int V = cities.length;
        Graph g = new Graph(V);
        g.addEdge(0, 1, 150);
        g.addEdge(0, 2, 210);
        g.addEdge(1, 2, 180);
        g.addEdge(2, 3, 700);
        g.addEdge(0, 4, 230);
        g.addEdge(4, 1, 250);
        Scanner sc = new Scanner(System.in);
        System.out.println("Available cities:");
        for (int i = 0; i < V; i++)
            System.out.println(i + " - " + cities[i]);
        System.out.print("\nEnter source city number: ");
        int src = sc.nextInt();
        g.dijkstra(src, cities);
        sc.close();
    }
}
