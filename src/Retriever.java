import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;


public class Retriever {

    final private JSONArray users;
    final private JSONArray products;
    final private JSONArray carts;

    public Retriever() throws IOException {
        this.users = retrieveData("users");
        this.carts = retrieveData("carts");
        this.products = retrieveData("products");
    }

    public JSONArray getProducts() {
        return products;
    }

    public String findMaxDistanceBetweenUsers(){

        Point2D.Double [] coordinates = new Point2D.Double[users.length()];

        String[] usernames = new String[users.length()];

        for(int i=0; i < users.length(); i++) {

            JSONObject address = new JSONObject(users.getJSONObject(i).get("address").toString());
            JSONObject geolocation = new JSONObject(address.getJSONObject("geolocation").toString());

            double x = Double.parseDouble(geolocation.get("lat").toString());
            double y = Double.parseDouble(geolocation.get("long").toString());

            coordinates[i] = new Point2D.Double(x, y);

            usernames[i] = String.valueOf(users.getJSONObject(i).get("username"));

        }

        return RotatingCaliper.rotatingCaliper(coordinates, usernames);
    }

    public String getHighestCartValue() throws IOException {

        double maxCartValue = 0;
        String userId = "";

        for(int i = 0; i < carts.length(); i++) {

            JSONArray products = new JSONArray(carts.getJSONObject(i).get("products").toString());

            String userId_tmp = String.valueOf(carts.getJSONObject(i).get("userId"));

            double cartValue = 0.0;

            for (int j = 0; j < products.length(); j++) {

                String productId = String.valueOf(products.getJSONObject(j).get("productId"));

                int quantity = Integer.parseInt(products.getJSONObject(j).get("quantity").toString());

                JSONObject product = this.retrieveSingleObject("products", productId);

                double productPrice = Double.parseDouble(product.get("price").toString());

                cartValue += productPrice * quantity;
            }

            //System.out.println(cartValue);

            if (cartValue > maxCartValue){
                maxCartValue = cartValue;
                userId = userId_tmp;
            }
        }
        String userName = String.valueOf(this.retrieveSingleObject("users", userId).get("username"));

        return String.format("The highest cart value is: %s, this cart belongs to: %s", maxCartValue, userName);
    }

    public JSONObject retrieveSingleObject(String mode, String id) throws IOException {

        String apiUrl = String.format("https://fakestoreapi.com/%s/%s", mode, id);
        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            return jsonObject;

        } else {
            System.out.println(responseCode);
            return new JSONObject();
        }
    }

    public JSONArray retrieveData(String detail) throws IOException {

        String apiUrl = String.format("https://fakestoreapi.com/%s", detail);
        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONArray jsonArray = new JSONArray(response.toString());
            return jsonArray;

        } else {
            System.out.println(responseCode);
            return new JSONArray();
        }
    }
}