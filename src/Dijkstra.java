import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {

    PriorityQueue<LocationCard> PQ = new PriorityQueue<>(new LocationCardComparator());

    public boolean randomizeValues(){
        System.out.println("Do you want randomized distance values between locations (Y) or hardcoded values (N)?\n" + "Y or N");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!(input.equals("Y") || input.equals("N"))){
            System.out.println("Invalid response provided. Please type Y or N."); // NEED TO MAKE THIS A LOOP
            input = scanner.nextLine();
        }
        if(input.equals("Y")){
            return true;
        }
        else if(input.equals("N")){
            return false;
        }
        else{
            return false;
        }
    }

    public Campus.LocationNode getStartingLocation(Campus campus){ // IS IT OK THAT THE DIJKSTRA METHOD IS ONLY CALLED IN THE CONTEXT OF THIS LOOP METHOD?
        System.out.println("Please select a starting location.");
        System.out.println(
                "Senior Lot\n" +
                        "Security\n" +
                        "Drop Off\n" +
                        "Business Office\n" +
                        "100-200 Building\n" +
                        "Fountain\n" +
                        "Main Lot\n" +
                        "Rooks\n" +
                        "Library\n" +
                        "Cohen\n" +
                        "Performing Arts\n" +
                        "Athletics\n" +
                        "Mariani\n" +
                        "Chapel\n"
        );

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String input = scanner.nextLine();  // Read user input
        while(!campus.getNodes().containsKey(input)){
            // had to create the map/locations (so that they are in the nodes hash map) then set currentLocation then set the distances
            System.out.println("Invalid location provided. Try again.");
            System.out.println(
                    "Senior Lot\n" +
                            "Security\n" +
                            "Drop Off\n" +
                            "Business Office\n" +
                            "100-200 Building\n" +
                            "Fountain\n" +
                            "Main Lot\n" +
                            "Rooks\n" +
                            "Library\n" +
                            "Cohen\n" +
                            "Performing Arts\n" +
                            "Athletics\n" +
                            "Mariani\n" +
                            "Chapel\n"
            );
            input = scanner.nextLine();
        }
        Campus.LocationNode startingLocation = campus.getNodes().get(input);
        return startingLocation;
    }

    public Campus.LocationNode getEndLocation(Campus campus){
        System.out.println("Please select an end location.");
        System.out.println(
                "Senior Lot\n" +
                        "Security\n" +
                        "Drop Off\n" +
                        "Business Office\n" +
                        "100-200 Building\n" +
                        "Fountain\n" +
                        "Main Lot\n" +
                        "Rooks\n" +
                        "Library\n" +
                        "Cohen\n" +
                        "Performing Arts\n" +
                        "Athletics\n" +
                        "Mariani\n" +
                        "Chapel\n"
        );
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String input = scanner.nextLine(); // read user input
        while(!campus.getNodes().containsKey(input)){
            // had to create the map/locations (so that they are in the nodes hash map) then set currentLocation then set the distances
            System.out.println("Invalid location provided. Try again.");
            System.out.println(
                    "Senior Lot\n" +
                            "Security\n" +
                            "Drop Off\n" +
                            "Business Office\n" +
                            "100-200 Building\n" +
                            "Fountain\n" +
                            "Main Lot\n" +
                            "Rooks\n" +
                            "Library\n" +
                            "Cohen\n" +
                            "Performing Arts\n" +
                            "Athletics\n" +
                            "Mariani\n" +
                            "Chapel\n"
            );
            input = scanner.nextLine();
        }
        Campus.LocationNode endLocation = campus.getNodes().get(input);
        return endLocation;
    }

    // do validation of starting and end location in diff method earlier
    // then do a separate method to check that the string input by user is valid starting location and convert the string to a locationnode
    public LocationCard dijkstraMethod(Campus campus, Campus.LocationNode startingLocation, Campus.LocationNode endLocation){
        LocationCard firstLocation = new LocationCard(startingLocation.getName(),startingLocation, null, 0);
        PQ.add(firstLocation);
        System.out.println( Person.ANSI_CYAN + "Adding a new location card to the PQ with the following information: " + Person.ANSI_RESET);
        System.out.println("Current Location Name: " + firstLocation.getCurrentName());
        System.out.println("Current Location: " + firstLocation.getCurrent());
        System.out.println("Previous Card: " + firstLocation.getPrevious());
        System.out.println("Distance Cost: " + firstLocation.getDistanceCost());


        while(PQ.isEmpty() == false){
            LocationCard mostRecent = PQ.remove();
            System.out.println(Person.ANSI_BLUE + "Removing the location card from the PQ with the following information: " + Person.ANSI_RESET);
            System.out.println("Current Location Name: " + mostRecent.getCurrentName());
            System.out.println("Current Location: " + mostRecent.getCurrent());
            System.out.println("Previous Card: " + mostRecent.getPrevious());
            System.out.println("Distance Cost: " + mostRecent.getDistanceCost());
            // COULD ADD CURRENT STATE OF PRIORITY QUEUE

            // go through all nodes the mostRecent is connected to and add to priorityQueue
            for(int i = 0; i < mostRecent.getCurrent().getConnectedNodes().size(); i++){
                int addedDistance = 0;
                if(campus.getDistances().containsKey(mostRecent.getCurrent().getName() + ":" + mostRecent.getCurrent().getConnectedNodes().get(i).getName())){
                    addedDistance = campus.getDistances().get(mostRecent.getCurrent().getName() + ":" + mostRecent.getCurrent().getConnectedNodes().get(i).getName());
                }
                else if(campus.getDistances().containsKey(mostRecent.getCurrent().getConnectedNodes().get(i).getName()+ ":" + mostRecent.getCurrent().getName())){
                    addedDistance = campus.getDistances().get(mostRecent.getCurrent().getConnectedNodes().get(i).getName()+ ":" + mostRecent.getCurrent().getName());
                }
                LocationCard newCard = new LocationCard(mostRecent.getCurrent().getConnectedNodes().get(i).getName(), mostRecent.getCurrent().getConnectedNodes().get(i), mostRecent, mostRecent.getDistanceCost() + addedDistance);
                PQ.add(newCard);
                System.out.println( Person.ANSI_CYAN + "Adding a new location card to the PQ with the following information: " + Person.ANSI_RESET);
                System.out.println("Current Location Name: " + newCard.getCurrentName());
                System.out.println("Current Location: " + newCard.getCurrent());
                System.out.println("Previous Card: " + newCard.getPrevious());
                System.out.println("Distance Cost: " + newCard.getDistanceCost());


                if(mostRecent.getCurrent().getConnectedNodes().get(i).equals(endLocation)){ // CHECK THIS .EQUALS
                    System.out.println(Person.ANSI_GREEN + "Shortest path found." + Person.ANSI_RESET);
                    // get path to get back
                    LocationCard current = newCard;
                    ArrayList<String> path = new ArrayList<>(); // path from last to first location
                    while(current.getPrevious() != null){ // prints out all of the locations (starting at the end) --> just traces way through location card previous cards
                        path.add(current.getCurrent().getName()); // adds the current location to the path
                        // System.out.println(current.getCurrent().getName());// prints out currentLocation
                        current = current.getPrevious(); // then goes to the previous location card
                    }
                    // now getPrevious is null but need to add the original location
                    path.add(current.getCurrent().getName());

                    // then iterate through array list in reverse order to print out path in correct order
                    System.out.print("The path is: ");
                    for(int k = path.size() - 1; k >= 0; k--){
                        if(k == path.size() - 1){
                            System.out.print(path.get(k));
                        }
                        else{
                            System.out.print(" --> " + path.get(k));
                        }
                    }
                    System.out.println();
                    System.out.println("The total distance cost is: " + newCard.getDistanceCost());
                    return newCard;
                }
            }
        }
        // if it empties and no endLocation node is found --> end location must not be able to be reached from starting location (not connected)
        System.out.println("End location not able to be reached from starting location.");
        return null;
    }



    public class LocationCard{
        public String getCurrentName() {
            return currentName;
        }

        public void setCurrentName(String currentName) {
            this.currentName = currentName;
        }

        private String currentName;
        private Campus.LocationNode current;
        private LocationCard previous;
        private Integer distanceCost;

        public LocationCard(String currentName, Campus.LocationNode current, LocationCard previous, Integer distanceCost){
            this.currentName = currentName;
            this.current = current;
            this.previous = previous;
            this.distanceCost = distanceCost;
        }

        // LocationCard toString method

        // Mr. U, I wrote a to String but I thought it was easier to keep track of the objects without the toString. You can uncomment out this method to test the code and the one in the Campus location node class to use them.
        /*
        public String toString(){
            return "Current Name - " + currentName + "; Current - " + current + "; Previous - " + previous + "; Distance Cost - " + distanceCost;
        }

         */


        public Campus.LocationNode getCurrent() {
            return current;
        }

        public void setCurrent(Campus.LocationNode current) {
            this.current = current;
        }

        public LocationCard getPrevious() {
            return previous;
        }

        public void setPrevious(LocationCard previous) {
            this.previous = previous;
        }

        public Integer getDistanceCost() {
            return distanceCost;
        }

        public void setDistanceCost(Integer distanceCost) {
            this.distanceCost = distanceCost;
        }
    }

    public class LocationCardComparator implements Comparator<LocationCard> {
        @Override // always use @override with implements -- way of getting past with subclassing where you can only have 1 subclass
        public int compare(LocationCard o1, LocationCard o2) {
            return o1.getDistanceCost().compareTo(o2.getDistanceCost());
            // if returns negative (first is less than second) --> will put at the BEGINNING of the priority queue --> so that when you dequeue, you get the lowest frequency
            // if returns positive --> will put at the END of priority queue
            // SO, this is what we want
        }

    }
}
