package viagens_poo;

import java.io.Serializable;

/**
 * Representa um parque aquático
 * @author Ricardo Martins
 * @author Tiago Fernandes
 */
public class Parque_Aquatico extends Parque_Diversoes implements Serializable {
    private int n_piscinas, n_escorregas;
    private boolean espetaculos;
    private double preco_espetaculos;

    /**
     * Cria um parque aquático com um nome, um horário de funcionamento, um preço de entrada, um número de escorregas e piscinas, se há espetáculos ou não e o preço dos espetáculos
     * @param nome - Nome
     * @param horario - Horário de funcionamento
     * @param entrada - Preço de entrada
     * @param preco_espetaculos - Preço dos espetáculos
     * @param espetaculos - Existência de espetáculos
     * @param n_escorregas - Número de escorregas
     * @param n_piscinas - Número de piscinas
     */
    public Parque_Aquatico(String nome, Horario horario, double entrada, double preco_espetaculos, boolean espetaculos, int n_escorregas, int n_piscinas){
        super(nome, horario, entrada);
        this.n_escorregas = n_escorregas;
        this.n_piscinas = n_piscinas;
        this.espetaculos = espetaculos;
        this.preco_espetaculos = preco_espetaculos;
    }

    /**
     * Devolve uma String com as informações a escrever no ficheiro
     * @return String com as informações a escrever no ficheiro
     */
    public String ficheiro(){
        return "Parque aquático: " + this.nome + " / Horário: " + this.horarioFunc.getHoras_inicio() + "h - " + this.horarioFunc.getHoras_fim() + "h / Preço de entrada: " + this.entrada + " / Número de piscinas: " + this.n_piscinas + " / Número de escorregas: " + this.n_escorregas + "\n";
    }

    /**
     * Devolve o custo total do parque aquático
     * @return Custo total
     */
    public double getCustoTotal(){
        return preco_espetaculos + entrada;
    }

    @Override
    public String toString(){
        return "Parque aquatico: " + nome;
    }

    /**
     * Devolve uma String com o tipo do ponto de interesse
     * @return String com o tipo do ponto de interesse
     */
    @Override
    public String getTipo() {
        return "Parque Aquatico";
    }
}
