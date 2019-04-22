package viagens_poo;

import java.io.Serializable;

/**
 * Representa um parque temático
 * @author Ricardo Martins
 * @author Tiago Fernandes
 */

public class Parque_Tematico extends Parque_Diversoes implements Serializable {
    private String tema;

    /**
     * Cria um parque temático com um nome, um horário de funcionamento, um preço de entrada e um tema
     * @param nome - Nome
     * @param horario - Horário de funcionamento
     * @param entrada - Preço de entrada
     * @param tema - Tema
     */
    public Parque_Tematico(String nome, Horario horario, double entrada, String tema){
        super(nome, horario, entrada);
        this.tema = tema;
    }

    /**
     * Devolve o custo total do parque temático
     * @return - Custo total do parque temático
     */
    public double getCustoTotal() {
        return entrada;
    }

    /**
     * Devolve uma String com as informações a escrever no ficheiro
     * @return String com as informações a escrever no ficheiro
     */
    public String ficheiro(){
        return "Parque temático: " + this.nome + " / Horário: " + this.horarioFunc.getHoras_inicio() + "h - " + this.horarioFunc.getHoras_fim() + "h / Preço de entrada: " + this.entrada + " / Tema: " + this.tema + "\n";
    }

    @Override
    public String toString(){
        return "Parque tematico: " + nome;
    }

    /**
     * Devolve uma String com o tipo do ponto de interesse
     * @return
     */
    @Override
    public String getTipo() {
        return "Parque Temático";
    }
}
