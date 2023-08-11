package group13.ecobikerental.subsystem.interbank;

import java.io.IOException;

import group13.ecobikerental.utils.API;

public class InterbankBoundary {
	public static String query(final String url, final String data) {
		String response = null;
		System.out.println("hello3. data: " + data);
		// response = API.post(url, data);
		
		// fixed value of response 
		response = "{\"errorCode\":\"00\",\"transaction\":{\"transactionId\":\"0x123\",\"cardCode\":\"vn_group2_2021\",\"owner\":\"Group 2\",\"cvvCode\":\"774\",\"dateExpired\":\"1125\",\"command\":\"pay\",\"transactionContent\":\"abcxyz\",\"amount\":550000,\"createdAt\":\"2023-08-12 00:26:14\"}}";
		System.out.println("hello4: " + response);
		// try {
//            response = API.post(url, data);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            throw new UnrecognizedException();
//        }
		return response;
	}

}
