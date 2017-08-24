package sayan.example.com.gsonsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sayan.example.com.gsonsample.pojos.UserDetails;

public class MainActivity extends AppCompatActivity {

    private Gson gsonObject;
    private final String JSON_STRING = "{\"age\":32,\"firstName\":\"John\",\"gender\":\"Male\",\"lastName\":\"Smith\"}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gsonObject = new Gson();
        UserDetails pojo = (UserDetails) gsonProcessJsonObjectToJavaObject(JSON_STRING);
        String result = gsonProcessJavaToJsonObject(pojo/*new UserDetails("John","Smith", "Male", 32)*/);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(result);
    }

    //get Java objects from String of JSON Object
    private String gsonProcessJavaToJsonObject(Object javaObject){
        return gsonObject.toJson(javaObject);
    }

    //get JSON Objects and Arrays
    private Object gsonProcessJsonObjectToJavaObject(String result) {
        // JsonObject
        return gsonObject.<UserDetails>fromJson(result,new TypeToken<UserDetails>(){}.getType());
    }
}
