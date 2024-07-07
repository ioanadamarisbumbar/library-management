package com.library.library_management.service.impl;

import com.library.library_management.exception.AuthorNotFoundException;
import com.library.library_management.model.Author;
import com.library.library_management.repository.AuthorRepository;
import com.library.library_management.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
//        return new Author(11l, "a", "b");
    }

    @Override
    public Author updateAuthor(String id, Author author) {
        Optional<Author> optionalAuthor
                = authorRepository.findById(id);

        if (optionalAuthor.isEmpty()) {
            throw new AuthorNotFoundException("The author ID does not exist!");
        }

        Author savedAuthor = optionalAuthor.get();
        savedAuthor.setFirstName(author.getFirstName().isEmpty() ? savedAuthor.getFirstName() : author.getFirstName());
        savedAuthor.setLastName(author.getLastName().isEmpty() ? savedAuthor.getLastName() : author.getLastName());
//        savedAuthor.setDateOfBirth(author.getDateOfBirth().() ? savedAuthor.getLastName() : author.getLastName());
        savedAuthor.setNationality(author.getNationality().isEmpty() ? savedAuthor.getNationality() : author.getNationality());
        savedAuthor.setBiography(author.getBiography().isEmpty() ? savedAuthor.getBiography() : author.getBiography());

        return authorRepository.save(savedAuthor);
//        return new Author(11l, "a", "b");
    }

    @Override
    public void deleteAuthor(String id) {
        Optional<Author> author = authorRepository.findById(id);

//        if (ObjectUtils.isEmpty(author)) {
        if (author.isEmpty()) {
            throw new AuthorNotFoundException("The author ID does not exist!");
        }
        authorRepository.deleteById(id);
//        System.out.println("delete author");
    }

    @Override
    public List<Author> retrieveAuthors() {
        return authorRepository.findAll();
//        return new ArrayList<>(Arrays.asList(new Author(11l, "a", "b"), new Author(15l, "ab", "ba")));
    }

    @Override
    public Optional<Author> retrieveAuthor(String id) {
        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()) {
            throw new AuthorNotFoundException("The author ID does not exist!");
        }
        return author;
//        return Optional.of(new Author(11l, "a", "b"));
    }
}
