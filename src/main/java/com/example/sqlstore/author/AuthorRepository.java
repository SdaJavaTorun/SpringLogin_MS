package com.example.sqlstore.author;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    Optional<Author> findByName(String name);

    @Query("SELECT a FROM Author a WHERE LOWER (a.name) = LOWER(:name)")
    List<Author> findSomeCustomMethod(@Param("name") String name);

}
