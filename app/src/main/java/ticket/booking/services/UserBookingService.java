package ticket.booking.services;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.utility.UserServiceUtil;
//.................................................................

public class UserBookingService {

    private User user;

    private static final String USERS_PATH = "src/main/java/ticket/booking/local_db/users.json";

    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private void configureObjectMapper() {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void loadUsers() throws IOException{
        File users = new File(USERS_PATH);
        userList =objectMapper.readValue(users, new TypeReference<List<User>>(){});
    }

    public UserBookingService() throws IOException{
        configureObjectMapper();
        loadUsers();
    }

    public boolean loginUser(User user){
        Optional<User> foundUser = userList.stream().filter(user1->{
            return user1.getName().equals(user.getName()) &&
                    UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();

        if(foundUser.isPresent()){
            this.user = foundUser.get();
            return true;
        }
        return false;
    }

    public void logOut(){
        this.user = null;
    }

    public boolean signUp(User user1){
        Optional<User> existingUser = userList.stream().filter(u->{
            return u.getName().equals(user1.getName());
        }).findFirst();

        if(existingUser.isPresent()){
            return false;
        }
        try {
            userList.add(user1);
            SaveUserListToFile();
            return true;
        } catch(Exception e){
            return false;
        }
    }

    private void SaveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(usersFile, userList);
    }

    public boolean fetchBooking(){
        if(user.getTicketBooked().isEmpty()){
            System.out.println("No Booking Found!");
            return false;
        }else{
            user.printTickets();
            return true;
        }
    }

    public Ticket createTicket(Train selectedTrain, String source, String destination,int row, int col, Date dateOfTravel){
        String ticketId = UUID.randomUUID().toString();

        List<List<Integer>> seatMatrix = selectedTrain.getSeats();
        List<Integer> currentRow = seatMatrix.get(0);

        int totalColumn = currentRow.size();

        int seatNumber = (row-1)*totalColumn+col;

        Ticket ticket = new Ticket(ticketId,user.getUserId(),source,destination,selectedTrain,dateOfTravel,seatNumber,row,col);

        return ticket;
    }

    public void addTicket(Ticket ticket) throws IOException{
        user.getTicketBooked().add(ticket);
        SaveUserListToFile();
    }

    public boolean validBookingChoice(int choice){
       int bookingSize = user.getTicketBooked().size();
        if(choice>=1 && choice<=bookingSize){
            return true;
        }
        return false;
    }

    public Ticket getTicketByBookingNo(int choice){
        int bookingNo = (choice-1);
        return user.getTicketBooked().get(bookingNo);
    }

    public void cancelBooking(Ticket ticket) throws IOException{
        user.getTicketBooked().remove(ticket);
        SaveUserListToFile();
    }
}