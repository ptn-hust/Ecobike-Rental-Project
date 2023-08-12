package group13.ecobikerental.business_layer;

import group13.ecobikerental.entity.bike.Bike;

/**
 * This class processes businesses related to Invoice
 */
public class InvoiceBL {
	
    public int calculateRentalFee(String timeRental, String paymemtMethod, Bike bike) {
        int rentalFee = 0;
        int minutes = InvoiceBL.processTime(timeRental);
        PaymentFactory paymentFactory = new PaymentFactory();
        PaymentBase payment = paymentFactory.getPayment(paymemtMethod, bike);
        rentalFee = payment.calculateRentalFee(minutes);        
        return rentalFee;
    }

    /**
     * This method convert the rental time into minute(s)
     * @param timeRental - the time that user rented bike
     * @return minutes - the rental minute(s)
     */
    private static int processTime(String timeRental) {
        String[] timeArr = timeRental.split(":");
        int minutes = Integer.parseInt(timeArr[0]) * 60 + Integer.parseInt(timeArr[1]);
        if (Integer.parseInt(timeArr[2]) > 0) {
            minutes++;
        }
        return minutes;
    }

//    public static void main(String[] args) {
//        String time = "00:10:00";
//        String[] timeArr = time.split(":");
//        int minutes = Integer.parseInt(timeArr[0]) * 60 + Integer.parseInt(timeArr[1]);
//        if (Integer.parseInt(timeArr[2]) > 0) {
//            minutes++;
//        }
//        System.out.println(minutes);
//        System.out.println(calculateRentalFee(time));
//    }
}
