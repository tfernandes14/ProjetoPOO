
package viagens_poo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Viagens_POO extends JFrame{
    //Frame Components
    private final JLabel label_utilizador, labelPrecoMaximo, labelGrauAcademico, label_utilizador2;
    private JLabel labelDetalhes, labelEscolha,label_budget, labelLocal1, labelLocal2, labelLocal3, labelPontosDisponiveis, labelPontosEscolhidos, labelViagensGeradas;
    private final JButton registar,entrar;
    private JButton gerar, sair, atualizarLocais, alterarBudget;
    private JButton addPonto;
    private final JTextField nome,nomeLogin;
    private JTextField budgetAluno;
    private JComboBox<Local> prefLocal1;
    private JComboBox<String> prefLocal2;
    private JComboBox<Local> prefLocal3;
    private JComboBox<String> grauAcademico;
    private JComboBox<String> escolherPrefLocal,escolherPrefPonto;
    private JComboBox<String> ordenar;
    private final JPanel main_panel;
    private final JSpinner defineBudget;
    private JScrollPane pontosDisponiveis, pontosUtilizador, viagensCriadas, melhoresLocais, melhoresPontos, viagemCompleta;
    private final JSeparator separateLogin;
    private JFrame frame;
    private JList listaLocais, listaPontosEscolhidos, listaPontosPossiveis, listaViagens;
    private JTable locaisVotados, pontosVotados;
    private JTextArea viagemEscolhida;
   
    //Logical Data
    private ArrayList<Local> locaisEscolhidos = new ArrayList<>();
    private ArrayList<PontoInteresse> pontosPossiveis = new ArrayList<>(), pontosEscolhidos = new ArrayList<>();
    private ArrayList<Viagem> viagensGeradas;
    private Viagem viagem;
    private Aluno aluno;
    private ArrayList<String> pontosInteresse = new ArrayList<String>();
    private String[] pontosInteresseFixes = new String[12];

    Handle_Ficheiros f = new Handle_Ficheiros();
    ArrayList<Aluno> alunos = f.getListAlunoObj();
    ArrayList<Local> locais = f.setFichLocaisObj();
    int[][] distancias = f.setFichDistanciasObj();

    // Array com os locais
    private String[] locaisComboBox;
    private String[] locaisArray;

    public Viagens_POO(){
        if (alunos == null){
            alunos = new ArrayList<>();
        }
        if (locais == null){
            locais = f.getListLocaisTxt();
        }
        if (distancias == null){
            distancias = f.getDistancias();
        }

        locaisArray = new String[80];
        int ind = 0;
        for (int i = 0; i < locais.size(); i++) {
            ArrayList<PontoInteresse> pontos = locais.get(i).getPontos();
            for (int j = 0; j < pontos.size(); j++){
                locaisArray[ind] = pontos.get(j).getNome();
                ind++;
            }
        }
        locaisComboBox = new String[locais.size()];
        for(int i = 0; i < locais.size(); i++){
            locaisComboBox[i] = locais.get(i).toString();
        }

        frame = new JFrame();
        //Parte inicial
        main_panel = new JPanel();
        main_panel.setLayout(null);

        registar = new JButton("REGISTAR");
        registar.setBounds(140,270,140,40);
        registar.addActionListener(new BotaoRegistarActionListener());

        label_utilizador = new JLabel("Nome do Utilizador:");
        label_utilizador.setBounds(20,60,120,30);
        nome = new JTextField(50);
        nome.setBounds(140, 60, 175, 30);

        labelPrecoMaximo = new JLabel("Preço Máximo:");
        labelPrecoMaximo.setBounds(20, 110, 120, 30);
        SpinnerModel sm = new SpinnerNumberModel(1, 1, 500, 1);
        defineBudget = new JSpinner(sm);
        defineBudget.setBounds(140, 110, 100, 30);

        labelGrauAcademico = new JLabel("Tipo de matricula:");
        labelGrauAcademico.setBounds(20, 160, 120, 30);


        String[] possibilidades = {null,"Licenciatura", "Mestrado"};
        grauAcademico = new JComboBox<>(possibilidades);
        grauAcademico.setBounds(140, 160, 180, 30);
        grauAcademico.addItemListener(new GrauAcademicoListener());

        separateLogin = new JSeparator(SwingConstants.VERTICAL);
        separateLogin.setBounds(400, 20, 5,400);

        label_utilizador2 = new JLabel("Nome Utilizador:");
        label_utilizador2.setBounds(420, 200, 100, 30);
        nomeLogin = new JTextField(30);
        nomeLogin.setBounds(520, 200, 200, 30);

        entrar = new JButton("ENTRAR");
        entrar.setBounds(500, 240, 100, 30);
        entrar.addActionListener(new BotaoEntrarActionListener());

        //adicionar os components ao panel
        main_panel.add(registar);
        main_panel.add(nomeLogin);
        main_panel.add(label_utilizador);
        main_panel.add(nome);
        main_panel.add(entrar);
        main_panel.add(label_utilizador2);
        main_panel.add(labelPrecoMaximo);
        main_panel.add(defineBudget);
        main_panel.add(grauAcademico);
        main_panel.add(labelGrauAcademico);
        main_panel.add(separateLogin);
        frame.add(main_panel);
        frame.setSize(800, 500);
        
        frame.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            sairSalvando();
        }
        });
        
        
        frame.setTitle("Login/Registar");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
 

    //inner class comboBox grauAcademico
    private class GrauAcademicoListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String item = (String) e.getItem();
                if(item.equals("Licenciatura")){
                    if(labelEscolha!= null && escolherPrefLocal!=null){
                        main_panel.remove(labelEscolha);
                        main_panel.remove(escolherPrefLocal);
                    }

                    //fazer aparecer opção para poder escolher ponto Hot
                    labelEscolha = new JLabel("Escolha um Ponto HOT:");
                    labelEscolha.setBounds(20, 195, 200, 30);

                    escolherPrefPonto = new JComboBox(locaisArray);
                    escolherPrefPonto.setBounds(20, 220, 200, 30);

                    main_panel.add(labelEscolha);
                    main_panel.add(escolherPrefPonto);
                    main_panel.updateUI();
                }else if(item.equals("Mestrado")){
                    if(labelEscolha!= null && escolherPrefPonto != null){
                        main_panel.remove(labelEscolha);
                        main_panel.remove(escolherPrefPonto);
                    }
                    //fazer opção para escolher local a evitar
                    labelEscolha = new JLabel("Escolha o local que quer evitar");
                    labelEscolha.setBounds(20, 195, 200, 30);

                    escolherPrefLocal = new JComboBox(locaisComboBox);
                    escolherPrefLocal.setBounds(20, 220, 200, 30);

                    main_panel.add(escolherPrefLocal);
                    main_panel.add(labelEscolha);
                    main_panel.updateUI();
                }
            }
        }

    
    }

    //inner class do botão Registar
    private class BotaoRegistarActionListener implements ActionListener{
            
        @Override
    public void actionPerformed(ActionEvent e){
        String aux = nome.getText();
        // Verificacao do utilizador
        if (!aux.equals("")) {
            // Verificacao se tem algum grau academico selecionado
            if (grauAcademico.getSelectedItem() != null) {
                // Verificacao se existem alunos ou nao
                if (!alunos.isEmpty()) {
                    int conta = 0;
                    int indice = 0;
                    for (int i = 0; i < alunos.size(); i++) {
                        if (aux.equals(alunos.get(i).getNome())) {
                            conta = 1;
                            indice = i;
                            break;
                        }
                    }
                    // Se for conta = 1, entao o utilizador ja existe
                    if (conta == 0) {
                        String x = grauAcademico.getSelectedItem().toString();
                        if (x.equals("Licenciatura")) {
                            String pontoFav = (String) escolherPrefPonto.getSelectedItem();
                            PontoInteresse pontoFavObj = null;
                            aluno = alunos.get(indice);
                            int help = 0;
                            
                            for (int i = 0; i < locais.size(); i++) {
                                for (int j = 0; j < 4; j++){
                                    if (pontoFav.equals(locais.get(i).getPontos().get(j).getNome())){
                                        pontoFavObj = locais.get(i).getPontos().get(j);
                                        help = 1;
                                        break;
                                    }
                                }
                                if (help == 1){
                                    break;
                                }
                            }
                            Licenciatura a = new Licenciatura(nome.getText(), "Licenciatura", (Integer) defineBudget.getValue(), pontoFavObj);
                      
                            alunos.add(a);
                            pontoFavObj.setContador(2);
                            
                            System.out.println(a.getNome() + " / " + a.getGrauAcademico() + " / " + a.getCustoMax() + " / " + a.getPontoHot());
                            JOptionPane.showMessageDialog(null, "Utilizador registado com sucesso!", "Registo efetuado", JOptionPane.PLAIN_MESSAGE);
                        } else if (x.equals("Mestrado")) {
                            String localLixo = (String) escolherPrefLocal.getSelectedItem();
                            Local localLixoObj = null;
                            for (int i = 0; i < locais.size(); i++){
                                if (localLixo.equals(locais.get(i).getNome())){
                                    localLixoObj = locais.get(i);
                                    break;
                                }
                            }
                            Mestrado a = new Mestrado(nome.getText(), "Mestrado", (Integer) defineBudget.getValue(), localLixoObj);
                            alunos.add(a);
                            localLixoObj.setContador(-1);
                            System.out.println(a.getNome() + " / " + a.getGrauAcademico() + " / " + a.getCustoMax() + " / " + a.getLocalEvitar());
                            JOptionPane.showMessageDialog(null, "Utilizador registado com sucesso!", "Registo efetuado", JOptionPane.PLAIN_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "O utilizador já existe, tente outro nome de utilizador", "ERRO", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    String x = grauAcademico.getSelectedItem().toString();
                    if (x.equals("Licenciatura")) {
                        String pontoFav = (String) escolherPrefPonto.getSelectedItem();
                        PontoInteresse pontoFavObj = null;
                        //aluno = alunos.get(0);
                        int help = 0;

                        for (int i = 0; i < locais.size(); i++) {
                            for (int j = 0; j < 4; j++){
                                if (pontoFav.equals(locais.get(i).getPontos().get(j).getNome())){
                                    pontoFavObj = locais.get(i).getPontos().get(j);
                                    help = 1;
                                    break;
                                }
                            }
                            if (help == 1){
                                break;
                            }
                        }
                        Licenciatura a = new Licenciatura(nome.getText(), "Licenciatura", (Integer) defineBudget.getValue(), pontoFavObj);
                        alunos.add(a);
                        System.out.println(a.getNome() + " / " + a.getGrauAcademico() + " / " + a.getCustoMax() + " / " + a.getPontoHot());
                        JOptionPane.showMessageDialog(null, "Utilizador registado com sucesso!", "Registo efetuado", JOptionPane.PLAIN_MESSAGE);
                    } else if (x.equals("Mestrado")) {
                        String localLixo = (String) escolherPrefLocal.getSelectedItem();
                        Local localLixoObj = null;
                        for (int i = 0; i < locais.size(); i++){
                            if (localLixo.equals(locais.get(i).getNome())){
                                localLixoObj = locais.get(i);
                                break;
                            }
                        }
                        Mestrado a = new Mestrado(nome.getText(), "Mestrado", (Integer) defineBudget.getValue(), localLixoObj);
                        alunos.add(a);
                        System.out.println(a.getNome() + " / " + a.getGrauAcademico() + " / " + a.getCustoMax() + " / " + a.getLocalEvitar());
                        JOptionPane.showMessageDialog(null, "Utilizador registado com sucesso!", "Registo efetuado", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Insira um grau académico", "ERRO", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Insira um nome de utilizador", "ERRO", JOptionPane.PLAIN_MESSAGE);
        }
    }

}
    
    //inner class do botão entrar
    public class BotaoEntrarActionListener implements ActionListener{
        @Override 
        public void actionPerformed(ActionEvent e){
            String aux = nomeLogin.getText();
            if (!aux.equals("")){
                int conta = 0;
                int indice = 0;
                for (int i = 0; i < alunos.size(); i++){
                    if (aux.equals(alunos.get(i).getNome())){
                        conta = 1;
                        indice = i;
                        break;
                    }
                }
                ArrayList<Local> locaisOrdenados = (ArrayList<Local>) locais.clone();
                Collections.sort(locaisOrdenados);
                Collections.reverse(locaisOrdenados);
                
                ArrayList<PontoInteresse> pontosOrdenados = new ArrayList<>();
                for(Local l : locais){
                    pontosOrdenados.addAll(l.getPontos());
                }
                Collections.sort(pontosOrdenados);
                Collections.reverse(pontosOrdenados);
                
                if (conta == 1){
                    main_panel.removeAll();
                    
                    if ((alunos.get(indice).getGrauAcademico()).equals("Mestrado")){
                        aluno = alunos.get(indice);
                        ArrayList<String> ajuda = new ArrayList<>(Arrays.asList(locaisComboBox));
                        ajuda.remove(alunos.get(indice).getLocalEvitar());
                        String[] locaisComboBoxAtualizada = new String[ajuda.size()];
                        ajuda.toArray(locaisComboBoxAtualizada);
                         
                        frame.setSize(1080,720);
                        frame.setLocationRelativeTo(null);
                        labelLocal1 = new JLabel("Escolha o primeiro local a visitar:");
                        labelLocal1.setBounds(20, 65, 300, 10);
                        prefLocal1 = new JComboBox(locaisComboBoxAtualizada);
                        prefLocal1.setBounds(20, 80, 300, 20);

                        labelLocal2 = new JLabel("Escolha o segundo local a visitar:");
                        labelLocal2.setBounds(20, 115, 300, 10);
                        prefLocal2 = new JComboBox<>(locaisComboBoxAtualizada);
                        prefLocal2.setBounds(20, 130, 300, 20);

                        labelLocal3 = new JLabel("Escolha o terceiro local a visitar:");
                        labelLocal3.setBounds(20, 165, 300, 10);
                        prefLocal3 = new JComboBox(locaisComboBoxAtualizada);
                        prefLocal3.setBounds(20, 180, 300, 20);

                        // Fazer a confirmação se nenhum dos locais é repetido
                        atualizarLocais = new JButton("ATUALIZAR LOCAIS");
                        atualizarLocais.setBounds(55, 210, 200, 20);
                        atualizarLocais.addActionListener(new BotaoAddLocalActionListener());

                        labelPontosDisponiveis = new JLabel("Pontos de Interesse disponiveis:");
                        labelPontosDisponiveis.setBounds(20, 240, 200, 15);
                        pontosDisponiveis = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        pontosDisponiveis.setBounds(20, 260, 300, 140);

                        //end
                        //Mostra as opçoes/Parte Final

                        labelPontosEscolhidos = new JLabel("Pontos de Interesse escolhidos:");
                        labelPontosEscolhidos.setBounds(340, 65, 200, 10);
                        pontosUtilizador = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        pontosUtilizador.setBounds(340, 80, 300, 130);

                        labelViagensGeradas = new JLabel("Ordenar Viagens Geradas por:");
                        labelViagensGeradas.setBounds(70, 435, 200, 15);//Alterar as localização disto
                        viagensCriadas = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        viagensCriadas.setBounds(60, 460, 900, 100);

                        String[] ordem = {"crescente","decrescente"};
                        ordenar = new JComboBox<>(ordem);
                        ordenar.setBounds(250, 435, 150, 20);
                        ordenar.addItemListener(new OrdemViagemActionListener());
 

                        gerar = new JButton("Gerar Viagens");
                        gerar.setBounds(340, 210, 300, 20);
                        gerar.addActionListener(new BotaoGerarActionPerformed());
                        budgetAluno = new JTextField(""+aluno.getCustoMax());
                        budgetAluno.setBounds(480, 300, 100, 30);
                        budgetAluno.setEditable(false);

                        label_budget = new JLabel("Budget Disponivel");
                        label_budget.setBounds(340, 300, 150, 30);

                        alterarBudget = new JButton("Alterar Budget");
                        alterarBudget.setBounds(450, 350, 150, 20);
                        alterarBudget.addActionListener(new AlterarBudgetActionListener());
                        //Locais e Pontos de Interesse mais escolhidos
                        JList listaLocaisOrdenados = new JList(locaisOrdenados.toArray());
                        JList listaPontosOrdenados = new JList(pontosOrdenados.toArray());
                        
                        JLabel label_melhoresLocais = new JLabel("Locais por ordem decrescente de votos: ");
                        label_melhoresLocais.setBounds(670, 60, 250, 20);
                        main_panel.add(label_melhoresLocais);
                        melhoresLocais = new JScrollPane(listaLocaisOrdenados,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        melhoresLocais.setBounds(670, 80, 300, 150);
                        
                        JLabel label_melhoresPontos = new JLabel("Pontos por ordem decescente de votos: ");
                        label_melhoresPontos.setBounds(670, 230, 250, 20);
                        main_panel.add(label_melhoresPontos);
                        melhoresPontos = new JScrollPane(listaPontosOrdenados,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        melhoresPontos.setBounds(670, 250, 300, 150);

                        labelDetalhes = new JLabel("Detalhes da Viagem:");
                        labelDetalhes.setBounds(70,560,120,20);
                        viagemCompleta = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        viagemCompleta.setBounds(60, 580, 900, 100);

                        JButton escolherViagem = new JButton("Selecionar viagem");
                        escolherViagem.setBounds(760, 435, 200, 20);
                        escolherViagem.addActionListener(new EscolherViagemActionListener());
                        main_panel.add(escolherViagem);
                        
                        main_panel.add(label_budget);
                        main_panel.add(budgetAluno);
                        main_panel.add(labelLocal1);
                        main_panel.add(prefLocal1);
                        main_panel.add(labelLocal2);
                        main_panel.add(prefLocal2);
                        main_panel.add(labelLocal3);
                        main_panel.add(prefLocal3);
                        main_panel.add(atualizarLocais);
                        main_panel.add(alterarBudget);
                        main_panel.add(ordenar);
                        main_panel.add(labelDetalhes);
                        main_panel.add(labelPontosEscolhidos);
                        main_panel.add(pontosUtilizador);
                        main_panel.add(labelViagensGeradas);
                        main_panel.add(viagensCriadas);
                        main_panel.add(gerar);
                        main_panel.add(viagemCompleta);
                        main_panel.add(labelPontosDisponiveis);
                        main_panel.add(pontosDisponiveis);

                        main_panel.add(melhoresLocais);
                        main_panel.add(melhoresPontos);

                        main_panel.updateUI();
                    }
                    if ((alunos.get(indice).getGrauAcademico()).equals("Licenciatura")){

                        aluno = alunos.get(indice);
                        String[] localObrigatorio = new String[1];                     
                        if(aluno.getPontoHot()!= null){
                            int batata = 0;
                            for(int i =0; i<locais.size();i++){
                                for(int j=0; j<locais.get(i).getPontos().size();j++){
                                    if((locais.get(i).getPontos().get(j).getNome()).equals(aluno.getPontoHot().nome)){
                                        localObrigatorio[0] = locais.get(i).getNome();
                                        batata = 1;
                                        break;
                                    }
                                }
                                if (batata == 1){
                                    break;
                                }
                            }
                        }
                        frame.setSize(1080,720);
                        frame.setLocationRelativeTo(null);
                        labelLocal1 = new JLabel("Escolha o primeiro local a visitar:");
                        labelLocal1.setBounds(20, 65, 300, 10);
                        prefLocal1 = new JComboBox(localObrigatorio);
                        prefLocal1.setBounds(20, 80, 300, 20);
                        prefLocal1.setEditable(false);

                        labelLocal2 = new JLabel("Escolha o segundo local a visitar:");
                        labelLocal2.setBounds(20, 115, 300, 10);
                        prefLocal2 = new JComboBox<>(locaisComboBox);
                        prefLocal2.setBounds(20, 130, 300, 20);

                        labelLocal3 = new JLabel("Escolha o terceiro local a visitar:");
                        labelLocal3.setBounds(20, 165, 300, 10);
                        prefLocal3 = new JComboBox(locaisComboBox);
                        prefLocal3.setBounds(20, 180, 300, 20);

                        // Fazer a confirmação se nenhum dos locais é repetido
                        atualizarLocais = new JButton("Atualizar Locais");
                        atualizarLocais.setBounds(55, 210, 200, 20);
                        atualizarLocais.addActionListener(new BotaoAddLocalActionListener());

                        labelPontosDisponiveis = new JLabel("Pontos de Interesse disponiveis:");
                        labelPontosDisponiveis.setBounds(20, 240, 200, 15);
                        pontosDisponiveis = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        pontosDisponiveis.setBounds(20, 260, 300, 140);

                        //end
                        //Mostra as opçoes/Parte Final

                        labelPontosEscolhidos = new JLabel("Pontos de Interesse escolhidos:");
                        labelPontosEscolhidos.setBounds(340, 65, 200, 10);
                        pontosUtilizador = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        pontosUtilizador.setBounds(340, 80, 300, 130);

                        labelViagensGeradas = new JLabel("Ordenar Viagens Geradas por:");
                        labelViagensGeradas.setBounds(70, 435, 200, 15);//Alterar as localização disto
                        viagensCriadas = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        viagensCriadas.setBounds(60, 460, 900, 100);

                        String[] ordem = {"crescente","decrescente"};
                        ordenar = new JComboBox<>(ordem);
                        ordenar.setBounds(250, 435, 150, 20);
                        ordenar.addItemListener(new OrdemViagemActionListener());
 

                        gerar = new JButton("Gerar Viagens");
                        gerar.setBounds(340, 210, 300, 20);
                        gerar.addActionListener(new BotaoGerarActionPerformed());
                        sair = new JButton("SAIR");

                        budgetAluno = new JTextField(""+aluno.getCustoMax());
                        budgetAluno.setBounds(480, 300, 100, 30);
                        budgetAluno.setEditable(false);

                        label_budget = new JLabel("BUDGET DISPONIVEL");
                        label_budget.setBounds(340, 300, 150, 30);

                        alterarBudget = new JButton("Alterar Budget");
                        alterarBudget.setBounds(450, 350, 150, 20);
                        alterarBudget.addActionListener(new AlterarBudgetActionListener());

                        //Locais e Pontos de Interesse mais escolhidos
                        JLabel label_melhoresLocais = new JLabel("Locais por ordem decrescente de votos: ");
                        label_melhoresLocais.setBounds(670, 60, 250, 20);
                        main_panel.add(label_melhoresLocais);
                        JList listaLocaisOrdenados = new JList(locaisOrdenados.toArray());
                        melhoresLocais = new JScrollPane(listaLocaisOrdenados, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        melhoresLocais.setBounds(670, 80, 300, 150);
                        
                        JLabel label_melhoresPontos = new JLabel("Pontos pro ordem decrescente de votos: ");
                        label_melhoresPontos.setBounds(670, 230, 250, 20);
                        main_panel.add(label_melhoresPontos);
                        JList listaPontosOrdenados = new JList(pontosOrdenados.toArray());
                        melhoresPontos = new JScrollPane(listaPontosOrdenados, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        melhoresPontos.setBounds(670, 250, 300, 150);

                        labelDetalhes = new JLabel("Detalhes da Viagem:");
                        labelDetalhes.setBounds(70,560,120,20);
                        viagemCompleta = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        viagemCompleta.setBounds(60, 580, 900, 100);
                        
                        JButton escolherViagem = new JButton("Selecionar viagem");
                        escolherViagem.setBounds(760, 435, 200, 20);
                        escolherViagem.addActionListener(new EscolherViagemActionListener());
                        main_panel.add(escolherViagem);
                        main_panel.add(label_budget);
                        main_panel.add(budgetAluno);
                        main_panel.add(labelLocal1);
                        main_panel.add(prefLocal1);
                        main_panel.add(labelLocal2);
                        main_panel.add(prefLocal2);
                        main_panel.add(labelLocal3);
                        main_panel.add(prefLocal3);
                        main_panel.add(atualizarLocais);
                        main_panel.add(alterarBudget);
                        main_panel.add(ordenar);
                        main_panel.add(labelDetalhes);
                        main_panel.add(labelPontosEscolhidos);
                        main_panel.add(pontosUtilizador);
                        main_panel.add(labelViagensGeradas);
                        main_panel.add(viagensCriadas);
                        main_panel.add(gerar);
                        main_panel.add(viagemCompleta);
                        main_panel.add(labelPontosDisponiveis);
                        main_panel.add(pontosDisponiveis);

                        main_panel.add(melhoresLocais);
                        main_panel.add(melhoresPontos);

                        main_panel.updateUI();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Utilizador não existe, faça o registo", "Utilizador não encontrado", JOptionPane.PLAIN_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Insira um nome de utilizador", "ERRO", JOptionPane.PLAIN_MESSAGE);
            }
        }
    
    }
    
    //inner class do botao gerar viagens
    private class BotaoGerarActionPerformed implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if (!pontosEscolhidos.isEmpty()){
                System.out.println("LOCAIS ESCOLHIDOS: ");
                for (Local l : locaisEscolhidos) {
                    System.out.println(l.toString());
                }
                System.out.println();
                System.out.println("PONTOS DE INTERESSE DISPONIVEIS: ");
                for (PontoInteresse p : pontosPossiveis) {
                    System.out.println(p.toString());
                }
                System.out.println();
                System.out.println("PONTOS DE INTERESSE ESCOLHIDOS: ");
                for (PontoInteresse p : pontosEscolhidos) {
                    System.out.println(p.toString());
                }
                
                viagensGeradas = new ArrayList<Viagem>();
                viagensGeradas = gerarViagens(aluno,locaisEscolhidos, pontosPossiveis,pontosEscolhidos);
                ArrayList<String> viagensAL = new ArrayList<>();
                if(viagensGeradas != null){
                    for (Viagem v : viagensGeradas){
                        viagensAL.add(v.toString());
                    }
                    
                    listaViagens = new JList(viagensAL.toArray());
                    main_panel.remove(viagensCriadas);
                    viagensCriadas = new JScrollPane(listaViagens, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    viagensCriadas.setBounds(60, 460, 900, 100);
                    main_panel.add(viagensCriadas);
                    main_panel.updateUI();
                    
                }else{
                    //Mostrar mensagem a dizer que não há viagens possiveis! Limpar parâmetros
                    JOptionPane.showMessageDialog(null, "Não foi possível gerar nenhuma viagem com as preferencias indicadas", "ERRO", JOptionPane.PLAIN_MESSAGE);

                }

            }
            else{
                JOptionPane.showMessageDialog(null, "Tem de ter pelo menos um ponto de interesse escolhido!", "ERRO", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    
    //inner class do botao atualizar locais
    private class BotaoAddLocalActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            pontosInteresse.clear();
            locaisEscolhidos.clear();
            pontosPossiveis.clear();
            pontosEscolhidos.clear();
            String local1 = prefLocal1.getSelectedItem().toString();
            String local2 = prefLocal2.getSelectedItem().toString();
            String local3 = prefLocal3.getSelectedItem().toString();
            if (!local1.equals(local2) && !local1.equals(local3) && !local2.equals(local3)){
                int[] indices = {0,0,0};
                for (int i = 0; i < 20; i++){
                    if (local1.equals(locais.get(i).getNome())) {
                        indices[0] = i;
                        locaisEscolhidos.add(locais.get(i));
                        for (int j = 0; j < 4; j++) {
                            pontosPossiveis.add(locais.get(i).getPontos().get(j));
                        }
                    }
                    if (local2.equals(locais.get(i).getNome())) {
                        indices[1] = i;
                        locaisEscolhidos.add(locais.get(i));
                        for (int j = 0; j < 4; j++) {
                            pontosPossiveis.add(locais.get(i).getPontos().get(j));
                        }
                    }
                    if (local3.equals(locais.get(i).getNome())) {
                        indices[2] = i;
                        locaisEscolhidos.add(locais.get(i));
                        for (int j = 0; j < 4; j++) {
                            pontosPossiveis.add(locais.get(i).getPontos().get(j));
                        }
                    }
                }

                for (PontoInteresse p : pontosPossiveis) {
                    System.out.println(p.getNome());
                }

                int k = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 4; j++) {
                        pontosInteresseFixes[k] = locais.get(indices[i]).getPontos().get(j).getNome();
                        k++;
                    }
                }
                main_panel.remove(pontosDisponiveis);
                addPonto = new JButton("Adicionar local");
                addPonto.setBounds(80, 400, 150, 20);
                addPonto.addActionListener(new BotaoAddPontosActionPerformed());
                listaPontosPossiveis = new JList(pontosInteresseFixes);
                pontosDisponiveis = new JScrollPane(listaPontosPossiveis,  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                pontosDisponiveis.setBounds(20, 260, 300, 140);
                main_panel.remove(pontosUtilizador);

                main_panel.add(pontosDisponiveis);
                main_panel.add(addPonto);
                main_panel.updateUI();
            }
            else{
                JOptionPane.showMessageDialog(null, "Os locais têm de ser todos diferentes", "ERRO", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
    
    private class EscolherViagemActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String ficheiro = JOptionPane.showInputDialog(null, "Insira o nome do ficheiro onde quer guardar a sua viagem!", "Guardar Viagem", JOptionPane.QUESTION_MESSAGE);
            String mensagem = "";
            if(listaViagens.getSelectedValue()!= null){
                int indice = listaViagens.getSelectedIndex();
                for(int i =0;i<viagensGeradas.get(indice).getLocais().size();i++){
                    mensagem = "\n"+mensagem + viagensGeradas.get(indice).getLocais().get(i).getNome()+":\n";
                    for(int j=0; j<viagensGeradas.get(indice).getPontosDeInteresse().size();j++){
                        for (int k = 0; k < viagensGeradas.get(indice).getLocais().get(i).getPontos().size(); k++) {
                            if(viagensGeradas.get(indice).getLocais().get(i).getPontos().get(k).getNome().equals(viagensGeradas.get(indice).getPontosDeInteresse().get(j).getNome())){
                                String temp = viagensGeradas.get(indice).getPontosDeInteresse().get(j).toString();
                                mensagem += temp;
                            }
                        }
                    }
                }
                System.out.println(mensagem);
                viagemEscolhida = new JTextArea(mensagem);
                main_panel.remove(viagemCompleta);
                viagemCompleta = new JScrollPane(viagemEscolhida, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                viagemCompleta.setBounds(60, 580, 900, 100);
                main_panel.add(viagemCompleta);
                f.escreveFinal(ficheiro, mensagem);
                main_panel.updateUI();
            }else{
                JOptionPane.showMessageDialog(null, "É preciso seleconar uma viagem", "ERRO", JOptionPane.PLAIN_MESSAGE);
            }
        }
        
    }

    // inner class para o botao de adicionar pontos de interesse
    private class BotaoAddPontosActionPerformed implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if (listaPontosPossiveis.getSelectedValue() != null) {
                String selected = listaPontosPossiveis.getSelectedValue().toString();
                pontosInteresse.add(selected);
                for (int i = 0; i < pontosPossiveis.size(); i++) {
                    if (selected.equals(pontosPossiveis.get(i).getNome())){
                        pontosEscolhidos.add(pontosPossiveis.get(i));
                    }
                }

                for (PontoInteresse p : pontosEscolhidos) {
                    System.out.println(p.getNome());
                }

                int aux = listaPontosPossiveis.getSelectedIndex();

                ArrayList<String> pontosAjuda = new ArrayList<>(Arrays.asList(pontosInteresseFixes));
                pontosAjuda.remove(aux);
                pontosAjuda.toArray(pontosInteresseFixes);

                main_panel.remove(pontosDisponiveis);
                listaPontosPossiveis = new JList(pontosInteresseFixes);
                pontosDisponiveis = new JScrollPane(listaPontosPossiveis,  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                pontosDisponiveis.setBounds(20, 260, 300, 140);
                main_panel.add(pontosDisponiveis);

                main_panel.remove(pontosUtilizador);
                listaPontosEscolhidos = new JList(pontosInteresse.toArray());
                pontosUtilizador = new JScrollPane(listaPontosEscolhidos, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                pontosUtilizador.setBounds(340, 80, 300, 130);
                main_panel.add(pontosUtilizador);
                main_panel.updateUI();
            }
            else{
                JOptionPane.showMessageDialog(null, "Selecione um local", "ERRO", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
    
    private class OrdemViagemActionListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
           if(e.getStateChange() == ItemEvent.SELECTED){
               String tipo_Ordem = (String) ordenar.getSelectedItem();
               ArrayList<Viagem> ordenarViagens = (ArrayList<Viagem>) viagensGeradas;
               Collections.sort(ordenarViagens);
               if(tipo_Ordem.equals("decrescente")){
                   main_panel.remove(viagensCriadas);
                   Collections.reverse(ordenarViagens);
                   JList listaOrdenada = new JList(ordenarViagens.toArray());
                   viagensCriadas = new JScrollPane(listaOrdenada, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                   viagensCriadas.setBounds(60, 460, 900, 100);
                   main_panel.add(viagensCriadas);
                   main_panel.updateUI();
               }else{
                   main_panel.remove(viagensCriadas);
                   JList listaOrdenada = new JList(ordenarViagens.toArray());
                   
                   viagensCriadas = new JScrollPane(listaOrdenada, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                   viagensCriadas.setBounds(60, 460, 900, 100);
                   main_panel.add(viagensCriadas);
                   main_panel.updateUI();
               }
           }
        }
    
    }
    
    //Botão para alterar o budget (falta fazer as proteções)
    private class AlterarBudgetActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

            SpinnerNumberModel modelo = new SpinnerNumberModel(1, 1, 1000, 1);
            JSpinner spinner = new JSpinner(modelo);
            int val = JOptionPane.showOptionDialog(null, spinner, "Introduza novo valor para o orçamento", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            
            if(val == JOptionPane.OK_OPTION){
                aluno.setCustoMax((Integer) spinner.getValue());

                main_panel.remove(budgetAluno);
                budgetAluno = new JTextField(""+ (Integer) spinner.getValue());
                budgetAluno.setBounds(480, 300, 100, 30);

                main_panel.add(budgetAluno);
                main_panel.updateUI();
            }
            
        }
    }

    public static void main(String[] args) {
        
        Viagens_POO viagens_POO = new Viagens_POO();
    }
    
    
    ArrayList<Viagem> gerarViagens(Aluno aluno, ArrayList<Local> locais, ArrayList<PontoInteresse> pontosPoss, ArrayList<PontoInteresse> pontosEscolhidos){
        ArrayList<Viagem> auxiliar = new ArrayList<>();
        boolean check = false;
        
        ArrayList<PontoInteresse> pontos_Visitar_aux = new ArrayList<>();
        //verifica se escolheu algum museu
        try{
            Viagem temp = new Viagem(locais,pontosEscolhidos, distancias);
            for(PontoInteresse pi : pontosEscolhidos){
            if(pi.getTipo().equals("Museu")){
                check = true;
            }
        }
        
        if (check == true){
            temp = new Viagem(locais, pontosEscolhidos,distancias);
            auxiliar.add(temp);
        }else{
            //verificar se é possível adicionar um museu
            boolean adicionarMuseu = false;
            for (PontoInteresse poi : pontosPoss){
                if(poi.getTipo().equals("Museu") && (temp.getPrecoTotal() + poi.getCustoTotal()) <= aluno.getCustoMax()){
                    adicionarMuseu = true;
                    pontos_Visitar_aux.add(poi);
                }
            }
            
            if(adicionarMuseu == true){ //Significa que posso adicionar um museu
                for(PontoInteresse p : pontosEscolhidos){
                    pontos_Visitar_aux.add(p);
                }
                temp = new Viagem(locais, pontos_Visitar_aux, distancias);
                auxiliar.add(temp);
            }else{
                return null;   
            }
             
        }

        //Adiciona todos os pontos  possiveis que não ultrapassam o valor maximo
        Viagem viagemCriada = null;
        
        for(PontoInteresse pI : pontosPoss){
            System.out.println("pontos "+pontosEscolhidos);
            System.out.println("aluno "+aluno);
            if(!pontosEscolhidos.contains(pI) && (temp.getPrecoTotal()+pI.getCustoTotal()) < aluno.getCustoMax()){
                ArrayList<PontoInteresse> vazio = new ArrayList<>();
                vazio.addAll(pontos_Visitar_aux);
                vazio.add(pI);
                
                viagemCriada = new Viagem(locais, vazio, distancias);
                auxiliar.add(viagemCriada);
                System.out.println(pI);
                System.out.println(viagemCriada);
            }
             pontos_Visitar_aux.add(pI);
            
            
        }
        for(int i=0; i<auxiliar.size();i++){
            System.out.println(auxiliar.get(i));
        }
        
        return auxiliar;
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Tem de gerar as viagens primero", "ERRO", JOptionPane.PLAIN_MESSAGE);
        }
        return null;
    }
        
       public void sairSalvando(){
        f.getFichDistanciasObj(distancias);
        f.getFichLocaisObj(locais);
        f.getFichAlunosObj(alunos);
        System.exit(0);
    }
    
    
}
