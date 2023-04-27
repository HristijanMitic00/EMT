package emt.lab.backend.repository;

import emt.lab.backend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long > {
    void deleteByName(String name);
}
