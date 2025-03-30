/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arboles;

import arboles.clases.ArbolBinario;

/**
 *
 * @author depm1
 */
public class Arboles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();

        arbol.insertar(45);
        arbol.insertar(32);
        arbol.insertar(28);
        arbol.insertar(52);
        arbol.insertar(64);
        arbol.insertar(61);
        arbol.insertar(29); 



        
        arbol.eliminar(64);
        arbol.printArbol();
        arbol.imprimirArray();
        arbol.imprimirArbolDesdeRaiz();
        
//        
//        System.out.print("Inorden: ");
//        arbol.inorden(); // 20 30 40 50 60 70 80
//
//        System.out.print("Preorden: ");
//        arbol.preorden(); // 50 30 20 40 70 60 80
//
//        System.out.print("Postorden: ");
//        arbol.postorden(); // 20 40 30 60 80 70 50
        
        
    }
    
}
