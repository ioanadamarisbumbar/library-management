package com.library.library_management.repository;

import com.library.library_management.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface extending MongoRepository for interacting with Author data.
 * This interface inherits methods for basic CRUD operations (Create, Read, Update, Delete)
 * on Author entities stored in a MongoDB collection.
 */
@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
}
