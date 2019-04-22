package viagens_poo;

import java.io.Serializable;
import java.util.*;

/**
 * Representa uma viagem
 * @author Ricardo Martins
 * @author Tiago Fernandes
 */
public class Viagem implements Serializable, Comparable<Viagem> {
    private ArrayList<Local> destinos;
    private ArrayList<PontoInteresse> pontosDeInteresse;
    private int[][] distancias;
    private double precoViagem;

    /**
     * Representa uma viagem om um conjuntos de locais de destino, pontos de interesse a visitar e distancia entre todos os locais
     * @param destinos - ArrayList de locais
     * @param pontosDeInteresse - ArrayList dos pontos de interesse que visita
     * @param distancias - Matriz com todas as distâncias
     */
    public Viagem(ArrayList<Local> destinos, ArrayList<PontoInteresse> pontosDeInteresse, int[][] distancias){
        this.destinos = destinos;
        this.pontosDeInteresse = pontosDeInteresse;
        this.distancias = distancias;
        precoViagem = calcPrecoViagem();
    }

    /**
     * Devolve o preço total da viagem
     * @return Preço total da viagem
     */
    private double calcPrecoViagem(){
        double valor = 0;
        double precoUnidade = 0.13;
        int distancia_total = calcDistanciaTotal();
        valor += precoUnidade * distancia_total;
        for(PontoInteresse p : pontosDeInteresse){
           valor += p.getCustoTotal();
        }
        return valor;
    }

    /**
     * Devolve a distância total entre os locais da viagem
     * @return Distância total entre os locais da viagem
     */
    private int calcDistanciaTotal(){
        int indiceCoimbra = 0, indice1 = destinos.get(0).getIndice(), indice2 = destinos.get(1).getIndice(), indice3 = destinos.get(2).getIndice();
        int distancia_total = 0;
        distancia_total += distancias[indiceCoimbra][indice1];
        distancia_total += distancias[indice1][indice2];
        distancia_total += distancias[indice2][indice3];
        distancia_total += distancias[indice3][indiceCoimbra];
        return distancia_total;
    }

    /**
     * Devolve o preço total da viagem
     * @return Preço total da viagem
     */
    public double getPrecoTotal(){
        return precoViagem;
    }

    /**
     * Devolve o conjunto dos locais da viagem
     * @return ArrayList dos locais da viagem
     */
    public ArrayList<Local> getLocais(){
        return this.destinos;
    }

    /**
     * Devolve o conjunto dos pontos de interesse da viagem
     * @return ArrayList dos pontos de interesse da viagem
     */
    public ArrayList<PontoInteresse> getPontosDeInteresse(){
        return this.pontosDeInteresse;
    }

    /**
     * Devolve uma String para mostrar as várias viagens selecionadas
     * @return String para mostras as várias viagens selecionadas
     */
    //fazer o toString para criar meter nas viagens geradas e outro para meter nas viagem escolhida
    public String toString(){
        String viagem = "";
        for(Local l : destinos){
            viagem = "\t " + viagem + l.getNome() + " : ";
            for(PontoInteresse ponto : pontosDeInteresse){
                if(l.getPontos().contains(ponto)){
                    viagem = viagem + ponto.getNome() + " -> ";
                }
            }
        }
        viagem += "Coimbra\tCusto: " + precoViagem;
        return viagem;
    }

    @Override
    public int compareTo(Viagem o) {
        return (int) (this.precoViagem - o.precoViagem);
    }
}
