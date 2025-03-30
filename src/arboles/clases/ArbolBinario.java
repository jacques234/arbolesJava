/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arboles.clases;

import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author depm1
 */
public class ArbolBinario {

    Nodo raiz;
    ArrayList<InfoNodo> arrayNodo;

    public ArbolBinario() {
        this.arrayNodo = new ArrayList<>();
    }

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    public void eliminarHoja(int valor) {
        raiz = eliminarHojaRec(raiz, valor);
    }

    public void eliminar(int valor) {
        raiz = eliminarRec(raiz, valor);
        arrayNodo.removeIf(info -> info.nodo.valor == valor);
    }

    public void printArbol() {
        System.out.println("Nodo raiz: " + raiz.valor);
        printArbol(raiz.izquierdo, 'i', 2);
        printArbol(raiz.derecho, 'i', 2);

    }

    public void printArbol(Nodo n, char l, int v) {
        if (n != null) {
            String lado = l == 'i' ? "Nodo izquierdo: " : "Nodo derecho: ";
            System.out.println("nivel " + v + ": " + lado + n.valor);
            v++;
            printArbol(n.izquierdo, 'i', v);
            printArbol(n.derecho, 'd', v);
        }
    }
    
    public void imprimirArray(){
        Set<InfoNodo> sinDuplicados = new LinkedHashSet<>(arrayNodo);
        arrayNodo = new ArrayList<>(sinDuplicados);
        for (InfoNodo infoNodo : arrayNodo) {
            System.out.println(infoNodo.lado + "-" + infoNodo.nodo.valor + "-" + infoNodo.raiz);
        }
    }

    public void imprimirArbolDesdeRaiz() {
        Set<InfoNodo> sinDuplicados = new LinkedHashSet<>(arrayNodo);
        arrayNodo = new ArrayList<>(sinDuplicados);

        
        InfoNodo nodoRaiz = new InfoNodo(' ', raiz, -1);
        printArbolRecursivo(nodoRaiz, "", true);
    }

    private void printArbolRecursivo(InfoNodo nodoActual, String prefijo, boolean esUltimo) {
        String label = (nodoActual.lado == ' ' ? "" : "(" + nodoActual.lado + ") ");
        System.out.println(prefijo + (esUltimo ? "|___ " : "|--- ") + label + nodoActual.nodo.valor);

        
        List<InfoNodo> hijos = new ArrayList<>(
                arrayNodo.stream()
                        .filter(info -> info.raiz == nodoActual.nodo.valor)
                        .toList()
        );

        hijos.sort(Comparator.comparing(info -> info.lado));
        for (int i = 0; i < hijos.size(); i++) {
            InfoNodo hijo = hijos.get(i);
            boolean esUltimoHijo = (i == hijos.size() - 1);
            String nuevoPrefijo = prefijo + (esUltimo ? "    " : "|   ");
            printArbolRecursivo(hijo, nuevoPrefijo, esUltimoHijo);
        }
    }

    private Nodo insertarRec(Nodo nodo, int valor) {
        if (nodo == null) {
            return new Nodo(valor);
        }

        if (valor < nodo.valor) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, valor);
            arrayNodo.add(new InfoNodo('i', nodo.izquierdo, nodo.valor));
        } else if (valor > nodo.valor) {
            nodo.derecho = insertarRec(nodo.derecho, valor);
            arrayNodo.add(new InfoNodo('d', nodo.derecho, nodo.valor));
        }

        return nodo;
    }

    public void inorden() {
        inordenRec(raiz);
        System.out.println();
    }

    private void inordenRec(Nodo nodo) {
        if (nodo != null) {
            inordenRec(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            inordenRec(nodo.derecho);
        }
    }

    public void preorden() {
        preordenRec(raiz);
        System.out.println();
    }

    private void preordenRec(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " ");
            preordenRec(nodo.izquierdo);
            preordenRec(nodo.derecho);
        }
    }

    public void postorden() {
        postordenRec(raiz);
        System.out.println();
    }

    private void postordenRec(Nodo nodo) {
        if (nodo != null) {
            postordenRec(nodo.izquierdo);
            postordenRec(nodo.derecho);
            System.out.print(nodo.valor + " ");
        }
    }

    private Nodo eliminarHojaRec(Nodo nodo, int valor) {
        if (nodo == null) {
            return null;
        }

        if (valor < nodo.valor) {
            nodo.izquierdo = eliminarHojaRec(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = eliminarHojaRec(nodo.derecho, valor);
        } else {
            // Si es el nodo que queremos eliminar...
            if (nodo.izquierdo == null && nodo.derecho == null) {
                // Es hoja: lo eliminamos
                return null;
            } else {
                // No es hoja: no se elimina
                System.out.println("El nodo " + valor + " no es una hoja.");
            }
        }

        return nodo;
    }

    private Nodo eliminarRec(Nodo nodo, int valor) {
        if (nodo == null) {
            return null;
        }

        if (valor < nodo.valor) {
            nodo.izquierdo = eliminarRec(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = eliminarRec(nodo.derecho, valor);
        } else {
            // Caso 1: sin hijos (hoja)
            if (nodo.izquierdo == null && nodo.derecho == null) {
                return null;
            }
            // Caso 2: un solo hijo
            if (nodo.izquierdo == null) {
                return nodo.derecho;
            }
            if (nodo.derecho == null) {
                return nodo.izquierdo;
            }

            // Caso 3: dos hijos
            Nodo sucesor = encontrarMinimo(nodo.derecho);
            nodo.valor = sucesor.valor; // Copiamos el valor
            nodo.derecho = eliminarRec(nodo.derecho, sucesor.valor); // Eliminamos el duplicado
        }

        return nodo;
    }

    private Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }
}
