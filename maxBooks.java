
/**
 * class for max books linkd list
 */
public class maxBooks
{
   
   public Member m;
   public maxBooks next;
    public maxBooks  prev;
  
    

    /**
     * Constructor for objects of class maxBooks
     */
    public maxBooks()
    {
        // initialise instance variables
        Member m=null;
        maxBooks next=null; 
        maxBooks prev=null;
        
    }
    
    public maxBooks(Member member,maxBooks nextt,maxBooks prevv){
        m=member;
        next=nextt;
        prev=prevv;
     
    }
   
        
        
    
    public void insartToList(Member member,maxBooks []arr){   
        if(arr[member.numOfBooks].next==null){         
            maxBooks max=new maxBooks(member,null,arr[member.numOfBooks]);
            arr[member.numOfBooks].next=max;
        }else{
            maxBooks max=new maxBooks(member,arr[member.numOfBooks].next,arr[member.numOfBooks]);
            arr[member.numOfBooks].next=max;      
    }
}
    
    
 public void delete (Member member,maxBooks []arr){   
     int i=member.numOfBooks;
     maxBooks p=new maxBooks();
     p=arr[i];
     while(p.next!=null){
         if(p.m.Sid==member.Sid){
             p.next.prev=p.prev;
             p.prev.next=p.next;
            }else{
                p=p.next;
            }
        }
    }
     
     
  
 }
    
    
    
    
    
    
    

