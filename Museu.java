package viagens_poo;

import java.io.Serializable;

/**
 * Representa um museu
 * @author Ricardo Martins
 * @author Tiago Fernandes
 * 
 */
public class Museu extends PontoInteresse implements Serializable {
    private double entrada;
    private String tema;

    /**
     * Cria um museu com um nome, horário de funcionamento, preço de entrada e o seu tema
     * @param nome - Nome
     * @param horario - Horário de funcionamento
     * @param entrada - Preço de entrada
     * @param tema - Tema
     */
    public Museu(String nome, Horario horario, double entrada, String tema){
        super(nome,horario);
        this.entrada = entrada;
        this.tema = tema;
    }

    /**
     * Devolve o valor do custo total do museu
     * @return Custo total do museu
     */
    public double getCustoTotal(){
        return entrada;
    }

    @Override
    public String toString(){
        return "Museu: " + nome;
    }

    /**
     * Devolve uma String para escrever no ficheiro
     * @return String para escrever as informações sobre a viagem escolhida
     */
    public String ficheiro(){
        return "Museu: " + this.nome + " / Horário: " + this.horarioFunc.getHoras_inicio() + "h - " + this.horarioFunc.getHoras_fim() + "h / Preço de entrada: " + this.entrada + " / Tema: " + this.tema + "\n";
    }

    /**
     * Devolve uma String do tipo do ponto de interesse
     * @return String com o tipo do ponto de interesse
     */
    public String getTipo() {
        return "Museu";
    }
}
