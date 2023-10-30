import com.google.gson.Gson;
import javax.swing.*;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DogsPics {
    public static final String BASE_URL = "https://dog.ceo/api/breeds/image/random/";
    public static String breed = null;

    public static void main(String[] args) {
        int n = 10;
        if (args.length >= 1) {
            n = Integer.parseInt(args[0]);
        }
        if (args.length >= 2) {
            breed = args[1];
        }

        DogsApiResponse response = query(n);
        ArrayList<String> urls = response.getMessage();

        EventQueue.invokeLater(() -> {
            JFrame ex = new DogImageCarousel(urls);
            ex.setVisible(true);
        });
    }

    public static DogsApiResponse query(int n) {
        DogsApiResponse response = null;
        try {

            String requestUrl;

            if (breed != null) {
                requestUrl = "https://dog.ceo/api/breed/" + breed + "/images/random/" + n;
            } else {
                requestUrl = BASE_URL + n;
            }

            URL u = new URL(BASE_URL + n);

            BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
            String buffer;
            StringBuilder jsonText = new StringBuilder();
            while ((buffer = in.readLine()) != null){
                jsonText.append(buffer);
            }
            in.close();
            Gson gson = new Gson();
            response = gson.fromJson(jsonText.toString(), DogsApiResponse.class);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return response;
    }
}