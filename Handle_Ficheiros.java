package viagens_poo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe para fazer a gestão dos ficheiros de texto e objetos
 * @author Ricardo Martins
 * @author Tiago Fernandes
 */

public class Handle_Ficheiros implements Serializable{

    /**
     * Construtor para poder aceder à classe Handle_Ficheiros
     */
    public Handle_Ficheiros(){
        
    }

    /**
     * Lê o ficheiro de texto dos locais
     * @return Devolve um ArrayList com os locais todos que estão nos ficheiros
     */
    private ArrayList<Local> lerLocaisTxt(){
        File f = new File("locais.txt");
        if(f.exists() && f.isFile())
            try {
                FileReader frd = new FileReader(f);
                BufferedReader brd = new BufferedReader(frd);
                String line;
                ArrayList<Local> locais = new ArrayList<>();
                while ((line = brd.readLine()) != null) {
                    ArrayList<PontoInteresse> pontos = new ArrayList<>();
                    // Separa pela "/" as coisas (local, universidade, bar, parque, museu)
                    String[] v = line.split("/");

                    String[] tipo = v[0].split(": ");
                    String[] coisas = tipo[1].split(", ");
                    String[] cafe = v[1].split(": ");
                    String[] univ = v[2].split(": ");
                    String[] muse = v[3].split(": ");
                    String[] park = v[4].split(": ");

                    String nome = coisas[0];
                    int indice = Integer.parseInt(coisas[1]);

                    System.out.printf("Local: %s (%d)\n", nome, indice);

                    // Bar: Nome, Horario, Classificacao, Preçoo de Entrada, Despesas
                    if (cafe[0].equals("Bar")){
                        String[] coisas_cafe = cafe[1].split(", ");
                        String[] horas = coisas_cafe[1].split("-");
                        String nome_b = coisas_cafe[0];
                        int horarioAbertura = Integer.parseInt(horas[0]);
                        int horarioFecho = Integer.parseInt(horas[1]);
                        Horario hora = new Horario(horarioAbertura, horarioFecho);
                        // System.out.printf("Horario: %d-%d\n", hora.getHoras_inicio(), hora.getHoras_fim());
                        int classificacao = Integer.parseInt(coisas_cafe[2]);
                        double precoEntrada = Double.parseDouble(coisas_cafe[3]);
                        double despesas = Double.parseDouble(coisas_cafe[4]);
                        Bar bar = new Bar(nome_b, hora, precoEntrada, despesas, classificacao);
                        pontos.add(bar);
                        // System.out.printf("Bar: %s / Classificacao: %d / Horario: %dh - %dh / Preco de entrada: %.2f€ / Despesas: %.2f€\n", bar.getNome(),  bar.getClassificacao(),  bar.getHorarioFunc().getHoras_inicio(), bar.getHorarioFunc().getHoras_fim(), bar.getEntrada(), bar.getDespesas());
                    }

                    // Museu: Nome, Horario, Preço de entrada, Tema
                    if (muse[0].equals("Museu")){
                        String[] coisas_muse = muse[1].split(", ");
                        String[] horas = coisas_muse[1].split("-");
                        String nome_m = coisas_muse[0];
                        int horarioAbertura = Integer.parseInt(horas[0]);
                        int horarioFecho = Integer.parseInt(horas[1]);
                        Horario hora = new Horario(horarioAbertura, horarioFecho);
                        double precoEntrada = Double.parseDouble(coisas_muse[2]);
                        String tema = coisas_muse[3];
                        Museu museu = new Museu(nome_m, hora, precoEntrada, tema);
                        pontos.add(museu);
                        // System.out.printf("Museu: %s / Horario: %dh - %dh / Preco de entrada: %.2f€ / Tema: %s\n", museu.getNome(), museu.getHorarioFunc().getHoras_inicio(), museu.getHorarioFunc().getHoras_fim(), museu.getEntrada(), museu.getTema());
                    }

                    // Universidade: Nome, Horario, Curso1/Curso2/.../CursoN
                    if (univ[0].equals("Universidade")){
                        String[] coisas_univ = univ[1].split(", ");
                        String[] horas = coisas_univ[1].split("-");
                        String nome_u = coisas_univ[0];
                        int horarioAbertura = Integer.parseInt(horas[0]);
                        int horarioFecho = Integer.parseInt(horas[1]);
                        Horario hora = new Horario(horarioAbertura, horarioFecho);
                        String cursos = coisas_univ[2];
                        String[] curso = cursos.split("$");
                        ArrayList<String> arrayCursos = new ArrayList<String>(Arrays.asList(curso));
                        Universidade universidade = new Universidade(nome_u, hora, arrayCursos);
                        pontos.add(universidade);
                        // System.out.printf("Universidade: %s / Horario: %dh - %dh / Cursos relacionados com Informatica: %s\n", universidade.getNome(), universidade.getHorarioFunc().getHoras_inicio(), universidade.getHorarioFunc().getHoras_fim(), universidade.getCursos());
                    }

                    // Parque cultural: Nome, Horario, Preço de entrada
                    if (park[0].equals("Parque Cultural")){
                        String[] coisas_park = park[1].split(", ");
                        String[] horas = coisas_park[1].split("-");
                        String nome_pc = coisas_park[0];
                        int horarioAbertura = Integer.parseInt(horas[0]);
                        int horarioFecho = Integer.parseInt(horas[1]);
                        Horario hora = new Horario(horarioAbertura, horarioFecho);
                        double precoEntrada = Double.parseDouble(coisas_park[2]);
                        Parque_Cultural parque_cultural = new Parque_Cultural(nome_pc, hora, precoEntrada);
                        pontos.add(parque_cultural);
                        // System.out.printf("Parque Cultural: %s / Horario: %dh - %dh / Preco de entrada: %.2f€\n", parque_cultural.getNome(), parque_cultural.getHorarioFunc().getHoras_inicio(), parque_cultural.getHorarioFunc().getHoras_fim(), parque_cultural.getCustoTotal());
                    }

                    // Parque aquatico: Nome, Horario, Preço de entrada, Nr de piscinas, Nr de escorregas, Espetaculos (true/false), Preco dos espetaculos
                    if (park[0].equals("Parque Aquatico")){
                        String[] coisas_park = park[1].split(", ");
                        String[] horas = coisas_park[1].split("-");
                        String nome_pa = coisas_park[0];
                        int horarioAbertura = Integer.parseInt(horas[0]);
                        int horarioFecho = Integer.parseInt(horas[1]);
                        Horario hora = new Horario(horarioAbertura, horarioFecho);
                        double preco_entrada = Double.parseDouble(coisas_park[2]);
                        int piscinas = Integer.parseInt(coisas_park[3]);
                        int escorregas = Integer.parseInt(coisas_park[4]);
                        boolean espetaculos = Boolean.parseBoolean(coisas_park[5]);
                        double preco_espetaculos = Double.parseDouble(coisas_park[6]);
                        Parque_Aquatico parque_aquatico = new Parque_Aquatico(nome_pa, hora, preco_entrada, preco_espetaculos, espetaculos, escorregas, piscinas);
                        pontos.add(parque_aquatico);
                        // System.out.printf("Parque Aquatico: %s / Horario: %dh - %dh / Preco de entrada: %.2f€ / Piscinas: %d / Escorregas: %d / Espetaculos: %b / Preco dos espetaculos: %.2f€\n", parque_aquatico.getNome(), parque_aquatico.getHorarioFunc().getHoras_inicio(), parque_aquatico.getHorarioFunc().getHoras_fim(), parque_aquatico.getEntrada(), parque_aquatico.getN_piscinas(), parque_aquatico.getN_escorregas(), parque_aquatico.isEspetaculos(), parque_aquatico.getPreco_espetaculos());
                    }

                    // Parque Tematico: Nome, Horario, Preco de Entrada, Tema
                    if (park[0].equals("Parque Tematico")) {
                        String[] coisas_park = park[1].split(", ");
                        String[] horas = coisas_park[1].split("-");
                        String nome_pt = coisas_park[0];
                        int horarioAbertura = Integer.parseInt(horas[0]);
                        int horarioFecho = Integer.parseInt(horas[1]);
                        Horario hora = new Horario(horarioAbertura, horarioFecho);
                        double preco_entrada = Double.parseDouble(coisas_park[2]);
                        String tema = coisas_park[3];
                        Parque_Tematico parque_tematico = new Parque_Tematico(nome_pt, hora, preco_entrada, tema);
                        pontos.add(parque_tematico);
                        // System.out.printf("Parque Tematico: %s / Horario: %dh - %dh / Preco de entrada: %.2f€ / Tema: %s\n", parque_tematico.getNome(), parque_tematico.getHorarioFunc().getHoras_inicio(), parque_tematico.getHorarioFunc().getHoras_fim(), parque_tematico.getEntrada(), parque_tematico.getTema());
                    }
                    // System.out.println();
                    Local local = new Local(nome, pontos, indice);
                    locais.add(local);
                }
                brd.close();
                return locais;
            } catch (FileNotFoundException ex) {
                System.out.println("[Leitura locais.txt] Erro a abrir o ficheiro");
            } catch (IOException ex) {
                System.out.println("[Leitura locais.txt] Erro a ler o ficheiro");
            }
        else{
            System.out.println("Ficheiro 'locais.txt' nao existe");
        }
        return null;
    }

