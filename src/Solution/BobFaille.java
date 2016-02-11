/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Solution;

import CryptoSysteme.CryptoSysteme;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Dylan
 */
public class BobFaille extends Personne.Bob {

    public BobFaille(CryptoSysteme cs) {
        super(cs);
    }
    
    @Override
    public void run() {
        try{
            Socket s = new Socket();
            s.connect(new InetSocketAddress(InetAddress.getByName("localhost"),48));
            
            // Bob reçoit la liste des réponses encryptées
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            ArrayList<String> listeReponseEncryptee = new ArrayList<>();
            for(int i = 1; ; i++){
                String rep = br.readLine();
                if (rep.isEmpty()){
                    break;
                } else {
                    listeReponseEncryptee.add(rep);
                }
            }
            
            for (int i = 0; i < listeReponseEncryptee.size(); i++){
                System.out.println("Bob : Numéro Réponse Selectionnée : "+(i+1));
                System.out.println("Bob : Réponse Selectionnée Encryptée :"+listeReponseEncryptee.get(i));
                BigInteger reponse = new BigInteger(listeReponseEncryptee.get(i));
                // on a choisit notre réponse
                // on multiplie l'aléatoire à la réponse choisie
                BigInteger aleatoire, nbAleatoire;
                do{
                    nbAleatoire = new BigInteger(Integer.toString(new Random().nextInt(512)));
                    System.out.println("Bob : NB aléatoire à ajouter : "+nbAleatoire);
                    aleatoire = cs.encrypter(nbAleatoire);
                } while (aleatoire == null);
                BigInteger reponseChoisie = aleatoire.multiply(reponse);
                System.out.println("Bob : Réponse Selectionnée Modifiée Aleatoire :"+reponseChoisie);

                // on envoie la réponse choisie
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                bw.write(reponseChoisie.toString() + '\n');
                bw.flush();

                // on reçoit la réponse décrypter par alice que bob peut décryter pour connaitre la réponse
                String reponseAlice = br.readLine();
                System.out.println("Bob : Reponse Decryptée Aleatoire Reçu : "+reponseAlice);
                BigInteger reponseFinale = new BigInteger(reponseAlice).subtract(nbAleatoire);
                System.out.println("Bob : Reponse Decryptée Originale : "+reponseFinale);
                System.out.println("Bob : Texte original : "+new String(reponseFinale.toByteArray()));
            }
            
            //Fin de échanges
            s.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}