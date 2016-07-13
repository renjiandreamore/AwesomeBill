package deliverable2;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.mockito.*;

public class citySimTest {
@SuppressWarnings("unchecked")
	
	@Mock
	citySim mock = Mockito.mock(citySim.class);
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(mock);		
	}
	
	@After
	public void tearDown() throws Exception {
		// no necessary right here
	}	
	
	// check there are 5 places includes the outside city
	@Test
	public void fivePlacesTest(){
		citySim places = Mockito.mock(citySim.class);
		int size = places.locations.length;
		assertTrue(size == 5);
	}
	
	// check that the input number is valid
	// the input should be only digit. and not null
	@Test
	public void inputTest(){
		citySim mock = Mockito.mock(citySim.class);
		String[] test = {"3","x"};
		assertFalse(mock.valid(test));
	}
	@Test
	public void inputTest2(){
		citySim mock = Mockito.mock(citySim.class);
		String[] test = {" "};
		assertFalse(mock.valid(test));
	}
	@Test
	public void inputTest3(){
		citySim mock = Mockito.mock(citySim.class);
		String[] test = {"a"};
		assertFalse(mock.valid(test));
	}
	@Test
	public void inputTest4(){
		citySim mock = Mockito.mock(citySim.class);
		String[] test = {""};
		assertFalse(mock.valid(test));
	}
	
	//check that wrong number printed
//	@Test
//	public void wrongNumTest(){
//		citySim mock = Mockito.mock(citySim.class);
//		String[] test = {"x"};
//		Mockito.doCallRealMethod().doReturn("wrong number, plz enter a digit!");
//		//assertTrue(System.out.print("wrong number, plz enter a digit!"));
//	}
	
	//run method check
	@Test
	public void runTest(){
		int seed = 9;
		String location = "Driver";
		citySim mock = Mockito.mock(citySim.class);
		Mockito.when(mock.run(seed)).thenCallRealMethod();
		assertFalse(mock.driveFromOut(seed) || mock.driveNormally(seed, location));
	}
	
	// method move2next test:
	// when before add elemtns to the hashmap, it is empty;
	@Test
	public void moveTest(){
		Places mock1 = new Places();
		boolean size = mock1.next.isEmpty();
		assertTrue(size);
		//Mockito.when(mock.move2next(1, "");).thenCallRealMethod();
	}
	
	@Test
	public void moveTest2(){
		Places mock = Mockito.mock(Places.class);
		boolean x = Mockito.when(mock.move2next(1, "Driver")).thenCallRealMethod() != null;
		assertTrue(x);
		//Mockito.when(mock.move2next(1, "");).thenCallRealMethod();
	}
	
	//
}
