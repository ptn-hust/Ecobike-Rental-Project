package group13.ecobikerental.subsystem.interbank;

import java.util.UUID;

import org.json.JSONObject;

public class InterbankBoundary {
	public static String query(final String url, final String data) {
		String response = null;
		System.out.println("hello3. data: " + data);
		// response = API.post(url, data);
		
		// fixed value of response 
		JSONObject jsonData = new JSONObject(data);
		UUID uuid = UUID.randomUUID();
		String transactionId = uuid.toString().replace("-", "").substring(0, 10);
		
		JSONObject transactionObject = jsonData.getJSONObject("transaction");
		transactionObject.put("transactionId", transactionId);
		
		JSONObject responseObj = new JSONObject();
		responseObj.put("errorCode", "00");
		responseObj.put("transaction", jsonData.getJSONObject("transaction"));
		response = responseObj.toString();
		
//		response = "{\"errorCode\":\"00\",\"transaction\":{\"transactionId\":\"0x123\",\"cardCode\":\"vn_group2_2021\",\"owner\":\"Group 2\",\"cvvCode\":\"774\",\"dateExpired\":\"1125\",\"command\":\"pay\",\"transactionContent\":\"abcxyz\",\"amount\":550000,\"createdAt\":\"2023-08-12 00:26:14\"}}";
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
