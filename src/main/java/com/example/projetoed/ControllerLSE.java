package com.example.projetoed;

import javafx.animation.FillTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
    private Button BotaoInserir;

    @FXML
    private Button BotaoRemover;

    @FXML
    private Button BotaoConsultaValor;

    @FXML
    private Button BotaoConsultaIndice;

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
    private HBox HBoxLinha1;

    @FXML
    private HBox HBoxLinha2;

    @FXML
    private HBox HBoxLinha3;

    @FXML
    private HBox HBoxLinha4;

    @FXML
    private HBox HBoxLinha5;
    
    private HBox[] linhas = new HBox[5];

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
            pos = Integer.parseInt(TFInserirPosicao.getText());
            cont = TFInserirConteudo.getText();
            if (cont.isEmpty())
                return;
        } catch (Exception e) {
            return;
        }
        if (this.LSE.size() == 40)
            return;
        if (!this.LSE.insert(cont, pos))
            return;
        TFInserirPosicao.setText("");
        TFInserirConteudo.setText("");
        TFNumeroDeElementos.setText(String.valueOf(++numeroDeElementos));

        // Criar os Blocos
        System.out.println(this.LSE.toString());
        linhas[pos / 8].getChildren().add(pos % 8, bloco(cont));
        for (int i = 0; i < 5; i++) {
            if (linhas[i].getChildren().size() > 8) {
                linhas[i + 1].getChildren().add(0, linhas[i].getChildren().get(8));
            }
        }
    }

    @FXML
    void EventoRemover(MouseEvent event) throws IOException {
        int pos;
        try {
            pos = Integer.parseInt(TFRemoverPosicao.getText());
        } catch (Exception e) {
            return;
        }

        String cont = this.LSE.remove(pos);
        if (cont == null)
            return;
        TFRemoverPosicao.setText("");
        TFNumeroDeElementos.setText(String.valueOf(--numeroDeElementos));

        // Remover os Blocos
        System.out.println(this.LSE.toString());
        linhas[pos / 8].getChildren().remove(pos % 8);
        for (int i = pos / 8; i < 4; i++)
            if (this.LSE.size() >= (i + 1) * 8)
                linhas[i].getChildren().add(7, linhas[i + 1].getChildren().get(0));
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
        this.animacao(contIndex);
    }
    @FXML
    void EventoConsultaIndice(MouseEvent event) throws IOException {
        int contIndex;
        try {
            contIndex = Integer.parseInt(TFConsultaIndicePosicao.getText());
        } catch (Exception e) {
            return;
        }

        String cont = this.LSE.get(contIndex);
        if (cont == null)
            return;
        TFConsultaIndicePosicao.setText("");

        // Consultar os Blocos por Indice
        this.animacao(contIndex);
    }

    private HBox bloco(String conteudo) {
        HBox novoNo = new HBox();
        novoNo.setAlignment(Pos.CENTER);

        StackPane sp = new StackPane();
        sp.setPrefWidth(60);
        sp.setPrefHeight(50);
        sp.setAlignment(Pos.CENTER);

        Rectangle retangulo = new Rectangle(60,50);
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

    private void animacao(int contIndex) {
        HBox auxHBox = (HBox) linhas[contIndex / 8].getChildren().get(contIndex % 8);
        StackPane auxSP = (StackPane) auxHBox.getChildren().get(0);
        Rectangle auxRec = (Rectangle) auxSP.getChildren().get(0);
        Text auxText = (Text) auxSP.getChildren().get(1);

        FillTransition transition = new FillTransition();
        transition.setShape(auxRec);
        transition.setFromValue(Color.web("#8b0000"));
        transition.setToValue(Color.web("#008B8B"));
        transition.setCycleCount(2);
        transition.setDuration(Duration.seconds(1));
        transition.play();
    }

//   // indice especifico
//    no1.getChildren().aff(índice,teste);
//    @FXML
//    void adicionaraovbox(MouseEvent event) {
//        no1.setSpacing(10);
//        StackPane teste = new StackPane();
//        Rectangle rectangulo = new Rectangle(50,50);
//        String adicionar = (adicionarelemento.getText());
//        rectangulo.setFill(Color.web("#4682B4"));
//        rectangulo.setArcHeight(10);
//        rectangulo.setArcWidth(10);
//        rectangulo.setStrokeWidth(2);
//        rectangulo.setStroke(Color.BLACK);
//        teste.getChildren().add(rectangulo);
//        Label label = new Label(adicionar);
////        label.setTextFill(Color.WHITE);
////        teste.getChildren().add(label);
////        no1.getChildren().add(teste);
////    }
//
//
//    @FXML
//    void removerElemento(MouseEvent event) {
//        int remover = Integer.valueOf(camporemover.getText());
//        no1.getChildren().remove(remover);
//    }
//
//    void adicionaraovbox(MouseEvent event) {
//        StackPane teste = new StackPane();
//        Rectangle rectangulo = new Rectangle(50,50);
//        String adicionar = (adicionarelemento.getText());
//        rectangulo.setFill(Color.BLUE);
//        teste.getChildren().add(rectangulo);
//        teste.getChildren().add(new Label(adicionar));
//        no1.getChildren().add(teste);
//    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.LSE = new SingleLinkedList<>();
        this.numeroDeElementos = 0;
        TFNumeroDeElementos.setText("0");

        linhas[0] = HBoxLinha1;
        linhas[1] = HBoxLinha2;
        linhas[2] = HBoxLinha3;
        linhas[3] = HBoxLinha4;
        linhas[4] = HBoxLinha5;
    }
}
