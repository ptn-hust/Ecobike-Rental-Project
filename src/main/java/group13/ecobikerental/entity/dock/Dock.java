package group13.ecobikerental.entity.dock;

/**
 * Entity class for Dock
 */
public class Dock {
	/**
	 * Represent for id of Dock
	 */
	private int id;
	/**
	 * Represent for name of Dock
	 */
	private String dockName;
	/**
	 * Represent for address of Dock
	 */
	private String address;
	/**
	 * Represent for total_bike of bike
	 */
	private int total_bike;
	/**
	 * Represent for area of Dock
	 */
	private int area;
	/**
	 * Represent for available bike dock can hold
	 */
	private int available_bike;
	/**
	 * Represent for img dock
	 */
	private String img_url;

	/**
     * Constructs a Dock object with specified attributes.
     * @param id         The ID of the docking station.
     * @param dockName   The name of the docking station.
     * @param address    The address of the docking station.
     * @param total_bike The total number of bikes that the docking station can hold.
     * @param area       The area of the docking station.
     */
	public Dock(int id, String dockName, String address, int total_bike, int area) {
		this.id = id;
		this.dockName = dockName;
		this.address = address;
		this.total_bike = total_bike;
		this.area = area;
	}

	/**
     * Constructs a Dock object with specified attributes.
     * @param id           The ID of the docking station.
     * @param dockName     The name of the docking station.
     * @param address      The address of the docking station.
     * @param total_bike   The total number of bikes that the docking station can hold.
     * @param area         The area of the docking station.
     * @param available_bike The number of available bikes at the docking station.
     */
	public Dock(int id, String dockName, String address, int total_bike, int area, int available_bike) {
		this.id = id;
		this.dockName = dockName;
		this.address = address;
		this.total_bike = total_bike;
		this.area = area;
		this.available_bike = available_bike;
	}

	/**
     * Constructs a Dock object with specified attributes.
     * @param id           The ID of the docking station.
     * @param dockName     The name of the docking station.
     * @param address      The address of the docking station.
     * @param total_bike   The total number of bikes that the docking station can hold.
     * @param area         The area of the docking station.
     * @param available_bike The number of available bikes at the docking station.
     * @param img_url      The URL of an image representing the docking station.
     */
	public Dock(int id, String dockName, String address, int total_bike, int area, int available_bike, String img_url) {
		super();
		this.id = id;
		this.dockName = dockName;
		this.address = address;
		this.total_bike = total_bike;
		this.area = area;
		this.available_bike = available_bike;
		this.img_url = img_url;
	}

	/**
	 * Default constructor for creating a Dock object.
	 */
	public Dock() {
	}

	/**
     * Gets the ID of the docking station.
     * @return The ID of the docking station.
     */
	public int getId() {
		return id;
	}

	/**
     * Sets the ID of the docking station.
     * @param id The ID of the docking station.
     */
	public void setId(int id) {
		this.id = id;
	}

	/**
     * Gets the name of the docking station.
     * @return The name of the docking station.
     */
	public String getDockName() {
		return dockName;
	}

	/**
     * Sets the name of the docking station.
     * @param dockName The name of the docking station.
     */
	public void setDockName(String dockName) {
		this.dockName = dockName;
	}

	/**
     * Gets the address of the docking station.
     * @return The address of the docking station.
     */
	public String getAddress() {
		return address;
	}

	/**
     * Sets the address of the docking station.
     * @param address The address of the docking station.
     */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
     * Gets the total number of bikes that the docking station can hold.
     * @return The total number of bikes that the docking station can hold.
     */
	public int getTotal_bike() {
		return total_bike;
	}

	/**
     * Sets the total number of bikes that the docking station can hold.
     * @param quantity The total number of bikes that the docking station can hold.
     */
	public void setTotal_bike(int quantity) {
		this.total_bike = quantity;
	}

	/**
     * Gets the area of the docking station.
     * @return The area of the docking station.
     */
	public int getArea() {
		return area;
	}

	/**
     * Sets the area of the docking station.
     * @param area The area of the docking station.
     */
	public void setArea(int area) {
		this.area = area;
	}

	/**
     * Gets the number of available bikes at the docking station.
     * @return The number of available bikes at the docking station.
     */
	public int getAvailable_bike() {
		return available_bike;
	}

	/**
     * Sets the number of available bikes at the docking station.
     * @param available_bike The number of available bikes at the docking station.
     */
	public void setAvailable_bike(int available_bike) {
		this.available_bike = available_bike;
	}

	/**
     * Gets the URL of an image representing the docking station.
     * @return The URL of the docking station's image.
     */
	public String getImg_url() {
		return img_url;
	}

	/**
     * Sets the URL of an image representing the docking station.
     * @param img_url The URL of the docking station's image.
     */
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
}
