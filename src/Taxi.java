import java.util.*;

public class Taxi {

    static int taxi_count = 0;  //  Taxi No
    int id;                     //  Taxi Id
    boolean booked;             //  Taxi Status
    char current_spot;          //  Taxi Loco
    int free_time;              //  Taxi Availability
    int total_earnings;         //  Taxi Earnings
    List<String> trips;         //  Taxi Trips
}
