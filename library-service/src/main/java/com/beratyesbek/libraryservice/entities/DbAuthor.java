package com.beratyesbek.libraryservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

import static com.beratyesbek.libraryservice.constants.HibernateDbConstants.COL_NAME;
import static com.beratyesbek.libraryservice.constants.HibernateDbConstants.TABLE_AUTHOR;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_AUTHOR)
public class DbAuthor extends DbEntity {

    @Column(name = COL_NAME, nullable = false, length = 60)
    private String name;
    @OneToMany(mappedBy = "dbAuthor")
    private List<DbBook> dbBooks;

}
