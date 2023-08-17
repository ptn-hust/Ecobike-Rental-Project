package group13.ecobikerental.entity.bike;

/**
 * Factory class for creating instances of different types of bikes.
 */
public class BikeFactory {
	
	/**
     * Creates and returns an instance of a bike based on the provided type.
     * @param type The type of the bike to create.
     * @return An instance of the bike.
     */
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
