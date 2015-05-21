import java.util.ArrayList;

/**
 * Created by Tank on 4/20/2015.
 */
public class City{
    private boolean included;
    public String name;
    private ArrayList<Edge> edges;




    public City(String name){
        edges = new ArrayList<Edge>();
        this.name = name;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (!name.equals(city.name)) return false;

        return true;
    }
    public void add(Edge e){
        edges.add(e);
    }
    public ArrayList<Edge> getEdges(){
        return edges;
    }

    public void setIncluded(){
        included = true;

    }


    public boolean isIncluded(){
        return included;
    }



}
