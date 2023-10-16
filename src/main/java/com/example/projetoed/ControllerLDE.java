package com.example.projetoed;

import com.example.projetoed.implementations.DoubleLinkedList;
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


public class ControllerLDE implements Initializable {
    private DoubleLinkedList<String> LDE;

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
    private AnchorPane paneConsultar1;

    @FXML
    private AnchorPane paneConsultar2;

    @FXML
    private AnchorPane paneInserir;

    @FXML
    private AnchorPane paneRemover;

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
        char inserir = 'i';
        String cont;
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");
        try {
            pos = Integer.parseInt(TFInserirPosicao.getText().trim()) - 1;
            cont = TFInserirConteudo.getText().trim();
            if (pos < 0 || pos > this.LDE.size())
                throw new NumberFormatException();
            if (cont.isEmpty())
                throw new Exception();
        } catch (NumberFormatException e) {
            alerta.setHeaderText("Valor inválido.");
            if (this.LDE.isEmpty())
                alerta.setContentText("Por favor, preencha o campo da posição com o valor 1 para adicionar o primeiro elemento da lista.");
            else
                alerta.setContentText(String.format("Por favor, preencha o campo da posição com um número inteiro entre 1 e %d.", this.LDE.size() + 1));
            alerta.showAndWait();
            return;
        } catch (Exception e) {
            alerta.setHeaderText("Conteúdo inválido.");
            alerta.setContentText("Por favor, preencha o campo de conteúdo com algo para ser armazenado (apenas espaços não são caracteres válidos).");
            alerta.showAndWait();
            return;
        }

        this.LDE.insert(cont, pos);
        TFInserirPosicao.setText("");
        TFInserirConteudo.setText("");
        TFNumeroDeElementos.setText(String.valueOf(this.LDE.size()));

        // Criar os Blocos
        FPDados.getChildren().add(pos, bloco(cont));

