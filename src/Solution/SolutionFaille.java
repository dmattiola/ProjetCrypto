/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Solution;

import Contexte.ListeQuestions;
import CryptoSysteme.Pallier;

/**
 *
 * @author Dylan
 */
public class SolutionFaille {
     public static void main(String[] args){
        ListeQuestions listeQ = new ListeQuestions();
        Pallier pa = new Pallier();
        Alice alice = new Alice(pa, listeQ);
        alice.start();
        BobFaille bob = new BobFaille(pa);
        bob.start();
    }
}
