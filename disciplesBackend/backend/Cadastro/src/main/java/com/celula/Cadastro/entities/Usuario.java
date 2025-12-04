package com.celula.Cadastro.entities;

import enums.TipoUsuario;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario", nullable = false)
    private Long idUsuario;

    @Column(name = "nomeUsuario", nullable = false, length = 100)
    private String nomeUsuario;

    @Column(name = "idade", nullable = false)
    private double idade;

    @Column(name = "userName", nullable = false, length = 100)
    private String userName;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "senha", nullable = false, length = 255)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoUsuario tipo = TipoUsuario.USUARIO;

    public Usuario() {}

    public Usuario(Long idUsuario, String nomeUsuario, String userName, String email,
                   String senha, TipoUsuario tipo, double idade) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.idade = idade;
        this.userName = userName;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public double getIdade() {
        return idade;
    }
    public void setIdade(double idade) {
        this.idade = idade;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipo;
    }
    public void setTipoUsuario(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}
