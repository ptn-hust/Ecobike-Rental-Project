package group13.ecobikerental.entity.dock;

/**
 * Entity Dock
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

	private int available_bike;

	private String img_url;

	/**
	 * Constructor with 5 arguments
	 *
	 * @param id         the id of dock
	 * @param dockName   - the name of dock
	 * @param address    - the address of dock
	 * @param total_bike - the total_bike of bike
	 * @param area       - the area of dock
	 */
	public Dock(int id, String dockName, String address, int total_bike, int area) {
		this.id = id;
		this.dockName = dockName;
		this.address = address;
		this.total_bike = total_bike;
		this.area = area;
	}

	public Dock(int id, String dockName, String address, int total_bike, int area, int available_bike) {
		this.id = id;
		this.dockName = dockName;
		this.address = address;
		this.total_bike = total_bike;
		this.area = area;
		this.available_bike = available_bike;
	}

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
	 * Default constructor for class Dock
	 */
	public Dock() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDockName() {
		return dockName;
	}

	public void setDockName(String dockName) {
		this.dockName = dockName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTotal_bike() {
		return total_bike;
	}

	public void setTotal_bike(int quantity) {
		this.total_bike = quantity;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getAvailable_bike() {
		return available_bike;
	}

	public void setAvailable_bike(int available_bike) {
		this.available_bike = available_bike;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
}
