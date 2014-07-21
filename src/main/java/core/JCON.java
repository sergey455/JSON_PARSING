package core;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;


public class JCON {
public static void main(String[] args) throws IOException {
    String item_01_name = "Soccer Ball";
    Double item_01_usd_price = 35.00;

   /* String ip_address_1 = "88.191.179.56";
    String ip_address_2 = "61.135.248.220";
    String ip_address_3 = "92.41.254.196";
    String ip_address_4 = "93.183.203.67";
    String ip_address_5 = "213.87.141.36";
  */  
    String[] ip_address = {"88.191.179.56", "61.135.248.220", "92.41.254.196", "93.183.203.67", "213.87.141.36"};
    
    for (String element : ip_address) {
       
        URL url = new URL("http://www.geoplugin.net/json.gp?ip="+element);

    String country_name_1 = " ";
    String currency_code_1 = " ";
    String currencyConverter_1;
    Double currencyConverterDbl_1= 0.00;
    Double local_price_1;
     

    final String element_01 = "geoplugin_countryName";

    final String element_02 = "geoplugin_currencyCode";

    final String element_03 = "geoplugin_currencyConverter";



    InputStream is = url.openStream();
    JsonParser parser = Json.createParser(is);

    while (parser.hasNext()) {

    Event e = parser.next();

    if (e == Event.KEY_NAME) {

        switch (parser.getString()) {

        case element_01:
            parser.next();
            //System.out.println(element_01 + " =  " + parser.getString());
            country_name_1 =  parser.getString();
        break;

        case element_02:
            parser.next();
            //System.out.println(element_02 + " =  " + parser.getString());
            currency_code_1 = parser.getString();
        break;

        case element_03:
            parser.next();
            //System.out.println(element_03 +" =  " + parser.getString());
            currencyConverter_1 = parser.getString();
            currencyConverterDbl_1 = new Double(String.valueOf(currencyConverter_1));
        break;
            }

    }

}

    local_price_1 = currencyConverterDbl_1 *item_01_usd_price ;
    System.out.println("Item_01: "+ item_01_name + "; US Price: " + item_01_usd_price + "; Country: "+ country_name_1 +"; Currency Code: "+ currency_code_1 +  "; Local Price: "+ local_price_1);
}
}
}
