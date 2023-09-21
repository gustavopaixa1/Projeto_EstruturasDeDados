package com.example.projetoed;

import com.example.projetoed.implementations.SeqList;
import javafx.animation.FillTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class ControllerListaSeq implements Initializable {
    @FXML
    private Button BotaoVoltar;

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

    @FXML
    private TextField TFAdicionarPosicao;

    @FXML
    private TextField TFAdicionarValor;

    @FXML
    private TextField TFCriar;

    @FXML
    private TextField TFNumeroDeElementos;

    @FXML
    private TextField TFPosicao;

    @FXML
    private TextField TFValor;

    private int totalDeElementos;

    private SeqList<String> LS;

    private HBox[] linhas = new HBox[5];

    private FillTransition animacao(int contIndex, int cycles, double time) {
        HBox auxHBox = (HBox) linhas[contIndex / 11].getChildren().get(contIndex % 11);
        StackPane auxSP = (StackPane) auxHBox.getChildren().get(0);
        Rectangle auxRec = (Rectangle) auxSP.getChildren().get(0);

        FillTransition transition = new FillTransition();
        transition.setShape(auxRec);
        transition.setFromValue(Color.web("#8b0000"));
        transition.setToValue(Color.web("#008B8B"));
        transition.setCycleCount(cycles);
        transition.setDuration(Duration.seconds(time));
        return transition;
    }

    private void animacaoSequencia(int contIndex, int cycles, double time, int max) {
        if (contIndex > max)
            return;
        FillTransition aux = animacao(contIndex, cycles, time);
        aux.setAutoReverse(true);

        aux.setOnFinished(event -> {
            animacaoSequencia(contIndex + 1, cycles, time, max);
        });

        aux.play();
    }

    private HBox bloco(String conteudo, String style) {
        HBox novoNo = new HBox();
        novoNo.setAlignment(Pos.CENTER);

        StackPane sp = new StackPane();
        sp.setPrefWidth(60);
        sp.setPrefHeight(50);
        sp.setAlignment(Pos.CENTER);

        Rectangle retangulo = new Rectangle(60,50);
        retangulo.getStyleClass().add(style);
        sp.getChildren().add(retangulo);

        Text texto = new Text(conteudo);
        texto.setFont(Font.font("Consolas", 15));
        texto.setFill(Color.web("#ffffff"));
        texto.setWrappingWidth(50);
        texto.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        sp.getChildren().add(texto);

        novoNo.getChildren().add(sp);

        return novoNo;
    }

    void adicionarBloco(String cont, int pos, String style) {
        linhas[pos / 11].getChildren().add(pos % 11, bloco(cont, style));
            for (int i = 0; i < 5; i++) {
                if (linhas[i].getChildren().size() > 11) {
                    linhas[i + 1].getChildren().add(0, linhas[i].getChildren().get(11));
                }
            }
    }

    void removerBloco(int pos) {
        linhas[pos / 11].getChildren().remove(pos % 11);
        for (int i = pos / 11; i < 4; i++){
            if (this.LS.size() >= (i + 1) * 11)
                linhas[i].getChildren().add(10, linhas[i + 1].getChildren().get(0));
        }
    }

    void alteracaoEmSequenciaVolta(int i, int objetivo) {
        if (i < objetivo) {
            return;
        }

        removerBloco(i);
        adicionarBloco(this.LS.get(i),i, "profile-boxes");

        FillTransition aux = this.animacao(i, 1, 0.5);

        aux.setOnFinished(evento -> {
            alteracaoEmSequenciaVolta(i - 1, objetivo);
        });

        aux.play();
    }

    void alteracaoEmSequenciaIda(int i, int objetivo) {
        if (i > objetivo) {
            return;
        }

        removerBloco(i);
        if (i == objetivo) {
            adicionarBloco("",i, "profile-boxes-black");
        } else {
            adicionarBloco(this.LS.get(i),i, "profile-boxes");
        }

        FillTransition aux = this.animacao(i, 1, 0.5);

        aux.setOnFinished(evento -> {
            alteracaoEmSequenciaIda(i + 1, objetivo);
        });

        aux.play();
    }

    @FXML
    void adicionar(MouseEvent event) throws IOException{
        int pos;
        String cont;
        try {
            pos = Integer.parseInt(TFAdicionarPosicao.getText()) - 1;
            cont = TFAdicionarValor.getText();
            if (cont.isEmpty())
                return;
        } catch (Exception e) {
            return;
        }
        if (pos > this.LS.size() || pos >= 55)
            return;
        if (!this.LS.insert(cont, pos))
            return;
        TFAdicionarPosicao.setText("");
        TFAdicionarValor.setText("");
        TFNumeroDeElementos.setText(String.valueOf(this.LS.size()));

        alteracaoEmSequenciaVolta(this.LS.size()-1, pos);
    }

    @FXML
    void buscarPorPosicao(MouseEvent event) {
        int pos;

        try {
            pos = Integer.parseInt(TFPosicao.getText()) - 1;
        } catch (Exception e) {
            return;
        }

        if (pos >= this.LS.size() || pos < 0) {
            return;
        }

        this.TFPosicao.setText("");

        this.animacao(pos, 1, 0.5).play();
    }

    @FXML
    void buscarPorValor(MouseEvent event) {
        String val;
        int pos;

        try {
            int teste = Integer.parseInt(TFValor.getText());
            val = String.valueOf(teste);
        } catch (Exception e) {
            return;
        }

        pos = this.LS.indexOf(val);

        if (pos == -1) {
            return;
        }

        this.TFPosicao.setText("");

        this.animacao(pos, 1, 0.5).play();
    }

    @FXML
    void criarListaSequencial(MouseEvent event) {

        if (this.LS != null) {
            return;
        }

        int amount;
        try {
            amount = Integer.parseInt(TFCriar.getText());
        } catch (Exception e) {
            return;
        }

        if (amount > 55) return;

        this.LS = new SeqList<>(amount);
        this.totalDeElementos = amount;

        TFCriar.setText("");

        for (int i = 0; i < amount; i++) {
            linhas[i / 11].getChildren().add(i % 11, bloco("", "profile-boxes-black"));
        }
        for (int i = 0; i < 5; i++) {
            if (linhas[i].getChildren().size() > 11) {
                linhas[i + 1].getChildren().add(0, linhas[i].getChildren().get(8));
            }
        }
    }

    @FXML
    void apagarLista(MouseEvent event) {
        this.LS = null;

        for (int i = 0; i < this.totalDeElementos; i++) {
            linhas[i / 11].getChildren().remove(0);
        }

        this.totalDeElementos = 0;
        TFNumeroDeElementos.setText("0");
    }

    @FXML
    void removerPorPosicao(MouseEvent event) {
        int pos;
        try {
            pos = Integer.parseInt(TFPosicao.getText()) - 1;
        } catch (Exception e) {
            return;
        }

        if (pos >= this.LS.size() || pos < 0)
            return;
        if (this.LS.remove(pos) == null)
            return;
        TFPosicao.setText("");
        TFNumeroDeElementos.setText(String.valueOf(this.LS.size()));

        alteracaoEmSequenciaIda(pos, this.LS.size());
    }

    @FXML
    void removerPorValor(MouseEvent event) {
        String val;
        int pos;

        try {
            int teste = Integer.parseInt(TFValor.getText());
            val = String.valueOf(teste);
        } catch (Exception e) {
            return;
        }

        pos = this.LS.indexOf(val);
        this.LS.remove(pos);

        if (pos == -1) {
            return;
        }

        this.TFValor.setText("");

        alteracaoEmSequenciaIda(pos, this.LS.size());

        TFNumeroDeElementos.setText(String.valueOf(this.LS.size()));
    }

    @FXML
    void voltar(MouseEvent event) throws IOException{
        Stage stage = (Stage) BotaoVoltar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menuPrincipal.fxml"));
        stage.setTitle("Estrutura de Dados");
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.totalDeElementos = 0;
        TFNumeroDeElementos.setText("0");

        linhas[0] = HBoxLinha1;
        linhas[1] = HBoxLinha2;
        linhas[2] = HBoxLinha3;
        linhas[3] = HBoxLinha4;
        linhas[4] = HBoxLinha5;
    }
}
