package com.example.sqlstore;

import com.example.sqlstore.author.Author;
import com.example.sqlstore.author.AuthorRepository;
import com.example.sqlstore.author.AuthorService;
import com.example.sqlstore.book.Book;
import com.example.sqlstore.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqlstoreApplication implements CommandLineRunner{

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorService authorService;

	public static void main(String[] args) {
		SpringApplication.run(SqlstoreApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		authorRepository.deleteAll();
		bookRepository.deleteAll();

		Author autor1 = new Author("Nowak");
				authorRepository.save(autor1);
		Author autor2 = new Author("Kowalski");
				authorRepository.save(autor2);



		Book book1 = new Book("Java dla ubogich", autor1);
		Book book2 = new Book("Java dla bogatych2", autor2);

		bookRepository.save(book1);
		bookRepository.save(book2);
		authorService.saveAuthor(new Author("karolina"));
		System.out.println(authorRepository.findSomeCustomMethod("NoWaK"));
		System.out.println(authorService.getAuthors());






	}
}
