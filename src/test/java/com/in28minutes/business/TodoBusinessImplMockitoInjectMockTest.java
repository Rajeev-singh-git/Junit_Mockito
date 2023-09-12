package com.in28minutes.business;

import com.in28minutes.data.api.TodoService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

//@Opens(java.base/java.lang)


public class TodoBusinessImplMockitoInjectMockTest {


	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
	TodoService todoServiceMock;

    @InjectMocks
	TodoBusinessImpl todoBusinessImpl;

	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		//TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		
		//TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> filteredTodods = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2,filteredTodods.size());	
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_withEmptyList() {

		
		List<String> todos = Arrays.asList();
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> filteredTodods = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(0,filteredTodods.size());	
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD() {
		
		//Given

		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//Then
	//	verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
		then(todoServiceMock).should().deleteTodo("Learn to Dance");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring");
		
	//	verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");	
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {
		
	   //Declare Argument Capture 
	//	ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		
		
		
		//Given

		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//Then

		then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
		assertThat(stringArgumentCaptor.getValue(),is("Learn to Dance"));
		
	}


}
