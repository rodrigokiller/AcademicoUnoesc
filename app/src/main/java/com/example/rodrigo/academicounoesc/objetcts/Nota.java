package com.example.rodrigo.academicounoesc.objetcts;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Rodrigo on 20/06/2015.
 */
@Table(name = "Notas")
public class Nota  extends Model {
    @Column(name = "Nota")
    public float nota;

    @Column(name = "Peso")
    public float peso;

    @Column(name = "Materia")
    public Materia materia;

    public Nota() {
        super();
    }

    public Nota(float nota, float peso, Materia materia) {
        super();
        this.nota = nota;
        this.peso = peso;
        this.materia = materia;
    }
}
