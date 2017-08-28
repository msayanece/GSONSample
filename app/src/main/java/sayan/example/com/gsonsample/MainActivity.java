package sayan.example.com.gsonsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import sayan.example.com.gsonsample.pojos.PhoneNumber;
import sayan.example.com.gsonsample.pojos.UserAddress;
import sayan.example.com.gsonsample.pojos.UserDetails;

public class MainActivity extends AppCompatActivity {

    // Json as a String
    //replace JSON from your web service API
    private final String JSON_STRING= "{\"firstName\":\"John\",\"lastName\":\"Smith\",\"gender\":\"man\",\"age\":32,\"address\":{\"streetAddress\":\"21 2nd Street\",\"city\":\"New York\",\"state\":\"NY\",\"postalCode\":\"10021\"},\"phoneNumbers\":[{\"type\":\"home\",\"number\":\"212 555-1234\"},{\"type\":\"fax\",\"number\":\"646 555-4567\"}]}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create GSON Object
        Gson gsonObject = new Gson();
        //get outer JSON Object to main Java Pojo Object... TypeToken<return type of fromJson or pojo name>(){}
        UserDetails pojo = gsonObject.fromJson(JSON_STRING, new TypeToken<UserDetails>(){}.getType());

        //JsonObject from the pojo to create anothe pojo
        JsonObject addressJsonObject = pojo.getAddress();
        UserAddress address= gsonObject.fromJson(addressJsonObject.toString(), new TypeToken<UserAddress>(){}.getType());

        //JsonArray for creating another pojo using GSON
        JsonArray phoneNumbersJsonArray = pojo.getPhoneNumbers();
        // ArrayList for holding PhoneNumber pojo objects
        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        //create ArrayList from JsonArray using GSON and get each JsonObject from it
        ArrayList<JsonObject> phoneNumberJsonObjects = gsonObject.fromJson(phoneNumbersJsonArray, new TypeToken<ArrayList<JsonObject>>(){}.getType());
        for (JsonObject phoneNumberJsonObject :
                phoneNumberJsonObjects) {
            //create another pojo from each JsonObject of the ArrayList
            PhoneNumber phoneNo= gsonObject.fromJson(phoneNumberJsonObject.toString(), new TypeToken<PhoneNumber>(){}.getType());
            phoneNumbers.add(phoneNo);
        }

        //getting raw data from the Pojos to display as a string
        //replace this code for using the pojo results
        String result = gsonObject.toJson(pojo);
        String adressResult = gsonObject.toJson(address);
        String phoneNoResult1 = gsonObject.toJson(phoneNumbers.get(0));
        String phoneNoResult2 = gsonObject.toJson(phoneNumbers.get(1));

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(result);
        textView.append("\n");
        textView.append("\n");
        textView.append(adressResult);
        textView.append("\n");
        textView.append("\n");
        textView.append(phoneNoResult1);
        textView.append("\n");
        textView.append("\n");
        textView.append(phoneNoResult2);
    }
}
