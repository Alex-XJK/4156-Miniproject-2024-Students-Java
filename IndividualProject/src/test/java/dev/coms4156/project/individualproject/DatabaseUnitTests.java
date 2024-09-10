package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * The unit test suite on file database-related implementation.
 */
@SpringBootTest
@ContextConfiguration
public class DatabaseUnitTests {

  /**
   * Sets up the database instance for testing.
   * Use exact provided data in IndividualProjectApplication.
   */
  @BeforeAll
  public static void setupItemsForTesting() {
    courses = new HashMap<>();
    compSci = new Department("COMS", courses, "Luca Carloni", 2700);
    mapping = new HashMap<>();
    mapping.put("COMS", compSci);
  }

  @Test
  public void manualSetupTest() {
    MyFileDatabase mdb = new MyFileDatabase(1, PATH);
    mdb.setMapping(mapping);
    assertEquals(1, mdb.getDepartmentMapping().size());
  }

  @Test
  public void rwFileTest() {
    MyFileDatabase mdb = new MyFileDatabase(1, PATH);
    mdb.setMapping(mapping);
    mdb.saveContentsToFile();
    MyFileDatabase adb = new MyFileDatabase(0, PATH);
    assertEquals(1, adb.getDepartmentMapping().size());
    // Department.toString() is already tested in DepartmentUnitTests
    String expected = "For the COMS department: \n" + compSci.toString();
    assertEquals(expected, adb.toString());
  }

  //  @Test
  //  public void errFileIoTest() {
  //    MyFileDatabase edb = new MyFileDatabase(1, "nonexistent.db");
  //    Map<String, Department> mapping = edb.deSerializeObjectFromFile();
  //    assertEquals(0, mapping.size());
  //  }

  //  @Test
  //  public void errFileContentTest() {
  //    boolean thrown = false;
  //    MyFileDatabase edb = new MyFileDatabase(1, "mvnw.cmd");
  //    try {
  //      Map<String, Department> mapping = edb.deSerializeObjectFromFile();
  //    } catch (IllegalArgumentException e) {
  //      thrown = true;
  //      assertEquals("Invalid object type in file.", e.getMessage());
  //    }
  //    assertTrue(thrown);
  //  }

  private static final String PATH = "test.db";
  public static Map<String, Course> courses;
  public static Department compSci;
  public static Map<String, Department> mapping;
}

