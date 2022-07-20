module com.example.pt03prak2072028modality {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pt03prak2072028modality to javafx.fxml;
    opens com.example.pt03prak2072028modality.model to javafx.fxml;
    exports com.example.pt03prak2072028modality;
    exports com.example.pt03prak2072028modality.model;
}