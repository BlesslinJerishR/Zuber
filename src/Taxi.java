import java.util.*;

public class Taxi {

    static int taxi_count = 0;  //  Taxi No
    int id;                     //  Taxi Id
    boolean booked;             //  Taxi Status
    char current_spot;          //  Taxi Loco
    int free_time;              //  Taxi Availability
    int total_earnings;         //  Taxi Earnings
    List<String> trips;         //  Taxi Trips

    public Taxi() {
        booked = false;
        current_spot = 'A';     //  Start Point
        free_time = 6;          //  Free Time 6 Clock
        total_earnings = 0;     //  Earnings
        trips = new ArrayList<String>();  //    new ArrayList for List String trips
        taxi_count = taxi_count + 1;      //    whenever new Taxi is created , += 1
        id = taxi_count;                  //    Taxi Id Assigns
    }

    public void set_details(boolean booked, char current_spot, int free_time, int total_earnings, String trip_info) {
        this.booked = booked;
        this.current_spot = current_spot;
        this.free_time = free_time;
        this.total_earnings = total_earnings;
        this.trips.add(trip_info);
    }

    public void print_info() {
        //   Trip Information
        System.out.println("Taxi No " + this.id + " Total Earnings " + this.total_earnings);
        System.out.println("Taxi Id   |   Booking Id   |   Customer Id   |   From   |   To   |   Pick Up   |   Drop   |   Amount   |");
        for (String trip : trips) {
            System.out.println(id + "                  " + trip);
        }
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }

    public void print_taxi_info() {
        //  Print Taxi Details
        System.out.println("Taxi - "+ this.id + "Total Earnings - "+ this.total_earnings +" Current Spot "+ this.current_spot +" Free Time "+ this.free_time);
    }
}
