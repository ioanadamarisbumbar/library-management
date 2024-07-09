package com.library.library_management.service.impl;

import com.library.library_management.dto.AuthorDto;
import com.library.library_management.exception.AuthorNotFoundException;
import com.library.library_management.model.Author;
import com.library.library_management.repository.AuthorRepository;
import com.library.library_management.service.AuthorService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AuthorDto addAuthor(AuthorDto author) {
        Author authorToBeAdded = modelMapper.map(author, Author.class);

        log.info("The author was aded.");
        return modelMapper.map(authorRepository.save(authorToBeAdded), AuthorDto.class);
    }

    @Override
    public AuthorDto updateAuthor(String id, AuthorDto author) {
        Optional<Author> optionalAuthor
                = authorRepository.findById(id);

        if (optionalAuthor.isEmpty()) {
            log.error("The author with the ID {} was not found.", id);
            throw new AuthorNotFoundException("The author ID does not exist!");
        }

        Author existingAuthor = optionalAuthor.get();
        existingAuthor.setFirstName(StringUtils.isEmpty(author.getFirstName()) ? existingAuthor.getFirstName() : author.getFirstName());
        existingAuthor.setLastName(StringUtils.isEmpty(author.getLastName()) ? existingAuthor.getLastName() : author.getLastName());
        existingAuthor.setDateOfBirth(author.getDateOfBirth() == null ? existingAuthor.getDateOfBirth() : author.getDateOfBirth());
        existingAuthor.setNationality(StringUtils.isEmpty(author.getNationality()) ? existingAuthor.getNationality() : author.getNationality());
        existingAuthor.setBiography(StringUtils.isEmpty(author.getBiography()) ? existingAuthor.getBiography() : author.getBiography());

        Author a = authorRepository.save(existingAuthor);
        log.info("The author with the ID {} was updated.", id);

        return modelMapper.map(a, AuthorDto.class);
    }

    @Override
    public void deleteAuthor(String id) {
        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()) {
            log.error("The author with the ID {} was not found.", id);
            throw new AuthorNotFoundException("The author ID does not exist!");
        }

        log.info("The author with the ID {} was deleted.", id);
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorDto> retrieveAuthors() {
        List<Author> authors = authorRepository.findAll();

        log.info("The list of authors was retrieved.");
        return authors.stream().map(author -> modelMapper.map(author, AuthorDto.class)).collect(Collectors.toList());
    }

    @Override
    public AuthorDto retrieveAuthor(String id) {
        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()) {
            log.error("The author with the ID {} was not found.", id);
            throw new AuthorNotFoundException("The author ID does not exist!");
        }


        log.info("The author with the ID {} was retrieved.", id);
        return modelMapper.map(author.get(), AuthorDto.class);
    }
}
