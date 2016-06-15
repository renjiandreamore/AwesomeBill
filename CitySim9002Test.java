package deliverable2;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.mockito.*;


public class CitySim9002Test {
	@SuppressWarnings("unchecked")
	
	@Mock
	CitySim9002 mockCity = Mockito.mock(CitySim9002.class);
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(mockCity);		
	}
	
	@After
	public void tearDown() throws Exception {
		// any necessary teardown - none needed here
	}	
	
	//check the object and name that matches the given
	//four groups
	@Test
	public void testStudentVisitRightLocation(){
		Student mockStu = Mockito.mock(Student.class);
		String msg = "The Cathedral of Learning";
		Mockito.when(mockStu.visitCity(msg)).thenReturn(true);
		Mockito.when(mockCity.visit(mockStu, msg)).thenCallRealMethod();
		assertTrue(mockCity.visit(mockStu, msg));
	}
	//check the error message that called false
	@Test
	public void testStudentVisitErrorLocation(){
		Student mockStu = Mockito.mock(Student.class);
		String msg = "error INPUT";
		Mockito.when(mockStu.visitCity(msg)).thenCallRealMethod();
		assertFalse(mockCity.visit(mockStu, msg));
	}
	
	
	//test how many times that the method has been executed, and then we know the final
	//value of run time is 5, which means there are 5 visitors in total.
	//and also see if the visitor visited the correct places. (businessman hates two places)
	@Test
	public void testRunTimes(){
		Mockito.when(mockCity.randomSelect(new Random(1))).thenReturn(0);
		Mockito.when(mockCity.generateVisitor(0)).thenReturn(new Business(1));
		Mockito.when(mockCity.visit(new Business(1), CitySim9002.locations[0])).thenReturn(false);
		Mockito.when(mockCity.visit(new Business(1), CitySim9002.locations[1])).thenReturn(true);
		Mockito.when(mockCity.visit(new Business(1), CitySim9002.locations[2])).thenReturn(false);
		Mockito.when(mockCity.visit(new Business(1), CitySim9002.locations[3])).thenReturn(true);
		Mockito.when(mockCity.run(9)).thenCallRealMethod();
		int val = mockCity.run(9);
		assertEquals(val, 5);
		Mockito.verify(mockCity, Mockito.times(5)).generateVisitor(0);
	}
	
	 
	
    // Check the input of the commend line is a digital, any thing other than one digital
	// is a false statement.
	@Test
	public void testSeedInteger2() {
		String[] b = {"1"};
		assertTrue(CitySim9002.isInteger(b));
	}
	
	
	// Check the input of the commend line is a digital, any thing other than one digital
	// is a false statement.
	@Test
	public void testSeedInteger1() {
		String[] a = {"x"};
		assertFalse(CitySim9002.isInteger(a));
	}
	
	// Check the input of the commend line is a digital, any thing other than one digital
	// is a false statement.
	public void testSeedInteger3() {
		String[] c = {" "};
		assertFalse(CitySim9002.isInteger(c));
	}
	
	// Check the input of the commend line is a digital, any thing other than one digital
	// is a false statement.
	@Test
	public void testSeedInteger4() {
		String[] d = {"5"," "};
		assertFalse(CitySim9002.isInteger(d));
	}
	
	//check a student is a student
	@Test
	public void testStudent(){
		Visitor a = Mockito.mock(Visitor.class);
		a = new Student(1);
		assertEquals(a.type(), "Student");
	}
	
	//test a businessman is a businessman
		@Test
		public void testBusiness(){
			Visitor a = Mockito.mock(Visitor.class);
			a = new Business(1);
			assertEquals(a.type(), "Business");
		}
	
	//check a professor is a professor
	@Test
	public void testProfessor(){
		Visitor a = Mockito.mock(Visitor.class);
		a = new Professor(1);
		assertEquals(a.type(), "Professor");
	}
	
	//check a blogger mathces the blogger
	@Test
	public void testBlogger(){
		Visitor a = Mockito.mock(Visitor.class);
		a = new Blogger(1);
		assertEquals(a.type(), "Blogger");
	}
	
	
	
}
