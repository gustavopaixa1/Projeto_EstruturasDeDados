package com.example.projetoed;

import com.example.projetoed.implementations.DoubleLinkedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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


public class ControllerLDE implements Initializable {
    private DoubleLinkedList<String> LDE;
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

        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");

        try {
            pos = Integer.parseInt(TFInserirPosicao.getText()) - 1;
            cont = TFInserirConteudo.getText();
            if (cont.isEmpty()) {

                alerta.setHeaderText("VAZIO!");
                alerta.setContentText("Preencha o campo do conteudo com um valor que exista na lista!");

                alerta.showAndWait();
                return;
            }
        } catch (Exception e) {
                alerta.setHeaderText("PRECISA DE UM NUMERO INTEIRO!");
            alerta.setContentText("Preencha o campo da posicao com uma posicao que exista na lista!");

            alerta.showAndWait();
            return;
        }

        if (pos > LDE.size() || pos < 0) {

            alerta.setHeaderText("NAO EXISTE!");
            alerta.setContentText("Preencha o campo da posicao com uma posicao que possa existir na lista!");

            alerta.showAndWait();
            return;
        }

        if (this.LDE.size() == 40)
            return;
        if (!this.LDE.insert(cont, pos))
            return;
        TFInserirPosicao.setText("");
        TFInserirConteudo.setText("");
        TFNumeroDeElementos.setText(String.valueOf(++numeroDeElementos));

        // Reajustar a Opacidade da seta esquerda esquerda
        if (pos % 8 > 0) {
            HBox hSeta = (HBox) linhas[pos / 8].getChildren().get((pos - 1) % 8);
            VBox vSeta;
            if (pos % 8 == 1)
                 vSeta = (VBox) hSeta.getChildren().get(2); // No primeiro no, o VBox direito estara na terceira posicao, o indice 2
            else
                vSeta = (VBox) hSeta.getChildren().get(1); // Nos outros nos, o Vbox direito estara na segunda posiçao, no indice 1
            ImageView setaFinal = (ImageView) vSeta.getChildren().get(1);
            setaFinal.setOpacity(1);
        }

        // Remover o VBox esquerdo do No que era a antiga cabeça
        if (pos % 8 <= (this.LDE.size() - 2) % 8 && pos % 8 == 0) {
            HBox hSeta = (HBox) linhas[pos / 8].getChildren().get(pos % 8);
            hSeta.getChildren().remove(0);
        }




        // Criar os Blocos
        linhas[pos / 8].getChildren().add(pos % 8, bloco(pos, cont, this.LDE.size() - 1)); //  -1 porque ja foi acrescentado mais um No na LDE
        for (int i = 0; i < 5; i++) {
            if (linhas[i].getChildren().size() > 8) {
                HBox carryBox = (HBox) linhas[i].getChildren().get(8);
                HBox apagaHBox = (HBox) linhas[i].getChildren().get(7);

                VBox apagaVBox = (VBox) apagaHBox.getChildren().get(1);
                ImageView apagaSeta = (ImageView) apagaVBox.getChildren().get(1);
                apagaSeta.setOpacity(0);

                VBox setas = new VBox();
                setas.setAlignment(Pos.CENTER_LEFT);

                ImageView img1 = new ImageView("seta.png");
                img1.setFitWidth(30);
                img1.setFitHeight(30);
                img1.setOpacity(0);

                ImageView img2 = new ImageView("seta.png");
                img2.setFitWidth(30);
                img2.setFitHeight(30);
                img2.setRotate(180);

                setas.getChildren().add(img1);
                setas.getChildren().add(img2);
                carryBox.getChildren().add(0, setas);

                if (linhas[i + 1].getChildren().size() > 0) {
                    HBox removerVBox = (HBox) linhas[i + 1].getChildren().get(0);
                    removerVBox.getChildren().remove(0);
                    VBox acenderVBox = (VBox) carryBox.getChildren().get(2);
                    ImageView acenderSeta = (ImageView) acenderVBox.getChildren().get(1);
                    acenderSeta.setOpacity(1);
                }

                linhas[i + 1].getChildren().add(0, carryBox);
            }
        }

