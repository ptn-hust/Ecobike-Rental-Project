package group13.ecobikerental.business_layer;

import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.payment.PaymentBase;
import group13.ecobikerental.entity.payment.PaymentFactory;

public class InvoiceBL {
	
	private static InvoiceBL instance;

    // Private constructor to prevent direct instantiation
    private InvoiceBL() {
        // Initialization logic here
    }

    // Method to get the singleton instance
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
	
    public int calculateRentalFee(String timeRental, String paymemtMethod, Bike bike) {
        int rentalFee = 0;
        int minutes = timeToMinute(timeRental);
        PaymentFactory paymentFactory = new PaymentFactory();
        PaymentBase payment = paymentFactory.getPayment(paymemtMethod, bike);
        rentalFee = payment.calculateRentalFee(minutes);        
        return rentalFee;
    }

    private int timeToMinute(String timeRental) {
        String[] timeArr = timeRental.split(":");
        int minutes = Integer.parseInt(timeArr[0]) * 60 + Integer.parseInt(timeArr[1]);
        if (Integer.parseInt(timeArr[2]) > 0) {
            minutes++;
        }
        return minutes;
    }
}
