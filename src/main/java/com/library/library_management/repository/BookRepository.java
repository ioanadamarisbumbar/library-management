package com.library.library_management.repository;

import com.library.library_management.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
//public interface BookRepository extends CrudRepository<Book, Long> {
}
