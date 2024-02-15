package it.be.epicode.EsercizioQuattro.repositories;

import it.be.epicode.EsercizioQuattro.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorsRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByEmail(String email);
}
