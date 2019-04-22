package viagens_poo;

import java.io.Serializable;

/**
 * Representa um parque de diversões
 * @author Ricardo Martins
 * @author Tiago Fernandes
 */
abstract class Parque_Diversoes extends Parque implements Serializable {

    /**
     * Cria um parque de diversões com um nome, horário de funcionamento e preço de entrada
     * @param nome - Nome
     * @param horario - Horário de funcionamento
     * @param entrada - Preço de entrada
     */
    public Parque_Diversoes(String nome, Horario horario, double entrada){
        super(nome, horario, entrada);
    }

    /**
     * Devolve o custo total do parque de diversões
     * @return Custo total do parque de diversões
     */
    abstract double getCustoTotal();

    /**
     * Devolve uma String com as informações a escrever no ficheiro
     * @return String com as informações a escrever no ficheiro
     */
    public abstract String ficheiro();

    @Override
    public String toString(){
        return "Parque diversoes: " + nome;
    }
}
