package com.example.rodrigo.academicounoesc.objetcts;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Rodrigo on 20/06/2015.
 */

@Table(name = "Materia")
public class Materia {
    @Column(name = "Codigo")
    public int codigo;

    @Column(name = "Nome")
    public String nome;

    @Column(name = "Campus")
    public String campus;

    @Column(name = "TotFaltas")
    public int totFaltas;

    @Column(name = "UltDataFaltas")
    public String ultDataFaltas;

    @Column(name = "MP")
    public float mp;

    @Column(name = "MF")
    public float mf;

    @Column(name = "Situacao")
    public String situacao;

    @Column(name = "Professor")
    public String professor;

    @Column(name = "Creditos")
    public int creditos;

    @Column(name = "CargaHoraria")
    public int cargaHoraria;

    @Column(name = "Frequencia")
    public float frequencia;

    @Column(name = "AnoPeriodo")
    public AnoPeriodo anoPeriodo;

    public Materia(){
        super();
    }

    public Materia(int codigo, String nome, String campus, int totFaltas, String ultDataFaltas, float mp, float mf, String situacao, String professor, int creditos, int cargaHoraria, float frequencia, AnoPeriodo anoPeriodo) {
        super();
        this.codigo = codigo;
        this.nome = nome;
        this.campus = campus;
        this.totFaltas = totFaltas;
        this.ultDataFaltas = ultDataFaltas;
        this.mp = mp;
        this.mf = mf;
        this.situacao = situacao;
        this.professor = professor;
        this.creditos = creditos;
        this.cargaHoraria = cargaHoraria;
        this.frequencia = frequencia;
        this.anoPeriodo = anoPeriodo;
    }
}