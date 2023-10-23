package com.example.projetoed;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenuPrincipal implements Initializable {
    @FXML
    private Button botaoLDE;

    @FXML
    private Button botaoLS;

    @FXML
    private Button botaoLSE;

    @FXML
    private Button botaoABP;

    @FXML
    private Button botaoFila;

    @FXML
    private Button botaoPilha;

    @FXML
    private HBox paneMenu;

    @FXML
    void abrirLDE(MouseEvent event) throws IOException {
        Stage stage = (Stage) botaoLDE.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("LDE.fxml"));
        stage.setTitle("Lista Duplamente Encadeada");
        stage.setScene(new Scene(root));
    }

    @FXML
    void abrirLS(MouseEvent event) throws IOException {
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
        Stage stage = (Stage) botaoLSE.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("LSE.fxml"));
        stage.setTitle("Lista Simplesmente Encadeada");
        stage.setScene(new Scene(root));
    }

    @FXML
    void abrirPilha(MouseEvent event) throws IOException {
        Stage stage = (Stage) botaoPilha.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Pilha.fxml"));
        stage.setTitle("Pilha Encadeada");
        stage.setScene(new Scene(root));
    }


    @FXML
    void abrirFila(MouseEvent event) throws IOException {
        Stage stage = (Stage) botaoFila.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Fila.fxml"));
        stage.setTitle("Fila Encadeada");
        stage.setScene(new Scene(root));
    }


    @FXML
    void abrirABP(MouseEvent event) throws IOException {
        Stage stage = (Stage) botaoABP.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ABP.fxml"));
        stage.setTitle("Árvore Binária de Pesquisa");
        stage.setScene(new Scene(root));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
