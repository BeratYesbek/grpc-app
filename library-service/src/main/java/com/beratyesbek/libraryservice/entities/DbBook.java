package com.beratyesbek.libraryservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;

import static com.beratyesbek.libraryservice.constants.HibernateDbConstants.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = TABLE_BOOK)
public class DbBook extends DbEntity {

    @Column(name = COL_NAME, nullable = false)
    private String name;
    @Column(name = COL_YEAR, nullable = false)
    private int year;
    @Column(name = COL_PAGE_SIZE, nullable = false)
    private int pageSize;
    @Column(name = COL_PRICE, nullable = false)
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COL_AUTHOR_ID, referencedColumnName = COL_ID)
    @JsonProperty("author")
    private DbAuthor dbAuthor;

}
