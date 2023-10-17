package com.example.projetoed;

import com.example.projetoed.implementations.Search_Binary_Tree;
import com.example.projetoed.implementations.SingleLinkedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;


import com.example.projetoed.implementations.SingleLinkedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerABP implements Initializable {
    private Search_Binary_Tree<Integer> ABP;

    private ABPPane<Integer> PaneDaArvore;

    @FXML
    private AnchorPane APTela;

    @FXML
    private Button BotaoVoltar;

    @FXML
    private TextField TFNumeroDeElementos;

    @FXML
    private TextField TFInserir;

    @FXML
    private TextField TFRemover;

    @FXML
    private TextField TFConsultaValor;

    @FXML
    private AnchorPane APInserir;

    @FXML
    private AnchorPane APRemover;

    @FXML
    private AnchorPane APConsultarValor;

    @FXML
    private VBox VBCaminhamentos;

    @FXML
    void EventoVoltar(MouseEvent event) throws IOException {
        Stage stage = (Stage) BotaoVoltar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menuPrincipal.fxml"));
        stage.setTitle("Estrutura de Dados");
        stage.setScene(new Scene(root));
    }

    @FXML
    void EventoInserir(MouseEvent event) {
        int cont = 0;
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");
        try {
            cont = Integer.parseInt(this.TFInserir.getText().trim());
            if (this.ABP.search(cont))
                throw new Exception();
        } catch (NumberFormatException e) {
            alerta.setHeaderText("Conteúdo inválido.");
            alerta.setContentText("Por favor, preencha o campo de conteúdo com um valor numérico inteiro para ser armazenado (apenas espaços não são caracteres válidos).");
            alerta.showAndWait();
            return;
        } catch (Exception e) {
            alerta.setHeaderText("Conteúdo Já Existe.");
            alerta.setContentText("Por favor, preencha o campo de conteúdo com um valor novo.");
            alerta.showAndWait();
            return;
        }

        this.ABP.insert(cont);
        this.TFInserir.setText("");
        this.TFNumeroDeElementos.setText(String.valueOf(this.ABP.size()));

        // Adicionar os nós
        this.PaneDaArvore.atualizarVisualizacao();

//        this.animacaoSequencia(0, 1, 0.1, "#8b0000", "#008B8B", pos - 1);
//        this.animacao(pos, 1 + pos / 5, 0.5, "#73ee81", "#008B8B").play();
    }

    @FXML
    void EventoRemover(MouseEvent event) {

    }

    @FXML
    void EventoConsultaValor(MouseEvent event) {

    }

    @FXML
    void EventoPreOrdem(MouseEvent event) {

    }

    @FXML
    void EventoInOrdem(MouseEvent event) {

    }

    @FXML
    void EventoPosOrdem(MouseEvent event) {

    }


    private void configurarPanePersonalizado() {
        this.PaneDaArvore = new ABPPane<Integer>(this.ABP);

        this.APTela.setBottomAnchor(this.PaneDaArvore, 15.0);
        this.APTela.setLeftAnchor(this.PaneDaArvore, 15.0);
        this.APTela.setRightAnchor(this.PaneDaArvore, 15.0);
        this.APTela.setTopAnchor(this.PaneDaArvore, 200.0);
        this.APTela.getChildren().add(this.PaneDaArvore);

//        Rectangle clip = new Rectangle();
//        clip.widthProperty().bind(this.PaneDaArvore.widthProperty());
//        clip.heightProperty().bind(this.PaneDaArvore.heightProperty());
//        this.PaneDaArvore.setClip(clip);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.ABP = new Search_Binary_Tree<>();
        TFNumeroDeElementos.setText("0");

        this.configurarPanePersonalizado();

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0); // Configurar o raio da sombra
        dropShadow.setOffsetX(3.0); // Configurar o deslocamento horizontal da sombra
        dropShadow.setOffsetY(3.0); // Configurar o deslocamento vertical da sombra
        dropShadow.setColor(javafx.scene.paint.Color.BLACK); // Configurar a cor da sombra
        this.APInserir.setEffect(dropShadow);
        this.APRemover.setEffect(dropShadow);
        this.APConsultarValor.setEffect(dropShadow);
        this.VBCaminhamentos.setEffect(dropShadow);
    }

}
