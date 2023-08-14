package group13.ecobikerental.business_layer;

public class BikeBL {
	private static BikeBL instance;
	
	private BikeBL() {
		
	}
	
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
    
	public boolean validateBarcode(final String barcode) {
		if (barcode == null || barcode.length() != 12 || !barcode.matches("\\d{12}")) {
			return false;
		}
		return true;
	}

	public String deduceBikeCode(String barcode) {
		int barcodeLength = barcode.length();
		System.out.println("length " + barcodeLength);
		String firstHalf = barcode.substring(0, 3);
		System.out.println("first half "+ firstHalf);
		
		String secondHalf = barcode.substring(9,12);
		System.out.println("second half "+ secondHalf);

		if (secondHalf.equals(firstHalf)) {
			return firstHalf;
		}

		return null; // Barcode does not match the pattern
	}

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
