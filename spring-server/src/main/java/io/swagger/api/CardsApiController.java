package io.swagger.api;

import io.swagger.model.Card;
import io.swagger.model.Cards;
import io.swagger.model.Error;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-08-29T17:28:16.715952712Z[GMT]")
@RestController
public class CardsApiController implements CardsApi {

    private static final Logger log = LoggerFactory.getLogger(CardsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    Connector connector = Connector.getInstance();

    @org.springframework.beans.factory.annotation.Autowired
    public CardsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Boolean> checkValid(
            @Parameter(in = ParameterIn.PATH, description = "The card number to validate", required = true, schema = @Schema()) @PathVariable("card_number") String cardNumber) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if (LuhnAlgo.isCCValid(cardNumber)) {
                    return new ResponseEntity<Boolean>(objectMapper.readValue("true", Boolean.class), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Boolean>(objectMapper.readValue("false", Boolean.class), HttpStatus.OK);
                }
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Boolean>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> createCards(@RequestBody Card card) {
    // public ResponseEntity<Void> createCards(@RequestParam) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Connection conn = connector.getConnection();
                Statement stmt = conn.createStatement();

                String query = String.format("INSERT INTO Cards (card_number, first_name, last_name, expiration, cvc, added_on, deactivated_on) VALUES ('%s', '%s', '%s', '%s', '%s', now(),NULL;",card.getCardNumber(),card.getFirstName(),card.getFirstName(),card.getExpiration(),card.getCvc());
                ResultSet rs = stmt.executeQuery(query);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception e) {
                log.error("Error creating credit card", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    public ResponseEntity<Cards> listCreditCards(
            @Max(100) @Parameter(in = ParameterIn.QUERY, description = "How many items to return at one time (max 100)", schema = @Schema(allowableValues = {
                    "100" }, maximum = "100")) @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Connection conn = connector.getConnection();
                Statement stmt = conn.createStatement();

                String query = "SELECT * FROM Cards LIMIT" + limit;
                ResultSet rs = stmt.executeQuery(query);

                List<Card> listOfCards = new ArrayList<>();

                while (rs.next()) {
                    Card card = new Card();
                    card.setCvc(rs.getString("cvc"));
                    card.setCardNumber(rs.getString("card_number"));
                    card.setLastName(rs.getString("last_name"));
                    card.setExpiration(rs.getLong("expiration"));
                    card.setFirstName(rs.getString("first_name"));
                    listOfCards.add(card);
                }

                Cards cards = new Cards();
                cards.addAll(listOfCards);
                return ResponseEntity.ok(cards);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Cards>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Cards>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Card> showCardByNumber(
            @Parameter(in = ParameterIn.PATH, description = "The card number for the card to retrieve", required = true, schema = @Schema()) @PathVariable("card_number") String cardNumber) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Connection conn = connector.getConnection();
                Statement stmt = conn.createStatement();

                String query = String.format("SELECT * FROM Cards WHERE card_number = '%s' AND deactivated_on IS NULL", cardNumber);
                ResultSet rs = stmt.executeQuery(query);

                if(rs.next()){
                    Card card = new Card();
                    card.setCvc(rs.getString("cvc"));
                    card.setCardNumber(rs.getString("card_number"));
                    card.setLastName(rs.getString("last_name"));
                    card.setExpiration(rs.getLong("expiration"));
                    card.setFirstName(rs.getString("first_name"));
                    return ResponseEntity.ok(card);
                } else{
                    return ResponseEntity.notFound().build();
                }

            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Card>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Card>(HttpStatus.NOT_IMPLEMENTED);
    }

}
