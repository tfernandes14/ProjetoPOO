package viagens_poo;

import java.io.Serializable;

/**
 * Representa um horário
 * @author Ricardo Martins
 * @author Tiago Fernandes
 */
public class Horario implements Serializable {
    private int horas_inicio, horas_fim;

    /**
     * Cria um horário com hora de abertura e de fecho
     * @param horas_inicio - Hora de abertura
     * @param horas_fim - Hora de fecho
     */
    public Horario(int horas_inicio, int horas_fim) {
        this.horas_inicio = horas_inicio;
        this.horas_fim = horas_fim;
    }

    /**
     * Devolve as horas de abertura
     * @return Hora de abertura
     */
    public int getHoras_inicio() {
        return horas_inicio;
    }

    /**
     * Devolve as horas de fecho
     * @return Hora de fecho
     */
    public int getHoras_fim() {
        return horas_fim;
    }
}
