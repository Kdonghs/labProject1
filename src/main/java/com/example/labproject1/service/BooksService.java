package com.example.labproject1.service;

import com.example.labproject1.domain.Books;
import com.example.labproject1.repository.BookRepository;

import java.util.List;

public class BooksService {
    private final BookRepository repository;

    public BooksService(BookRepository repository) {
        this.repository = repository;
    }

    public long join(Books books){
        repository.save(books);
        return books.getId();
    }

    public List<Books> findMembers(){
        return repository.findAll();
    }

    public List<Books> search(String name, String writer){return repository.search(name,writer);}

    public void del(Long[] ids){
        repository.del(ids);
    }

    public boolean compare(String name, String writer){
        if(repository.findByName(name).isEmpty() && repository.findByWriter(writer).isEmpty()){
            return true;
        }
        return false;
    }

}
