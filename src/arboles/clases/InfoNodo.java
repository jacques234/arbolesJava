/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arboles.clases;

import java.util.Objects;
/**
 *
 * @author depm1
 */
public class InfoNodo {
    char lado;
    Nodo nodo;
    int raiz;

    public char getLado() {
        return lado;
    }

    public void setLado(char lado) {
        this.lado = lado;
    }

    public Nodo getNodo() {
        return nodo;
    }

    public InfoNodo(char lado, Nodo nodo, int raiz) {
        this.lado = lado;
        this.nodo = nodo;
        this.raiz = raiz;
    }

    public int getRaiz() {
        return raiz;
    }

    public void setRaiz(int raiz) {
        this.raiz = raiz;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        
        InfoNodo that = (InfoNodo) o;
        return this.lado == that.lado && this.nodo.valor == that.nodo.valor;
    }
    @Override
    public int hashCode(){
        return Objects.hash(lado,nodo.valor);
    }
}
