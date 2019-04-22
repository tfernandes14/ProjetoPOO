package viagens_poo;

import java.io.Serializable;

/**
 * Representa um utilizador de Mestrado
 * @author Ricardo Martins
 * @author Tiago Fernandes
 */
public class Mestrado extends Aluno implements Serializable {
    private Local localEvitar;

    /**
     * Cria um utilizador de Mestrado com um nome, grau académico (Mestrado), custo máximo que o utilizador pretende gastar e o local que quer evitar
     * @param nome - Nome
     * @param grauAcademico - Mestrado
     * @param custoMax - Custo máximo que pretende gastar
     * @param localEvitar - Local a evitar (objeto)
     */
    public Mestrado(String nome, String grauAcademico, double custoMax, Local localEvitar) {
        super(nome, grauAcademico, custoMax);
        this.localEvitar = localEvitar;
    }

    /**
     * Devolve o objeto do local a evitar
     * @return Local a evitar (objeto)
     */
    public Local getLocalEvitar(){
        return localEvitar;
    }

    /**
     * Devolve o objeto do ponto de interesse hot
     * @return Ponto de interesse hot (objeto)
     */
    public PontoInteresse getPontoHot() {
        return null;
    }

}
