package it.be.epicode.EsercizioQuattro.services;

import it.be.epicode.EsercizioQuattro.exceptions.BadRequestException;
import it.be.epicode.EsercizioQuattro.entities.Author;
import it.be.epicode.EsercizioQuattro.exceptions.BadRequestException;
import it.be.epicode.EsercizioQuattro.exceptions.NotFoundException;
import it.be.epicode.EsercizioQuattro.payloads.AuthorDTOClass;
import it.be.epicode.EsercizioQuattro.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AuthorsService {

    @Autowired
    private AuthorsRepository authorsRepository;

    public Author save(AuthorDTOClass authorDTOClass) {
        authorsRepository.findByEmail(authorDTOClass.getEmail()).ifPresent(user -> {
            throw new BadRequestException("L'email " + authorDTOClass.getEmail() + " è già stata utilizzata");
        });
        Author author = new Author(authorDTOClass.getId(),authorDTOClass.getName(),authorDTOClass.getSurname(),authorDTOClass.getEmail(),authorDTOClass.getPassword(),"https://ui-avatars.com/api/?name=" + authorDTOClass.getName().charAt(0) + "+" + authorDTOClass.getSurname().charAt(0));
        return authorsRepository.save(author);
    }

    public Page<Author> getAuthors(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return authorsRepository.findAll(pageable);
    }

    public Author findById(int id) {
        return authorsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Author found = this.findById(id);
        authorsRepository.delete(found);
    }

    public Author findByIdAndUpdate(int id, Author body) {

        Author found = this.findById(id);
        found.setEmail(body.getEmail());
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setDateOfBirth(body.getDateOfBirth());
        found.setAvatar(body.getAvatar());
        return authorsRepository.save(found);
    }
}
