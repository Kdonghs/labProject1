package com.example.labproject1.repository;

import com.example.labproject1.domain.Books;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Books save(Books books);
    Optional<Books> findById(long id);
    Optional<Books> findByName(String name);
    Optional<Books> findByWriter(String writer);
    Optional<Books> findByPrice(int price);
    Optional<Books> findByBuydate(Object buydate);
    List<Books> findAll();
    List<Books> search(String name, String writer);
    void del(Long[] ids);
}
