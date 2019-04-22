package viagens_poo;

import java.io.Serializable;

/**
 * Representa um parque cultural
 * @author Ricardo Martins
 * @author Tiago Fernandes
 * 
 */
public class Parque_Cultural extends Parque implements Serializable {

    /**
     * Cria um parque cultural com um nome, horário de funcionamento e preço de entrada
     * @param nome - Nome
     * @param horario - Horário de funcionamento
     * @param entrada - Preço de entrada
     */
    public Parque_Cultural(String nome, Horario horario, double entrada){
        super(nome, horario, entrada);
    }

    /**
     * Devolve o custo total do parque
     * @return Custo total do parque
     */
    public double getCustoTotal() {
        return entrada;
    }

    @Override
    public String toString(){
        return "Parque cultural: " + nome;
    }

    /**
     * Devolve uma String com o tipo do ponto de interesse
     * @return String com o tipo do ponto de interesse
     */
    @Override
    public String getTipo() {
        return "Parque Cultural";
    }

    /**
     * Devolve uma String com as informações a escrever no ficheiro
     * @return String com as informações a escrever no ficheiro
     */
    public String ficheiro(){
        return "Parque cultural: " + this.nome + " / Horário: " + this.horarioFunc.getHoras_inicio() + "h - " + this.horarioFunc.getHoras_fim() + "h / Preço de entrada: " + this.entrada + "\n";
    }
}
