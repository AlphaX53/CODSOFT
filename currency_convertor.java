import com.google.gson.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class CurrencyConverter {
    public static void main(String[] args) {
        
        String apiKey = "hN9/ZpFkA9dsfEc1su34BQ==PLxlub2WGRrgi1ai";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the base currency (e.g. USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();
        System.out.print("Enter the target currency (e.g. EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();
        System.out.println("Enter your "+baseCurrency+" value:- ");
        int val = scanner.nextInt();

        String url_str = "https://api.api-ninjas.com/v1/convertcurrency?have=" + baseCurrency + "&want=" + targetCurrency + "&amount=" + val;

        try {
            
            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestProperty("X-Api-Key", apiKey);
            request.connect();

            
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            
            double oldAmount = jsonobj.get("old_amount").getAsDouble();
            String oldCurrency = jsonobj.get("old_currency").getAsString();
            double newAmount = jsonobj.get("new_amount").getAsDouble();
            String newCurrency = jsonobj.get("new_currency").getAsString();

            System.out.println(oldAmount + " " + oldCurrency + " is equivalent to " + newAmount + " " + newCurrency + ".");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
