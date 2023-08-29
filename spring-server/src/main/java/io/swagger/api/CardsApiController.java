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
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-08-29T17:28:16.715952712Z[GMT]")
@RestController
public class CardsApiController implements CardsApi {

    private static final Logger log = LoggerFactory.getLogger(CardsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CardsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Boolean> checkValid(@Parameter(in = ParameterIn.PATH, description = "The card number to validate", required=true, schema=@Schema()) @PathVariable("card_number") String cardNumber) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if(LuhnAlgo.isCCValid(cardNumber)){
                    return new ResponseEntity<Boolean>(objectMapper.readValue("true", Boolean.class), HttpStatus.OK);
                }else{
                    return new ResponseEntity<Boolean>(objectMapper.readValue("false", Boolean.class), HttpStatus.OK);
                }
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Boolean>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> createCards() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Cards> listCreditCards( @Max(100) @Parameter(in = ParameterIn.QUERY, description = "How many items to return at one time (max 100)" ,schema=@Schema(allowableValues={ "100" }, maximum="100"
)) @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Cards>(objectMapper.readValue("[ {\n  \"cvc\" : \"cvc\",\n  \"card_number\" : \"card_number\",\n  \"last_name\" : \"last_name\",\n  \"expiration\" : 0,\n  \"first_name\" : \"first_name\"\n}, {\n  \"cvc\" : \"cvc\",\n  \"card_number\" : \"card_number\",\n  \"last_name\" : \"last_name\",\n  \"expiration\" : 0,\n  \"first_name\" : \"first_name\"\n}, {\n  \"cvc\" : \"cvc\",\n  \"card_number\" : \"card_number\",\n  \"last_name\" : \"last_name\",\n  \"expiration\" : 0,\n  \"first_name\" : \"first_name\"\n}, {\n  \"cvc\" : \"cvc\",\n  \"card_number\" : \"card_number\",\n  \"last_name\" : \"last_name\",\n  \"expiration\" : 0,\n  \"first_name\" : \"first_name\"\n}, {\n  \"cvc\" : \"cvc\",\n  \"card_number\" : \"card_number\",\n  \"last_name\" : \"last_name\",\n  \"expiration\" : 0,\n  \"first_name\" : \"first_name\"\n}, {\n  \"cvc\" : \"cvc\",\n  \"card_number\" : \"card_number\",\n  \"last_name\" : \"last_name\",\n  \"expiration\" : 0,\n  \"first_name\" : \"first_name\"\n}, {\n  \"cvc\" : \"cvc\",\n  \"card_number\" : \"card_number\",\n  \"last_name\" : \"last_name\",\n  \"expiration\" : 0,\n  \"first_name\" : \"first_name\"\n}, {\n  \"cvc\" : \"cvc\",\n  \"card_number\" : \"card_number\",\n  \"last_name\" : \"last_name\",\n  \"expiration\" : 0,\n  \"first_name\" : \"first_name\"\n}, {\n  \"cvc\" : \"cvc\",\n  \"card_number\" : \"card_number\",\n  \"last_name\" : \"last_name\",\n  \"expiration\" : 0,\n  \"first_name\" : \"first_name\"\n}, {\n  \"cvc\" : \"cvc\",\n  \"card_number\" : \"card_number\",\n  \"last_name\" : \"last_name\",\n  \"expiration\" : 0,\n  \"first_name\" : \"first_name\"\n} ]", Cards.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Cards>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Cards>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Card> showCardByNumber(@Parameter(in = ParameterIn.PATH, description = "The card number for the card to retrieve", required=true, schema=@Schema()) @PathVariable("card_number") String cardNumber) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Card>(objectMapper.readValue("{\n  \"cvc\" : \"cvc\",\n  \"card_number\" : \"card_number\",\n  \"last_name\" : \"last_name\",\n  \"expiration\" : 0,\n  \"first_name\" : \"first_name\"\n}", Card.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Card>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Card>(HttpStatus.NOT_IMPLEMENTED);
    }

}