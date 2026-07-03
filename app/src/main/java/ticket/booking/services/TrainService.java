package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TrainService {

    private static final String TRAIN_PATH="src/main/java/ticket/booking/local_db/trains.json";
    private List<Train> trainList;
    private ObjectMapper objectMapper = new ObjectMapper();

    public void loadTrains() throws IOException {
        File trains = new File(TRAIN_PATH);
        trainList = objectMapper.readValue(trains, new TypeReference<List<Train>>(){});
    }

    private void configureObjectMapper() {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private void saveTrainListToFile() throws IOException{
        File trainsFile =new File(TRAIN_PATH);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(trainsFile, trainList);
    }

    public TrainService() throws IOException{
        configureObjectMapper();
        loadTrains();
    }

    public List<Train> trainSearch(String source,String destination){
        List<Train> foundTrain = trainList.stream().filter(train->{
            return validTrain(train,source,destination);
        }).toList();

        return foundTrain;
    }

    private boolean validTrain(Train train, String source, String destination) {
        boolean stationExist =  train.getStations().contains(source) && train.getStations().contains(destination);
        if(!stationExist){
            return false;
        }
        int sourceIndex = train.getStations().indexOf(source);
        int destinationIndex = train.getStations().indexOf(destination);

        if(sourceIndex<destinationIndex){
            return true;
        }
        return false;
    }

    public void displayTrains(List<Train> trains, String s, String d){
        if(trains.isEmpty()){
            System.out.println();
            System.out.println("No Train Found!");
            return;
        }
        System.out.println("===========================");
        System.out.println("     Available Trains    ");
        System.out.println("===========================");

        for(int i=0;i<trains.size();i++){
            Train train = trains.get(i);

            String departureTime = train.getStationTimes().get(s);
            String arrivalTime = train.getStationTimes().get(d);

            System.out.println(i+1+".");
            System.out.println("Train Name  : "+train.getTrainName()+" ("+train.getTrainNo()+")");
            System.out.println(s+" -----------> "+d);
            System.out.println(departureTime+"               "+arrivalTime);
            System.out.println();
            System.out.println("Available Seats: "+train.AvailableSeatsCount()+" of "+train.TotalSeatsCount());
            System.out.println("---------------------------");
        }
    }

    public Train selectTrain(List<Train> foundTrains, int choice3){
        if(choice3<=0 || choice3>foundTrains.size()){
            System.out.println("Invalid Train Choice!");
            return null;
        }
          return foundTrains.get(choice3-1);
    }

    public void displaySeats(Train selectedTrain){
        List<List<Integer>> seatMatrix = selectedTrain.getSeats();
        System.out.println("============================");
        System.out.println("     Seat Layout   ");
        System.out.println("============================");
        System.out.println();
        System.out.println("     C1   C2  C3  C4  C5  C6");

        for(int i=0;i<seatMatrix.size();i++){
            List<Integer> currentRow = seatMatrix.get(i);
            System.out.print("R"+(i+1)+"    ");

            for(int j=0;j<currentRow.size();j++){
                if(currentRow.get(j)==0){
                    System.out.print("O   ");
                }else{
                    System.out.print("X   ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("------------------------");
        System.out.println("O = Available \nX = Booked");

    }

    public boolean validSeatPosition(Train selectedTrain, int row, int col){
        List<List<Integer>> seatMatrix = selectedTrain.getSeats();
        List<Integer> currentRow = seatMatrix.get(0);
        if(row>=1 && row<=seatMatrix.size() && col>=1 && col<=currentRow.size()){
            return true;
        }
        return false;
    }

    public boolean checkSeatAvailability(Train selectedTrain, int selectRow, int selectCol) {
        int row = selectRow-1;
        int col = selectCol-1;
        List<List<Integer>> seatMatrix = selectedTrain.getSeats();

        if(seatMatrix.get(row).get(col)==0){
            return true;
        }
        return false;
    }

    public void bookSeat(Train selectedTrain, int row, int col) throws IOException{
        row -= 1;
        col -= 1;

        List<List<Integer>> seatMatrix = selectedTrain.getSeats();
        List<Integer> currentRow = seatMatrix.get(row);

        currentRow.set(col, 1);

        saveTrainListToFile();
    }

    public void freeSeat(Ticket ticket) throws IOException{
        Train train = ticket.getTrain();
        int row = ticket.getRow();
        int col = ticket.getCol();

        row -=1;
        col -=1;

        List<List<Integer>> seatMatrix = train.getSeats();
        List<Integer> currentRow = seatMatrix.get(0);

        currentRow.set(col,0);
        saveTrainListToFile();
    }
}