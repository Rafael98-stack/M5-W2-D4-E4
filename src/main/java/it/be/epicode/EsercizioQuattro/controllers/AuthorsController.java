package it.be.epicode.EsercizioQuattro.controllers;

import it.be.epicode.EsercizioQuattro.entities.Author;
import it.be.epicode.EsercizioQuattro.exceptions.BadRequestException;
import it.be.epicode.EsercizioQuattro.payloads.AuthorDTOClass;
import it.be.epicode.EsercizioQuattro.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")

public class AuthorsController {
    @Autowired
    AuthorsService authorsService;

    // 1. - POST http://localhost:3001/authors (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Author saveAuthor(@RequestBody @Validated AuthorDTOClass authorDTOClass, BindingResult validation) throws Exception {

        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }
        return authorsService.save(authorDTOClass);
    }

    // 2. - GET http://localhost:3001/authors
    @GetMapping("")
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return authorsService.getAuthors(page, size, sortBy);
    }

    // 3. - GET http://localhost:3001/authors/{id}
    @GetMapping("/{authorId}")
    public Author findById(@PathVariable int authorId){
        return authorsService.findById(authorId);
    }

    // 4. - PUT http://localhost:3001/authors/{id} (+ req.body)
    @PutMapping("/{authorId}")
    public Author findAndUpdate(@PathVariable int authorId, @RequestBody Author body){
        return authorsService.findByIdAndUpdate(authorId, body);
    }

    // 5. - DELETE http://localhost:3001/authors/{id}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int authorId) {
        authorsService.findByIdAndDelete(authorId);
    }

}
