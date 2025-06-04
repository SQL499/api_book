package com.hampcode;

import com.hampcode.dto.AuthorRequest;
import com.hampcode.model.Author;
import com.hampcode.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthorServiceTests {

    @Autowired
    private AuthorService authorService;

    @Test
    @DirtiesContext
    void createNewAuthorReturnsAuthor() {
        int initialSize = authorService.findAll().size();
        AuthorRequest req = new AuthorRequest("Julio Cortazar");
        Author created = authorService.create(req);
        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();
        assertThat(created.getName()).isEqualTo("Julio Cortazar");
        assertThat(authorService.findAll()).hasSize(initialSize + 1);
    }

    @Test
    @DirtiesContext
    void createDuplicateAuthorReturnsNull() {
        AuthorRequest req = new AuthorRequest("Gabriel García Márquez");
        Author created = authorService.create(req);
        assertThat(created).isNull();
        assertThat(authorService.findAll()).hasSize(2);
    }

    @Test
    void findByNameReturnsMatches() {
        List<Author> results = authorService.findByName("Isabel");
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getName()).isEqualTo("Isabel Allende");
    }
}
