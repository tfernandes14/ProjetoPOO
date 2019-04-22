package viagens_poo;

import java.io.Serializable;

/**
 * Representa um utilizador de Licenciatura
 * @author Ricardo Martins
 * @author Tiago Fernandes
 */

public class Licenciatura extends Aluno implements Serializable {
    private PontoInteresse pontoHot;

    /**
     * Cria um utilizador de licenciatura com um nome, grau académico (Licenciatura), custo máximo que pretende gastar e um ponto hot
     * @param nome - Nome do utilizador
     * @param grauAcademico - Licenciatura
     * @param custoMax - Custo máximo que pretende gastar
     * @param pontoHot - Ponto hot (objeto)
     */
    public Licenciatura(String nome, String grauAcademico, double custoMax, PontoInteresse pontoHot) {
        super(nome, grauAcademico, custoMax);
        this.pontoHot = pontoHot;
    }

    /**
     * Devolve o objeto do ponto hot
     * @return Objeto de ponto hot
     */
    public PontoInteresse getPontoHot() {
        return pontoHot;
    }

    /**
     * Devolve o objeto do local a evitar
     * @return Objeto do local a evitar
     */
    public Local getLocalEvitar(){
        return null;
    }
}
