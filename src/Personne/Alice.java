/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Personne;

import Contexte.ListeQuestions;
import Contexte.Question;
import Contexte.Reponse;
import CryptoSysteme.CryptoSysteme;
import java.util.ArrayList;

/**
 *
 * @author Dylan
 */
public class Alice extends Thread {
    
    public CryptoSysteme cs;
    private ArrayList<Reponse> listReponses;
    
    public Alice(CryptoSysteme cs, ListeQuestions listeQ){
        this.cs = cs;
        listReponses = new ArrayList<>();
        for(Question q : listeQ.getListeQuestion()){
            Reponse reponse = new Reponse(q,"R"+q.getNum()+" : Réponse n°"+q.getNum());
            listReponses.add(reponse);
        }
    }

    public ArrayList<Reponse> getListReponses(){ return listReponses; }

    public void setListReponses(ArrayList<Reponse> listReponses){ this.listReponses = listReponses; }
    
}