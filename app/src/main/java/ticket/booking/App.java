package ticket.booking;

import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.services.TrainService;
import ticket.booking.services.UserBookingService;
import ticket.booking.utility.UserServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class App {
       public static void main(String[] args) {
           Scanner sc=new Scanner(System.in);

           System.out.println("==================================");
           System.out.println("      TRAIN BOOKING SYSTEM        ");
           System.out.println("              v1.0                ");
           System.out.println("==================================");

           UserBookingService userBookingService;
           TrainService trainService;
        try{
            userBookingService = new UserBookingService();
            trainService = new TrainService();

            System.out.print("Loading application");
            for(int i=0;i<3;i++){
                Thread.sleep(900);
                System.out.print(".");
            }
            System.out.println();
            System.out.print("Application Loaded Successfully :)");
        }catch(IOException e){
           System.out.println("Unable to load users or trains!");
            e.printStackTrace();
            return;
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            return;
        }

            int choice=0;
            System.out.println();
            while(true){
                System.out.println();
                System.out.println("=========================");
                System.out.println("         MAIN MENU       ");
                System.out.println("=========================");
                System.out.println("1. Sign Up \n2. Login \n3. Exit ");
                System.out.print("Enter Choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch(choice){
                    case 1:
                        System.out.print("Enter Username: ");
                        String nameToSignUp=sc.nextLine();
                        System.out.print("Enter Password: ");
                        String passwordToSignUp=sc.nextLine();

                        User userToSignUp = new User(UUID.randomUUID().toString(),nameToSignUp,
                                passwordToSignUp,UserServiceUtil.hashPassword(passwordToSignUp),new ArrayList<>());

                        if(userBookingService.signUp(userToSignUp)){
                            System.out.println();
                            System.out.println("Sign Up Successful :)");
                        }else{
                            System.out.println();
                            System.out.println("User already Registered!");
                        }
                        break;

                    case 2:
                        System.out.print("Enter username: ");
                        String nameToLogin=sc.nextLine();
                        System.out.print("Enter Password: ");
                        String passwordToLogin=sc.nextLine();

                        User loginUser = new User(null,nameToLogin,passwordToLogin,
                                null,null);

                        if(userBookingService.loginUser(loginUser)){
                            System.out.println();
                            System.out.println("User Logged In Successfully :)");

                            boolean loggedIn = true;
                            int choice2;
                            while(loggedIn){
                                System.out.println();
                                System.out.println("=========================");
                                System.out.println("         USER MENU       ");
                                System.out.println("=========================");
                                System.out.println("1. Book Ticket \n2. View My Bookings \n3. Cancel Booking \n4. Logout ");
                                System.out.print("Enter Choice: ");
                                choice2 = sc.nextInt();
                                sc.nextLine();

                                switch(choice2){
                                    case 1:
                                        System.out.println();
                                        System.out.println("Enter Source: ");
                                        String source = sc.nextLine().trim();
                                        System.out.println("Enter Destination: ");
                                        String destination = sc.nextLine().trim();

                                        List<Train> foundTrains = trainService.trainSearch(source,destination);
                                        trainService.displayTrains(foundTrains,source,destination);

                                        if(foundTrains.isEmpty()){
                                            break;
                                        }else {
                                            Train selectedTrain;
                                            while (true) {
                                                System.out.println("Enter Train Choice: ");
                                                int choice3 = sc.nextInt();
                                                selectedTrain = trainService.selectTrain(foundTrains, choice3);
                                                if(selectedTrain==null){
                                                    continue;
                                                }
                                                break;
                                            }
                                            trainService.displaySeats(selectedTrain);

                                            while(true) {
                                                System.out.println("Enter Row Of Seat: ");
                                                int selectRow = sc.nextInt();
                                                System.out.println("Enter Column Of Seat: ");
                                                int selectCol = sc.nextInt();

                                                if(!trainService.validSeatPosition(selectedTrain,selectRow, selectCol)){
                                                    System.out.println("Invalid seat position! Please enter a valid row and column.");
                                                    continue;
                                                }

                                                if(!trainService.checkSeatAvailability(selectedTrain, selectRow, selectCol)){
                                                    System.out.println("This seat is already booked. Please choose another seat.");
                                                    continue;
                                                }
                                                sc.nextLine();

                                                System.out.println("Enter Journey Date (dd/MM/yyyy): ");
                                                String travelDate = sc.nextLine();

                                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                                                try {
                                                    Date dateOfTravel = formatter.parse(travelDate);

                                                    Ticket ticket = userBookingService.createTicket(selectedTrain,source,destination,selectRow,selectCol,dateOfTravel);
                                                    trainService.bookSeat(selectedTrain,selectRow,selectCol);
                                                    userBookingService.addTicket(ticket);

                                                    System.out.println();
                                                    System.out.println("================================");
                                                    System.out.println("      BOOKING SUCCESSFUL :)");
                                                    System.out.println("================================");
                                                    System.out.println(ticket.TicketInfo());

                                                    System.out.println();
                                                    System.out.println("Press Enter to continue...");
                                                    sc.nextLine();


                                                }catch(ParseException e){
                                                    System.out.println("Invalid date format! Please use dd/MM/yyyy.");
                                                    continue;
                                                }catch(IOException ex){
                                                    System.out.println("Something went wrong while saving your booking.");
                                                    break;
                                                }
                                                break;
                                            }
                                        }

                                        break;

                                    case 2:
                                        System.out.println();
                                        System.out.print("fetching bookings");
                                        try{
                                            for(int i=0;i<3;i++){
                                                Thread.sleep(500);
                                                System.out.print(".");
                                            }
                                            System.out.println();
                                            System.out.println("================================");
                                            System.out.println("      MY BOOKING :) ");
                                            System.out.println("================================");
                                            userBookingService.fetchBooking();

                                        }catch(InterruptedException e){
                                            Thread.currentThread().interrupt();
                                        }

                                        break;

                                    case 3:
                                        System.out.println();
                                        System.out.println("================================");
                                        System.out.println("      MY BOOKING HISTORY  ");
                                        System.out.println("================================");

                                        if(!userBookingService.fetchBooking()){
                                            break;
                                        }

                                        while(true) {
                                            System.out.print("Enter Booking Number to Cancel: ");
                                            int choice4 = sc.nextInt();

                                            if(!userBookingService.validBookingChoice(choice4)){
                                                System.out.println("Invalid Choice! please enter correct number.");
                                                continue;
                                            }

                                            Ticket ticket = userBookingService.getTicketByBookingNo(choice4);
                                            try {
                                                userBookingService.cancelBooking(ticket);
                                                trainService.freeSeat(ticket);
                                                System.out.println("Booking Cancelled Successfully!");
                                            }catch(IOException e){
                                                System.out.println("Something went wrong while cancelling the booking.");
                                            }

                                            break;
                                        }
                                        break;

                                    case 4:
                                        userBookingService.logOut();
                                        System.out.println();
                                        System.out.println("logged out successfully");
                                        loggedIn = false;
                                        break;

                                    default:
                                        System.out.println();
                                        System.out.println("Invalid choice!");
                                        break;
                                }
                            }

                        }else{
                            System.out.println("Invalid Username or Password!");
                        }
                        break;

                    case 3:
                        System.out.println();
                        System.out.println("Thankyou for using train booking system!");
                        return;

                    default:
                        System.out.println("Invalid Choice!");
                }
            }

    }
}
