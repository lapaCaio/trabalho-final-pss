/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lp.caio.model;

/**
 *
 * @author tiago
 */
import java.time.LocalDate;

public class Message {
    private long id;
    private String titulo;
    private String mensagem;
    private LocalDate dataCriacao;

    public Message(String titulo, String mensagem, LocalDate dataCriacao) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataCriacao = dataCriacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Título: " + titulo + 
               ", Mensagem: " + mensagem + ", Data de Criação: " + dataCriacao;
    }
}