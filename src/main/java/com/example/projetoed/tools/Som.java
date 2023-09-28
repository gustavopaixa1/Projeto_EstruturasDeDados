package com.example.projetoed.tools;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Som {
    private Clip inserir;
    private Clip remover;
    private Clip consultarValor;
    private Clip consultarPosicao;
    private Clip voltar;
    private Clip criarLista;
    private Clip apagarLista;
    private Clip escolherTela;

    public Som() {
        String caminhoSonsDb = FolderFinder.findAbsolutePath(System.getProperty("user.dir"), "resources")
                + "\\sonsDB\\";
        try {
            this.inserir = AudioSystem.getClip();
            this.remover = AudioSystem.getClip();
            this.consultarValor = AudioSystem.getClip();
            this.consultarPosicao = AudioSystem.getClip();
            this.voltar = AudioSystem.getClip();
            this.criarLista = AudioSystem.getClip();
            this.apagarLista = AudioSystem.getClip();
            this.escolherTela = AudioSystem.getClip();
        } catch (Exception e) {
        }

        AudioInputStream tmp;
        try {
            tmp = AudioSystem.getAudioInputStream(new File(caminhoSonsDb + "inserir.wav"));
            this.inserir.open(tmp);
            tmp.close();

            tmp = AudioSystem.getAudioInputStream(new File(caminhoSonsDb + "remover.wav"));
            this.remover.open(tmp);
            tmp.close();

            tmp = AudioSystem.getAudioInputStream(new File(caminhoSonsDb + "consultarValor.wav"));
            this.consultarValor.open(tmp);
            tmp.close();

            tmp = AudioSystem.getAudioInputStream(new File(caminhoSonsDb + "consultarPosicao.wav"));
            this.consultarPosicao.open(tmp);
            tmp.close();

            tmp = AudioSystem.getAudioInputStream(new File(caminhoSonsDb + "voltar.wav"));
            this.voltar.open(tmp);
            tmp.close();

            tmp = AudioSystem.getAudioInputStream(new File(caminhoSonsDb + "criarLista.wav"));
            this.criarLista.open(tmp);
            tmp.close();

            tmp = AudioSystem.getAudioInputStream(new File(caminhoSonsDb + "apagarLista.wav"));
            this.apagarLista.open(tmp);
            tmp.close();

            tmp = AudioSystem.getAudioInputStream(new File(caminhoSonsDb + "escolherTela.wav"));
            this.escolherTela.open(tmp);
            tmp.close();
        } catch (Exception e) {
        }
    }

    public void inserir() {
        this.inserir.setMicrosecondPosition(0);
        this.inserir.start();
    }

    public void remover() {
        this.remover.setMicrosecondPosition(0);
        this.remover.start();
    }

    public void consultarValor() {
        this.consultarValor.setMicrosecondPosition(0);
        this.consultarValor.start();
    }

    public void consultarPosicao() {
        this.consultarPosicao.setMicrosecondPosition(0);
        this.consultarPosicao.start();
    }

    public void voltar() {
        this.voltar.setMicrosecondPosition(0);
        this.voltar.start();
    }

    public void criarLista() {
        this.criarLista.setMicrosecondPosition(0);
        this.criarLista.start();
    }

    public void apagarLista() {
        this.apagarLista.setMicrosecondPosition(0);
        this.apagarLista.start();
    }

    public void escolherTela() {
        this.escolherTela.setMicrosecondPosition(0);
        this.escolherTela.start();
    }
}
