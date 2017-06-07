package com.example.sqlstore.book;

import com.example.sqlstore.author.Author;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    private Integer id;
    private String title;
    private Author author;

    public Book() {
    }


    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @ManyToOne
    @JoinColumn(name = "author_id")
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
