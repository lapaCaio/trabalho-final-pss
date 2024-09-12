package lp.caio.model;

import lp.caio.message.Message;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private final int id;
    private String name;
    private String password;
    private final LocalDate dataCadastro;
    private double notificacoesLidas;
    private double notificacoesRecebidas;
    private Estado estado;
    private List<Message> mensagens;

    private boolean aproved;

    public User(int id, String name, String password, LocalDate dataCadastro, double notificacoesLidas, double notificacoesRecebidas, String estado, List<Message> mensagens, boolean aproved) throws Exception {
        this.id = id;
        this.name = name;
        this.password = password;
        this.dataCadastro = dataCadastro;
        this.notificacoesLidas = notificacoesLidas;
        this.notificacoesRecebidas = notificacoesRecebidas;

        if ("admin".equalsIgnoreCase(estado)) {
            this.estado = new Admin(this);
        } else if ("common".equalsIgnoreCase(estado)) {
            this.estado = new Common(this);
        } else {
            throw new Exception();
        }

        this.mensagens = mensagens;
        this.aproved = aproved;
    }


    // Getters e Setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getNotificacoesLidas() {
        return notificacoesLidas;
    }

    public void setNotificacoesLidas(double notificacoesLidas) {
        this.notificacoesLidas = notificacoesLidas;
    }

    public double getNotificacoesRecebidas() {
        return notificacoesRecebidas;
    }

    public void setNotificacoesRecebidas(double notificacoesRecebidas) {
        this.notificacoesRecebidas = notificacoesRecebidas;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Message> getMensagens() {
        return mensagens;
    }

    public void addMensagem(Message mensagem) {
        this.mensagens.add(mensagem);
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
}
