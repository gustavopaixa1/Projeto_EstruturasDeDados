package com.example.projetoed;

import com.example.projetoed.implementations.Search_Binary_Tree;
import com.example.projetoed.implementations.SBTNode;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class ABPPane extends Pane {
    private Search_Binary_Tree abp;
    public ArrayList<StackPane> nodes;
    private double raio = 15;
    private int sideGap = 40;

    public ABPPane(Search_Binary_Tree tree) {
        this.abp = tree;
        this.nodes = new ArrayList<>();
        this.setStyle("-fx-border-color: #008B8B; -fx-border-width: 7; -fx-border-radius: 10 10 10 10;");
    }

    public ArrayList<StackPane> getNodes() {
        return this.nodes;
    }
    public void atualizarVisualizacao() {
        this.getChildren().clear();
        this.nodes.clear();
        if (!this.abp.isEmpty())
            atualizarVisualizacao(this.abp.root(), 1, this.widthProperty().subtract(this.sideGap).divide(2).add(this.sideGap / 2), 40, 25);
    }

    private void atualizarVisualizacao(SBTNode<Integer> raiz, double profundidade, DoubleBinding startXPosition, double startY, double YDistancia) {
        StackPane sp = new StackPane();
        sp.layoutXProperty().bind(startXPosition.subtract(this.raio / Math.log(profundidade + 1)));
        sp.setLayoutY(startY - this.raio / Math.log(profundidade + 1));
        sp.setAlignment(Pos.CENTER);
        this.nodes.add(sp);

        Circle circulo = new Circle(this.raio / Math.log(profundidade + 1));
        circulo.setStyle("-fx-arc-height: 10; -fx-arc-width: 10; -fx-stroke: #003B6F; -fx-fill: #008B8B; -fx-stroke-width: 2;");
        sp.getChildren().add(circulo);

        Text texto = new Text(Integer.toString((Integer)raiz.getContent()));
        texto.setFont(Font.font("Consolas", 15));
        texto.setFill(Color.web("#ffffff"));
        texto.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        texto.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 3, 1, 0, 0);");
        sp.getChildren().add(texto);

        if (raiz.getLeft() != null) {
            DoubleBinding endXPosition = startXPosition.subtract(this.widthProperty().subtract(this.sideGap).divide(Math.pow(2, profundidade + 1)));
            Line linha = new Line();
            linha.startXProperty().bind(startXPosition);
            linha.endXProperty().bind(endXPosition);
            linha.setStartY(startY);
            linha.setEndY(startY + YDistancia);

            this.getChildren().add(linha);
            this.atualizarVisualizacao(raiz.getLeft(), profundidade + 1, endXPosition, startY + YDistancia, YDistancia);
        }

        if (raiz.getRight() != null) {
            DoubleBinding endXPosition = startXPosition.add(this.widthProperty().subtract(this.sideGap).divide(Math.pow(2, profundidade + 1)));
            Line linha = new Line();
            linha.startXProperty().bind(startXPosition);
            linha.endXProperty().bind(endXPosition);
            linha.setStartY(startY);
            linha.setEndY(startY + YDistancia);

            this.getChildren().add(linha);
            this.atualizarVisualizacao(raiz.getRight(), profundidade + 1, endXPosition, startY + YDistancia, YDistancia);
        }

        this.getChildren().add(sp);
    }
}


