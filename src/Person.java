
import java.util.ArrayList;
import java.util.Scanner;

public class Person {

    private Campus.LocationNode currentLocation;
    private int distanceTravelled;


    // ----------- FONT COLORS -----------
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    // ------------------------------------------------------------------




    public void loop(){
        System.out.println(ANSI_PURPLE + "Welcome to SA!" + ANSI_RESET);
        Campus campus = new Campus();
        campus.defaultCampus();
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
        currentLocation = campus.getNodes().get(input);
        currentLocation.setNumTimesVisited(1); // DO WE COUNT THE STARTING LOCATION AS A TIME WE VISITED IT?
        ArrayList<Campus.LocationNode> visitedLocations = new ArrayList<>();
        visitedLocations.add(currentLocation);

        distanceTravelled = 0;

        // SHOULD CURRENTLOCATION BE THE NODE OR THE STRING
        System.out.println("Do you want randomized distance values (Y) or hardcoded values (N)?\n" + "Y or N");
        input = scanner.nextLine();
        boolean randomized = false;
        while(!(input.equals("Y") || input.equals("N"))){
            System.out.println("Invalid response provided. Please type Y or N."); // NEED TO MAKE THIS A LOOP
            input = scanner.nextLine();
        }
        if(input.equals("Y")){
            randomized = true;
        }
        else if(input.equals("N")){
            randomized = false;
        }

        campus.addDistances(randomized);
        System.out.println("Map generated! Displaying...");
        campus.display();
        System.out.println();


        // STARTING TRAVERSAL
        boolean end = false;
        while(end == false){
            System.out.println(ANSI_CYAN + "Current Location: " + currentLocation.getName() + ". " + "Total Distance Traversed: " + distanceTravelled + "." + ANSI_RESET);

            System.out.println(ANSI_BLUE + "We have " + currentLocation.getConnectedNodes().size() + " paths available." + ANSI_RESET);
            for(int i = 0; i < currentLocation.getConnectedNodes().size(); i++){
                int num = i+1; // must make a num variable holding i+1 bc otherwise it will cast the i and 1 to a string since it comes after "["
                System.out.print("[" + num + "] " + currentLocation.getConnectedNodes().get(i).getName() + " with a travel value of ");
                if(campus.getDistances().containsKey(currentLocation.getName() + ":" + currentLocation.getConnectedNodes().get(i).getName())){
                    System.out.println(campus.getDistances().get(currentLocation.getName() + ":" + currentLocation.getConnectedNodes().get(i).getName()));
                }
                else if(campus.getDistances().containsKey(currentLocation.getConnectedNodes().get(i).getName() + ":" + currentLocation.getName())){
                    System.out.println(campus.getDistances().get(currentLocation.getConnectedNodes().get(i).getName() + ":" + currentLocation.getName()));
                }
            }
            System.out.println("[-] Done");
            System.out.println("Which one do you want to travel to?");

            input = scanner.nextLine();
            boolean invalidText = true;
            while(invalidText){ // the loop makes sure the user enters valid text
                for(int i = 0; i < currentLocation.getConnectedNodes().size(); i++){
                    if(input.equals(i+1 + "")){ // add the "" to turn i+1 into a string to compare it with input which is a string
                        invalidText = false;
                        // now we can figure out which connection we want based on the number inputted (if there even is a connection they want)
                        if(campus.getDistances().containsKey(currentLocation.getName() + ":" + currentLocation.getConnectedNodes().get(i).getName())){
                            // the correct connection is found! now we need to update distance travelled and currentLocation to the new location
                            distanceTravelled += campus.getDistances().get(currentLocation.getName() + ":" + currentLocation.getConnectedNodes().get(i).getName());
                            currentLocation = currentLocation.getConnectedNodes().get(i);

                            currentLocation.incrementNumTimesVisited();

                            if(!visitedLocations.contains(currentLocation)){
                                visitedLocations.add(currentLocation);
                            }
                            //System.out.println("here 1");
                        }
                        else if(campus.getDistances().containsKey(currentLocation.getConnectedNodes().get(i).getName() + ":" + currentLocation.getName())){
                            // the correct connection is found! now we need to update distance travelled and currentLocation to the new location
                            distanceTravelled += campus.getDistances().get(currentLocation.getConnectedNodes().get(i).getName() + ":" + currentLocation.getName());
                            currentLocation = currentLocation.getConnectedNodes().get(i);

                            currentLocation.incrementNumTimesVisited();

                            if(!visitedLocations.contains(currentLocation)){
                                visitedLocations.add(currentLocation);
                            }
                            //System.out.println("here 2");
                        }
                    }
                }
                if(input.equals("-")){
                    end = true;
                    invalidText = false;
                }
                if(invalidText == true){ // means the text is something else
                    System.out.println("Invalid response provided. Try again.");
                    input = scanner.nextLine();
                }
            }

            // else, it will just end the loop and the game
            if(end == true){
                System.out.println("Our final location is " + currentLocation.getName() + ". We had a total travelled distance of " + distanceTravelled + ".");

                int max = 0;
                ArrayList<String> multipleMaxVisits = new ArrayList<>();
                String maxVisitedLocationName = "";
                for(int i = 0; i < visitedLocations.size(); i++){
                    if(visitedLocations.get(i).getNumTimesVisited() > max){
                        multipleMaxVisits.clear(); // bc now we are setting a new max
                        //System.out.println("cleared");
                        multipleMaxVisits.add(visitedLocations.get(i).getName());

                        max = visitedLocations.get(i).getNumTimesVisited();
                        maxVisitedLocationName = visitedLocations.get(i).getName();
                        //System.out.println(maxVisitedLocationName);
                    }
                    else if(visitedLocations.get(i).getNumTimesVisited() == max){
                        //System.out.println("found one equal");
                        multipleMaxVisits.add(visitedLocations.get(i).getName());
                    }
                }
                if(multipleMaxVisits.size() <= 1){ // if it has 1, that 1 is the same as the maxVisitedLocationName, so we don't need to traverse through the array list
                    System.out.println(maxVisitedLocationName + " was most frequently visited. It was visited " + max + " times.");
                }
                else{
                    for(int i = 0; i < multipleMaxVisits.size(); i++){
                        if(i==0){ // done to get the comma placement correct
                            System.out.print(multipleMaxVisits.get(i));
                        }
                        else{
                            System.out.print(", " + multipleMaxVisits.get(i));
                        }
                    }
                    System.out.println(" were most frequently visited. They were each visited " + max + " times.");
                }

                System.out.println("Campus travel over! Thank you!");
            }

        }







    }

}
