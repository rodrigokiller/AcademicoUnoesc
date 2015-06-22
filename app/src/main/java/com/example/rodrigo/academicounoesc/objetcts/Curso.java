package com.example.rodrigo.academicounoesc.objetcts;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Rodrigo on 20/06/2015.
 */

@Table(name = "Curso", id = "Codigo")
public class Curso extends Model {
    @Column(name = "Codigo", index = true)
    public int codigo;

    @Column(name = "Nome")
    public String nome;

    @Column(name = "Aluno")
    public Aluno aluno;

    public Curso() {
        super();
    }

    public Curso(int codigo, String nome, Aluno aluno) {
        super();
        this.codigo = codigo;
        this.nome = nome;
        this.aluno = aluno;
    }

    public void salvar() {
        //https://github.com/pardom/ActiveAndroid/issues/380#issuecomment-113716165
        if (Curso.getCursoByCode(this.codigo) == null)
            this.save();
    }

    public static Curso getRandom() {
        return new Select().from(Curso.class).orderBy("RANDOM()").executeSingle();
    }

    public static Curso getCursoByCode(int codigo) {
        return new Select()
                .from(Curso.class)
                .where("Codigo = ?", codigo)
                .orderBy("RANDOM()")
                .executeSingle();
    }

    public static List<Curso> getAll() {
        return new Select()
                .from(Curso.class)
                .orderBy("Nome ASC")
                .execute();
    }

    public static void removeAll() {
        new Delete().from(Curso.class).execute();
    }
}
