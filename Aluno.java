package viagens_poo;

import java.io.Serializable;

/**
 * Representa um aluno
 * @author Ricardo Martins
 * @author Tiago Fernandes
 */

abstract class Aluno implements Serializable {
    protected String nome, grauAcademico;
    protected double custoMax;

    /**
     * Cria um aluno com um nome, o seu grau académico e o custo máximo que ele quer gastar na sua viagem
     * @param nome - Nome
     * @param grauAcademico - Grau académico
     * @param custoMax - Custo máximo
     */
    public Aluno(String nome, String grauAcademico, double custoMax){
        this.custoMax = custoMax;
        this.nome = nome;
        this.grauAcademico = grauAcademico;
    }

    /**
     * Devolve o nome do aluno
     * @return Nome do aluno
     */
    public String getNome(){
        return nome;
    }

    /**
     * Devolve o grau académico do aluno
     * @return Grau académico do aluno
     */
    public String getGrauAcademico(){
        return grauAcademico;
    }

    /**
     * Devolve o custo máximo que o aluno pretende gastar na viagem
     * @return Custo máximo pretendido gastar na viagem
     */
    public double getCustoMax(){
        return custoMax;
    }

    /**
     * Método abstrato para saber qual é o local a evitar
     * @return - Objeto Local
     */
    abstract Local getLocalEvitar();

    /**
     * Método abstrato para saber qual é o ponto hot
     * @return - Objeto PontoInteresse
     */
    abstract PontoInteresse getPontoHot();
    public void setCustoMax(int value){
        this.custoMax = value;
    }
}
