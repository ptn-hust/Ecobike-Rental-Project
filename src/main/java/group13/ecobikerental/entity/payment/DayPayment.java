package group13.ecobikerental.entity.payment;

public class DayPayment extends Payment {
	@Override
	public int calculateRentalFee(int minutes) {
		int baseFee = 200000;     // Giá thuê cơ bản cho 24 tiếng
        int earlyReturnPenalty = 10000; // Phạt trả xe sớm
        int lateReturnPenalty = 2000;    // Phạt trả xe muộn
        
        // Tính số tiền trả cho thuê dưới 12 tiếng
        if (minutes <= 12 * 60) {
            int hours = (int) minutes / 60;
            return (int) (baseFee - (hours * earlyReturnPenalty));
        }
        // Tính số tiền trả cho thuê từ tiếng thứ 12 trở đi
        else if (minutes <= 24 * 60) {
            return baseFee;
        }
        // Tính số tiền trả cho việc trả xe muộn
        else {
            int lateMinutes = minutes - 24 * 60;
            int lateFee = (lateMinutes / 15) * lateReturnPenalty;
            return baseFee + lateFee;
        }
	}
}
