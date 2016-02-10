package Contexte;

import java.util.ArrayList;

/**
 *
 * @author Dylan
 */
public class ListeQuestions {
    
    private ArrayList<Question> listeQuestion;
    
    public ListeQuestions(){
        listeQuestion = new ArrayList<>();
        listeQuestion.add(new Question(1,"Q1 : Question n°1"));
        listeQuestion.add(new Question(2,"Q2 : Question n°2"));
        listeQuestion.add(new Question(3,"Q3 : Question n°3"));
        listeQuestion.add(new Question(4,"Q4 : Question n°4"));
        listeQuestion.add(new Question(5,"Q5 : Question n°5"));
        listeQuestion.add(new Question(6,"Q6 : Question n°6"));
        listeQuestion.add(new Question(7,"Q7 : Question n°7"));
        listeQuestion.add(new Question(8,"Q8 : Question n°8"));
        listeQuestion.add(new Question(9,"Q9 : Question n°9"));
        listeQuestion.add(new Question(10,"Q10 : Question n°10"));
    }

    public ArrayList<Question> getListeQuestion(){ return listeQuestion; }

    public void setListeQuestion(ArrayList<Question> listeQuestion){ this.listeQuestion = listeQuestion; }
       
}