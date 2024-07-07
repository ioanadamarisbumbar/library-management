package com.library.library_management.repository;

import com.library.library_management.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
//public interface AuthorRepository extends CrudRepository<Author, Long> {
}
