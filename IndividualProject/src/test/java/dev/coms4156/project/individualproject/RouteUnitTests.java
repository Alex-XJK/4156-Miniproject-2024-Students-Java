package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

/**
 * The unit test suite on route controller implementation.
 */
@SpringBootTest
@ContextConfiguration
public class RouteUnitTests {

  @BeforeAll
  public static void setupCourseForTesting() {
    controller = new RouteController();
  }

  @Test
  public void indexTest() {
    String expected = "Welcome, in order to make an API call direct your browser or"
        + " Postman to an endpoint "
        + "\n\n This can be done using the following format: \n\n http:127.0.0"
        + ".1:8080/endpoint?arg=value";
    assertEquals(expected, controller.index());
  }

  @Test
  public void retrieveTest() {
    assertEquals(
        HttpStatus.OK,
        controller.retrieveCourse("COMS", 4156).getStatusCode()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        controller.retrieveCourse("COMS", 9999).getStatusCode()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        controller.retrieveCourse("ABCD", 9999).getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        controller.isCourseFull("COMS", 4156).getStatusCode()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        controller.isCourseFull("COMS", 9999).getStatusCode()
    );
  }

  @Test
  public void courseInfoTest() {
    assertEquals(
        "501 NWC is where the course is located.",
        controller.findCourseLocation("COMS", 4156).getBody()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        controller.findCourseLocation("COMS", 9999).getStatusCode()
    );
    assertEquals(
        "Gail Kaiser is the instructor for the course.",
        controller.findCourseInstructor("COMS", 4156).getBody()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        controller.findCourseInstructor("COMS", 9999).getStatusCode()
    );
    assertEquals(
        "The course meets at: 10:10-11:25",
        controller.findCourseTime("COMS", 4156).getBody()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        controller.findCourseTime("COMS", 9999).getStatusCode()
    );
  }

  @Test
  public void depatmentInfoTest() {
    assertEquals(
        HttpStatus.OK,
        controller.getMajorCtFromDept("COMS").getStatusCode()
    );
    assertEquals(
        "Department Not Found",
        controller.getMajorCtFromDept("ABCD").getBody()
    );
    assertEquals(
        "Luca Carloni is the department chair.",
        controller.identifyDeptChair("COMS").getBody()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        controller.identifyDeptChair("ABCD").getStatusCode()
    );
  }

  @Test
  public void infoEdgeCaseTest() {
    assertEquals(
        "501 NWC is where the course is located.",
        controller.findCourseLocation("COMS", 4156).getBody()
    );
    assertEquals(
        "501 NWC is where the course is located.",
        controller.findCourseLocation("coms", 4156).getBody()
    );
    assertEquals(
        "501 NWC is where the course is located.",
        controller.findCourseLocation("cOmS", 4156).getBody()
    );
  }

  @Test
  public void manipulateDepartmentTest() {
    assertEquals(
        HttpStatus.OK,
        controller.addMajorToDept("IEOR").getStatusCode()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        controller.addMajorToDept("ABCD").getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        controller.removeMajorFromDept("IEOR").getStatusCode()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        controller.removeMajorFromDept("ABCD").getStatusCode()
    );
  }

  @Test
  public void manipulateCourseTest() {
    assertEquals(
        HttpStatus.OK,
        controller.setEnrollmentCount("COMS", 3157, 100).getStatusCode()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        controller.setEnrollmentCount("COMS", 9999, 100).getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        controller.changeCourseTime(
            "COMS", 3157, "0:00-01:00"
        ).getStatusCode()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        controller.changeCourseTime(
            "COMS", 9999, "0:00-01:00"
        ).getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        controller.changeCourseTeacher(
            "COMS", 3157, "Alex Xu"
        ).getStatusCode()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        controller.changeCourseTeacher(
            "COMS", 9999, "Alex Xu"
        ).getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        controller.changeCourseLocation(
            "COMS", 3157, "My House"
        ).getStatusCode()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        controller.changeCourseLocation(
            "COMS", 9999, "My House"
        ).getStatusCode()
    );
  }
  

  /** The route controller instance used for testing. */
  public static RouteController controller;
}

