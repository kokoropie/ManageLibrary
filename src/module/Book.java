package module;

import java.util.Date;

public class Book {
    private char name;
    private int price;
    private Author author;
    private Date entryDate;
    private Date publicationDate;

    public Book(char name, int price, Author author, Date entryDate, Date publicationDate) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.entryDate = entryDate;
        this.publicationDate = publicationDate;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
