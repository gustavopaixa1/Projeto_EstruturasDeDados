package com.example.projetoed;

import com.example.projetoed.implementations.Linked_Queue;
import com.example.projetoed.implementations.SingleLinkedList;
import javafx.animation.FillTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFila implements Initializable {

    private Linked_Queue<String> Fila;

    @FXML
    private Button BotaoVoltar;

    @FXML
    private FlowPane FPDados;

    @FXML
    private TextField TFConsultaIndicePosicao;

    @FXML
    private TextField TFInserirConteudo;

    @FXML
    private TextField TFNumeroDeElementos;

    @FXML
    private TextField TFCampoPrimeiroElemento;

    @FXML
    private AnchorPane paneConsultar1;

    @FXML
    private AnchorPane paneConsultar2;

    @FXML
    private AnchorPane paneInserir;

    @FXML
    private AnchorPane paneRemover;

    @FXML
    void EventoConsultaIndice(MouseEvent event) {
        int pos;
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");

        try{
            pos = Integer.parseInt(TFConsultaIndicePosicao.getText().trim())-1;
            if (pos<0 || pos >= this.Fila.size())
                throw new Exception();
        } catch (Exception e){
            if(this.Fila.isEmpty()){
                alerta.setHeaderText("Fila vazia");
                alerta.setContentText("A fila está vázia, não existem itens para serem buscados.");
            } else if(this.Fila.size() == 1){
                alerta.setHeaderText("Valor inválido");
                alerta.setContentText("Por favor, preencha o campo da posição com o valor 1 para buscar o único elemento da lista.");
            } else{
                alerta.setHeaderText("Valor inválido");
                alerta.setContentText(String.format("Por favor, preencha o campo da posição com um número inteiro entre 1 e %d.", this.Fila.size()));
            }
            alerta.showAndWait();
            return;
        }
        TFConsultaIndicePosicao.setText("");
        this.animacao(pos, 2, 1, "#8b0000", "#008B8B").play();
    }

    @FXML
    void EventoConsultaValor(MouseEvent event) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");
        try {
            String cont = Fila.front();
            if(cont.isEmpty())
                throw new Exception();


        } catch(Exception e){
            alerta.setHeaderText("Fila vazia");
            alerta.setContentText("O primeiro elemento não foi encontrado pois a fila está vazia");
            alerta.showAndWait();
            return;
        }

        this.animacao(0, 2, 1,"#8b0000", "#008B8B").play();
    }

    @FXML
    void EventoInserir(MouseEvent event) {
        String cont;
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");
        try{
            cont = TFInserirConteudo.getText().trim();
            if(cont.isEmpty())
                throw new Exception();
        } catch(Exception e){
            alerta.setHeaderText("Conteúdo Inválido");
            alerta.setContentText("Por favor, preencha o campo de conteúdo com um valor válido");
            alerta.showAndWait();
            return;
        }

        this.Fila.push(cont);
        TFInserirConteudo.setText("");
        TFNumeroDeElementos.setText(String.valueOf(this.Fila.size()));
        TFCampoPrimeiroElemento.setText(String.valueOf(this.Fila.front()));

        FPDados.getChildren().add(this.Fila.size()-1, bloco(cont));

        this.animacaoSequencia(this.Fila.size()-1,1,0.1, "#8b0000", "#008B8B", 0);
        this.animacao(this.Fila.size()-1, 1 + 0 / 5, 0.5, "#73ee81", "#008B8B").play();
    }

    private void animacaoSequencia(int contIndex, int cycles, double time, String fromColor, String toColor, int max) {
        if (contIndex > max)
            return;
        FillTransition aux = animacao(contIndex, cycles, time, fromColor, toColor);
        aux.setAutoReverse(true);

        aux.setOnFinished(event -> {
            animacaoSequencia(contIndex + 1, cycles, time, fromColor, toColor, max);
        });

        aux.play();
    }

    @FXML
    void EventoRemover(MouseEvent event) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");
        if (Fila.isEmpty()){
            TFCampoPrimeiroElemento.setText("Fila vazia");
            alerta.setHeaderText("Fila vazia.");
            alerta.setContentText("A fila está vazia, não existem itens para serem removidos.");
            alerta.showAndWait();
            return;
        }

        this.Fila.pop();
        TFNumeroDeElementos.setText(String.valueOf(this.Fila.size()));

        if(this.Fila.size() == 0) {
            TFCampoPrimeiroElemento.setText("Fila vazia");}
        else {
            TFCampoPrimeiroElemento.setText(String.valueOf(this.Fila.front()));
        }

        this.animacaoSequencia(0, 1, 0.1, "#8b0000", "#008B8B", 0 - 1);
        FillTransition aux = this.animacao(0, 1 + 0 / 5, 0.5, "#008B8B", "#ffffff");

        aux.setOnFinished(evento -> {
            FPDados.getChildren().remove(0);
        });
        aux.play();
    }

    @FXML
    void EventoVoltar(MouseEvent event) throws IOException {
        Stage stage = (Stage) BotaoVoltar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menuPrincipal.fxml"));
        stage.setTitle("Estrutura de Dados");
        stage.setScene(new Scene(root));
    }
    private FillTransition animacao(int contIndex, int cycles, double time, String fromColor, String toColor) {
        HBox auxHBox = (HBox) FPDados.getChildren().get(contIndex);
        StackPane auxSP = (StackPane) auxHBox.getChildren().get(0);
        Rectangle auxRec = (Rectangle) auxSP.getChildren().get(0);

        FillTransition transition = new FillTransition();
        transition.setShape(auxRec);
        transition.setFromValue(Color.web(fromColor));
        transition.setToValue(Color.web(toColor));
        transition.setCycleCount(cycles);
        transition.setDuration(Duration.seconds(time));
        return transition;
    }

    private HBox bloco(String conteudo) {
        HBox novoNo = new HBox();
        novoNo.setAlignment(Pos.CENTER);

        StackPane sp = new StackPane();
        sp.setPrefWidth(60);
        sp.setPrefHeight(50);
        sp.setAlignment(Pos.CENTER);

        Rectangle retangulo = new Rectangle(50,50);
        retangulo.getStyleClass().add("profile-boxes");
        sp.getChildren().add(retangulo);

        Text texto = new Text(conteudo);
        texto.setFont(Font.font("Consolas", 15));
        texto.setFill(Color.web("#ffffff"));
        texto.setWrappingWidth(50);
        texto.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        sp.getChildren().add(texto);

        novoNo.getChildren().add(sp);

        ImageView img = new ImageView("seta.png");
        img.setFitWidth(30);
        img.setFitHeight(30);
        novoNo.getChildren().add(img);

        return novoNo;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.Fila = new Linked_Queue<>();
        TFNumeroDeElementos.setText("0");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0); // Configurar o raio da sombra
        dropShadow.setOffsetX(3.0); // Configurar o deslocamento horizontal da sombra
        dropShadow.setOffsetY(3.0); // Configurar o deslocamento vertical da sombra
        dropShadow.setColor(javafx.scene.paint.Color.BLACK); // Configurar a cor da sombra

        paneConsultar1.setEffect(dropShadow);
        paneInserir.setEffect(dropShadow);
        paneRemover.setEffect(dropShadow);

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(FPDados.widthProperty());
        clip.heightProperty().bind(FPDados.heightProperty());
        FPDados.setClip(clip);
    }
}
