package com.beratyesbek.libraryservice.entities;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

import static com.beratyesbek.libraryservice.constants.HibernateDbConstants.COL_CREATED_AT;
import static com.beratyesbek.libraryservice.constants.HibernateDbConstants.COL_ID;

@Getter
@Setter
@MappedSuperclass
public abstract class DbEntity implements Serializable {

    @Id
    @Column(name = COL_ID)
    @Access(AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = COL_CREATED_AT, nullable = false)
    @CreationTimestamp
    protected Date createdAt;
}
