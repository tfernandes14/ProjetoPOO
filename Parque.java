package viagens_poo;

import java.io.Serializable;

/**
 * Representa um parque
 * @author Ricardo Martins
 * @author Tiago Fernandes
 * 
 */
abstract class Parque extends PontoInteresse implements Serializable {
    protected double entrada;

    /**
     * Cria um parque com um nome, horário de funcionamento e preço de entrada
     * @param nome - Nome
     * @param horario - Horário de funcionamento
     * @param entrada - Preço de entrada
     */
    public Parque(String nome, Horario horario, double entrada){
        super(nome, horario);
        this.entrada = entrada;
    }

    /**
     * Devolve o custo total do parque
     * @return Custo total do parque
     */
    abstract double getCustoTotal();

    /**
     * Devolve uma String com as informações a escrever no ficheiro
     * @return String com as informações a escrever no ficheiro
     */
    public abstract String ficheiro();
}
