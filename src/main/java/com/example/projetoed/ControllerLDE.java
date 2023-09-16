package com.example.projetoed;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class ControllerLDE {

    @FXML
    private Button botaoAdicionarElemento;

    @FXML
    private TextArea campoAdicionarElemento;

    @FXML
    private Button botoRemover;

    @FXML
    private TextArea campoRemover;

    @FXML
    private ListView<String> dados;

    @FXML
    void adicionarOpcoes(MouseEvent event) {
        dados.getItems().add((campoAdicionarElemento.getText()));
    }

    @FXML
    void removerElemento(MouseEvent event) {
        int indiceRemover = Integer.valueOf(campoRemover.getText());
        dados.getItems().remove(indiceRemover);
    }


}
