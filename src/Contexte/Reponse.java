package Contexte;

/**
 *
 * @author Dylan
 */
public class Reponse {
    
    private Question question;
    private String reponse;
    
    public Reponse(Question question, String reponse){
        this.question = question;
        this.reponse = reponse;
    }

    public Question getQuestion(){ return question; }
    public String getReponse(){ return reponse; }
    
    public void setQuestion(Question question){ this.question = question; }
    public void setReponse(String reponse){ this.reponse = reponse; }
    
}