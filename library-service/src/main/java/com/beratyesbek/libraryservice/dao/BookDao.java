package com.beratyesbek.libraryservice.dao;

import com.beratyesbek.libraryservice.entities.DbBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<DbBook, Integer> {
}