    /**
     * Lê o ficheiro de texto das distâncias
     * @return Devolve uma matriz com as distâncias entre os vários locais
     */
    private int[][] lerDistanciasTxt(){
        File f = new File("distancias.txt");
        int [][] matriz = new int[20][20];
        if(f.exists() && f.isFile())
            try {
                FileReader frd = new FileReader(f);
                BufferedReader brd = new BufferedReader(frd);
                String line;
                int i = 0;
                while ((line = brd.readLine()) != null) {
                    String[] v = line.split(" ");
                    for (int j = 0; j < 20; j++){
                        matriz[i][j] = Integer.parseInt(v[j]);
                    }
                    i++;
                }
                brd.close();
            } catch (FileNotFoundException ex) {
                System.out.println("[Leitura distancias.txt] Erro a abrir o ficheiro");
            } catch (IOException ex) {
                System.out.println("[Leitura distancias.txt] Erro a ler o ficheiro");
            }
        else{
            System.out.println("Ficheiro 'distancias.txt' nao existe");
        }
        return matriz;
    }

    /**
     * Escreve para um ficheiro de objetos os alunos
     * @param alunos - ArrayList dos alunos no programa
     */
    private void escreveFichAlunosObj(ArrayList<Aluno> alunos){
        File f = new File("alunos_obj");
        try{
            FileOutputStream os = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(alunos);

            oos.close();

        } catch (FileNotFoundException e){
            System.out.println("[Escrita alunos_obj] Erro a criar o ficheiro");
        } catch (IOException e){
            System.out.println("[Escrita alunos_obj] Erro ao escrever para o ficheiro");
        }
    }

