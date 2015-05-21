import java.util.Comparator;

/**
 * Created by Tank on 4/20/2015.
 */
public class Edge implements Comparable<Edge>{


    private final City city1;
    private final City city2;
    private final int weight;

    public Edge(City city1, City city2, int weight){

        this.city1 = city1;
        this.city2 = city2;
        this.weight = weight;
    }
    static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return Integer.compare(o1.weight, o2.weight);
        }
    }
    public int getWeight() {
        return weight;
    }

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public boolean isVisible(){
        return (city1.isIncluded() && !city2.isIncluded()) || (!city1.isIncluded() && city2.isIncluded());
    }
    public void setInvalid(){
        city1.setIncluded();
        city2.setIncluded();
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(weight, o.weight);
    }
}
