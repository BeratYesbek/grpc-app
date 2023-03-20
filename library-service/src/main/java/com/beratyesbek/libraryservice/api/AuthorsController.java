package com.beratyesbek.libraryservice.api;

import com.beratyesbek.libraryservice.dao.AuthorDao;
import com.beratyesbek.libraryservice.entities.DbAuthor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/authors")
public class AuthorsController {

    private final AuthorDao authorDao;

    public AuthorsController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @PostMapping
    public ResponseEntity<DbAuthor> create(@RequestBody DbAuthor dbAuthor) {
        return new ResponseEntity<>(
                authorDao.save(dbAuthor),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<DbAuthor>> findAll() {
        return new ResponseEntity<>(
                authorDao.findAll(),
                HttpStatus.OK
        );
    }
}