    /**
     * Escreve para um ficheiro de objetos as distâncias
     * @param locais - ArrayList com os locais
     */
    private void escreveFichLocaisObj(ArrayList<Local> locais){
        File f = new File("locais_obj");
        try{
            FileOutputStream fos = new FileOutputStream(f);

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(locais);
            oos.close();
        } catch (FileNotFoundException e){
            System.out.println("[Escrita locais_obj] Erro a criar o ficheiro");
        } catch (IOException e){
            System.out.println("[Escrita locais_obj] Erro ao escrever para o ficheiro");
        }
    }

    /**
     * Escreve para um ficheiro de objetos as distâncias
     * @param distancias - Matriz com as distancias
     */
    private void escreveFichDistanciasObj(int[][] distancias){
        File f = new File("distancias_obj");
        try{
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(distancias);

            oos.close();
        } catch (FileNotFoundException e){
            System.out.println("[Escrita distancias_obj] Erro a criar o ficheiro");
        } catch (IOException e){
            System.out.println("[Escrita distancias_obj] Erro ao escrever para o ficheiro");
        }
    }

    /**
     * Le um ficheiro de texto onde estão os alunos do programa
     * @return Devolve o ArrayList dos alunos
     */
    private ArrayList<Aluno> lerFichAlunosObj(){
        File f = new File("alunos_obj");
        if (f.exists() && f.isFile()){
            try{
                FileInputStream is = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(is);

                ArrayList<Aluno> alunos = (ArrayList<Aluno>) ois.readObject();

                ois.close();
                return alunos;
            } catch (IOException e){
                System.out.println("[Leitura alunos_obj] Erro ao ler do ficheiro");
            } catch (ClassNotFoundException e) {
                System.out.println("[Leitura alunos_obj] Erro a converter objeto");
            }
        }
        else{
            System.out.println("Ficheiro 'alunos_obj' nao existe");
        }
        return null;
    }

