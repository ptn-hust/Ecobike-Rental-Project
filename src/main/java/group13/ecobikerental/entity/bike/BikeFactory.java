package group13.ecobikerental.entity.bike;

public class BikeFactory {
    public Bike getBike(String type) {
        if (type.equals("Standard bike")) {
            return new Bike();
        } else if (type.equals("Standard e-bike")) {
            return new ElectricBike();
        } else if(type.equals("Twin bike")) {
        	return new TwinBike();
        }
        return null;
    }
}
