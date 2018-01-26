import java.lang.*;
import java.util.Scanner;
import java.util.Vector;

public class Book
{
    private String author;
    private String title;
    private String description;


    public void Book(String Title)
    {
        this.Book(Title, "unknown");
    }
    public void Book( String Title, String Author)
    {
        this.title = Title;
        this.author = Author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }
    public void setDescription(String description)
    {
       this.description = description;
    }


    public String getAuthor()
    {
        return author;
    }

    public String getTitle()
    {
        return title;
    }
    public void printBook()
    {
        System.out.println("title: " + title + " by: " + author);
    }
}

class BookCollection extends Book
{
    private Vector <Book>library = new <Book>Vector();
    private Vector bookAuthor = new Vector();

    private int index = 0;

    private Scanner in = new Scanner(System.in);

    void begin()
    {
        int temp;

        System.out.println("Choose mode: 1 = new book  2 = view library");
        temp = in.nextInt();

        if (temp == 1)
        {

            addBook();
        }else if (temp == 2)
        {

            viewLibrary();
        }else
        {
            System.out.println("Not a valid entry.");
        }

    }
    private void addBook()
    {
        Book b = new Book();
        String temp;
        String temp2;

        System.out.println("Please enter the title");
        temp = in.next();
        System.out.println("Enter the author, otherwise leave blank");
        temp2 = in.next();

        b.Book(temp, temp2);


        library.add(index, b);
        library.elementAt(index).printBook();
        index++;
    }

    private void viewLibrary()
    {
        for(int i = 0; i < index; i++)
        {
            library.elementAt(i).printBook();
        }
    }
    public static void main(String args[])
    {

        BookCollection a = new BookCollection();
        int i = 1;
        while (i == 1)
        {
            a.begin();
        }


    }
}
