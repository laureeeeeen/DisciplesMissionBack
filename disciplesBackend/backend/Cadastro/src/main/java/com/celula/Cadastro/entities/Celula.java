package com.celula.Cadastro.entities;

import enums.FaixaEtaria;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_celula")
public class Celula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCelula", nullable = false)
    private Long idCelula;

    @Column(name = "nomeCelula", nullable = false, length = 255)
    private String nomeCelula;

    @Column(name = "localCelula", length = 400)
    private String local;

    @Column(name = "dia", nullable = false, length = 100)
    private String dia;

    @Column(name = "horario", nullable = false, length = 20)
    private String horario;

    @Enumerated(EnumType.STRING)
    @Column(name = "faixa_etaria", nullable = false, length = 20)
    private FaixaEtaria faixaEtaria;

    @Column(name = "lider", nullable = false, length = 400)
    private String lider;

    // 🔽 NOVOS CAMPOS
    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false) // FK pra tb_usuario.idUsuario
    private Usuario usuario;

    public Celula() {}

    public Celula(Long idCelula, String nomeCelula, String local, String dia,
                  String horario, FaixaEtaria faixaEtaria, String lider, Double latitude, Double longitude,
                  Usuario usuario) {
        this.idCelula = idCelula;
        this.nomeCelula = nomeCelula;
        this.local = local;
        this.dia = dia;
        this.horario = horario;
        this.faixaEtaria = faixaEtaria;
        this.lider = lider;
        this.latitude = latitude;
        this.longitude = longitude;
        this.usuario = usuario;
    }

    public Long getIdCelula() {
        return idCelula;
    }
    public void setIdCelula(Long idCelula) {
        this.idCelula = idCelula;
    }

    public String getNomeCelula() {
        return nomeCelula;
    }
    public void setNomeCelula(String nomeCelula) {
        this.nomeCelula = nomeCelula;
    }

    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }

    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }

    public FaixaEtaria getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(FaixaEtaria faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public String getLider() {
        return lider;
    }
    public void setLider(String lider) {
        this.lider = lider;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
