package com.example.projetoed;

import com.example.projetoed.implementations.Search_Binary_Tree;
import com.example.projetoed.implementations.Linked_Queue;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.animation.FillTransition;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;

public class ControllerABP implements Initializable {
    static class TransitionModificada {
        public FillTransition ft;
        public Text txt;

        public TransitionModificada(FillTransition ft, Text txt) {
            this.ft = ft;
            this.txt = txt;
        }
    }

    private Search_Binary_Tree ABP;

    private ABPPane PaneDaArvore;

    private Linked_Queue<String> sequenciaDaAnimacao;

    private boolean animacaoEmAndamento;

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
        if (this.animacaoEmAndamento) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO");
            alerta.setHeaderText("Caminhamento em andamento.");
            alerta.setContentText("Espere que o caminhamento atual termine antes de chamar outra função.");
            alerta.showAndWait();
            return;
        }

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

        ArrayList<String> auxStrings = this.ABP.getHistory();
        for (String auxString : auxStrings)
            this.sequenciaDaAnimacao.push(auxString);
        this.animacaoSequencia(1, 0.2, "#8b0000", "#008B8B");

        TransitionModificada tm = this.animacao(Integer.toString(cont), (auxStrings.size() / 5 + 1) * 2, 0.5, "#73ee81", "#008B8B");
        tm.ft.setOnFinished(evento -> {
            tm.txt.setVisible(true);
        });
        tm.ft.play();
    }

    @FXML
    void EventoRemover(MouseEvent event) {
        if (this.animacaoEmAndamento) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO");
            alerta.setHeaderText("Caminhamento em andamento.");
            alerta.setContentText("Espere que o caminhamento atual termine antes de chamar outra função.");
            alerta.showAndWait();
            return;
        }

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

        ArrayList<String> auxStrings = this.ABP.getHistory();
        for (String auxString : auxStrings)
            this.sequenciaDaAnimacao.push(auxString);
        this.animacaoSequencia(1, 0.2, "#8b0000", "#008B8B");

        TransitionModificada tm = this.animacao(Integer.toString(cont), (auxStrings.size() / 5 + 1) * 2, 0.5, "#008B8B", "#ffffff");
        tm.txt.setText("");
        tm.ft.setOnFinished(evento -> {
            this.PaneDaArvore.atualizarVisualizacao();
        });
        tm.ft.play();
    }

    @FXML
    void EventoConsultaValor(MouseEvent event) {
        if (this.animacaoEmAndamento) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO");
            alerta.setHeaderText("Caminhamento em andamento.");
            alerta.setContentText("Espere que o caminhamento atual termine antes de chamar outra função.");
            alerta.showAndWait();
            return;
        }

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

        this.ABP.search(cont);
        ArrayList<String> auxStrings = this.ABP.getHistory();
        for (String auxString : auxStrings)
            this.sequenciaDaAnimacao.push(auxString);
        this.animacaoSequencia(1, 0.2, "#8b0000", "#008B8B");

        TransitionModificada tm = this.animacao(Integer.toString(cont), (this.ABP.getHistory().size() / 5 + 1) * 2, 1, "#8b0000", "#008B8B");
        tm.ft.setOnFinished(evento -> {
            tm.txt.setVisible(true);
        });
        tm.ft.play();
    }

    @FXML
    void EventoPreOrdem(MouseEvent event) {
        if (this.ABP.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO");
            alerta.setHeaderText("Árvore vazia.");
            alerta.setContentText("A árvore está vazia, não existem itens para serem caminhados.");
            alerta.showAndWait();
            return;
        }
        if (this.animacaoEmAndamento) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO");
            alerta.setHeaderText("Caminhamento em andamento.");
            alerta.setContentText("Espere que o caminhamento atual termine antes de iniciar outro.");
            alerta.showAndWait();
            return;
        }

        this.animacaoEmAndamento = true;
        ArrayList<String> auxStrings = this.ABP.preOrder_Traversal();
        for (String auxString : auxStrings)
            this.sequenciaDaAnimacao.push(auxString);

        this.animacaoSequencia(1, 0.5, "#8b0000", "#008B8B");
    }

    @FXML
    void EventoInOrdem(MouseEvent event) {
        if (this.ABP.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO");
            alerta.setHeaderText("Árvore vazia.");
            alerta.setContentText("A árvore está vazia, não existem itens para serem caminhados.");
            alerta.showAndWait();
            return;
        }
        if (this.animacaoEmAndamento) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO");
            alerta.setHeaderText("Caminhamento em andamento.");
            alerta.setContentText("Espere que o caminhamento atual termine antes de iniciar outro.");
            alerta.showAndWait();
            return;
        }

        this.animacaoEmAndamento = true;
        ArrayList<String> auxStrings = this.ABP.inOrder_Traversal();
        for (String auxString : auxStrings)
            this.sequenciaDaAnimacao.push(auxString);

        this.animacaoSequencia(1, 0.5, "#8b0000", "#008B8B");
    }

    @FXML
    void EventoPosOrdem(MouseEvent event) {
        if (this.ABP.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO");
            alerta.setHeaderText("Árvore vazia.");
            alerta.setContentText("A árvore está vazia, não existem itens para serem caminhados.");
            alerta.showAndWait();
            return;
        }
        if (this.animacaoEmAndamento) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO");
            alerta.setHeaderText("Caminhamento em andamento.");
            alerta.setContentText("Espere que o caminhamento atual termine antes de iniciar outro.");
            alerta.showAndWait();
            return;
        }

        this.animacaoEmAndamento = true;
        ArrayList<String> auxStrings = this.ABP.postOrder_Traversal();
        for (String auxString : auxStrings)
            this.sequenciaDaAnimacao.push(auxString);

        this.animacaoSequencia(1, 0.5, "#8b0000", "#008B8B");
    }

    private void configurarPanePersonalizado() {
        this.PaneDaArvore = new ABPPane(this.ABP);

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

    private TransitionModificada animacao(String cont, int cycles, double time, String fromColor, String toColor) {
        ArrayList<StackPane> nodes = this.PaneDaArvore.getNodes();
        Circle no = null;
        Text texto = null;
        for (StackPane sp : nodes) {
            if (((Text) sp.getChildren().get(1)).getText().equals(cont)) {
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

        TransitionModificada tm = new TransitionModificada(transition, finalTexto);
        finalTexto.setVisible(false);
        transition.setOnFinished(event -> {
            (finalTexto).setVisible(true);
        });
        return tm;
    }

    private void animacaoSequencia(int cycles, double time, String fromColor, String toColor) {
        if (this.sequenciaDaAnimacao.isEmpty())
            return;
        TransitionModificada tm = animacao(this.sequenciaDaAnimacao.pop(), cycles, time, fromColor, toColor);
        FillTransition aux = tm.ft;
        aux.setAutoReverse(false);
        aux.setOnFinished(event -> {
            tm.txt.setVisible(true);
            if (this.sequenciaDaAnimacao.isEmpty())
                this.animacaoEmAndamento = false;
            animacaoSequencia(cycles, time, fromColor, toColor);
        });

        aux.play();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.ABP = new Search_Binary_Tree();
        this.sequenciaDaAnimacao = new Linked_Queue<>();
        this.animacaoEmAndamento = false;
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
