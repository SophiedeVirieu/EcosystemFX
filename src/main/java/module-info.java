module org.example.ecosystemfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.ecosystemfx.ihm to javafx.graphics;

    opens org.example.ecosystemfx to javafx.fxml;
    exports org.example.ecosystemfx;
}
