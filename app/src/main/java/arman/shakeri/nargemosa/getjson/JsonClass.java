package arman.shakeri.nargemosa.getjson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonClass {

    public static String getJson(String url){

        URL urlAddress = null;
        try{
            urlAddress = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlAddress.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("USER-AGENT","Mozilla/5.0");
            connection.setRequestProperty("ACCEPT-LANGUAGE","en-US,en;0.5");

            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            StringBuilder stringBuilder = new StringBuilder();
            while((line = br.readLine()) != null ){

              stringBuilder.append(line);
            }

            br.close();

            return stringBuilder.toString();

        }catch(Exception e){

            e.printStackTrace();
        }
        return "";
    }
}
