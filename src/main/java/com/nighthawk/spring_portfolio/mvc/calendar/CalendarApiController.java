package com.nighthawk.spring_portfolio.mvc.calendar;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/** Calendar API
 * Calendar Endpoint: /api/calendar/isLeapYear/2022, Returns: {"year":2020,"isLeapYear":false}
 */
@RestController
@RequestMapping("/api/calendar")
public class CalendarApiController {

    /** GET isLeapYear endpoint
     * ObjectMapper throws exceptions on bad JSON
     *  @throws JsonProcessingException
     *  @throws JsonMappingException
     */
    @GetMapping("/isLeapYear/{year}")
    public ResponseEntity<JsonNode> getIsLeapYear(@PathVariable int year) throws JsonMappingException, JsonProcessingException {
      // Backend Year Object
      Year year_obj = new Year();
      year_obj.setYear(year);  // evaluates Leap Year

      // Turn Year Object into JSON
      ObjectMapper mapper = new ObjectMapper(); 
      JsonNode json = mapper.readTree(year_obj.isLeapYearToString()); // this requires exception handling

      return ResponseEntity.ok(json);  // JSON response, see ExceptionHandlerAdvice for throws
    }

    @GetMapping("/firstDayOfYear/{year}")
    public ResponseEntity<JsonNode> getFirstDayOfYear(@PathVariable int year) throws JsonMappingException, JsonProcessingException {
      // Backend Year Object
      Year year_obj = new Year();
      year_obj.setYear(year);  // evaluates Leap Year

      // Turn Year Object into JSON
      ObjectMapper mapper = new ObjectMapper(); 
      JsonNode json = mapper.readTree(year_obj.firstDayOfYearToString()); // this requires exception handling

      return ResponseEntity.ok(json);  // JSON response, see ExceptionHandlerAdvice for throws
    }

    @GetMapping("/dayOfYear/{month}/{day}/{year}")
    //public ResponseEntity<JsonNode> getDayOfYear(@PathVariable("month") int month, @PathVariable("day") int day, @PathVariable("year") int year) throws JsonMappingException, JsonProcessingException {
      // IMPORTANT: you can have multiple @PathVariables!
    public ResponseEntity<JsonNode> getDayOfYear(@PathVariable int month, @PathVariable int day, @PathVariable int year) throws JsonMappingException, JsonProcessingException {

      // Backend Year Object
      Year year_obj = new Year();
      year_obj.setYear(month, day, year);  // evaluates Leap Year

      // Turn Year Object into JSON
      ObjectMapper mapper = new ObjectMapper(); 
      JsonNode json = mapper.readTree(year_obj.dayOfYearToString()); // this requires exception handling

      return ResponseEntity.ok(json);  // JSON response, see ExceptionHandlerAdvice for throws
    }

    @GetMapping("/numberOfLeapYears/{year1}/{year2}")
    //public ResponseEntity<JsonNode> getDayOfYear(@PathVariable("month") int month, @PathVariable("day") int day, @PathVariable("year") int year) throws JsonMappingException, JsonProcessingException {
      // IMPORTANT: you can have multiple @PathVariables!
    public ResponseEntity<JsonNode> getNumberOfLeapYears(@PathVariable int year1, @PathVariable int year2) throws JsonMappingException, JsonProcessingException {

      // Backend Year Object
      Year year_obj = new Year();
      year_obj.setYear(year1, year2);  // evaluates Leap Year

      // Turn Year Object into JSON
      ObjectMapper mapper = new ObjectMapper(); 
      JsonNode json = mapper.readTree(year_obj.numberOfLeapYearsToString()); // this requires exception handling

      return ResponseEntity.ok(json);  // JSON response, see ExceptionHandlerAdvice for throws
    }

    @GetMapping("/dayOfWeek/{month}/{day}/{year}")
    //public ResponseEntity<JsonNode> getDayOfYear(@PathVariable("month") int month, @PathVariable("day") int day, @PathVariable("year") int year) throws JsonMappingException, JsonProcessingException {
      // IMPORTANT: you can have multiple @PathVariables!
    public ResponseEntity<JsonNode> getdayOfWeek(@PathVariable int month, @PathVariable int day, @PathVariable int year) throws JsonMappingException, JsonProcessingException {

      // Backend Year Object
      Year year_obj = new Year();
      year_obj.setYear(month, day, year);  // evaluates Leap Year

      // Turn Year Object into JSON
      ObjectMapper mapper = new ObjectMapper(); 
      JsonNode json = mapper.readTree(year_obj.dayOfWeekToString()); // this requires exception handling

      return ResponseEntity.ok(json);  // JSON response, see ExceptionHandlerAdvice for throws
    }

    @GetMapping("/dayOfWeek/{month1}/{day1}/{year1}/{month2}/{day2}/{year2}")
    //public ResponseEntity<JsonNode> getDayOfYear(@PathVariable("month") int month, @PathVariable("day") int day, @PathVariable("year") int year) throws JsonMappingException, JsonProcessingException {
      // IMPORTANT: you can have multiple @PathVariables!
    public ResponseEntity<JsonNode> getNumDaysToDeadline(@PathVariable int month1, @PathVariable int day1, @PathVariable int year1, @PathVariable int month2, @PathVariable int day2, @PathVariable int year2) throws JsonMappingException, JsonProcessingException {

      // Backend Year Object
      Year year_obj = new Year();
      year_obj.setYear(month1, day1, year1, month2, day2, year2);  // evaluates Leap Year

      // Turn Year Object into JSON
      ObjectMapper mapper = new ObjectMapper(); 
      JsonNode json = mapper.readTree(year_obj.numDaysToDeadlineToString()); // this requires exception handling

      return ResponseEntity.ok(json);  // JSON response, see ExceptionHandlerAdvice for throws
    }

    // add other methods
}