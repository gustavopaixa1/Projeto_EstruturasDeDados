package com.example.projetoed;

import com.sun.javafx.stage.EmbeddedWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLSE {

    @FXML
    private Button botaoDeEscolha;

    @FXML
    private ChoiceBox<String> opcoes;

    private EmbeddedWindow stage;

    @FXML
    void getOpcoes(MouseEvent event) throws IOException {
        String opcao = opcoes.getSelectionModel().getSelectedItem();

    }


}
