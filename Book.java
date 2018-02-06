//Alec Mcdaugale
//This program creates a library of books


import java.lang.*;
import java.util.Scanner;
import java.util.Vector;

//book class
public class Book
{
    //for a book, we need at least a title, and an author
    //i have not yet implemented the description, but there is a way to add one
    private String author;
    private String title;
    private String description;

   //constructor to create book, calls itself to add author when unknown so !null
    public void Book(String Title)
    {
        this.Book(Title, "unknown");
    }
    //The default constructor to create a book, title and author
    public void Book( String Title, String Author)
    {
        this.title = Title;
        this.author = Author;
    }
    //a way to set the author if later discovered
    public void setAuthor(String author)
    {
        this.author = author;
    }
    //a way to set a description
    public void setDescription(String description)
    {
       this.description = description;
    }

    // a means to get the author in another class
    public String getAuthor()
    {
        return author;
    }
    //get the title
    public String getTitle()
    {
        return title;
    }
    //prints out the title and author of the book
    //did not include the description yet
    public void printBook()
    {
        System.out.println("title: " + title + " by: " + author);
    }
}





// a book collection class

class BookCollection extends Book
{
    //created a vector which stores the book object
    private Vector <Book>library = new <Book>Vector();

    //index to reference books in object
    private int index = 0;


    // calls itself to add book object with no author
    public void addBook(String title)
    {
        this.addBook(title, "unknown");
    }
    //creates a book object and adds it to the array
    public void addBook(String title, String author)
    {
        Book b = new Book();



        b.Book(title, author);


        library.add(index, b);
        library.elementAt(index).printBook();
        index++;
    }
    //prints the list of objects in the vector using the book class
    public void viewLibrary()
    {
        for(int i = 0; i < index; i++)
        {
            library.elementAt(i).printBook();
        }
    }
    //method to remove book
    public void removeBook(String title)
    {
        library.remove(findBook(title));
        index--;
    }
    //method to find a book and return index

    private int findBook(String title)
    {
        int location = 0;
        for(int i = 0; i < index; i++)
        {

            System.out.println (title);
           if (title.equals( library.elementAt(i).getTitle()))
            {
                System.out.println(title + "is at position " + i);
                 location = i;
            }else
           {
               System.out.println("Book not in library. Check spelling.");
           }

        }
        return location;
    }
    private void organiseBooks()
    {
        int i2;
        Vector<Book> coppy = new Vector<Book>();
        for (int i = 0; i < index; i++)
        {
            i2 = i + 1;
            if(library.elementAt(i).getTitle().charAt(0) > library.elementAt(i2).getTitle().charAt(0))
            {
                coppy.addElement(library.elementAt(i2));
                coppy.addElement(library.elementAt(i));
            }else if(i == 0)
            {
                coppy.addElement(library.elementAt(i));
                coppy.addElement(library.elementAt(i2));
            }else
            {
                coppy.addElement(library.elementAt(i2));
            }
        }

            library.equals(coppy.clone());

    }








}



class UserInterface extends BookCollection
{
    private BookCollection Library = new BookCollection();
    private boolean titleCheck;
    private Scanner in = new Scanner(System.in);


    public static void main(String args[])
    {
        // UI Object
        UserInterface UI = new UserInterface();


        //only need to store the mode
        int mode;

        boolean i = true;
        //I created an infinite loop here until exit.
        while (i == true)
        {

            //select a mode
            mode = UI.getModeUser();

            //if logic to determine mode
            if (mode == 1)
            {

                UI.userAddBook();
            }else if (mode == 2)//view library
            {

                UI.usersLibrary();
            }else if(mode == 3)
            {

                UI.userRemove();
            } else if (mode == 0)//if 0 exit
            {
                System.exit(0);

            }else//else not a valid entry
            {
                System.out.println("Not a valid entry.");
            }

        }

    }

    private int getModeUser()
    {
        System.out.println("Choose Mode: 1 = New Book  2 = View Library 3 = Remove Book 0 = Exit");
        return in.nextInt();
    }
    private String getTitleFromUser()
    {
        titleCheck = true;
        String title = null;
        while (titleCheck == true)
        {


            System.out.println("Enter the Title.");
            title = in.nextLine();

            if (title.isEmpty() == false) //If title is not empty, exit loop
            {
                titleCheck = false;
            }

        }
        return title;
    }

    private String getAuthorFromUser()
    {
        System.out.println("Enter the author, or leave blank.");
        return in.nextLine();
    }

    private void userRemove()
    {
        String title;

        System.out.println("Please enter the name of the book you wish to remove.");
        title = this.getTitleFromUser();


        Library.removeBook(title);
    }
    private void usersLibrary()
    {
        Library.viewLibrary();
    }
    private void userAddBook()
    {
        String title = this.getTitleFromUser();
        String author = this.getAuthorFromUser();

        //Add author
        if (author.isEmpty())
        {
            Library.addBook(title);
        }else
        {
            Library.addBook(title, author);
        }
    }

}