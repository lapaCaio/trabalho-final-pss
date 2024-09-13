/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lp.caio.model;

/**
 *
 * @author tiago
 */
public class UserView {
  private long id;
    private long idUsuario;
    private long idMensagem;
    private boolean lida;

    public UserView(long idUsuario, long idMensagem, boolean lida) {
        this.idUsuario = idUsuario;
        this.idMensagem = idMensagem;
        this.lida = lida;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(long idMensagem) {
        this.idMensagem = idMensagem;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", ID Usuário: " + idUsuario + 
               ", ID Mensagem: " + idMensagem + ", Lida: " + lida;
    }   
}
