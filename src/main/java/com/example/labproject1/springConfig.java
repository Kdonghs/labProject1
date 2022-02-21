package com.example.labproject1;

import com.example.labproject1.repository.BookRepository;
import com.example.labproject1.repository.JdbcTBookRepository;
import com.example.labproject1.service.BooksService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class springConfig {
    private final DataSource dataSource;

    public springConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public BooksService BooksService(){
        return new BooksService(bookRepository());
    }

    @Bean
    public BookRepository bookRepository(){
        return new JdbcTBookRepository(dataSource);
    }
}
