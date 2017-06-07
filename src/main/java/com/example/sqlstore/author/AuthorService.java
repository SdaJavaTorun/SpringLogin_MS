package com.example.sqlstore.author;

import com.example.sqlstore.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    AuthorRepository authorRepository;

    public List<Author> getAuthors() {

        Query query = entityManager.createQuery("SELECT a FROM Author a");
            return query.getResultList();
    }
    @Transactional
    public void saveAuthor(Author author) {
        entityManager.persist(author);
    }

}
