package ticket.booking.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class User {
    private String userId;
    private String name;
    @JsonIgnore
    private String password;
    private String hashPassword;
    private List<Ticket> ticketBooked;

    public User(){}

    public User(String userId, String name, String password, String hashPassword, List<Ticket> ticketBooked){
          this.userId=userId;
          this.name=name;
          this.password=password;
          this.hashPassword=hashPassword;
          this.ticketBooked=ticketBooked;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public List<Ticket> getTicketBooked() {
        return ticketBooked;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public void setTicketBooked(List<Ticket> ticketBooked) {
        this.ticketBooked = ticketBooked;
    }

    public void printTickets(){
        for(int i=0; i<ticketBooked.size();i++){
            System.out.println("Booking "+(i+1));
            System.out.println(ticketBooked.get(i).TicketInfo());
            System.out.println();
        }
    }
}