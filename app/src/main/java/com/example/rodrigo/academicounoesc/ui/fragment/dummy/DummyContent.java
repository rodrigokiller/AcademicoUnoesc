package com.example.rodrigo.academicounoesc.ui.fragment.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        addItem(new DummyItem("1", "ÁLGEBRA LINEAR E GEOMETRIA ANALÍTICA"));
        addItem(new DummyItem("2", "ALGORITMOS"));
        addItem(new DummyItem("3", "LABORATÓRIO DE PRÁTICA DE ALGORITMOS"));
        addItem(new DummyItem("4", "CIRCUITOS DIGITAIS"));
        addItem(new DummyItem("5", "INTRODUÇÃO À COMPUTAÇÃO"));
        addItem(new DummyItem("6", "METODOLOGIA CIENTÍFICA"));
        addItem(new DummyItem("7", "PRODUÇÃO DE TEXTOS"));
        addItem(new DummyItem("8", "CÁLCULO DIFERENCIAL E INTEGRAL I"));
        addItem(new DummyItem("9", "PROGRAMAÇÃO I"));
        addItem(new DummyItem("10", "ARQUITETURA DE COMPUTADORES"));
        addItem(new DummyItem("11", "MATEMÁTICA DISCRETA"));
        addItem(new DummyItem("12", "ÉTICA E SOCIEDADE"));
        addItem(new DummyItem("13", "PSICOLOGIA"));
        addItem(new DummyItem("14", "CÁLCULO DIFERENCIAL E INTEGRAL II"));
        addItem(new DummyItem("15", "PROBABILIDADE E ESTATÍSTICA"));
        addItem(new DummyItem("16", "PROGRAMAÇÃO II"));
        addItem(new DummyItem("17", "LABORATÓRIO DE PRÁTICA DE PROGRAMAÇÃO II"));
        addItem(new DummyItem("18", "REDES DE COMPUTADORES"));
        addItem(new DummyItem("19", "CÁLCULO NUMÉRICO"));
        addItem(new DummyItem("20", "SOCIOLOGIA"));
        addItem(new DummyItem("21", "MÉTODOS ESTOCÁSTICOS"));
        addItem(new DummyItem("22", "ESTRUTURA DE DADOS I"));
        addItem(new DummyItem("23", "SISTEMAS OPERACIONAIS"));
        addItem(new DummyItem("24", "LABORATÓRIO DE PRÁTICA DE ESTRUTURA DE DADOS I"));
        addItem(new DummyItem("25", "SISTEMAS DISTRIBUÍDOS"));
        addItem(new DummyItem("26", "TEORIA DOS GRAFOS"));
        addItem(new DummyItem("27", "LINGUAGENS FORMAIS"));
        addItem(new DummyItem("28", "ESTRUTURA DE DADOS II"));
        addItem(new DummyItem("29", "BANCO DE DADOS I"));
        addItem(new DummyItem("30", "LÓGICA"));
        addItem(new DummyItem("31", "INTELIGÊNCIA ARTIFICIAL"));
        addItem(new DummyItem("32", "LINGUAGENS DE PROGRAMAÇÃO"));
        addItem(new DummyItem("33", "COMPILADORES"));
        addItem(new DummyItem("34", "ENGENHARIA DE SOFTWARE I"));
        addItem(new DummyItem("35", "BANCO DE DADOS II"));
        addItem(new DummyItem("36", "METODOLOGIA DA PESQUISA"));
        addItem(new DummyItem("37", "TRABALHO DE CONCLUSÃO DE CURSO I"));
        addItem(new DummyItem("38", "MÉTODOS FORMAIS"));
        addItem(new DummyItem("39", "APLICAÇÕES DE INTELIGÊNCIA ARTIFICIAL"));
        addItem(new DummyItem("40", "ENGENHARIA DE SOFTWARE II"));
        addItem(new DummyItem("41", "INTERFACE HOMEM MÁQUINA"));
        addItem(new DummyItem("42", "COMPUTADOR E SOCIEDADE"));
        addItem(new DummyItem("43", "EMPREENDEDORISMO"));
        addItem(new DummyItem("44", "TRABALHO DE CONCLUSÃO DE CURSO II"));
        addItem(new DummyItem("45", "INTEGRAÇÃO HARDWARE E SOFTWARE"));
        addItem(new DummyItem("46", "ANÁLISE E COMPLEXIDADE DE ALGORITMOS"));
        addItem(new DummyItem("47", "AUDITORIA E SEGURANÇA DE SISTEMAS"));
        addItem(new DummyItem("48", "MODELAGEM E SIMULAÇÃO DE SISTEMAS"));
        addItem(new DummyItem("49", "PROGRAMAÇÃO AVANÇADA"));
        addItem(new DummyItem("50", "TRABALHO DE CONCLUSÃO DE CURSO III"));
        addItem(new DummyItem("51", "SISTEMAS DE INFORMAÇÃO"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
