package com.srm.demo.bootstrap;

import com.srm.demo.domain.Author;
import com.srm.demo.domain.Book;
import com.srm.demo.domain.Publisher;
import com.srm.demo.repositories.AuthorRepository;
import com.srm.demo.repositories.BookRepository;
import com.srm.demo.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 12/23/19.
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher Addison_Wesley = new Publisher("11 London Rd", "Southampton", "Hants", "GU14 7RS");
        publisherRepository.save(Addison_Wesley);
        System.out.println("Number of publishers: " + publisherRepository.count());


        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(Addison_Wesley);
        Addison_Wesley.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(Addison_Wesley);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(Addison_Wesley);
        Addison_Wesley.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(Addison_Wesley);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher book count: " + Addison_Wesley.getBooks().size());
    }
}
