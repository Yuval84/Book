
/**
 * Member class
 */
import java.util.*;
public class Member
{
    public String last_name;
    public int Sid=0;//id
    private Book [] books;//list of books
    public int numOfBooks;//num of books
    private int max=10; //max books num
    public maxBooks mb;//maxBooks pointer
     /**
     * Constructor for objects of class member
     */
    public Member(int id , String name)
    {
       Sid=id;
      last_name=name;
      books=new  Book [max];
      numOfBooks=0;
    }

    public int getId()
    {
        return Sid; 
    }
    public String getName()
    {
        return last_name;
    }
    public int numOfBooks()
    {
        return numOfBooks;
    }
    
    
    //print all the books 
    public void  getBooks()
    {
        for(int i=0;i<max;i++){
            if (books[i]!=null){
                System.out.println(books[i].b_id);
            }
        }
    }
    
    public void returnBook(String book)
    {
         for(int i=0;i<max;i++){
             if(books[i]!=null){
                 if (books[i].getName().compareTo(book)==0){
                     books[i]=null;
                     numOfBooks--;
                     System.out.println("book "+book+" return");
                     
                     return;
                    }
                }
        }
    }
    
     public void returnAllBooks()
    {
         for(int i=0;i<max;i++){
             if(books[i]!=null){
                    books[i].m=null;
                     books[i]=null;
                     numOfBooks-=1;               
                    }
                    
                }
        
    }
    
    
    public void setBook( Book book)
    {
       int i=0;
       while (i<max){
           if (books[i]==null){
               books[i]=book;
               numOfBooks+=1;
        
               return ;
            }
            else i++;
        }
        return ;
    }
       
    public void setName(String name)
    {
        last_name=name;
    }
            
    public void setId(int id)
    {
        Sid=id;
    }
    
    
    
    
    
    
    
    
    
    
    
    
   
   
}
