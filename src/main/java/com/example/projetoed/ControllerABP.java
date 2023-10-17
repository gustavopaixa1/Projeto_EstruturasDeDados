package com.example.projetoed;

import com.example.projetoed.implementations.Search_Binary_Tree;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
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
            alerta.setHeaderText("Conteúdo já existe.");
            alerta.setContentText("Por favor, preencha o campo de conteúdo com um valor novo.");
            alerta.showAndWait();
            return;
        }

        // Adiciona o nó
        this.ABP.insert(cont);
        this.TFInserir.setText("");
        this.TFNumeroDeElementos.setText(String.valueOf(this.ABP.size()));
        this.PaneDaArvore.atualizarVisualizacao();

//        this.animacaoSequencia(0, 1, 0.1, "#8b0000", "#008B8B", pos - 1);
        this.animacao(cont, 2, 0.5, "#73ee81", "#008B8B").play();
    }

    @FXML
    void EventoRemover(MouseEvent event) {
        int cont;
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");
        try {
            if (this.ABP.isEmpty())
                throw new Exception();
            cont = Integer.parseInt(this.TFRemover.getText().trim());
            if (!this.ABP.search(cont))
                throw new Exception();
        } catch (NumberFormatException e) {
            alerta.setHeaderText("Conteúdo inválido.");
            alerta.setContentText("Por favor, preencha o campo de conteúdo com um valor numérico inteiro para ser removido (apenas espaços não são caracteres válidos).");
            alerta.showAndWait();
            return;
        } catch (Exception e) {
            if (this.ABP.isEmpty()) {
                alerta.setHeaderText("Árvore vazia.");
                alerta.setContentText("A árvore está vazia, não existem itens para serem removidos.");
            } else {
                alerta.setHeaderText("Conteúdo não existente.");
                alerta.setContentText("Por favor, preencha o campo de conteúdo com um valor já existente para ser removido.");
            }
            alerta.showAndWait();
            return;
        }

        // Remove o nó
        this.ABP.remove(cont);
        this.TFRemover.setText("");
        this.TFNumeroDeElementos.setText(String.valueOf(this.ABP.size()));


//        this.animacaoSequencia(0, 1, 0.1, "#8b0000", "#008B8B", pos - 1);
        FillTransition aux = this.animacao(cont, 2, 0.5, "#008B8B", "#ffffff");
        aux.setOnFinished(evento -> {
            this.PaneDaArvore.atualizarVisualizacao();
        });
        aux.play();
    }

    @FXML
    void EventoConsultaValor(MouseEvent event) {
        int cont;
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO");
        try {
            if (this.ABP.isEmpty())
                throw new Exception();
            cont = Integer.parseInt(this.TFConsultaValor.getText().trim());
            if (!this.ABP.search(cont))
                throw new Exception();
        } catch (NumberFormatException e) {
            alerta.setHeaderText("Conteúdo inválido.");
            alerta.setContentText("Por favor, preencha o campo de conteúdo com um valor numérico inteiro para ser buscado (apenas espaços não são caracteres válidos).");
            alerta.showAndWait();
            return;
        } catch (Exception e) {
            if (this.ABP.isEmpty()) {
                alerta.setHeaderText("Árvore vazia.");
                alerta.setContentText("A árvore está vazia, não existem itens para serem consultados.");
            } else {
                alerta.setHeaderText("Conteúdo não encontrado.");
                alerta.setContentText("O conteúdo buscado não se encontra na árvore atualmente.");
            }
            alerta.showAndWait();
            return;
        }

        // Consulta o nó
        this.TFConsultaValor.setText("");
        this.animacao(cont, 2, 1, "#8b0000", "#008B8B").play();
    }

    @FXML
    void EventoPreOrdem(MouseEvent event) {
        int[] aa = {32, 16, 8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15, 24, 20, 18, 17, 19, 22, 21, 23, 28, 26,
                25, 27, 30, 29, 31, 48, 40, 36, 34, 33, 35, 38, 37, 39, 44, 42, 41, 43, 46, 45, 47, 56, 52, 50, 49, 51,
                54, 53, 55, 60, 58, 57, 59, 62, 61, 63};
        int[] bb = {128, 64, 32, 16, 8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15, 24, 20, 18, 17, 19, 22, 21, 23, 28, 26, 25, 27, 30, 29, 31,
                192, 160, 144, 136, 132, 130, 129, 131, 134, 133, 135, 140, 138, 137, 139, 142, 141, 143, 152, 148, 146, 145, 147, 150, 149, 151,
                156, 154, 153, 155, 158, 157, 159,
                224, 208, 200, 196, 194, 193, 195, 198, 197, 199, 204, 202, 201, 203, 206, 205, 207, 216, 212, 210, 209, 211, 214, 213, 215,
                220, 218, 217, 219, 222, 221, 223,
                240, 232, 228, 226, 225, 227, 230, 229, 231, 236, 234, 233, 235, 238, 237, 239, 244, 242, 241, 243, 246, 245, 247,
                252, 250, 249, 251, 254, 253, 255
        };
        for (int i: aa) {
            this.TFInserir.setText(Integer.toString(i));
            this.EventoInserir(event);
        }
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

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(this.PaneDaArvore.widthProperty());
        clip.heightProperty().bind(this.PaneDaArvore.heightProperty());
        this.PaneDaArvore.setClip(clip);
    }

    private FillTransition animacao(int cont, int cycles, double time, String fromColor, String toColor) {
        ArrayList<StackPane> nodes = this.PaneDaArvore.getNodes();
        Circle no = null;
        Text texto = null;
        for (StackPane sp : nodes) {
            if (((Text) sp.getChildren().get(1)).getText().equals(Integer.toString(cont))) {
                no = (Circle) sp.getChildren().get(0);
                texto = (Text) sp.getChildren().get(1);
                break;
            }
        }

        FillTransition transition = new FillTransition();
        transition.setShape(no);
        transition.setFromValue(Color.web(fromColor));
        transition.setToValue(Color.web(toColor));
        transition.setCycleCount(cycles);
        transition.setDuration(Duration.seconds(time));
        final Text finalTexto = texto;
        finalTexto.setVisible(false);
        transition.setOnFinished(event -> {
            (finalTexto).setVisible(true);
        });
        return transition;
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
