import org.json.JSONArray;
import java.text.DecimalFormat;
import java.io.IOException;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;

public class Categories {

    private final HashMap<String, Double> productCategories;


    public Categories() throws IOException {
        this.productCategories = setCategories();
    }

    public HashMap<String, Double> setCategories() throws IOException {

        HashMap<String, Double> productCategories =  new HashMap<>();

        Retriever ret = new Retriever();

        JSONArray products = ret.getProducts();
        DecimalFormat decimalFormat = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));

        for(int i=0;i< products.length();i++){
            String cat = String.valueOf(products.getJSONObject(i).get("category"));

            double value = Double.parseDouble(products.getJSONObject(i).get("price").toString());

            if(productCategories.containsKey(cat)){
                double a = productCategories.get(cat) + value;
                productCategories.put(cat, Double.parseDouble(decimalFormat.format(a)));
            }else{
                productCategories.put(cat, Double.parseDouble(decimalFormat.format(value)));
            }
        }
        return productCategories;
    }

    public HashMap<String, Double> getProductCategories() {
        return productCategories;
    }

    public Double getCategoryValue(String category){
        return this.productCategories.get(category);
    }
}