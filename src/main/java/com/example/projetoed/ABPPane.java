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

public class ABPPane<T extends Comparable<T>> extends Pane {
    private Search_Binary_Tree<T> abp;
    private double raio = 15;

    public ABPPane(Search_Binary_Tree<T> tree) {
        this.abp = tree;
        this.setStyle("-fx-border-color: #008B8B; -fx-border-width: 7; -fx-border-radius: 10 10 10 10;");
    }

    public void atualizarVisualizacao() {
        this.getChildren().clear();
        if (!this.abp.isEmpty())
            atualizarVisualizacao(this.abp.root(), 1, this.widthProperty().divide(2), 40, 25, Color.web("#ff0000"));
    }

    private void atualizarVisualizacao(SBTNode<T> raiz, double profundidade, DoubleBinding startXPosition, double startY, double YDistancia, Color cor) {
        StackPane sp = new StackPane();
        sp.layoutXProperty().bind(startXPosition.subtract(this.raio / Math.log(profundidade + 1)));
        sp.setLayoutY(startY - this.raio / Math.log(profundidade + 1));
        sp.setAlignment(Pos.CENTER);

        Circle circulo = new Circle(this.raio / Math.log(profundidade + 1));
        circulo.setStyle("-fx-arc-height: 10; -fx-arc-width: 10; -fx-stroke: #003B6F; -fx-fill: #008B8B; -fx-stroke-width: 2;");
        sp.getChildren().add(circulo);

        Text texto = new Text(Integer.toString((Integer)raiz.getContent()));
        texto.setFont(Font.font("Consolas", 15));
        texto.setFill(Color.web("#ffffff"));
        texto.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        texto.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 2, 2, 2);");
        sp.getChildren().add(texto);


        if (raiz.getLeft() != null) {
            DoubleBinding endXPosition = startXPosition.subtract(this.widthProperty().divide(Math.pow(2, profundidade + 1)));
            Line linha = new Line();
            linha.startXProperty().bind(startXPosition);
            linha.endXProperty().bind(endXPosition);
            linha.setStartY(startY);
            linha.setEndY(startY + YDistancia);

            this.getChildren().add(linha);
            this.atualizarVisualizacao(raiz.getLeft(), profundidade + 1, endXPosition, startY + YDistancia, YDistancia, cor);
        }

        if (raiz.getRight() != null) {
            DoubleBinding endXPosition = startXPosition.add(this.widthProperty().divide(Math.pow(2, profundidade + 1)));
            Line linha = new Line();
            linha.startXProperty().bind(startXPosition);
            linha.endXProperty().bind(endXPosition);
            linha.setStartY(startY);
            linha.setEndY(startY + YDistancia);

            this.getChildren().add(linha);
            this.atualizarVisualizacao(raiz.getRight(), profundidade + 1, endXPosition, startY + YDistancia, YDistancia, cor);
        }

        this.getChildren().add(sp);
    }
}


