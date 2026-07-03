package ticket.booking.entities;

import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private String trainNo;
    private String trainName;
    private List<String> stations;
    private Map<String, String> stationTimes;
    private List<List<Integer>> seats;

    public Train(){};

    public Train(String trainId, String trainNo,String trainName, List<String> stations, Map<String, String> stationTimes, List<List<Integer>> seats) {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.trainName=trainName;
        this.stations = stations;
        this.stationTimes = stationTimes;
        this.seats = seats;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public String getTrainName(){ return trainName;}

    public List<String> getStations() {
        return stations;
    }

    public Map<String, String> getStationTimes() {
        return stationTimes;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public void setTrainName(String trainName){ this.trainName=trainName;}

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public void setStationTimes(Map<String, String> stationTimes) {
        this.stationTimes = stationTimes;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public int AvailableSeatsCount(){
        int count = 0;
        for(int i=0;i<getSeats().size();i++){
            for(int j=0;j<getSeats().get(i).size();j++){
                if(getSeats().get(i).get(j)==0){
                    count++;
                }
            }
        }
        return count;
    }

    public int TotalSeatsCount(){
        return getSeats().size() * getSeats().get(0).size();
    }
}
