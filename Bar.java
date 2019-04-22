package viagens_poo;

import java.io.Serializable;

/**
 * Representa um bar
 * @author Ricardo Martins
 * @author Tiago Fernandes
 */
public class Bar extends PontoInteresse implements Serializable {
    private double entrada, despesas;
    private int classificacao;

    /**
     * Cria um bar com um nome, horário de funcionamento, preço de entrada, despesas e classificação média
     * @param nome - Nome
     * @param horario - Horário de funcionamento
     * @param entrada - Preço de entrada
     * @param despesas - Preço das despesas
     */
    public Bar(String nome, Horario horario, double entrada, double despesas, int classificacao){
        super(nome,horario);
        this.entrada = entrada;
        this.despesas = despesas;
        this.classificacao = classificacao;
    }

    /**
     * Devolve o custo total do bar
     * @return Custo total do bar
     */
    public double getCustoTotal(){
        return despesas + entrada;
    }

    /**
     * Devolve uma String para escrever no ficheiro
     * @return String para escrever no ficheiro a viagem escolhida com os seus detalhes
     */
    public String ficheiro(){
        return "Nome: " + this.nome + " / Horário: " + this.horarioFunc.getHoras_inicio() + "h - " + this.horarioFunc.getHoras_fim() + "h / Preço de entrada: " + this.entrada + " / Despesas: " + this.despesas + " / Classificação: " + this.classificacao + "\n";
    }

    @Override
    public String toString(){
        return "Bar: " + nome;
    }

    /**
     * Devolve uma String com o tipo de ponto de Interesse
     * @return String com o tipo de ponto de interesse
     */
    @Override
    public String getTipo() {
        return "Bar";
    }
}
