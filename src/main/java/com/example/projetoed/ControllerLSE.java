package com.example.projetoed;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.example.projetoed.implementations.SingleLinkedList;

public class ControllerLSE implements Initializable {
    SingleLinkedList<String> LSE;
    int numeroDeElementos;

    @FXML
    private AnchorPane APBase;

    @FXML
    private AnchorPane APTitulo;

    @FXML
    private AnchorPane APNumeroDeElementos;

    @FXML
    private AnchorPane APInserir;

    @FXML
    private AnchorPane APRemover;

    @FXML
    private AnchorPane APConsultaValor;

    @FXML
    private AnchorPane APConsultaIndice;

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

        if (!this.LSE.insert(cont, pos))
            return;
        TFInserirPosicao.setText("");
        TFInserirConteudo.setText("");
        TFNumeroDeElementos.setText(String.valueOf(++numeroDeElementos));

        // Criar os Blocos
        System.out.println(this.LSE.toString());
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
        System.out.printf("%d - %s\n", contIndex, cont);
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
        System.out.printf("%d - %s\n", contIndex, cont);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.LSE = new SingleLinkedList<>();
        this.numeroDeElementos = 0;
        TFNumeroDeElementos.setText("0");
    }
}