    /**
     * Le um ficheiro de texto onde estão os locais do programa
     * @return Devolve o ArrayList dos locais
     */
    private ArrayList<Local> lerFichLocaisObj(){
        File f = new File("locais_obj");
        if (f.exists() && f.isFile()){
            try{
                FileInputStream is = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(is);

                ArrayList<Local> locais = (ArrayList<Local>) ois.readObject();

                ois.close();
                return locais;
            } catch (IOException e){
                System.out.println("[Leitura locais_obj] Erro ao ler do ficheiro");
            } catch (ClassNotFoundException e) {
                System.out.println("[Leitura locais_obj] Erro a converter objeto");
            }
        }
        else{
            System.out.println("Ficheiro 'locais_obj' nao existe");
        }
        return null;
    }
    
    private void escreveResultado(String nome, String texto){
        String fich = nome + ".txt";
        File f = new File(fich);
        try{
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.close();
        } catch(IOException e){
            System.out.println("Erro ao escrever no ficheiro");
        }
    }

    /**
     * Le um ficheiro de texto onde estão as distancias entre os vários locais do programa
     * @return Devolve uma matriz com as distâncias
     */
    private int[][] lerFichDistanciasObj(){
        File f = new File("distancias_obj");
        if (f.exists() && f.isFile()){
            try{
                FileInputStream is = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(is);

                int[][] matriz = (int[][]) ois.readObject();

                ois.close();
                return matriz;
            } catch (IOException e){
                System.out.println("[Leitura distancias_obj] Erro ao ler do ficheiro");
            } catch (ClassNotFoundException e) {
                System.out.println("[Leitura distancias_obj] Erro a converter objeto");
            }
        }
        else{
            System.out.println("Ficheiro 'distancias_obj' nao existe");
        }
        return null;
    }

    /**
     * Executa o método lerLocaisTxt
     * @return Devolve o resultado do método lerLocaisTxt
     */
    public ArrayList<Local> getListLocaisTxt(){
       return lerLocaisTxt();
    }

    /**
     * Executa o método lerDistanciasTxt
     * @return Devolve o resultado do método lerDistanciasTxt
     */
    public int[][] getDistancias(){
        return lerDistanciasTxt();
    }

    /**
     * Executa o método lerFichAlunosObj
     * @return Devolve o resultado do método lerFichAlunosObj
     */
    public ArrayList<Aluno> getListAlunoObj(){
        return lerFichAlunosObj();
    }

    /**
     * Executa o método escreveFichAlunosObj
     * @param alunos - ArrayList dos alunos
     */
    public void getFichAlunosObj(ArrayList<Aluno> alunos) {
        escreveFichAlunosObj(alunos);
    }

    /**
     * Executa o método escreveFichLocaisObj
     * @param locais - ArrayList dos locais
     */
    public void getFichLocaisObj(ArrayList<Local> locais){
        escreveFichLocaisObj(locais);
    }

    /**
     * Executa o método escreveFichDistanciasObj
     * @param matriz - Matriz das distancias
     */
    public void getFichDistanciasObj(int[][] matriz){
        escreveFichDistanciasObj(matriz);
    }

    /**
     * Executa o método lerFichAlunosObj
     * @return Devolve o resultado do método lerFichAlunosObj
     */
    public ArrayList<Aluno> setFichAlunosObj(){
        return lerFichAlunosObj();
    }

    /**
     * Executa o método lerFichLocaisObj
     * @return Devolve o resultado do método lerFichLocaisObj
     */
    public ArrayList<Local> setFichLocaisObj(){
        return lerFichLocaisObj();
    }

    /**
     * Executa o método lerFichDistanciasObj
     * @return Devolve o resultado do método lerFichDistanciasObj
     */
    public int[][] setFichDistanciasObj(){
        return lerFichDistanciasObj();
    }

    /**
     * Cria um ficheiro 'fich.txt' e escreve para lá o texto
     * @param fich - Nome do ficheiro
     * @param texto - Texto que escreve
     */
    public void escreveFinal(String fich, String texto){
        escreveResultado(fich, texto);
    }
}