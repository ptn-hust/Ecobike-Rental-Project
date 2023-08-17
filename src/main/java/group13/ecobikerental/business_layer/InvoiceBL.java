package group13.ecobikerental.business_layer;

import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.payment.PaymentBase;
import group13.ecobikerental.entity.payment.PaymentFactory;
/**
 * Business Logic class to calculate rental fees for bikes.
 */
public class InvoiceBL {
	
	private static InvoiceBL instance;

    // Private constructor to prevent direct instantiation
    private InvoiceBL() {
        // Initialization logic here
    }


    /**
     * Get the singleton instance of InvoiceBL.
     * @return The InvoiceBL instance.
     */
    public static InvoiceBL getInstance() {
        if (instance == null) {
            synchronized (InvoiceBL.class) {
                if (instance == null) {
                    instance = new InvoiceBL();
                }
            }
        }
        return instance;
    }
	
    /**
     * Calculate the rental fee for a bike based on the rental time and payment method.
     * @param timeRental    The rental time in HH:mm:ss format.
     * @param paymentMethod The payment method chosen by the user.
     * @param bike          The bike for which the rental fee is calculated.
     * @return The calculated rental fee.
     */
    public int calculateRentalFee(String timeRental, String paymemtMethod, Bike bike) {
        int rentalFee = 0;
        int minutes = timeToMinute(timeRental);
        PaymentFactory paymentFactory = new PaymentFactory();
        PaymentBase payment = paymentFactory.getPayment(paymemtMethod, bike);
        rentalFee = payment.calculateRentalFee(minutes);        
        return rentalFee;
    }

    /**
     * Convert rental time from HH:mm:ss format to total minutes.
     * @param timeRental The rental time in HH:mm:ss format.
     * @return The total minutes.
     */
    private int timeToMinute(String timeRental) {
        String[] timeArr = timeRental.split(":");
        int minutes = Integer.parseInt(timeArr[0]) * 60 + Integer.parseInt(timeArr[1]);
        if (Integer.parseInt(timeArr[2]) > 0) {
            minutes++;
        }
        return minutes;
    }
}
