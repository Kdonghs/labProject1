package com.example.labproject1.repository;

import com.example.labproject1.domain.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTBookRepository implements BookRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTBookRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Books save(Books books) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("books").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", books.getName());
        parameters.put("writer", books.getWriter());
        parameters.put("price", books.getPrice());
        parameters.put("buydate", books.getBuydate());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        books.setId(key.longValue());

        return books;
    }

    @Override
    public Optional<Books> findById(long id) {
        List<Books> result = jdbcTemplate.query("select * from books where id = ?", bookRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Books> findByName(String name) {
        List<Books> result = jdbcTemplate.query("select * from books where name = ?", bookRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public Optional<Books> findByWriter(String writer) {
        List<Books> result = jdbcTemplate.query("select * from books where weiter = ?", bookRowMapper(), writer);
        return result.stream().findAny();
    }

    @Override
    public Optional<Books> findByPrice(int price) {
        List<Books> result = jdbcTemplate.query("select * from books where price = ?", bookRowMapper(), price);
        return result.stream().findAny();
    }

    @Override
    public Optional<Books> findByBuydate(Object buydate) {
        List<Books> result = jdbcTemplate.query("select * from books where buydate = ?", bookRowMapper(), buydate);
        return result.stream().findAny();
    }

    @Override
    public List<Books> findAll() {
        return jdbcTemplate.query("select * from books", bookRowMapper());
    }

    @Override
    public List<Books> search(String name, String writer) {
        if (!name.equals("") && writer.equals("")){
            List<Books> result = jdbcTemplate.query("select * from books where name = ?", bookRowMapper(), name);
            return result;
        }else if (name.equals("") && !writer.equals("")){
            List<Books> result = jdbcTemplate.query("select * from books where writer = ?", bookRowMapper(), writer);
            return result;

        }else if (!name.equals("") && !writer.equals("")){
            List<Books> result = jdbcTemplate.query("select * from books where name = ? and writer = ?", bookRowMapper(), name, writer);
            return result;
        }

       return findAll();
    }

    @Override
    public void del(Long[] ids) {
        jdbcTemplate.update("delete from books where id = ?;",ids);
    }


    private RowMapper<Books> bookRowMapper(){
        return (rs, rowNum) -> {
            Books books = new Books();
            books.setId(rs.getLong("id"));
            books.setName(rs.getNString("name"));
            books.setWriter(rs.getNString("writer"));
            books.setPrice(rs.getInt("price"));
            books.setBuydate(rs.getObject("buydate"));
            return books;
        };
    }
}
