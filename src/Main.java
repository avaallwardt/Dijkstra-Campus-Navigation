import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // PART 1
        /*
        Person person = new Person();
        person.loop();

         */

        // Part 2
        System.out.println(Person.ANSI_PURPLE + "Welcome to SA!" + Person.ANSI_RESET); // IS IT OK THAT I ONLY STORE THE COLORS IN THE PERSON CLASS AND THEN CALL THEM FROM THERE
        Campus campus = new Campus();
        campus.defaultCampus();

        Dijkstra dijkstra = new Dijkstra();
        campus.addDistances(dijkstra.randomizeValues()); // now the user will have the user to randomize distance values
        dijkstra.dijkstraMethod(campus, dijkstra.getStartingLocation(campus), dijkstra.getEndLocation(campus)); // this method returns the location card



        // can check if end location is valid by searching through nodes list
        // if the end node isnt reachable (doesnt apply for the SA campus map) then return null once the priority queue is empty (ran out of nodes)


    }

}