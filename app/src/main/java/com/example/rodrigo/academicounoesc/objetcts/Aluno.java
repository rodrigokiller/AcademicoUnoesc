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

@Table(name = "Aluno", id = "Codigo")
public class Aluno extends Model {
    @Column(name = "Nome")
    public String nome;

    @Column(name = "Codigo")
    public String codigo;

    @Column(name = "Email")
    public String email;

    @Column(name = "Apelido")
    public String apelido;

    public Aluno() {
        super();
    }

    public Aluno (String nome, String codigo, String email, String apelido) {
        super();
        this.nome = nome;
        this.codigo = codigo;
        this.email = email;
        this.apelido = apelido;
    }

    public void salvar() {
        //https://github.com/pardom/ActiveAndroid/issues/380#issuecomment-113716165
        if (Aluno.getAlunoByCode(Integer.parseInt(this.codigo)) == null)
            this.save();
    }

    public static Aluno getRandom() {
        return new Select().from(Aluno.class).orderBy("RANDOM()").executeSingle();
    }

    public static Aluno getAlunoByCode(int codigo) {
        return new Select()
                .from(Aluno.class)
                .where("Codigo = ?", codigo)
                .orderBy("RANDOM()")
                .executeSingle();
    }

    public static List<Aluno> getAll() {
        return new Select()
                .from(Aluno.class)
                .orderBy("Nome ASC")
                .execute();
    }

    public static void removeAll() {
        Curso.removeAll();
        new Delete().from(Aluno.class).execute();
    }
}