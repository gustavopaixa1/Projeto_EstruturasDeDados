package com.example.projetoed;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenuPrincipal implements Initializable {

    @FXML
    private Button botaoDeEscolha;

    @FXML
    private ChoiceBox<String> opcoes;

    @FXML
    private Text opcaoEscolhida;

    @FXML
    void getOpcoes(MouseEvent event) throws IOException {
        String opcao = opcoes.getSelectionModel().getSelectedItem();

        if (opcao.equals("Lista Sequencial")){
            Scene scene;
            Stage stage = (Stage)botaoDeEscolha.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("listaSeq.fxml"));
            stage.setTitle("Lista Sequencial");
            scene = new Scene(root);
            scene.getStylesheets().add("style.css");
            stage.setScene(scene);
        }
        else if(opcao.equals("Lista Simplesmente Encadeada")){
            Stage stage = (Stage)botaoDeEscolha.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("LSE.fxml"));
            stage.setTitle("Lista Simplesmente Encadeada");
            stage.setScene(new Scene(root));
        }
        else if(opcao.equals("Lista Duplamente Encadeada")){
            Stage stage = (Stage)botaoDeEscolha.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("LDE.fxml"));
            stage.setTitle("Lista Duplamente Encadeada");
            stage.setScene(new Scene(root));
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        opcoes.getItems().addAll("Lista Sequencial", "Lista Simplesmente Encadeada", "Lista Duplamente Encadeada");
    }


}
