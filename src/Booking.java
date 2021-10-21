import java.util.*;

public class Booking {
    public static void book_taxi(int customer_id, char pick, char drop, int p_time, List<Taxi> free_taxis){
        int min = 999;         //  Nearest
        int dist_btw_pd = 0;   //  Distance between pick & drop
        int earning = 0;       //  Earning
        int que_free = 0;      //  Next Free Time
        char que_spot = 'Z';   //  Next Free Spot
        Taxi booked_taxi = null;
        String trip_detail = "";

        for(Taxi t:free_taxis){
            int dist_btw_ct = Math.abs((t.current_spot - '0') - (pick - '0')) * 15;
            if(dist_btw_ct < min){
                booked_taxi = t;
//                Distance Between Pickup & Drop = ( drop - pick) * 15KM
                dist_btw_pd = Math.abs((drop - '0') - (pick -'0')) * 15;
//                Trip Earning = 100 + ( dis_btw_pd - 5 ) * 10
                earning = (dist_btw_pd - 5) * 10 + 100;
//                Drop Time
                int d_time;
                d_time = p_time + dist_btw_pd / 15;
//                Que Taxi
                que_free = d_time;
//                Que Spot
                que_spot = drop;
//                Trip Details
                trip_detail = customer_id +"           "+ customer_id +"           "+ pick +"           "+ drop +"           "+ p_time +"           "+ d_time +"           "+ earning;
                min = dist_btw_ct;
            }
        }

//         Corresponding details to allotted Taxi
        assert booked_taxi != null;
        booked_taxi.set_details(true, que_spot, que_free, booked_taxi.total_earnings + earning , trip_detail);
//        Successful Booking
        System.out.println("Taxi "+ booked_taxi.id +" booked");
    }

    public static List<Taxi> createTaxis(int n){
        List<Taxi> taxis = new ArrayList<Taxi>();
//        Create Taxis
        for(int i=1; i<=1; i++){
            Taxi t = new Taxi();
            taxis.add(t);
        }
        return taxis;
    }

    public static List<Taxi> getFreeTaxis(List<Taxi> taxis, int p_time, char pickup){
        List<Taxi> freeTaxis = new ArrayList<Taxi>();
        for(Taxi t : taxis)
        {
            //taxi should be free
            //taxi should have enough time to reach customer before pickuptime
            if(t.free_time <= pickup && (Math.abs((t.current_spot - '0') - (pickup - '0')) <= pickup - t.free_time))
                freeTaxis.add(t);

        }
        return freeTaxis;
    };

    public static void main(String[] args) {
//        Create 4 taxis
        List<Taxi> taxis = createTaxis(4);
        
        Scanner s = new Scanner(System.in);
        int id = 1;

        while(true) {
            System.out.println("0 - Book Taxi");
            System.out.println("1 - Taxi Info");
            int choice = s.nextInt();
            switch (choice) {
                case 0: {
                    //get details from customers

                    int customerID = id;
                    System.out.println("Enter Pickup point [A - F]");
                    char pickup = s.next().toUpperCase().charAt(0);
                    System.out.println("Enter Drop point [A - F]");
                    char drop = s.next().toUpperCase().charAt(0);
                    System.out.println("Enter Pickup time");
                    int p_time = s.nextInt();

                    //check if pickup and drop points are valid
                    if (pickup < 'A' || drop > 'F' || pickup > 'F' || drop < 'A') {
                        System.out.println("Valid pickup and drop are A, B, C, D, E, F. Thank You ");
                        return;
                    }
                    // get all free taxis that can reach customer on or before pickup time
                    List<Taxi> freeTaxis = getFreeTaxis(taxis, p_time, pickup);

                    //no free taxi means we cannot allot, exit!
                    if (freeTaxis.size() == 0) {
                        System.out.println("No Taxi can be Allotted . Thank You");
                        return;
                    }

                    //sort taxis based on earnings
                    freeTaxis.sort((a, b) -> a.total_earnings - b.total_earnings);
                    // 3,4,2 - > 2,3,4

                    //get free Taxi nearest to us
                    book_taxi(id, pickup, drop, p_time, freeTaxis);
                    id++;
                    break;
                }
                case 1: {
                    //two functions to print details
                    for (Taxi t : taxis)
                        t.print_taxi_info();
                    for (Taxi t : taxis)
                        t.print_info();
                    break;
                }
                default:
                    return;
            }
        }
        }
}
