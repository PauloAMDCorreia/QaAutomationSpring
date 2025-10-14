package com.example.demo.steps;

import com.example.demo.context.TestContext;
import com.example.demo.payloads.*;
import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

@SpringBootTest
public class UserSteps {

    private final TestContext context = new TestContext();

    @Dado("que desejo criar um usuário")
    public void criarUsuario() {
        context.setUsername("user" + System.currentTimeMillis());
        context.setPassword("Senha@123");

        UserPayload user = UserPayload.builder()
                .userName(context.getUsername())
                .password(context.getPassword())
                .build();

        Response resp = given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .body(user)
                .post("https://demoqa.com/Account/v1/User");

        System.out.println("CREATE USER RESPONSE: " + resp.asString());
        assertEquals("Status code diferente do esperado", 201, resp.statusCode());

        context.setUserId(resp.jsonPath().getString("userID"));
        assertNotNull("Usuário não foi criado!", context.getUserId());
    }

    @Quando("gero um token de acesso para o usuário criado")
    public void gerarToken() {
        UserPayload user = UserPayload.builder()
                .userName(context.getUsername())
                .password(context.getPassword())
                .build();

        Response resp = given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .body(user)
                .post("https://demoqa.com/Account/v1/GenerateToken");

        System.out.println("GENERATE TOKEN RESPONSE: " + resp.asString());
        assertEquals("Status code diferente do esperado", 200, resp.statusCode());

        context.setToken(resp.jsonPath().getString("token"));
        assertNotNull("Token não gerado!", context.getToken());
    }

    @Quando("verifico se o usuário está autorizado")
    public void verificarAutorizacao() {
        UserPayload user = UserPayload.builder()
                .userName(context.getUsername())
                .password(context.getPassword())
                .build();

        Response resp = given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .body(user)
                .post("https://demoqa.com/Account/v1/Authorized");

        System.out.println("AUTHORIZED RESPONSE: " + resp.asString());
        assertEquals("Status code diferente do esperado", 200, resp.statusCode());
        assertTrue("Usuário não está autorizado!", resp.getBody().as(Boolean.class));
    }

    @Quando("listo os livros disponíveis")
    public void listarLivros() {
        Response resp = given()
                .relaxedHTTPSValidation()
                .get("https://demoqa.com/BookStore/v1/Books");

        assertEquals("Status code diferente do esperado", 200, resp.statusCode());

        BookListResponse booksResponse = resp.as(BookListResponse.class);
        List<Book> books = booksResponse.getBooks();

        assertTrue("Menos de dois livros disponíveis!", books.size() >= 2);

        context.setBookIsbns(books.stream()
                .map(Book::getIsbn)
                .collect(Collectors.toList()));
    }

    @Quando("alugo dois livros de minha escolha")
    public void alugarLivros() {
        context.setChosenBooks(context.getBookIsbns().subList(0, 2));

        BookCollectionPayload bookCollection = BookCollectionPayload.builder()
                .userId(context.getUserId())
                .collectionOfIsbns(
                        context.getChosenBooks().stream()
                                .map(isbn -> Map.of("isbn", isbn))
                                .collect(Collectors.toList())
                )
                .build();

        Response resp = given()
                .relaxedHTTPSValidation()
                .header("Authorization", "Bearer " + context.getToken())
                .contentType("application/json")
                .body(bookCollection)
                .post("https://demoqa.com/BookStore/v1/Books");

        System.out.println("RENT BOOKS RESPONSE: " + resp.asString());
        assertEquals("Não foi possível alugar os livros!", 201, resp.statusCode());
    }

    @Então("confirmo que os livros estão associados ao usuário")
    public void confirmarLivros() {
        Response resp = given()
                .relaxedHTTPSValidation()
                .header("Authorization", "Bearer " + context.getToken())
                .get("https://demoqa.com/Account/v1/User/" + context.getUserId());

        System.out.println("CONFIRM USER BOOKS: " + resp.asString());
        assertEquals("Status code diferente do esperado", 200, resp.statusCode());

        List<Map<String, Object>> userBooks = resp.jsonPath().getList("books");
        List<String> userBookIsbns = userBooks.stream()
                .map(book -> (String) book.get("isbn"))
                .collect(Collectors.toList());

        assertTrue("Os livros não estão associados ao usuário!",
                userBookIsbns.containsAll(context.getChosenBooks()));
    }
}