        this.animacaoSequencia(0, 1, 0.1, "#8b0000", "#008B8B", pos - 1, LDE.size() - 1, inserir, LDE);
        this.animacao(pos, 1 + pos / 5, 0.5, "#73ee81", "#008B8B").play();
    }

    @FXML
    void EventoRemover(MouseEvent event) throws IOException {
        int pos;
        char remove = 'r';
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");
        try {
            pos = Integer.parseInt(TFRemoverPosicao.getText().trim()) - 1;
            if (pos < 0 || pos >= this.LDE.size())
                throw new Exception();
        } catch (Exception e) {
            if (this.LDE.isEmpty()) {
                alerta.setHeaderText("Lista vazia.");
                alerta.setContentText("A lista está vazia, não existem itens para serem removidos.");
            } else if (this.LDE.size() == 1) {
                alerta.setHeaderText("Valor inválido.");
                alerta.setContentText("Por favor, preencha o campo da posição com o valor 1 para remover o único elemento da lista.");
            } else {
                alerta.setHeaderText("Valor inválido.");
                alerta.setContentText(String.format("Por favor, preencha o campo da posição com um número inteiro entre 1 e %d.", this.LDE.size()));
            }
            alerta.showAndWait();
            return;
        }

        this.LDE.remove(pos);
        TFRemoverPosicao.setText("");
        TFNumeroDeElementos.setText(String.valueOf(this.LDE.size()));

        // Remover os Blocos
        this.animacaoSequencia(0, 1, 0.1, "#8b0000", "#008B8B", pos - 1, LDE.size(), remove, LDE);
        FillTransition aux = this.animacao(pos, 1 + pos / 5, 0.5, "#008B8B", "#ffffff");

        aux.setOnFinished(evento -> {
            FPDados.getChildren().remove(pos);
        });
        aux.play();
    }

    @FXML
    void EventoConsultaValor(MouseEvent event) throws IOException {
        String cont;
        int contIndex;
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");
        try {
            cont = TFConsultaValorConteudo.getText().trim();
            if (cont.isEmpty())
                throw new Exception();
            contIndex = this.LDE.indexOf(cont);
            if (contIndex == -1)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            alerta.setHeaderText("Conteúdo não encontrado.");
            alerta.setContentText("O conteúdo buscado não se encontra na lista atualmente.");
            alerta.showAndWait();
            return;
        } catch (Exception e) {
            alerta.setHeaderText("Conteúdo inválido.");
            alerta.setContentText("Por favor, preencha o campo de conteúdo com algo para ser buscado (apenas espaços não são caracteres válidos).");
            alerta.showAndWait();
            return;
        }

        TFConsultaValorConteudo.setText("");

        // Consultar os Blocos por Valor
        this.animacao(contIndex, 2, 1, "#8b0000", "#008B8B").play();
    }
    @FXML
    void EventoConsultaIndice(MouseEvent event) throws IOException {
        int contIndex;
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");
        try {
            contIndex = Integer.parseInt(TFConsultaIndicePosicao.getText().trim()) - 1;
            if (contIndex < 0 || contIndex >= this.LDE.size())
                throw new Exception();
        } catch (Exception e) {
            if (this.LDE.isEmpty()) {
                alerta.setHeaderText("Lista vazia.");
                alerta.setContentText("A lista está vazia, não existem itens para serem buscados.");
            } else if (this.LDE.size() == 1) {
                alerta.setHeaderText("Valor inválido.");
                alerta.setContentText("Por favor, preencha o campo da posição com o valor 1 para buscar o único elemento da lista.");
            } else {
                alerta.setHeaderText("Valor inválido.");
                alerta.setContentText(String.format("Por favor, preencha o campo da posição com um número inteiro entre 1 e %d.", this.LDE.size()));
            }
            alerta.showAndWait();
            return;
        }

        TFConsultaIndicePosicao.setText("");

        // Consultar os Blocos por Indice
        this.animacao(contIndex, 2, 1, "#8b0000", "#008B8B").play();
    }

    private HBox bloco(String conteudo) {
        HBox novoNo = new HBox();
        novoNo.setAlignment(Pos.CENTER);

        ImageView img = new ImageView("seta.png");
        img.setFitWidth(30);
        img.setFitHeight(30);
        img.setScaleX(-1);
        novoNo.getChildren().add(img);
        img.setTranslateY(15);

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

        img = new ImageView("seta.png");
        img.setFitWidth(30);
        img.setFitHeight(30);
        novoNo.getChildren().add(img);
        img.setTranslateY(-15);



        return novoNo;
    }

    private FillTransition animacao(int contIndex, int cycles, double time, String fromColor, String toColor) {
        HBox auxHBox = (HBox) FPDados.getChildren().get(contIndex);
        StackPane auxSP = (StackPane) auxHBox.getChildren().get(1);
        Rectangle auxRec = (Rectangle) auxSP.getChildren().get(0);

        FillTransition transition = new FillTransition();
        transition.setShape(auxRec);
        transition.setFromValue(Color.web(fromColor));
        transition.setToValue(Color.web(toColor));
        transition.setCycleCount(cycles);
        transition.setDuration(Duration.seconds(time));
        return transition;
    }

    private void animacaoSequencia(int contIndex, int cycles, double time, String fromColor, String toColor, int selecionado, int max, char operation, DoubleLinkedList LDE) {


        FillTransition aux;


        if ((selecionado + 1 < LDE.size()/2 && operation == 'i') || (selecionado < LDE.size()/2 && operation == 'r')) {
            if (contIndex > selecionado)
                return;
            aux = animacao(contIndex, cycles, time, fromColor, toColor);
            aux.setAutoReverse(true);
            aux.setOnFinished(event -> {
                animacaoSequencia(contIndex + 1, cycles, time, fromColor, toColor, selecionado, max, operation, LDE);
            });
        } else {
            if (max <= selecionado)
                return;
            aux = animacao(max, cycles, time, fromColor, toColor);
            aux.setAutoReverse(true);
            aux.setOnFinished(event -> {
                animacaoSequencia(contIndex, cycles, time, fromColor, toColor, selecionado, max - 1, operation, LDE);
            });
        }

        aux.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.LDE = new DoubleLinkedList<>();
        TFNumeroDeElementos.setText("0");
        FPDados.setHgap(-30);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0); // Configurar o raio da sombra
        dropShadow.setOffsetX(3.0); // Configurar o deslocamento horizontal da sombra
        dropShadow.setOffsetY(3.0); // Configurar o deslocamento vertical da sombra
        dropShadow.setColor(javafx.scene.paint.Color.BLACK); // Configurar a cor da sombra
        paneConsultar1.setEffect(dropShadow);
        paneInserir.setEffect(dropShadow);
        paneConsultar2.setEffect(dropShadow);
        paneRemover.setEffect(dropShadow);


        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(FPDados.widthProperty());
        clip.heightProperty().bind(FPDados.heightProperty());
        FPDados.setClip(clip);
    }
}
