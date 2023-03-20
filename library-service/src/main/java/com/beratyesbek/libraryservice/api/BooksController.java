package com.beratyesbek.libraryservice.api;

import com.beratyesbek.libraryservice.dao.BookDao;
import com.beratyesbek.libraryservice.entities.DbBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BooksController {

    private final BookDao bookDao;

    public BooksController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @PostMapping
    public ResponseEntity<DbBook> create(@RequestBody DbBook dbBook) {
        return new ResponseEntity<>(
                bookDao.save(dbBook),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<DbBook>> findAll() {
        return new ResponseEntity<>(
                bookDao.findAll(),
                HttpStatus.OK
        );
    }
}
