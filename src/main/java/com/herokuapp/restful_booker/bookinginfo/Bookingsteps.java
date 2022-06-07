package com.herokuapp.restful_booker.bookinginfo;

import com.herokuapp.restful_booker.constants.EndPoints;
import com.herokuapp.restful_booker.model.BookingPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class Bookingsteps {

    @Step("Getting Authentication with username:{0}, password:{1}")
    public ValidatableResponse getAuthentication(String username, String password) {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setUsername(username);
        bookingPojo.setPassword(password);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingPojo)
                .when()
                .post("/auth")
                .then();
    }

    @Step("Getting All Booking IDs")
    public ValidatableResponse getAllBookingIds() {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_BOOKING_BY_ID)
                .then();
    }

    @Step("Getting Booking With BookingId:{0})")
    public ValidatableResponse getBookingById(int bookingId) {

        return SerenityRest.given().log().all()
                .pathParam("id", bookingId)
                .when()
                .get(EndPoints.GET_BOOKING_BY_ID)
                .then();

    }

    @Step("Creating Booking with firstname:{0},lastname:{1},totalprice:{2},depositPaid {3} ,bookingDate {4}, additionalNeeds {5}")
    public ValidatableResponse createBooking(String firstname, String lastname, int totalprice, Boolean depositpaid,
                                             HashMap<Object, Object> bookingDates, String additionalneeds) {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setUsername("admin");
        bookingPojo.setPassword("password123");
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setAdditionalneeds(additionalneeds);
        HashMap<Object, Object> bookingdates = new HashMap<>();
        bookingPojo.setBookingdates(bookingdates);


        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingPojo)
                .when()
                .post()
                .then();

    }

    @Step("Updating Booking with bookingId:{0}, firstname:{1},lastname:{2},totalprice:{3},depositPaid {4} ,bookingDate {5}, additionalNeeds {6}")
    public ValidatableResponse updateBooking(int id, String firstname, String lastname, int totalprice, Boolean depositpaid,
                                             HashMap<Object, Object> bookingDates, String additionalneeds, String token) {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setUsername("admin");
        bookingPojo.setPassword("password123");
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setAdditionalneeds(additionalneeds);
        //HashMap<Object, Object> bookingDates = new HashMap<>();
        //bookingDates.put("check in ", "2018-01-01");
        //bookingDates.put("checkout", "2019-01-01");
        bookingPojo.setBookingdates(bookingDates);


        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                //.header("Authorisation", "YWRtaW46cGFzc3dvcmQxMjM=]")
                //.header("Cookie", "token=cce7ea74c94f244")
                .pathParam("id", id)
                .body(bookingPojo)
                .when()
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }

    @Step
    public ValidatableResponse deleteBooking(int bookingId, String token) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                //.header("Authorisation", "YWRtaW46cGFzc3dvcmQxMjM=]")
                //.header("Cookie", "token=cce7ea74c94f244")
                .pathParam("id", bookingId)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();

    }

}




