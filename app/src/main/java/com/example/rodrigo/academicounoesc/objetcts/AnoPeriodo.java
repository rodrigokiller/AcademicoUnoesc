package com.example.rodrigo.academicounoesc.objetcts;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Rodrigo on 20/06/2015.
 */

@Table(name = "AnoPeriodo")
public class AnoPeriodo {
    @Column(name = "AnoPeriodo")
    String anoPeriodo;

    @Column(name = "Fase")
    int fase;

    @Column(name = "Curso")
    Curso curso;

    public AnoPeriodo(){
        super();
    }

    public AnoPeriodo(String anoPeriodo, int fase, Curso curso) {
        super();
        this.anoPeriodo = anoPeriodo;
        this.fase = fase;
        this.curso = curso;
    }
}
