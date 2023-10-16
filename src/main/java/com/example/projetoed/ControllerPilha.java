package com.example.projetoed;

import com.example.projetoed.implementations.Linked_Stack;
import javafx.animation.FillTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPilha implements Initializable  {

    private Linked_Stack<String> PE;

    @FXML
    private Button BotaoVoltar;

    @FXML
    private FlowPane FPDados;

    @FXML
    private TextField TopoConteudo;

    @FXML
    private TextField TFInserirConteudo;


    @FXML
    private TextField TFNumeroDeElementos;


    @FXML
    private Button ConsultarTopo;

    @FXML
    private AnchorPane paneInserir;

    @FXML
    private AnchorPane paneRemover;


    @FXML
    void EventoConsultaTopo(MouseEvent event) {

        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");

        if (PE.isEmpty()) {
            alerta.setHeaderText("VAZIO");
            alerta.setContentText("A pilha já está vazia, por isso não tem como consultar topo!");
            alerta.showAndWait();
            return;
        }

        this.animacao(PE.size() - 1, 2, 1, "#008000", "#008B8B").play();

    }


    @FXML
    void EventoInserir(MouseEvent event) {
        String conteudo;
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");
        conteudo = TFInserirConteudo.getText().trim();

        if (conteudo.equalsIgnoreCase("")) {
            alerta.setHeaderText("Sem Conteúdo Válido");
            alerta.setContentText("Por favor, preencha o campo de conteúdo antes de apertar para inserir (apenas espaços não são válidos)!");
            alerta.showAndWait();
            return;
        }

        this.PE.push(conteudo);
        TFInserirConteudo.setText("");
        TFNumeroDeElementos.setText(String.valueOf(PE.size()));
        TopoConteudo.setText(PE.top());

        // O desafio eh criar os blocos

        FPDados.getChildren().add(PE.size() - 1, bloco(conteudo));

        this.animacao(PE.size() - 1, 2, 1, "#8b0000", "#008B8B").play();


    }

    @FXML
    void EventoRemover(MouseEvent event) {

        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");

        if (PE.isEmpty()) {
            alerta.setHeaderText("VAZIO");
            alerta.setContentText("A pilha já está vazia, por isso não tem como remover mais!");
            alerta.showAndWait();
            return;
        }

        PE.pop();
        TFNumeroDeElementos.setText(String.valueOf(PE.size()));
        if (!PE.isEmpty())
            TopoConteudo.setText(PE.top());
        else
            TopoConteudo.setText("Não tem!");

        FillTransition aux = this.animacao(PE.size(), 1, 0.5, "#008B8B", "#ffffff");
        aux.play();

        aux.setOnFinished(evento -> {
            FPDados.getChildren().remove(PE.size());
        });

    }

    @FXML
    void EventoVoltar(MouseEvent event) throws IOException {
        Stage stage = (Stage) BotaoVoltar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menuPrincipal.fxml"));
        stage.setTitle("Estrutura de Dados");
        stage.setScene(new Scene(root));
    }

    private FillTransition animacao(int contIndex, int cycles, double time, String fromColor, String toColor) {
        VBox auxVBox = (VBox) FPDados.getChildren().get(contIndex);
        StackPane auxSP = (StackPane) auxVBox.getChildren().get(0);
        Rectangle auxRec = (Rectangle) auxSP.getChildren().get(0);

        FillTransition transition = new FillTransition();
        transition.setShape(auxRec);
        transition.setFromValue(Color.web(fromColor));
        transition.setToValue(Color.web(toColor));
        transition.setCycleCount(cycles);
        transition.setDuration(Duration.seconds(time));
        return transition;
    }





    private VBox bloco(String conteudo) {
        VBox novoNo = new VBox();
        novoNo.setAlignment(Pos.CENTER);
        novoNo.getStyleClass().add("vbox-pilha");

        StackPane sp = new StackPane();
        sp.setPrefWidth(90);
        sp.setPrefHeight(60);
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
        img.setRotate(90);
        novoNo.getChildren().add(img);

        novoNo.setScaleY(-1); // Para inverter os nos dentro do ja invertido FlowPane

        return novoNo;
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)  {
        this.PE = new Linked_Stack<>();
        TFNumeroDeElementos.setText("0");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0); // Configurar o raio da sombra
        dropShadow.setOffsetX(3.0); // Configurar o deslocamento horizontal da sombra
        dropShadow.setOffsetY(3.0); // Configurar o deslocamento vertical da sombra
        dropShadow.setColor(javafx.scene.paint.Color.BLACK); // Configurar a cor da sombra

        ConsultarTopo.setEffect(dropShadow);
        paneInserir.setEffect(dropShadow);
        paneRemover.setEffect(dropShadow);


        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(FPDados.widthProperty());
        clip.heightProperty().bind(FPDados.heightProperty());
        FPDados.setClip(clip);
        FPDados.setOrientation(Orientation.VERTICAL);
        FPDados.setAlignment(Pos.TOP_LEFT);
        FPDados.setScaleY(-1);

    }




}
