package com.example.projetoed;

import javafx.animation.FillTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.example.projetoed.implementations.SingleLinkedList;
import javafx.util.Duration;

public class ControllerLSE implements Initializable {
    private SingleLinkedList<String> LSE;
    private int numeroDeElementos;

    @FXML
    private Button BotaoVoltar;

    @FXML
    private TextField TFNumeroDeElementos;

    @FXML
    private TextField TFInserirPosicao;

    @FXML
    private TextField TFInserirConteudo;

    @FXML
    private TextField TFRemoverPosicao;

    @FXML
    private TextField TFConsultaValorConteudo;

    @FXML
    private TextField TFConsultaIndicePosicao;

    @FXML
    private FlowPane FPDados;

    @FXML
    void EventoVoltar(MouseEvent event) throws IOException {
        Stage stage = (Stage) BotaoVoltar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menuPrincipal.fxml"));
        stage.setTitle("Estrutura de Dados");
        stage.setScene(new Scene(root));
    }

    @FXML
    void EventoInserir(MouseEvent event) throws IOException {
        int pos;
        String cont;
        try {
            pos = Integer.parseInt(TFInserirPosicao.getText()) - 1;
            cont = TFInserirConteudo.getText();
            if (cont.isEmpty())
                return;
        } catch (Exception e) {
            return;
        }

        if (!this.LSE.insert(cont, pos))
            return;
        TFInserirPosicao.setText("");
        TFInserirConteudo.setText("");
        TFNumeroDeElementos.setText(String.valueOf(++numeroDeElementos));

        // Criar os Blocos
        FPDados.getChildren().add(pos, bloco(cont));

        this.animacaoSequencia(0, 1, 0.1, "#8b0000", "#008B8B", pos - 1);
        this.animacao(pos, 1 + pos / 5, 0.5, "#73ee81", "#008B8B").play();
    }

    @FXML
    void EventoRemover(MouseEvent event) throws IOException {
        int pos;
        try {
            pos = Integer.parseInt(TFRemoverPosicao.getText()) - 1;
        } catch (Exception e) {
            return;
        }

        String cont = this.LSE.remove(pos);
        if (cont == null)
            return;
        TFRemoverPosicao.setText("");
        TFNumeroDeElementos.setText(String.valueOf(--numeroDeElementos));

        // Remover os Blocos
        this.animacaoSequencia(0, 1, 0.1, "#8b0000", "#008B8B", pos - 1);
        FillTransition aux = this.animacao(pos, 1 + pos / 5, 0.5, "#008B8B", "#ffffff");

        aux.setOnFinished(evento -> {
            FPDados.getChildren().remove(pos);
        });
        aux.play();
    }

    @FXML
    void EventoConsultaValor(MouseEvent event) throws IOException {
        String cont = TFConsultaValorConteudo.getText();
        if (cont.isEmpty())
            return;

        int contIndex = this.LSE.indexOf(cont);
        if (contIndex == -1)
            return;
        TFConsultaValorConteudo.setText("");

        // Consultar os Blocos por Valor
        this.animacao(contIndex, 2, 1, "#8b0000", "#008B8B").play();
    }
    @FXML
    void EventoConsultaIndice(MouseEvent event) throws IOException {
        int contIndex;
        try {
            contIndex = Integer.parseInt(TFConsultaIndicePosicao.getText()) - 1;
        } catch (Exception e) {
            return;
        }

        String cont = this.LSE.get(contIndex);
        if (cont == null)
            return;
        TFConsultaIndicePosicao.setText("");

        // Consultar os Blocos por Indice
        this.animacao(contIndex, 2, 1, "#8b0000", "#008B8B").play();
    }

    @FXML
    void EventoDebugEncher(MouseEvent event) throws IOException {
        for (int i = 1; i <= 40; i++) {
            this.TFInserirPosicao.setText(Integer.toString(i));
            this.TFInserirConteudo.setText(Integer.toString(i));
            this.EventoInserir(event);
        }
    }

    @FXML
    void EventoDebugEsvaziar(MouseEvent event) throws IOException {
        for (int i = this.numeroDeElementos; i >= 1; i--) {
            this.TFRemoverPosicao.setText("1");
            this.EventoRemover(event);
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.LSE = new SingleLinkedList<>();
        this.numeroDeElementos = 0;
        TFNumeroDeElementos.setText("0");

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(FPDados.widthProperty());
        clip.heightProperty().bind(FPDados.heightProperty());
        FPDados.setClip(clip);
    }
}
