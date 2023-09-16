package com.example.projetoed;

import com.sun.javafx.stage.EmbeddedWindow;
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

public class ControllerListaSeq {

    @FXML
    private Button botaoDeEscolha;

    @FXML
    private ChoiceBox<String> opcoes;

    private EmbeddedWindow stage;

    @FXML
    void getOpcoes(MouseEvent event) throws IOException {
        String opcao = opcoes.getSelectionModel().getSelectedItem();
        Stage stage = (Stage)botaoDeEscolha.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("listaSeq.fxml"));
        stage.setTitle("Lista Sequencial");
        stage.setScene(new Scene(root));
    }


}
