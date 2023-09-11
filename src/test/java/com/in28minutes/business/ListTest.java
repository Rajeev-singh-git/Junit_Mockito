package com.in28minutes.business;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void letsMockListSize() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		assertEquals(2,listMock.size());
			
	}
	
	@Test
	public void letsMockListSize_ReturnMultipleValues() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3);
		assertEquals(2,listMock.size());
		assertEquals(3,listMock.size());
			
	}
	
	@Test
	public void letsMockListGet() {
		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("Rajeev");
		assertEquals("Rajeev",listMock.get(0));
		assertEquals(null,listMock.get(1));
			
	}
	
	
	
	@Test(expected=RuntimeException.class)
	public void letsMockList_throwAnException() {
		List listMock = mock(List.class);
		
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
		listMock.get(0);
	}


}
