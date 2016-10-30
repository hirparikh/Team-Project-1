import java.net.*;
import java.io.*;

public class ScoreUpdate{

public static void main(String args[]){
	
	setScore(100);
}
	
	
public static void setScore(int score){
	
	try {

		URL url = new URL("http://localhost:3000/updatescore");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");

		String input = "{\"score\":"+score+"}";

		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();

		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	 }

}

public static void getScore(){
        try {
    		URL url = new URL("https://google.com");
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setDoOutput(true);
    		conn.setRequestMethod("GET");
    		//conn.setRequestProperty("Content-Type", "application/json");
    		//String input = "{\"qty\":100,\"name\":\"iPad 4\"}";
    		//OutputStream os = conn.getOutputStream();
    		//os.write(input.getBytes());
    		//os.flush();
    		//if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
    		//	throw new RuntimeException("Failed : HTTP error code : "
    		//		+ conn.getResponseCode());
    		//}
    		BufferedReader br = new BufferedReader(new InputStreamReader(
    				(conn.getInputStream())));
    		String output;
    		System.out.println("Output from Server .... \n");
    		while ((output = br.readLine()) != null) {
    			System.out.println(output);
    		}
    		conn.disconnect();
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
        } catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    }
}	