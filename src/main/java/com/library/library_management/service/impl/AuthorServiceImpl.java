package com.library.library_management.service.impl;

import com.library.library_management.exception.AuthorNotFoundException;
import com.library.library_management.model.Author;
import com.library.library_management.repository.AuthorRepository;
import com.library.library_management.service.AuthorService;
import com.library.library_management.dto.AuthorDto;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AuthorDto addAuthor(AuthorDto author) {
        Author authorToBeAdded = modelMapper.map(author, Author.class);
        return modelMapper.map(authorRepository.save(authorToBeAdded), AuthorDto.class);
    }

    @Override
    public AuthorDto updateAuthor(String id, AuthorDto author) {
        Optional<Author> optionalAuthor
                = authorRepository.findById(id);

        if (optionalAuthor.isEmpty()) {
            throw new AuthorNotFoundException("The author ID does not exist!");
        }

        Author existingAuthor = optionalAuthor.get();
        existingAuthor.setFirstName(StringUtils.isEmpty(author.getFirstName()) ? existingAuthor.getFirstName() : author.getFirstName());
        existingAuthor.setLastName(StringUtils.isEmpty(author.getLastName()) ? existingAuthor.getLastName() : author.getLastName());
        existingAuthor.setDateOfBirth(author.getDateOfBirth() == null ? existingAuthor.getDateOfBirth() : author.getDateOfBirth());
        existingAuthor.setNationality(StringUtils.isEmpty(author.getNationality()) ? existingAuthor.getNationality() : author.getNationality());
        existingAuthor.setBiography(StringUtils.isEmpty(author.getBiography()) ? existingAuthor.getBiography() : author.getBiography());

        Author a = authorRepository.save(existingAuthor);
        return modelMapper.map(a, AuthorDto.class);
    }

    @Override
    public void deleteAuthor(String id) {
        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()) {
            throw new AuthorNotFoundException("The author ID does not exist!");
        }
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorDto> retrieveAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(author -> modelMapper.map(author, AuthorDto.class)).collect(Collectors.toList());
    }

    @Override
    public AuthorDto retrieveAuthor(String id) {
        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()) {
            throw new AuthorNotFoundException("The author ID does not exist!");
        }

        return modelMapper.map(author.get(), AuthorDto.class);
//        return Optional.of(new Author(11l, "a", "b"));
    }
}
