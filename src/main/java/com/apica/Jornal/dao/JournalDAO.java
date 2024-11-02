package com.apica.Jornal.dao;

import com.apica.Jornal.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalDAO extends JpaRepository<Journal,Integer> {
    List<Journal> findByUserNameOrderByEventDateDesc(String userName);
}
