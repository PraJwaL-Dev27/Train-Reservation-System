package ticket.booking.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
   private String ticketId;
   private String userId;
   private String source;
   private String destination;
   private Train train;
   private Date dateOfTravel;
   private int seatNumber;
   private int row;
   private int col;

   public Ticket(){};

   public Ticket(String ticketId, String userId, String source, String destination, Train train, Date dateOfTravel, int seatNo,int row,int col){
       this.ticketId=ticketId;
       this.userId=userId;
       this.source=source;
       this.destination=destination;
       this.train=train;
       this.dateOfTravel=dateOfTravel;
       this.seatNumber=seatNo;
       this.row=row;
       this.col=col;
   }

    public String getTicketId() {
        return ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public Train getTrain() {
        return train;
    }

    public Date getDateOfTravel() {
        return dateOfTravel;
    }

    public int getSeatNumber(){
       return seatNumber;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setDateOfTravel(Date dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public void setSeatNumber(int seatNo){
       this.seatNumber=seatNo;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public String TicketInfo(){
       return "...........Train Ticket..........."+
               "\nTicket ID     : "+ticketId+
               "\nTrain Name    : "+train.getTrainName()+
               "\nTrain number  : "+train.getTrainNo()+"\n"+

               "\nSource        : "+source+
               "\nDeparture Time: "+train.getStationTimes().get(source)+"\n"+

               "\nDestination   : "+destination+
               "\nArrival Time  : "+train.getStationTimes().get(destination)+"\n"+

               "\nSeat Number   : "+seatNumber+ " (R" +row+ " C" +col+")"+
               "\nJourney Date  : "+formatter.format(dateOfTravel)+
               "\n...................................";
    }
}