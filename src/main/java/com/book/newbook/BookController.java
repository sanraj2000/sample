package com.book.newbook;


//import com.book.sandy.document.Book;
//import com.book.sandy.search.SearchRequestDTO;
//import com.book.sandy.services.BookService;
import com.book.newbook.BookService;
//import com.example.demo.Vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.example.demo.Vehicle;
//import com.example.demo.SearchRequestDTO;
//import com.example.demo.VehicleDummyDataService;
//import com.example.demo.VehicleService;
//import com.san.gradledemo1.services.VehicleDummyDataService;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")

public class BookController {
	private final BookService service;
	//private final DummyDataService dummyDataService;


    @Autowired
    public BookController(BookService service) {
        this.service = service;
        //this.dummyDataService= dummyDataService;
         
    }

    @PostMapping("/add")
    public void index(@RequestBody final Book book) {
        service.index(book);
    }

    @GetMapping("/book/{id}")
    public Book getById(@PathVariable final String id) {
        return service.getById(id);
    }
    
    @PostMapping("/book/search")
    public List<Book> search(@RequestBody final SearchRequestDTO dto) {
        return service.search(dto);
    }
	
    @GetMapping("/sample")
    public void sample(){
	System.out.println("Welcome to springboot+elasticsearch+docker");    
    }
    
    @GetMapping("/book/search/{date}")
    public List<Book> getAllBookCreatedSince(
            @PathVariable
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            final Date date) {
        return service.getAllBooksCreatedSince(date);
    }
    
    @PostMapping("/book/searchcreatedsince/{date}")
    public List<Book> searchCreatedSince(
            @RequestBody final SearchRequestDTO dto,
            @PathVariable
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            final Date date) {
        return service.searchCreatedSince(dto, date);
    }

    
    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable String  id) throws IOException {
    	return service.deleteBook(id);
    }
    
    
    @GetMapping("/author/{aname}")
    public List<Book> getByAuthor(@PathVariable String aname){
    	return service.getByAuthor(aname);
    }
    
    
    @ResponseBody
    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity<List<Book>>(service.getAll(), HttpStatus.OK);
    }
    
   
    @PutMapping("/up")
    public void updateVechicle(@RequestBody Book book)
    {
    	 Book b= service.getById(book.getId());
    	 b.setCreated(book.getCreated());
    	 b.setName(book.getName());
    	 b.setBookname(book.getBookname());
    	 
    	 service.index(b);
    }
   

}
