
/**
 * class for the book 
 */
public class Book
{
    public int out=0;//0-in 1-out
    public String b_id;
    public Member m=null;//pointer to member tree
    /**
     * Constructor for objects of class Book
     */
    public Book(String bId)
    {
       b_id=bId;
       out=0;
       m=null;
    }

    public String getName(){
        return b_id;
    }
    
    public int isOut(){
        return out;
    }
    
    public Member where(){
       return m;
    }
    
   public void returnBook(){
       out=0;
       m=null;
    }
    
    public void takeBook(){
        out=1;
    }
    
    public void setMember(Member x){
        if(out==0){
        m=x;
        out=1;
      }
    } 
    
    
   
}
