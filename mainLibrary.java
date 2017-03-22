
/**
 * library
 * @authoredelman yuval
 * @version 27/2/13
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class mainLibrary
{

/**
 * insart member to the max list linkd list array
 * member-the member to insart
 * arr-the link list array
 */
 static public void insartToList(Member member,maxBooks []arr){
     int i=member.numOfBooks;
      maxBooks p=new maxBooks();
      if(i!=0){       
             maxBooks cur=new maxBooks(member,arr[i].next,arr[i]);
             member.mb=cur;
             if(arr[i].next!=null){  
                arr[i].next.prev=cur;
            }
             arr[i].next=cur;
             return;
            }           
}

/**
 * delete member to the max list linkd list array
 * member-the member to delete
 * arr-the link list array
 */
static  public void delete (Member member,maxBooks []arr){   
     int i=member.numOfBooks;
     maxBooks p=new maxBooks();
     p=member.mb;
    if(p!=null){
        p.prev.next=p.next;
        if(p.next!=null){
        p.next.prev=p.prev;       
     }
    
    }
   }
    
    /**
     * print the member thet have the max numbers of books
     * 
     */
static  public void printListMax (maxBooks []arr){
     maxBooks p=new maxBooks();
     int c=0;
     int n=0;
     for(int i=10;i>0 && c==0 ;i--){   
         n=i;
         p=arr[i].next;
         while(p!=null){
             System.out.println(p.m.last_name+" "+p.m.Sid);
             p=p.next;
             c=1;
            }
        }    
}       
    
    
    
 /**
  * rent abook to a member
  */
static public void rent( int id, String book,BookTree b, RBTree t,maxBooks[] max){
    delete(t.find(id).member,max);
    b.findAndGive(book,t.find(id).member); //log m+log n
    t.findAndGive(id,b.find(book));//log n+log m    
    insartToList(t.find(id).member,max);
}
 
/**
 * recive a book from a member
 */
static public void recive( int id, String book,BookTree b, RBTree t,maxBooks[] max){
    delete(t.find(id).member,max);
    b.findAndReturn(book);//log m
   insartToList(t.find(id).member,max);   
}

/**
 * end the membership to a member
 */
static public void End( int id, RBTree t){
    t.findAndDelete(id);
    System.out.println(id+" membership has ended");
}

/**
 * check if string is a number
 */
public static boolean isInteger(String s) {
    try { 
        Integer.parseInt(s); 
    } catch(NumberFormatException e) { 
        return false; 
    }
    // only got here if we didn't return false
    return true;
}


  public static void main( String [ ] args ) {
    maxBooks[]  max=new  maxBooks[11];//build array of linkd list
    int i=0;
    while (i<11){
        max[i]=new maxBooks() ;
        i++;
    } 
    ////////////////////////////////////////////////////////////////////////////
      //////////////////////////////////////////////////////////////////////////
      BookTree b=new BookTree();//book tree
      //////////////////////////////////////////////////////////////////////////
       RBTree t = new RBTree();//Member tree
      ///////////////////////////////////////////////////////////////////////////
      // SET THE BOOKS IN THE BOOK TREE
      Book b1=new Book("AB3323");
      Book b2=new Book("AX2211");
      Book b3=new Book("AX6554");
      Book b4=new Book("QW2333");
      Book b5=new Book("AA9999");
      Book b6=new Book("HG4434");
      Book b7=new Book("DD2222");
      Book b8=new Book("AA2211");
      Book b9=new Book("XC5479");
      Book b10=new Book("ZZ9984");
      Book b11=new Book("BN4532");
      Book b12=new Book("ER4367");
        b.insert(b1.getName(),b1);
        b.insert(b2.getName(),b2);
        b.insert(b3.getName(),b3);
        b.insert(b4.getName(),b4);
        b.insert(b5.getName(),b5);
        b.insert(b6.getName(),b6);
        b.insert(b7.getName(),b7);
        b.insert(b8.getName(),b8);
        b.insert(b9.getName(),b9);
        b.insert(b10.getName(),b10);
        b.insert(b11.getName(),b11);
        b.insert(b12.getName(),b12);
  /////////////////////////////////////////////////////////////////////////////////  
      
    try {
            File f = new File("data.txt"); //read from file, to test your own file replace the file name!
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] details = line.split(" ");
                int j=details.length;
                if(j==4)  // case 1 or 2 
                {
                    String n=details[0];
                    int id = Integer.parseInt(details[1]);
                    String bk=details[2];
                    String s=details[3];
                    if(s.compareTo( "+")==0){
                        rent(id,bk,b,t,max);                    
                    }else{
                        recive(id,bk,b,t,max);
                    }
                }
                if(j==3) // case 3 or 4
                {
                     String s=details[0];
                     String n=details[1];
                     int id = Integer.parseInt(details[2]);
                     if(s.compareTo( "+")==0){
                         t.insert(new Member(id,n));
                        }else{
                            End(id,t);
                        }
                    }
                if(j==2) // sheilta
                {                                               
                    if(isInteger(details[1])){ // case 1
                        int id = Integer.parseInt(details[1]);
                        System.out.println("the member "+id+" have:");
                        t.printBooks(id);
                        System.out.println("************************");
                    }
                    else{ 
                        if(details[1].compareTo( "!")==0){ //case 3
                            System.out.println("MAX BOOKS MEMBER");
                            printListMax(max);
                            System.out.println("************************");
                    }
                    else{ //case 2
                        System.out.println("the book "+details[1]+" is at:");
                        b.findAndPrint(details[1]); 
                        System.out.println("************************");
                    }
                 }
               }                                       
            }
        }catch (FileNotFoundException e){         
            e.printStackTrace();  
    }    
   }
}
