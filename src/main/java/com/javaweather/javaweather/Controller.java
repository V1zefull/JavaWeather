package com.javaweather.javaweather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField cityInput;

    @FXML
    private Text curentTemp;

    @FXML
    private Button getData;

    @FXML
    private Text maxTemp;

    @FXML
    private Text minTemp;

    @FXML
    void initialize() {
        getData.setOnAction(event ->{
            String getCity = cityInput.getText().trim();
            String output = getUlrContent("https://api.openweathermap.org/data/2.5/weather?q=" + getCity + "&appid=740f3e587341f2a55e58cb8eeceec35d&units=metric");

            if(!output.isEmpty()){
                JSONObject obj = new JSONObject(output);
                curentTemp.setText("На данный момент: " + obj.getJSONObject("main").getDouble("temp") + "°C");
                maxTemp.setText("Максимальная: " + obj.getJSONObject("main").getDouble("temp_max") + "°C");
                minTemp.setText("Минимальная: " + obj.getJSONObject("main").getDouble("temp_min") + "°C");
            }
        });
    }

    private static String getUlrContent(String urlWeather){
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlWeather);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String lineFormat;

            while((lineFormat = bufferedReader.readLine()) != null){
                content.append(lineFormat+ "\n");
            }
        } catch (Exception e){
            System.out.println("Такой город не был найден!");
        }
        return content.toString();
    }

}
