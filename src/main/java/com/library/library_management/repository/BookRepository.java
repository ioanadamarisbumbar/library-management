package com.library.library_management.repository;

import com.library.library_management.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface extending MongoRepository for interacting with Book data.
 * This interface inherits methods for basic CRUD operations (Create, Read, Update, Delete)
 * on Book entities stored in a MongoDB collection.
 */
@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}
