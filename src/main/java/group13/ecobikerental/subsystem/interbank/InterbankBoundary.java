package group13.ecobikerental.subsystem.interbank;

import java.io.IOException;
import java.util.UUID;

import org.json.JSONObject;

import group13.ecobikerental.utils.API;

/**
 * This class provides methods for interacting with the interbank system through API calls.
 */
public class InterbankBoundary {
	
	/**
     * Sends a query to the specified URL with the given data using the API.post method.
     * @param url  The URL to send the query to.
     * @param data The data to be sent in the query.
     * @return The response received from the API call.
     */
	public static String query(final String url, final String data) {
		String response = null;
		try {
			response = API.post(url, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	/**
     * Sends a manual query to the specified URL with the given data.
     * @param url  The URL to send the query to.
     * @param data The data to be sent in the query.
     * @return The response generated for the manual query.
     */
	public static String queryManual(String url, String data) {
		System.out.println("\ncheck 7: " + data);
		String response = null;

		// fixed value of response
		JSONObject jsonData = new JSONObject(data);
		UUID uuid = UUID.randomUUID();
		String transactionId = uuid.toString().replace("-", "").substring(0, 10);

		JSONObject dataObject = jsonData.getJSONObject("data");
		JSONObject transactionObject = dataObject.getJSONObject("transaction");
		transactionObject.put("transactionId", transactionId);
		dataObject.put("transaction", transactionObject);

		JSONObject responseObj = new JSONObject();
		responseObj.put("errorCode", "00");
		responseObj.put("data", dataObject);
		response = responseObj.toString();
		/*
		 * response =
		 * {"data":{"transaction":{"owner":"PHAM THAO NHI","createdAt":"2023-08-13 00:29:02"
		 * ,"amount":100,"cvvCode":"774","dateExpired":"1125","cardCode":
		 * "131313","transactionContent":"test pay","command":"pay",
		 * "transactionId":"cafae64fcf"}},"errorCode":"00"}
		 */
		return response;
	}

}
