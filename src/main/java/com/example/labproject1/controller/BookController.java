package com.example.labproject1.controller;

import com.example.labproject1.domain.Books;
import com.example.labproject1.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class BookController extends Component {
    private final BooksService booksService;

    @Autowired
    public BookController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/books")
    public String list(Model model){
        List<Books> books = booksService.findMembers();
        model.addAttribute("books",books);
        return "books/bookList";
    }

    @GetMapping("/books/new")
    public String createForm(){
        return "books/createBookForm";
    }

    @PostMapping("/books/new")
    public String create(BookForm form,HttpServletResponse response) throws IOException {
        if (booksService.compare(form.getName(),form.getWriter())) {
            Books books = new Books();
            books.setName(form.getName());
            books.setWriter(form.getWriter());
            books.setPrice(form.getPrice());
            books.setBuydate(form.getBuydate());

            booksService.join(books);
        }else{
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 같은 도서가 등록되어있습니다.');</script>");

            out.flush();
            return "books/createBookForm";
        }
        return "redirect:/";
    }

    @GetMapping("/books/search")
    public String search(){
        return "books/bookSearch";
    }

    @PostMapping("/books/search")
    public String searchList(BookForm form, Model model){
        List<Books> search = booksService.search(form.getName(), form.getWriter());
        model.addAttribute("books",search);
        return "books/bookSearchList";
    }

    @PostMapping("/books/del")
    public String del(Long[] del){
        if (del.length!=0)
            booksService.del(del);
        return "redirect:/";
    }
}
