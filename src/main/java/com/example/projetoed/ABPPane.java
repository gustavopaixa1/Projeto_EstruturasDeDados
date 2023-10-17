package com.example.projetoed;

import com.example.projetoed.implementations.Search_Binary_Tree;
import com.example.projetoed.implementations.SBTNode;
import javafx.geometry.Insets;
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
    private double YDistancia = 50;

    public ABPPane(Search_Binary_Tree<T> tree) {
        this.abp = tree;
        this.setStyle("-fx-border-color: #008B8B; -fx-border-width: 7; -fx-border-radius: 10 10 10 10;");
    }

    public void atualizarVisualizacao() {
        this.getChildren().clear();
        if (!this.abp.isEmpty())
            atualizarVisualizacao(this.abp.root(), this.getWidth() / 2, this.getWidth() / 4, 30, this.YDistancia, Color.web("#ff0000"));
    }

    private void atualizarVisualizacao(SBTNode<T> raiz, double startX, double XDistancia, double startY, double YDistance, Color cor) {
        if (raiz.getLeft() != null) {
            this.getChildren().add(new Line(startX, startY, startX - XDistancia, startY + YDistance));
            this.atualizarVisualizacao(raiz.getLeft(), startX - XDistancia, XDistancia / 2, startY + YDistance, YDistance, cor);
        }

        if (raiz.getRight() != null) {
            this.getChildren().add(new Line(startX, startY, startX + XDistancia, startY + YDistance));
            this.atualizarVisualizacao(raiz.getRight(), startX + XDistancia, XDistancia / 2, startY + YDistance, YDistance, cor);
        }

        StackPane sp = new StackPane();
        sp.setLayoutX(startX - this.raio);
        sp.setLayoutY(startY - this.raio);
        sp.setAlignment(Pos.CENTER);
//        sp.setBackground(new Background(new BackgroundFill(Color.web("#" + "40E0D0"), CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().add(sp);

        Circle circulo = new Circle(startX, startY, this.raio);
        circulo.setStyle("-fx-arc-height: 10; -fx-arc-width: 10; -fx-stroke: #003B6F; -fx-fill: #008B8B; -fx-stroke-width: 2;");
        sp.getChildren().add(circulo);

        Text texto = new Text((String) raiz.getContent());
        texto.setFont(Font.font("Consolas", 15));
        texto.setFill(Color.web("#ffffff"));
        texto.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        sp.getChildren().add(texto);
    }
}


