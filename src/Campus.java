
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Campus {
    private HashMap<String, Integer> distances = new HashMap<>();
    private HashMap<String, LocationNode> nodes = new HashMap<>();

    public HashMap<String, Integer> getDistances() {
        return distances;
    }

    public void setDistances(HashMap<String, Integer> distances) {
        this.distances = distances;
    }

    public HashMap<String, LocationNode> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<String, LocationNode> nodes) {
        this.nodes = nodes;
    }

    public void addConnection(LocationNode node1, LocationNode node2){
        node1.addLocation(node2);
        node2.addLocation(node1);
    }


    public void defaultCampus(){
        LocationNode seniorLot = new LocationNode("Senior Lot"); // cannot call a non-static inner class call with an outer static call
        nodes.put("Senior Lot", seniorLot);
        LocationNode security = new LocationNode("Security");
        nodes.put("Security", security);
        LocationNode dropOff = new LocationNode("Drop Off");
        nodes.put("Drop Off", dropOff);
        LocationNode businessOffice = new LocationNode("Business Office");
        nodes.put("Business Office", businessOffice);
        LocationNode mainLot = new LocationNode("Main Lot");
        nodes.put("Main Lot", mainLot);
        LocationNode building = new LocationNode("100-200 Building");
        nodes.put("100-200 Building", building);
        LocationNode fountain = new LocationNode("Fountain");
        nodes.put("Fountain", fountain);
        LocationNode rooks = new LocationNode("Rooks");
        nodes.put("Rooks", rooks);
        LocationNode library = new LocationNode("Library");
        nodes.put("Library", library);
        LocationNode cohen = new LocationNode("Cohen");
        nodes.put("Cohen", cohen);
        LocationNode mariani = new LocationNode("Mariani");
        nodes.put("Mariani", mariani);
        LocationNode chapel = new LocationNode("Chapel");
        nodes.put("Chapel", chapel);
        LocationNode performingArts = new LocationNode("Performing Arts");
        nodes.put("Performing Arts", performingArts);
        LocationNode athletics = new LocationNode("Athletics");
        nodes.put("Athletics", athletics);

        addConnection(seniorLot, security);
        addConnection(seniorLot, dropOff);
        addConnection(security, dropOff);
        addConnection(dropOff, businessOffice);
        addConnection(businessOffice, building);
        addConnection(building, dropOff);
        addConnection(security, mainLot);
        addConnection(mainLot, fountain);
        addConnection(dropOff, fountain);
        addConnection(rooks, dropOff);
        addConnection(rooks, building);
        addConnection(rooks, library);
        addConnection(library, dropOff);
        addConnection(library, cohen);
        addConnection(cohen, fountain);
        addConnection(fountain, performingArts);
        addConnection(cohen, mariani);
        addConnection(mariani, chapel);
        addConnection(mariani, performingArts);
        addConnection(performingArts, athletics);
        addConnection(performingArts, mainLot);
        addConnection(mainLot, athletics);
    }

    public void addDistances(boolean randomize){
        if(randomize == true){ // did randomized distances from 1-10
            distances.put("Senior Lot:Security", (int) (Math.random()*10) + 1);
            distances.put("Senior Lot:Drop Off", (int) (Math.random()*10) + 1);
            distances.put("Security:Drop Off", (int) (Math.random()*10) + 1);
            distances.put("Business Office:Drop Off", (int) (Math.random()*10) + 1);
            distances.put("100-200 Building:Business Office", (int) (Math.random()*10) + 1);
            distances.put("100-200 Building:Drop Off", (int) (Math.random()*10) + 1);
            distances.put("100-200 Building:Rooks", (int) (Math.random()*10) + 1);
            distances.put("Rooks:Library", (int) (Math.random()*10) + 1);
            distances.put("Rooks:Drop Off", (int) (Math.random()*10) + 1);
            distances.put("Library:Drop Off", (int) (Math.random()*10) + 1);
            distances.put("Library:Cohen", (int) (Math.random()*10) + 1);
            distances.put("Cohen:Fountain", (int) (Math.random()*10) + 1);
            distances.put("Fountain:Drop Off", (int) (Math.random()*10) + 1);
            distances.put("Security:Main Lot", (int) (Math.random()*10) + 1);
            distances.put("Fountain:Main Lot", (int) (Math.random()*10) + 1);
            distances.put("Fountain:Performing Arts", (int) (Math.random()*10) + 1);
            distances.put("Cohen:Mariani", (int) (Math.random()*10) + 1);
            distances.put("Mariani:Chapel", (int) (Math.random()*10) + 1);
            distances.put("Mariani:Performing Arts", (int) (Math.random()*10) + 1);
            distances.put("Performing Arts:Athletics", (int) (Math.random()*10) + 1);
            distances.put("Performing Arts:Main Lot", (int) (Math.random()*10) + 1);
            distances.put("Main Lot:Athletics", (int) (Math.random()*10) + 1);
        }
        else{
            distances.put("Senior Lot:Security", 5);
            distances.put("Senior Lot:Drop Off", 3);
            distances.put("Security:Drop Off", 2);
            distances.put("Business Office:Drop Off", 3);
            distances.put("100-200 Building:Business Office", 1);
            distances.put("100-200 Building:Drop Off", 4);
            distances.put("100-200 Building:Rooks", 1);
            distances.put("Rooks:Library", 2);
            distances.put("Rooks:Drop Off", 5);
            distances.put("Library:Drop Off", 6);
            distances.put("Library:Cohen", 2);
            distances.put("Cohen:Fountain", 1);
            distances.put("Fountain:Drop Off", 2);
            distances.put("Security:Main Lot", 4);
            distances.put("Fountain:Main Lot", 5);
            distances.put("Fountain:Performing Arts", 3);
            distances.put("Cohen:Mariani", 1);
            distances.put("Mariani:Chapel", 3);
            distances.put("Mariani:Performing Arts", 1);
            distances.put("Performing Arts:Athletics", 2);
            distances.put("Performing Arts:Main Lot", 2);
            distances.put("Main Lot:Athletics", 2);
        }
    }

    public void display(){
        for (Map.Entry<String,Integer> mapElement : distances.entrySet()) {
            String character = mapElement.getKey();
            System.out.println(mapElement.getKey() + " - " + mapElement.getValue());
        }
    }


    public class LocationNode{
        private String name;
        private ArrayList<LocationNode> connectedNodes = new ArrayList<>();
        private int numTimesVisited; // automatically set to 0

        public int getNumTimesVisited() {
            return numTimesVisited;
        }

        public void setNumTimesVisited(int numTimesVisited) {
            this.numTimesVisited = numTimesVisited;
        }

        public void incrementNumTimesVisited(){
            this.numTimesVisited++;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<LocationNode> getConnectedNodes() {
            return connectedNodes;
        }

        public void setConnectedNodes(ArrayList<LocationNode> connectedNodes) {
            this.connectedNodes = connectedNodes;
        }

        public void addLocation(LocationNode location){
            connectedNodes.add(location);
        }

        public LocationNode(String name){
            this.name = name;
        }

        /*
        public String toString(){
            return name;
        }

         */

    }

}
