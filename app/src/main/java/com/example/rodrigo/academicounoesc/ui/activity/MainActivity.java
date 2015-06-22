/*
 * Copyright 2015 Rudson Lima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.rodrigo.academicounoesc.ui.activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import br.liveo.Model.HelpLiveo;
import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.navigationliveo.NavigationLiveo;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.example.rodrigo.academicounoesc.R;
import com.example.rodrigo.academicounoesc.objetcts.*;
import com.example.rodrigo.academicounoesc.ui.fragment.IntegralizacaoFragment;
import com.example.rodrigo.academicounoesc.ui.fragment.MainFragment;
import com.example.rodrigo.academicounoesc.ui.fragment.Materias;
import com.example.rodrigo.academicounoesc.ui.fragment.ViewPagerFragment;

import java.util.List;

public class MainActivity extends NavigationLiveo implements OnItemClickListener {

    private HelpLiveo mHelpLiveo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // verifica se deve pedir dados de login ou não
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            return;
        }
        else
            checkLoginStatus();
    }

    @Override
    public void onInt(Bundle savedInstanceState) {



        // Pega o código do aluno das preferencias
        int codigo = 0;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getString("usuario", "").isEmpty())
            codigo = Integer.parseInt(prefs.getString("usuario", ""));

        // Seleciona do banco o aluno
        String nameDrawer = Aluno.getAlunoByCode(codigo) != null ? Aluno.getAlunoByCode(codigo).nome : "Error";
        List<Aluno> alunos = Aluno.getAll();


        // Seleciona do banco o curso
        String curseDrawer = Curso.getRandom() != null ? Curso.getRandom().nome : "Error";

        // User Information
        this.userName.setText(nameDrawer);
        this.userEmail.setText(curseDrawer);
        this.userPhoto.setImageResource(R.drawable.ic_avatar);
        this.userBackground.setImageResource(R.drawable.ic_user_background);

        // Creating items navigation
        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(R.string.academico), R.mipmap.library_24dp);
        mHelpLiveo.add(getString(R.string.consulta_notas), R.mipmap.library_books_24dp);
        mHelpLiveo.add(getString(R.string.integralizacao), R.mipmap.integralizacao_24dp);

        with(this).startingPosition(0) //Starting position in the list
                .addAllHelpItem(mHelpLiveo.getHelp())

                        //{optional} - List Customization "If you remove these methods and the list will take his white standard color"
                        //.selectorCheck(R.drawable.selector_check) //Inform the background of the selected item color
                        //.colorItemDefault(R.color.nliveo_blue_colorPrimary) //Inform the standard color name, icon and counter
                        //.colorItemSelected(R.color.nliveo_purple_colorPrimary) //State the name of the color, icon and meter when it is selected
                        //.backgroundList(R.color.nliveo_black_light) //Inform the list of background color
                        //.colorLineSeparator(R.color.nliveo_transparent) //Inform the color of the subheader line

                        //{optional} - SubHeader Customization
                .colorItemSelected(R.color.nliveo_green_colorPrimary)
                .colorNameSubHeader(R.color.nliveo_green_colorPrimary)
                        //.colorLineSeparator(R.color.nliveo_blue_colorPrimary)

                .footerItem(R.string.settings, R.mipmap.ic_settings_black_24dp)

                        //{optional} - Footer Customization
                        //.footerNameColor(R.color.nliveo_blue_colorPrimary)
                        //.footerIconColor(R.color.nliveo_blue_colorPrimary)
                        //.footerBackground(R.color.nliveo_white)

                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)
                .build();

        int position = this.getCurrentPosition();
        this.setElevationToolBar(position != 2 ? 15 : 0);

    }

    @Override
    public void onItemClick(int position) {
        Fragment mFragment;
        FragmentManager mFragmentManager = getSupportFragmentManager();

        switch (position){
            case 0:
                mFragment = Materias.newInstance(mHelpLiveo.get(position).getName());
                break;

            case 1:
                mFragment = new ViewPagerFragment();
                break;

            case 2:
                mFragment = IntegralizacaoFragment.newInstance("Materia", "asdf");
                Toast.makeText(this, mHelpLiveo.get(position).getName(), Toast.LENGTH_SHORT).show();
                break;

            default:
                mFragment = MainFragment.newInstance(mHelpLiveo.get(position).getName());
                break;
        }

        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        }

        setElevationToolBar(position != 1 ? 15 : 0);
    }

    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
        }
    };

    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "onClickPhoto :D", Toast.LENGTH_SHORT).show();
            closeDrawer();
        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            closeDrawer();
        }
    };

    public void checkLoginStatus(){
        /* Verifica se já tem parametro de login (usuario e senha)
         * - Ao clicar em atualizar vai verificar
         * - A cada momento da sincronização (se a sincronização falhar não faz nada)
         */

        // variaveis para tratamento login
        SharedPreferences prefs;
        SharedPreferences.Editor editor;

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        String user = prefs.getString("usuario", "");
        String pass = prefs.getString("senha", "");

        // Se tiver, tenta fazer login
        /* Erros do qual vai mostrar a tela de login:
         * - Não ter senha (aparece a tela só com usuário) mas apenas se clicar em atualizar
         * - Nâo ter dados gravados ainda no banco (primeiro login)
         * - Ao fazer o login e der erro (clicando em atualizar)
         */

        // Se não tem a senha
        if (pass.length() == 0){
            //TODO por enquanto será assim, mais pra frente vai ser assim: verifica se há dados já gravados no sqlite, se o cara não logar e sair da tela de login tudo bem :)
            Intent Login = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(Login);
        }

        //TODO verifica se tem internet, se tiver, faz um refresh nos dados
        //TODO se ao fazer refresh nos dados dar erro de senha ou usuario, tem q mostrar tela de login novamente
    }
}

