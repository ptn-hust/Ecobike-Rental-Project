package group13.ecobikerental.entity.bike;

/**
 * Entity Bike
 */
public class Bike {

	private int bikeId;
    /**
     * Represent for code of Bike.
     */
    private String bikeCode;
    /**
     * Represent for type of Bike.
     */
    private String type;
    /**
     * Represent for deposit fee of bike.
     */
    private String dockName;
    /**
     * deposit fee of bike.
     */
    private int dockId;
    
    private int deposit;
    
    private int isBeingUsed;
    
    private int baseFee;
    
    private int extraFee;

    public Bike() {

    }

    /**
     * Constructor with 5 arguments.
     *
     * @param bikecode
     * @param type     - the type of bike
     * @param dockName - the name of dock
     * @param deposit  - the deposit fee of bike
     */
    public Bike(String bikecode, String type, String dockName, int deposit) {
        this.bikeCode = bikecode;
        this.type = type;
        this.dockName = dockName;
        this.deposit = deposit;
        this.baseFee = 10000;
        this.extraFee = 3000;
    }
    /**
	 * @param bikeCode
	 * @param type
	 * @param dockName
	 * @param deposit
	 * @param baseFee
	 * @param extraFee
	 */
	public Bike(String bikeCode, String type, String dockName, int deposit, int baseFee, int extraFee) {
		super();
		this.bikeCode = bikeCode;
		this.type = type;
		this.dockName = dockName;
		this.deposit = deposit;
		this.baseFee = baseFee;
		this.extraFee = extraFee;
	}
    

    /**
     * Getter for type
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type
     *
     * @param type - the type of bike
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for bikeCode
     *
     * @return bikeCode
     */
    public String getBikecode() {
        return this.bikeCode;
    }

    /**
     * Getter for dock name.
     *
     * @return dock name
     */
    public String getDockName() {
        return dockName;
    }

    /**
     * Setter for dock name.
     *
     * @param dockName - dock name.
     */
    public void setDockName(String dockName) {
        this.dockName = dockName;
    }

    /**
     * Getter for depositFee.
     *
     * @return - deposit
     */
    public int getDeposit() {
        return deposit;
    }

    /**
     * Setter for deposit fee.
     *
     * @param deposit - deposit fee
     */
    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getBikeCode() {
        return bikeCode;
    }

    public void setBikeCode(String bikeCode) {
        this.bikeCode = bikeCode;
    }
    
    public int calculateRentalFee(int minutes) {
    	int rentalFee = 0;
        if (minutes <= 1) {
            rentalFee = 0;
        } else if (minutes < 30) {
            rentalFee = this.baseFee;
        } else if (minutes % 15 == 0) {
            rentalFee = this.baseFee + (minutes - 30) / 15 * this.extraFee;
        } else {
            rentalFee = this.baseFee + ((minutes - 30) / 15 + 1) * this.extraFee;
        }
        return rentalFee;
    }


    @Override
	public String toString() {
		return "Bike [bikeId=" + bikeId + ", bikeCode=" + bikeCode + ", type=" + type + ", dockName=" + dockName
				+ ", deposit=" + deposit + ", isBeingUsed=" + isBeingUsed + ", baseFee=" + baseFee + ", extraFee="
				+ extraFee + "]";
	}

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

	public int getIsBeingUsed() {
		return isBeingUsed;
	}

	public void setIsBeingUsed(int isBeingUsed) {
		this.isBeingUsed = isBeingUsed;
	}

	public int getBaseFee() {
		return baseFee;
	}

	public void setBaseFee(int baseFee) {
		this.baseFee = baseFee;
	}

	public int getExtraFee() {
		return extraFee;
	}

	public void setExtraFee(int extraFee) {
		this.extraFee = extraFee;
	}

	public int getDockId() {
		return dockId;
	}

	public void setDockId(int dockId) {
		this.dockId = dockId;
	}
}