        this.animacaoSequencia(0, 1, 0.1, pos - 1, this.LDE.size() - 1, LDE);
        this.animacao(pos, 1 + pos / 5, 0.5).play();
    }

    @FXML
    void EventoRemover(MouseEvent event) throws IOException {
        int pos;

        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO!");

        try {
            pos = Integer.parseInt(TFRemoverPosicao.getText()) - 1;
        } catch (Exception e) {
            alerta.setHeaderText("PRECISA DE UM NUMERO INTEIRO!");
            alerta.setContentText("Preencha o campo da posicao com uma posicao que exista na lista!");

            alerta.showAndWait();
            return;
        }

        if (pos > LDE.size() || pos < 0) {

            alerta.setHeaderText("NAO EXISTE!");
            alerta.setContentText("Preencha o campo da posicao com uma posicao que exista na lista!");

            alerta.showAndWait();
            return;
        }

        String cont = this.LDE.remove(pos);
        if (cont == null)
            return;
        TFRemoverPosicao.setText("");
        TFNumeroDeElementos.setText(String.valueOf(--numeroDeElementos));

        // Remover os Blocos
        this.animacaoSequencia(0, 1, 0.1, pos - 1, this.LDE.size() - 1, this.LDE);
        FillTransition aux = this.animacao(pos, 1 + pos / 5, 0.5);

        aux.setOnFinished(evento -> {

            if (pos % 8 == 0 && LDE.size() != 0) {
                HBox No = (HBox) linhas[pos / 8].getChildren().get((pos + 1) % 8);
                VBox setas = new VBox();
                setas.setAlignment(Pos.CENTER_LEFT);

                ImageView img1 = new ImageView("seta.png");
                img1.setFitWidth(30);
                img1.setFitHeight(30);
                img1.setOpacity(0);

                ImageView img2 = new ImageView("seta.png");
                img2.setFitWidth(30);
                img2.setFitHeight(30);
                img2.setRotate(180);

                setas.getChildren().add(img1);
                setas.getChildren().add(img2);
                No.getChildren().add(0, setas);
            }

            linhas[pos / 8].getChildren().remove(pos % 8);

            for (int i = pos / 8; i < 4; i++)
                if (this.LDE.size() >= (i + 1) * 8) {
                    HBox novoVizinho = (HBox) linhas[i].getChildren().get(6);
                    HBox bound = (HBox)  linhas[i + 1].getChildren().get(0);
                    if (this.LDE.size() > (i + 1) * 8) {
                        HBox vizinho = (HBox) linhas[i + 1].getChildren().get(1);

                        vizinho.getChildren().add(0, bound.getChildren().get(0));
                    } else {
                        bound.getChildren().remove(0);
                    }

                    VBox iluminarNovo = (VBox) novoVizinho.getChildren().get(1);
                    ImageView iluminarSeta = (ImageView) iluminarNovo.getChildren().get(1);
                    iluminarSeta.setOpacity(1);

                    VBox apagarSetaBound = (VBox) bound.getChildren().get(1);
                    ImageView apagarSeta = (ImageView) apagarSetaBound.getChildren().get(1);
                    apagarSeta.setOpacity(0);

                    linhas[i].getChildren().add(7, bound);
                }
        });
        aux.play();
    }

    @FXML
    void EventoConsultaValor(MouseEvent event) throws IOException {
        String cont = TFConsultaValorConteudo.getText();
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO!");

        if (cont.isEmpty()) {
            alerta.setHeaderText("VAZIO!");
            alerta.setContentText("Preencha o campo do conteudo com um valor que exista na lista!");

            alerta.showAndWait();
            return;
        }
        int contIndex = this.LDE.indexOf(cont);
        if (contIndex == -1) {
            alerta.setHeaderText("NAO EXISTE!");
            alerta.setContentText("Preencha o campo do conteudo com um valor que exista na lista!");

            alerta.showAndWait();
            return;
        }
        TFConsultaValorConteudo.setText("");

        // Consultar os Blocos por Valor
        this.animacao(contIndex, 2, 1).play();
    }
    @FXML
    void EventoConsultaPosicao(MouseEvent event) throws IOException {
        int contIndex;
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO!");

        try {
            contIndex = Integer.parseInt(TFConsultaIndicePosicao.getText()) - 1;
        } catch (Exception e) {
            alerta.setHeaderText("PRECISA DE UM NUMERO INTEIRO!");
            alerta.setContentText("Preencha o campo da posicao com uma posicao que exista na lista!");

            alerta.showAndWait();
            return;
        }

        if (contIndex > LDE.size() || contIndex < 0) {

            alerta.setHeaderText("NAO EXISTE!");
            alerta.setContentText("Preencha o campo da posicao com uma posicao que exista na lista!");

            alerta.showAndWait();
            return;
        }

        String cont = this.LDE.get(contIndex);
        if (cont == null)
            return;
        TFConsultaIndicePosicao.setText("");

        // Consultar os Blocos por Indice
        this.animacao(contIndex, 2, 1).play();
    }

    private HBox bloco(int pos, String conteudo, int tamanho) {
        HBox novoNo = new HBox();
        novoNo.setAlignment(Pos.CENTER);

        if (pos % 8 == 0) {
            VBox setas = new VBox();
            setas.setAlignment(Pos.CENTER_LEFT);

            ImageView img1 = new ImageView("seta.png");
            img1.setFitWidth(30);
            img1.setFitHeight(30);
            img1.setOpacity(0);

            ImageView img2 = new ImageView("seta.png");
            img2.setFitWidth(30);
            img2.setFitHeight(30);
            img2.setRotate(180);

            setas.getChildren().add(img1);
            setas.getChildren().add(img2);
            novoNo.getChildren().add(setas);
        }

        StackPane sp = new StackPane();
        sp.setPrefWidth(50);
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

        VBox setas = new VBox();
        setas.setAlignment(Pos.CENTER_LEFT);

        ImageView img1 = new ImageView("seta.png");
        img1.setFitWidth(30);
        img1.setFitHeight(30);

        ImageView img2 = new ImageView("seta.png");
        img2.setFitWidth(30);
        img2.setFitHeight(30);
        img2.setRotate(180);
        if (pos == tamanho)
            img2.setOpacity(0);

        setas.getChildren().add(img1);
        setas.getChildren().add(img2);
        novoNo.getChildren().add(setas);


        return novoNo;
    }

    private FillTransition animacao(int contIndex, int cycles, double time) {
        HBox auxHBox = (HBox) linhas[contIndex / 8].getChildren().get(contIndex % 8);
        StackPane auxSP;
        if (contIndex % 8 != 0)
            auxSP = (StackPane) auxHBox.getChildren().get(0);
        else
            auxSP = (StackPane) auxHBox.getChildren().get(1);
        Rectangle auxRec = (Rectangle) auxSP.getChildren().get(0);

        FillTransition transition = new FillTransition();
        transition.setShape(auxRec);
        transition.setFromValue(Color.web("#8b0000"));
        transition.setToValue(Color.web("#008B8B"));
        transition.setCycleCount(cycles);
        transition.setDuration(Duration.seconds(time));
        return transition;
    }

    private void animacaoSequencia(int contIndex, int cycles, double time, int selecionado, int max, DoubleLinkedList LDE) {

        FillTransition aux;


        if (selecionado < (LDE.size())/2) {
            if (contIndex > selecionado)
                return;

            aux = animacao(contIndex, cycles, time);
            aux.setAutoReverse(true);


            aux.setOnFinished(event -> {
                animacaoSequencia(contIndex + 1, cycles, time, selecionado, max, LDE);
            });
        } else {
            if (max <= selecionado)
                return;

            aux = animacao(max, cycles, time);
            aux.setAutoReverse(true);


            aux.setOnFinished(event -> {
                        animacaoSequencia(contIndex, cycles, time, selecionado, max - 1, LDE);
            });
        }

        aux.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.LDE = new DoubleLinkedList<>();
        this.numeroDeElementos = 0;
        TFNumeroDeElementos.setText("0");

        linhas[0] = HBoxLinha1;
        linhas[1] = HBoxLinha2;
        linhas[2] = HBoxLinha3;
        linhas[3] = HBoxLinha4;
        linhas[4] = HBoxLinha5;
    }
}

