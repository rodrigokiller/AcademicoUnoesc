package com.example.rodrigo.academicounoesc;

import com.example.rodrigo.academicounoesc.objetcts.Aluno;
import com.example.rodrigo.academicounoesc.objetcts.AnoPeriodo;
import com.example.rodrigo.academicounoesc.objetcts.Curso;
import com.example.rodrigo.academicounoesc.objetcts.Materia;
import com.example.rodrigo.academicounoesc.objetcts.Nota;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Rodrigo on 21/06/2015.
 */
public class Navegar {

    Map<String, String> loginCookies = null;
    String username;
    String password;

    Aluno aluno = new Aluno();
    Curso curso = new Curso();
    AnoPeriodo anoPeriodo = new AnoPeriodo();
    Materia materia = new Materia();
    Nota nota = new Nota();

    public Navegar (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean doLogin(){
        Connection.Response res = null;
        try {
            res = Jsoup.connect("https://acad.unoesc.edu.br/academico/j_security_check")
                    .data("j_username", this.username, "j_password", this.password)
                    .method(Connection.Method.POST)
                    .execute();
            Document doc = res.parse();
            this.loginCookies = res.cookies();

            if (!doc.title().contains("Login")) {
                createAluno();
                createCursos();
            }

            return !doc.title().contains("Login");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void refresh() {
        createCursos();
    }

    public void createAluno() {
        String url = "https://acad.unoesc.edu.br/academico/portal/modules/comum/dadospessoais.jspa";

        try {
            if (loginCookies == null) doLogin();

            Document doc = Jsoup.connect(url).cookies(loginCookies).method(Connection.Method.GET).execute().parse();

            if (doc.title().contains("Login"))
                if (doLogin())
                    doc = Jsoup.connect(url).cookies(loginCookies).method(Connection.Method.GET).execute().parse();

            if (doc.title().contains("Dados Pessoais")) {
                // Nome
                Element elNome = doc.getElementById("logoff").getElementsByTag("h4").first();
                if (elNome != null){
                    String nome = elNome.text().substring(elNome.text().lastIndexOf("- ") + 1, elNome.text().length());
                    aluno.nome = nome;
                }

                // Apelido
                if (doc.select("#apelido") != null)
                    aluno.apelido = (doc.select("#apelido").attr("value"));

                // Código
                if (doc.select("#dpForm > table > tbody > tr > td > span").get(1) != null)
                    aluno.codigo = (doc.select("#dpForm > table > tbody > tr > td > span").get(1).text());

                // E-mail pega do endereço 1 que vem de um AJAX
                Document docEnd1 = Jsoup.connect("https://acad.unoesc.edu.br/academico/portal/modules/comum/dadosPessoaisEnd.jspa?action=getEnderecos&codPessoa=230361&codEndereco=1&_=")
                        .cookies(loginCookies).method(Connection.Method.POST).execute().parse();
                if (docEnd1.select("#enderecoEletronicoEnd") != null)
                    aluno.email = (docEnd1.select("#enderecoEletronicoEnd").attr("value"));

                aluno.salvar();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createCursos() {
        String url = "https://acad.unoesc.edu.br/academico/portal/modules/espacad/consultaNotas.jspa";

        try {
            if (loginCookies == null) doLogin();

            Document doc = Jsoup.connect(url).cookies(loginCookies).method(Connection.Method.GET).execute().parse();

            if (doc.title().contains("Login"))
                if (doLogin())
                    doc = Jsoup.connect(url).cookies(loginCookies).method(Connection.Method.GET).execute().parse();

            if (doc.title().contains("notas")) {
                //Cursos
                for (Element option : doc.select("#selAluCurso > option")){
                    // Nome
                    curso.nome = correctString(option.text().substring(option.text().indexOf("- ") + 2, option.text().indexOf("/")));

                    // Código
                    curso.codigo = Integer.parseInt(option.text().substring(0, option.text().indexOf("-")-1));

                    // Aluno
                    curso.aluno = aluno;

                    curso.salvar();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO melhorar este método de converter string toda em maiúscula para primeira apenas (ta uma gambi)
    public String correctString(String value){

        StringBuffer res = new StringBuffer();
        String[] matches = new String[] {"do", "da", "dos", "das", "e", "para"};
        String[] strArr = value.toLowerCase().split(" ");

        for (String str : strArr) {
            boolean encontrou = false;
            for (String s : matches)
            {
                if (str.contains(s))
                    encontrou = true;
            }
            char[] stringArray = str.trim().toCharArray();
            if (!encontrou)
                stringArray[0] = Character.toUpperCase(stringArray[0]);
            str = new String(stringArray);

            res.append(str).append(" ");
        }

        return res.toString().trim();
    }
}
