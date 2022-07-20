package com.example.pt03prak2072028modality;

import com.example.pt03prak2072028modality.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SupplierController {
    public Menu menubar;
    public MenuItem menuItemBarang;
    public MenuItem menuItemClose;
    public TextField inputIDSupp;
    public TextField inputNamaSupp;
    public Button btnInsertSupp;
    public Button btnDeleteSupp;
    public Button btnUpdateSupp;
    public TableView tableSupp;
    public TableColumn columnIDSupp;
    public TableColumn columnNameSupp;
    public TableColumn columnAlamatSupp;
    public TextField inputAlamatSupp;

    public ObservableList<Supplier> getSuppList() {
        return suppList;
    }

    public void setSuppList(ObservableList<Supplier> suppList) {
        this.suppList = suppList;
    }

    private ObservableList<Supplier> suppList;

    public void initialize() {
        suppList = FXCollections.observableArrayList(
                new Supplier(1, "PT. Selamat Sejahtera", "Dummy Address 01"),
                new Supplier(2, "PT. Sehat Sentosa", "Dummy Address 02"),
                new Supplier(3, "PT. Panjang Umur", "Dummy Address 03")
        );
        tableSupp.setItems(suppList);
        columnIDSupp.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("id"));
        columnNameSupp.setCellValueFactory(new PropertyValueFactory<Supplier, String>("nama"));
        columnAlamatSupp.setCellValueFactory(new PropertyValueFactory<Supplier, String>("alamat"));
    }


    public void insertSupp(ActionEvent actionEvent) {
        suppList.add(new Supplier(Integer.parseInt(inputIDSupp.getText()),
                inputNamaSupp.getText(),
                inputAlamatSupp.getText()
        ));
    }

    public void deleteSupp(ActionEvent actionEvent) {
        int selectedRow = tableSupp.getSelectionModel().getSelectedIndex();
        tableSupp.getItems().remove(selectedRow);
    }

    public void updateSupp(ActionEvent actionEvent) {
        int selectedRow = tableSupp.getSelectionModel().getSelectedIndex();
        suppList.get(selectedRow).setId(Integer.parseInt(inputIDSupp.getText()));
        suppList.get(selectedRow).setNama(inputNamaSupp.getText());
        suppList.get(selectedRow).setAlamat(inputAlamatSupp.getText());
        suppList.set(selectedRow, suppList.get(selectedRow));
    }

    public void rowSelected(MouseEvent mouseEvent) {
        int selectedRow = tableSupp.getSelectionModel().getSelectedIndex();
        inputIDSupp.setText(String.valueOf(suppList.get(selectedRow).getId()));
        inputNamaSupp.setText(suppList.get(selectedRow).getNama());
        inputAlamatSupp.setText(suppList.get(selectedRow).getAlamat());
    }

    public void goToBarang(ActionEvent actionEvent) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("barang-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 435, 385);
//
//        BarangController bc = fxmlLoader.getController();
//        bc.comboboxSuppBarang.setItems(suppList);
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
