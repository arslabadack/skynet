module arslabadack.ifsc.oop2.skynet {
    requires javafx.controls;
    requires javafx.fxml;

    opens arslabadack.ifsc.oop2.skynet to javafx.fxml;
    exports arslabadack.ifsc.oop2.skynet;
}