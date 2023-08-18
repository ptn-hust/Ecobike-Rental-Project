package group13.ecobikerental.business_layer;

/**
 * Bike Business Logic class to validate and convert bike barcode
 */
public class BikeBL {
	private static BikeBL instance;
	
	private BikeBL() {
		
	}
	/**
     * Get the instance of BikeBL using a thread-safe singleton pattern.
     * @return The BikeBL instance.
     */
    public static BikeBL getInstance() {
        if (instance == null) {
            synchronized (BikeBL.class) {
                if (instance == null) {
                    instance = new BikeBL();
                }
            }
        }
        return instance;
    }
    /**
     * Validate a bike barcode.
     * @param barcode The barcode to validate.
     * @return true if the barcode is valid, false otherwise.
     */
    // barcode is a string of number, length 12
	public boolean validateBarcode(final String barcode) {
		if (barcode == null || barcode.length() != 12 || !barcode.matches("\\d{12}")) {
			return false;
		}
		return true;
	}
	
	/**
     * Deduce the bike code from a barcode.
     * @param barcode The bike barcode 
     * @return The deduced bike code or null if not deducible.
     */
	public String deduceBikeCode(String barcode) {
		String firstHalf = barcode.substring(0, 3);
		String secondHalf = barcode.substring(9,12);

		if (secondHalf.equals(firstHalf)) {
			return firstHalf;
		}

		return null;
	}
	
	/**
     * Convert a barcode to a bike code.
     * @param barcode The barcode to convert.
     * @return The converted bike code or null if conversion fails.
     */
	public String convertBarcodeToBikeCode(final String barcode) {
		if (!validateBarcode(barcode)) {
			System.out.println("Invalid barcode format");
			return null;
		}
		String bikecode = deduceBikeCode(barcode);
		System.out.println("bike code: " + bikecode);
		return bikecode;
	}

}
