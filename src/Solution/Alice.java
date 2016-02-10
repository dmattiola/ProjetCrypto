package Solution;

import Contexte.ListeQuestions;
import Contexte.Reponse;
import CryptoSysteme.CryptoSysteme;
import CryptoSysteme.Pallier;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Dylan
 */
public class Alice extends Personne.Alice {

    public Alice(CryptoSysteme cs, ListeQuestions listeQ) {
        super(cs, listeQ);
    }
    
    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(48);
            Socket s = ss.accept();
            // Alice dispose de la liste des questions
            // Alice donne ses réponses
            // et les chiffre pour que Bob ne les retrouve pas
            ArrayList<BigInteger> listeReponsesEncryptees = new ArrayList<>();
            for(Reponse reponse : super.getListReponses()){
                BigInteger rep = cs.encrypter(new BigInteger(reponse.getReponse().getBytes()));
                listeReponsesEncryptees.add(rep);
            }
            // On envoie les reponses encryptées à Bob
            System.out.println("Voici la liste des réponses :");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            for (BigInteger reponseEncryptee : listeReponsesEncryptees) {
                bw.write(reponseEncryptee.toString() + '\n');
                System.out.println(reponseEncryptee.toString());
            }
            bw.write('\n');
            bw.flush();
            // Une fois les réponses envoyées, on attend la réponse choisie par Bob
            // On le décrypte et on le renvoie à Bob
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String reponseChoisie = br.readLine();
            BigInteger repBob = cs.decrypter(new BigInteger(reponseChoisie));
            System.out.println("Réponse choisie : " + repBob.toString());
            // On renvoie alors la réponse que l'on a décrypté pour que Bob connaisse la réponse
            bw.write(repBob.toString());
            bw.flush();
            // Fin des échanges
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        ListeQuestions listeQ = new ListeQuestions();
        Alice alice = new Alice(new Pallier(), listeQ);
        alice.start();
    }
    
}