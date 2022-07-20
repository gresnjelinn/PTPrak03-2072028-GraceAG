package com.example.pt03prak2072028modality;

import com.example.pt03prak2072028modality.model.Barang;
import com.example.pt03prak2072028modality.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BarangController {
    public Menu menubar;
    public MenuItem menuItemSupp;
    public MenuItem menuItemClose;
    public TextField inputIDBarang;
    public ComboBox comboboxSuppBarang;
    public Button btnInsertBarang;
    public Button btnDeleteBarang;
    public Button btnUpdateBarang;
    public TableView tableBarang;
    public TableColumn columnIDBarang;
    public TableColumn columnNamaBarang;
    public TableColumn columnSuppBarang;
    public TextField inputNamaBarang;

    private ObservableList<Integer> comboList;
    private ObservableList<Supplier> supplierList;

    private ObservableList<Barang> barangList;


    public Stage supplierStage;

    public void initialize() throws IOException {
        supplierStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("supplier-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 435, 385);

        SupplierController sc = fxmlLoader.getController();

        comboList = FXCollections.observableArrayList();
        supplierList = sc.getSuppList();
        supplierList.forEach((data) -> {
            comboList.add(data.getId());
        });

        comboboxSuppBarang.setItems(comboList);

        barangList = FXCollections.observableArrayList(
                new Barang(1, "Buku Tulis", supplierList.get(0)),
                new Barang(2, "Tikar", supplierList.get(1)),
                new Barang(3, "Kursi", supplierList.get(2))
        );
        tableBarang.setItems(barangList);
        columnIDBarang.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("id"));
        columnNamaBarang.setCellValueFactory(new PropertyValueFactory<Supplier, String>("nama"));
        columnSuppBarang.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supplier"));
    }

    public void goToSupplier(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("supplier-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 435, 385);
        supplierStage.setTitle("Supplier Management");
        supplierStage.setScene(scene);
        supplierStage.show();
    }

    public void insertBarang(ActionEvent actionEvent) {
        barangList.add(new Barang(Integer.parseInt(inputIDBarang.getText()),
                inputNamaBarang.getText(),
                supplierList.get(comboboxSuppBarang.getSelectionModel().getSelectedIndex())
        ));
    }

    public void deleteBarang(ActionEvent actionEvent) {
        int selectedRow = tableBarang.getSelectionModel().getSelectedIndex();
        tableBarang.getItems().remove(selectedRow);
    }

    public void updateBarang(ActionEvent actionEvent) {
        int selectedRow = tableBarang.getSelectionModel().getSelectedIndex();
        barangList.get(selectedRow).setId(Integer.parseInt(inputIDBarang.getText()));
        barangList.get(selectedRow).setNama(inputNamaBarang.getText());
        barangList.get(selectedRow).setSupplier(supplierList.get(comboboxSuppBarang.getSelectionModel().getSelectedIndex()));
        barangList.set(selectedRow, barangList.get(selectedRow));
    }

    public void rowSelected(MouseEvent mouseEvent) {
        int selectedRow = tableBarang.getSelectionModel().getSelectedIndex();
        inputIDBarang.setText(String.valueOf(barangList.get(selectedRow).getId()));
        inputNamaBarang.setText(barangList.get(selectedRow).getNama());
        comboboxSuppBarang.setValue(barangList.get(selectedRow).getSupplier().getId());
    }
}
