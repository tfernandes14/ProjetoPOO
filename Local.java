package viagens_poo;

import java.io.Serializable;
import java.util.*;

/**
 * Representa um local
 * @author Ricardo Martins
 * @author Tiago Fernandes
 */
public class Local implements Serializable, Comparable<Local>{
    private String nome;
    private ArrayList<PontoInteresse> pontos;
    private double gastoLocal;
    private int contador;
    private int indice;

    /**
     * Cria um local com um contador relativo à popularidade inicializado a zero
     */
    public Local(){
        this.contador = 0;
    }

    /**
     * Cria um local com um contador relativo à popularidade inicializado a zero, um nome, um ArrayList de pontos de interesse e o índice do local (para as distâncias)
     * @param nome - Nome do local
     * @param pontos - ArrayList de pontos de interesse
     * @param indice - Indice do local
     */
    public Local(String nome, ArrayList<PontoInteresse> pontos, int indice){
        this.nome = nome;
        this.pontos = pontos;
        this.indice = indice;
        this.contador = 0;
    }

    /**
     * Cria uma cópia de um local
     * @param l - Local a copiar
     */
    public Local(Local l){
        this.nome = l.nome;
        this.pontos = l.pontos;
        this.gastoLocal = l.gastoLocal;
        this.contador = l.contador;
        this.indice = l.indice;
    }

    /**
     * Devolve o índice do local (para as distâncias)
     * @return Índice do local (para as distâncias)
     */
    public int getIndice() {
        return indice;
    }

    /**
     * Devolve o nome do local
     * @return Nome do local
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do local
     * @param nome - Nome do local
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Devolve o ArrayList dos pontos de interesse
     * @return ArrayList dos pontos de interesse
     */
    public ArrayList<PontoInteresse> getPontos() {
        return pontos;
    }

    /**
     * Incrementa o contador em x unidade, isto é, este método é chamado quando alguém escolhe o local
     * @param value - Valor que incrementa
     */
    public void setContador(int value) {
        this.contador+=value;
    }

    @Override
    public String toString(){
        return nome;
    }


    /**
     * Método auxiliar para organizar
     * @param o
     * @return
     */
    @Override
    public int compareTo(Local o) {
        return this.contador-o.contador;
    }
}
