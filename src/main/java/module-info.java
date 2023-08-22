module com.javaweather.javaweather {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.javaweather.javaweather to javafx.fxml;
    exports com.javaweather.javaweather;
}