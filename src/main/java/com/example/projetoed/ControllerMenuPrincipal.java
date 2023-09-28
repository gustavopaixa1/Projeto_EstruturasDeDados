package com.example.projetoed;

import com.example.projetoed.tools.Som;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenuPrincipal implements Initializable {
    private Som som;
    @FXML
    private Button botaoLDE;

    @FXML
    private Button botaoLS;

    @FXML
    private Button botaoLSE;

    @FXML
    private HBox paneMenu;

    @FXML
    void abrirLDE(MouseEvent event) throws IOException {
        this.som.escolherTela();
        Stage stage = (Stage) botaoLDE.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("LDE.fxml"));
        stage.setTitle("Lista Duplamente Encadeada");
        stage.setScene(new Scene(root));
    }

    @FXML
    void abrirLS(MouseEvent event) throws IOException {
        this.som.escolherTela();
        Scene scene;
        Stage stage = (Stage) botaoLS.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("listaSeq.fxml"));
        stage.setTitle("Lista Sequencial");
        scene = new Scene(root);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
    }

    @FXML
    void abrirLSE(MouseEvent event) throws IOException {
        this.som.escolherTela();
        Stage stage = (Stage) botaoLSE.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("LSE.fxml"));
        stage.setTitle("Lista Simplesmente Encadeada");
        stage.setScene(new Scene(root));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.som = new Som();
    }
}
