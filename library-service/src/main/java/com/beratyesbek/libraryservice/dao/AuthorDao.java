package com.beratyesbek.libraryservice.dao;

import com.beratyesbek.libraryservice.entities.DbAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<DbAuthor, Integer> {
}
