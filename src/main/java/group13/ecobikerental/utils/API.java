/**
 * API.class
 *
 * This class provides an API client for making HTTP requests to a specified base URL.
 * 
 * @Credits: This class is adapted from the 'API' module in a project 
 *           Original source: 
 *
 */
package group13.ecobikerental.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

public class API {

	/**
     * The date formatter used for converting dates to string.
     */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	/**
     * The logger instance for logging.
     */
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

	/**
     * Sends a GET request to the specified URL with the provided token.
     * @param url   The URL to send the GET request to.
     * @param token The authorization token.
     * @return The response from the server.
     * @throws Exception If an error occurs during the request.
     */
	public static String get(String url, String token) throws Exception {
		LOGGER.info("Request URL: " + url + "\n");

		HttpURLConnection conn = setupConnection(url);

		conn.setRequestMethod("GET");

		conn.setRequestProperty("Authorization", "Bearer " + token);
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder(); // ising StringBuilder for the sake of memory and performance
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
		}
		response.append(inputLine + "\n");
		in.close();
		LOGGER.info("Response Info: " + response.substring(0, response.length() - 1).toString());
		return response.substring(0, response.length() - 1).toString();
	}

	/**
     * Sets up the connection for the HTTP request.
     * @param url The URL to connect to.
     * @return The prepared HttpURLConnection.
     * @throws IOException If an I/O error occurs.
     */
	private static HttpURLConnection setupConnection(String url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		return conn;
	}

	/**
     * Sends a POST request to the specified URL with the provided data.
     * @param url  The URL to send the POST request to.
     * @param data The payload data to include in the request.
     * @return The response from the server.
     * @throws IOException If an I/O error occurs.
     */
	public static String post(String url, String data) throws IOException {
		String payload = data;
		LOGGER.info("Request Info:\nRequest URL: " + url + "\n" + "Payload Data: " + payload + "\n");

		HttpURLConnection conn = setupCustomConnection(url, "POST");

		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(payload);
		writer.close();

		BufferedReader in;
		String inputLine;
		if (conn.getResponseCode() / 100 == 2) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		LOGGER.info("Response Info: " + response.toString());
		return response.toString();
	}

	/**
     * Sets up a custom connection for the HTTP request.
     * @param url    The URL to connect to.
     * @param method The HTTP method (e.g., "POST") to use.
     * @return The prepared HttpURLConnection.
     * @throws IOException If an I/O error occurs.
     */
	private static HttpURLConnection setupCustomConnection(String url, String method) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		return conn;
	}

}
