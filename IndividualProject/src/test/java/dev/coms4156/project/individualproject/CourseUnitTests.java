package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * The unit test suite on course-related implementation.
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeAll
  public static void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }

  @Test
  public void toStringTest() {
    String expectedResult =
        "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void reassignTest() {
    testCourse.reassignInstructor("Alex Xu");
    assertEquals("Alex Xu", testCourse.getInstructorName());
    testCourse.reassignLocation("My House");
    assertEquals("My House", testCourse.getCourseLocation());
    testCourse.reassignTime("7:00-8:00");
    assertEquals("7:00-8:00", testCourse.getCourseTimeSlot());
  }

  @Test
  public void capacityTest() {
    testCourse.setEnrolledStudentCount(250);
    assertTrue(testCourse.isCourseFull());
    assertFalse(testCourse.enrollStudent());
    testCourse.setEnrolledStudentCount(100);
    assertFalse(testCourse.isCourseFull());
    assertTrue(testCourse.enrollStudent());
    assertTrue(testCourse.dropStudent());
    testCourse.setEnrolledStudentCount(0);
    assertFalse(testCourse.isCourseFull());
    assertFalse(testCourse.dropStudent());
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}
