//Alec Mcdaugale
//This program creates a library of books


import java.lang.*;
import java.util.InputMismatchException;
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

class BookCollection //extends Book
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




        // i want to put this method here, but im still working on a bubble sort
        //this.organiseBooks();
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
        //if negative 1 is returned, title not found
        int location = -1;

            for (int i = 0; i < index; i++) {

                //System.out.println (title);
                if (title.equals(library.elementAt(i).getTitle())) {
                    System.out.println(title + "is at position " + i);
                    location = i;
                }
            }

        return location;
    }
    //a method to organise books using a bubble sort type of algorithm, needs work still but kinda works, and by that i mean
    //you need to manually iterate it until everything is sorted, but for now also avoid entering two titles that start
    //with the same char.
    public void organiseBooks()
    {



        int i2;
        //uses a vector to coppy
        Vector<Book> libraryCoppy = new Vector<Book>();

        for (int i = 0 ; i < index - 1; i++)
        {
            i2 = i + 1;
            System.out.println("iteration: " + i + " of total: " + index);

            if (i != this.alphaChar(i, i2))
            {
                for (int dex = 0; dex < i; dex++)
                {
                    libraryCoppy.addElement(library.elementAt(dex));
                }

                libraryCoppy.addElement(library.elementAt(i2));
                libraryCoppy.addElement((library.elementAt(i)));

                if (i2 < index - 1) {
                    for (int dex = i2 + 1; dex < index; dex++)
                    {
                        libraryCoppy.addElement((library.elementAt(dex)));
                    }
                }
                library.clear();
                for (int dex = 0; dex < index; dex++)
                {
                    library.addElement(libraryCoppy.elementAt(dex));
                }

                //added this to help debug
                this.viewLibrary();
            }
        }

    }
    // a method to return the first item in alphabetical order, compares the first char and does not take into account
    //when there is more than one book title with the same letter
    private int alphaChar(int i1, int i2) {
        char ch1 = library.elementAt(i1).getTitle().charAt(0);
        char ch2 = library.elementAt(i2).getTitle().charAt(0);

        //returns ch1 if it comes first in alphabetical order, implying a < b according to ASCII standard
        if( ch1 < ch2)
        {
            System.out.println(ch1 + ", " + ch2 );
            return i1;
        }else{
            System.out.println(ch2 + ", " + ch1 );
            return i2;

        }


    }

}


//a user interface class
class UserInterface //extends BookCollection
{
    //create library obj, a boolean to check for title, and a scanner to take user input.
    private BookCollection Library = new BookCollection();
    private boolean titleCheck;
    private Scanner in = new Scanner(System.in);


    // main method, navigates the user input and modes with an if statement in an infinite loop
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
            }else if(mode == 3)//remove book
            {

                UI.userRemove();
            } else if (mode==4)//secret testing mode, not displayed to user
            {

                UI.test();
            }
            else if (mode == 0)//if 0 exit
            {
                System.exit(0);

            }else//else not a valid entry
            {
                System.out.println("Not a valid entry.");
            }

        }

    }

    //get the mode selection from the user
    private int getModeUser()
    {

        System.out.println("Choose Mode: 1 = New Book  2 = View Library 3 = Remove Book 0 = Exit");
        int mode = -1;

        //catch to check the int error where the input does not match
        try {
            mode = in.nextInt();
        }catch (InputMismatchException e)
        {
            System.out.println("must enter a mode number!");
            in.nextLine();
        }

        return mode;
    }
    //get the title from the user
    private String getTitleFromUser()
    {
        //

        titleCheck = true;
        String title = null;
        System.out.println("Enter the Title.");

        while (titleCheck == true)
        {



            title = in.nextLine();

            if (title.isEmpty() == false) //If title is not empty, exit loop
            {
                titleCheck = false;
            }

        }
        return title;
    }
    //get the author from the user
    private String getAuthorFromUser()
    {
        System.out.println("Enter the author, or leave blank.");
        return in.nextLine();
    }
    //get the title to remove from the user
    private void userRemove()
    {
        String title;

        System.out.println("Please enter the name of the book you wish to remove.");
        title = this.getTitleFromUser();


        Library.removeBook(title);
    }
    //a method to view the library
    private void usersLibrary()
    {
        Library.viewLibrary();
    }
    //a method to add the book, calls the get title and get author to send the information to the object
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
    //secret class test, hidden from user
    private void test()
    {
        System.out.println("Congratulations, you have found the secret test area.");
        Library.organiseBooks();
    }
}
