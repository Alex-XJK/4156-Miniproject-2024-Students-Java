package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * The unit test suite on department-related implementation.
 */
@SpringBootTest
@ContextConfiguration
public class DepartmentUnitTests {

  @BeforeAll
  public static void setupDepartmentForTesting() {
    // Use provided department information in IndividualProjectApplication
    Map<String, Course> courses = new HashMap<>();
    compSci = new Department("COMS", courses, "Luca Carloni", 2700);
    dummy = new Department("DUMMY", courses, "Dummy Chair", 0);
  }

  @Test
  public void getterTest() {
    assertEquals("Luca Carloni", compSci.getDepartmentChair());
    assertEquals(2700, compSci.getNumberOfMajors());
    assertTrue(compSci.getCourseSelection().isEmpty());
  }

  @Test
  public void modifyMajorsTest() {
    assertEquals(2700, compSci.getNumberOfMajors());
    compSci.addPersonToMajor();
    assertEquals(2701, compSci.getNumberOfMajors());
    compSci.dropPersonFromMajor();
    assertEquals(2700, compSci.getNumberOfMajors());
    dummy.dropPersonFromMajor();
    assertEquals(0, dummy.getNumberOfMajors());
  }

  @Test
  public void modifyCoursesTest() {
    assertTrue(compSci.getCourseSelection().isEmpty());
    // Use provided department information in IndividualProjectApplication
    Course coms4156 = new Course("Gail Kaiser", "501 NWC", "10:10-11:25", 120);
    coms4156.setEnrolledStudentCount(109);
    compSci.addCourse("4156", coms4156);
    assertEquals(1, compSci.getCourseSelection().size());
    // Use provided department information in IndividualProjectApplication
    compSci.createCourse("3157", "Jae Lee", "417 IAB", "4:10-5:25", 400);
    assertEquals(2, compSci.getCourseSelection().size());
  }

  @Test
  public void toStringTest() {
    String expected = "COMS 4156: \nInstructor: Gail Kaiser; Location: 501 " +
                      "NWC; Time: 10:10-11:25\n"
                      + "COMS 3157: \nInstructor: Jae Lee; Location: 417 " +
                        "IAB; Time: 4:10-5:25\n";
    assertEquals(expected, compSci.toString());
  }

  /** The test department instance used for testing. */
  public static Department compSci;
  public static Department dummy;
}
