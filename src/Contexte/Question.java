package Contexte;

/**
 *
 * @author Dylan
 */
public class Question {
    
    private int num;
    private String question;
    
    public Question(int num, String question){
        this.num = num;
        this.question = question;
    }

    public int getNum(){ return num; }
    public String getQuestion(){ return question; }
    
    public void setNum(int num){ this.num = num; }
    public void setQuestion(String question) { this.question = question; }
    
}