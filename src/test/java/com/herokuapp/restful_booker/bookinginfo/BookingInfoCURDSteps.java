package com.herokuapp.restful_booker.bookinginfo;

import com.herokuapp.restful_booker.testbase.TestBase;
import com.herokuapp.restful_booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

@RunWith(SerenityRunner.class)
public class BookingInfoCURDSteps extends TestBase {


    static String username = "admin";
    static String password = "password123";
    static int id;
    static String token = "";
    static String firstname = "newname" + TestUtils.getRandomValue();
    static String lastname = "newlastname" + TestUtils.getRandomValue();
    static int totalprice = 1000;
    static Boolean depositpaid = true;
    static String additionalneeds = "Breakfast";

    @Steps
    Bookingsteps bookingSteps;


    @Title("Getting Authentication")
    @Test
    public void test001 (){
        bookingSteps.getAuthentication(username,password).log().all().statusCode(200);
    }

    @Title("Getting All Bookings")
    @Test
    public void test002(){
        bookingSteps.getAllBookingIds().statusCode(200);
    }


    @Title("Getting Bookings By Id")
    @Test
    public void test003(){
        bookingSteps.getBookingById(id).statusCode(200);
    }

    @Title("Creating Booking ")
    @Test
    public void test004(){

        HashMap<Object, Object> bookingsDates = new HashMap<>();
        bookingsDates.put("checkin", "2018-01-10");
        bookingsDates.put("checkout", "2019-01-12");
        ValidatableResponse response = bookingSteps.createBooking(firstname, lastname, totalprice, depositpaid, bookingsDates, additionalneeds);
        response.log().all().statusCode(200);
        id = response.extract().path("bookingid");
        System.out.println(id);
    }
    @Title("Updating Booking By Id")
    @Test
    public void test005(){
        bookingSteps.getBookingById(id).statusCode(200);
    }
    @Title("Deleting Booking By Id")
    @Test
    public void test006(){
        id=369;
        ValidatableResponse response = bookingSteps.deleteBooking(id, token);
        response.log().all().statusCode(201);
        bookingSteps.getBookingById(id).log().all().statusCode(404);
    }



}
