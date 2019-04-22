package viagens_poo;

import java.io.Serializable;

/**
 * Representa um ponto de interesse
 * @author Ricardo Martins
 * @author Tiago Fernandes
 */

public abstract class PontoInteresse implements Serializable, Comparable<PontoInteresse>{
    protected String nome;
    protected Horario horarioFunc;
    protected int contador;
    protected double custoTotal;

    /**
     * Cria um ponto de interesse com um nome, um horário de funcionamento e um contador relativo à popularidade inicializada a zero
     * @param nome - Nome do ponto de interesse
     * @param horario - Horário de funcionamento
     */
    public PontoInteresse(String nome, Horario horario){
        this.nome = nome;
        this.horarioFunc = horario;
        this.contador = 0;
    }

    /**
     * Devolve o nome do ponto de interesse
     * @return Nome do ponto de interesse
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do ponto de interesse
     * @param nome - Nome do ponto de interesse
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Incrementa o contador em x unidade, isto é, este método é chamado quando alguém escolhe o local
     * @param value - Valor que incrementa
     */
    public void setContador(int value){
        this.contador+=value;
    }

    /**
     * Devolve uma String com as informações a escrever no ficheiro
     * @return String com as informações a escrever no ficheiro
     */
    public abstract String ficheiro();

    /**
     * Devolve o valor do custo total do ponto de interesse
     * @return Custo total do ponto de interesse
     */
    abstract double getCustoTotal();

    /**
     * Devolve uma String com o tipo do ponto de interesse
     * @return String com o tipo de ponto de interesse
     */
    public abstract String getTipo();

    @Override
    public abstract String toString();

    @Override
    public int compareTo(PontoInteresse o) {
        return this.contador - o.contador;
    }
}
