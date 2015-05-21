import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by Tank on 4/20/2015.
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        args = new String[1];
        args[0] = "Lab3\\src\\USA-highway-miles.in.txt";
        if (args.length < 1) {
            System.out.println("Must enter a valid filename");
            System.exit(0);
        }

        File f = new File(args[0]);
        Scanner scan = null;
        System.out.println(f.getAbsolutePath());
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Must enter a valid filename");
            System.exit(0);
        }

        HashMap<String, City> cities = new HashMap<String, City>();
        ArrayList<City> cityList = new ArrayList<City>();
        PriorityQueue<Edge> edgeList = new PriorityQueue<Edge>(new Edge.EdgeComparator());

        while (scan.hasNext()) {

            String s = scan.nextLine();
            if (s.contains("--")) break;
            if (s.charAt(0) == '\"' && s.charAt(s.length() - 2) == '\"') {
                s = s.substring(1, s.length() - 2);
            }
            s = s.trim();
            City c = new City(s);
            cityList.add(c);
//            System.out.println(s);
            cities.put(c.name, c);

        }

        while (scan.hasNext()) {
            String s = scan.nextLine();
            String city1 = "";
            if (s.charAt(0) == '\"') {
                city1 = s.substring(1, s.indexOf("--") - 1);
            } else city1 = s.substring(0, s.indexOf('-'));
            String city2 = "";
            if (s.charAt(s.indexOf("--") + 2) == '\"') {

                city2 = s.substring(s.indexOf("--") + 3, s.indexOf('[') - 2);
            } else
                city2 = s.substring(s.indexOf("--") + 2, s.indexOf('[') - 1);
            String g = s.substring(s.indexOf('[') + 1, s.indexOf(']'));
            int edgeValue = Integer.parseInt(g);
            Edge e = new Edge(cities.get(city1), cities.get(city2), edgeValue);
            //edgeList.add(e);
        }

       // edgeList.sort(new Edge.EdgeComparator());
        ArrayList<Edge> minSpanningTree = new ArrayList<Edge>();
        City first = cityList.get(0);
        first.setIncluded();


//Algoritmen 6 rader kod #yolo

        while (cityList.size() > 0) {
            Edge cheapest = edgeList.poll(); // Minst vikt av de städer som finns tillgängliga
            edgeList.remove(cheapest);
            cheapest.setInvalid();
            minSpanningTree.add(cheapest);
            cityList.remove(cheapest.getCity1()); //Tar bort städerna för edgen
            cityList.remove(cheapest.getCity2());
        }
        long endTime = System.nanoTime()-startTime;
        int totalWeight = 0;
        for (Edge e : minSpanningTree) {
            totalWeight +=  e.getWeight();


        }
        System.out.println("Total weight of spanning tree: " + totalWeight + "\n" + "Total time for running algorithm in seconds (including scanning): " + endTime/1000000000.0 + "s");
    }



    /**
     * Finds the cheapest edge like a sir boss pro mario
     * @param list
     * @return
     */
    public static Edge cheapestEdge(ArrayList<Edge> list){
        Edge cheapest = list.get(0);
        for(Edge e: list){
            if(e.isVisible() && e.getWeight() < cheapest.getWeight()){
                cheapest = e;
            }
        }
        return cheapest;
    }
    public static void addAllEdges(City c1, PriorityQueue<Edge> e){
        for(Edge temp: c1.getEdges()){
            temp.isVisible();
        }
    }







}
