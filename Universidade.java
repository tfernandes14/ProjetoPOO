package viagens_poo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representa uma universidade
 * @author Ricardo Martins
 * @author Tiago Fernandes
 */
public class Universidade extends PontoInteresse implements Serializable {
    private ArrayList<String> cursos;

    /**
     * Cria uma universidade com um nome, um horário de funcionamento e um ArrayList de cursos relacionados com Engenharia Informática
     * @param nome - Nome da universidade
     * @param horario - Horário de funcionamento
     * @param cursos - ArrayList de cursos relacionados com Engenharia Informática
     */
    public Universidade(String nome, Horario horario, ArrayList<String> cursos){
        super(nome, horario);
        this.cursos = cursos;
    }

    /**
     * Devolve o ArrayList de cursos relacionados com Engenharia Informática
     * @return ArrayList de cursos relacionados com Engenharia Informática
     */
    public ArrayList<String> getCursos(){
        return cursos;
    }

    /**
     * Define o ArrayList de cursos relacionados com Engenharia Informática
     * @param cursos
     */
    public void setCursos(ArrayList<String> cursos) {
        this.cursos = cursos;
    }

    /**
     * Devolve o valor do custo total da universidade
     * @return Valor do custo total da universidade
     */
    public double getCustoTotal(){
        return 0;
    }

    public String toString(){
        return "Universidade: " + nome;
    }

    /**
     * Devolve uma String com as informações a escrever no ficheiro
     * @return String com as informações a escrever no ficheiro
     */
    public String ficheiro(){
        return "Universidade: " + this.nome + " / Horário: " + this.horarioFunc.getHoras_inicio() + "h - " + this.horarioFunc.getHoras_fim() + "h\n";
    }

    /**
     * Devolve uma String com o tipo do ponto de interesse
     * @return String com o tipo do ponto de interesse
     */
    @Override
    public String getTipo() {
        return "Universidade"; 
    }
}
