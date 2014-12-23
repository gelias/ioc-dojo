package org.mockito.mock;

import static org.mockito.BDDMockito.*;
import static org.mockito.MockitoAnnotations.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MockSampleTest {

	@Mock
	private List<String> queue;
	@Mock
	private RuntimeException exception;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldMockListBehaviour() {
		queue.add("Marcelo");
		verify(queue).add("Marcelo");
	}
	
	@Test
	public void shouldStubbingListBehaviour() {
		Mockito.when(queue.get(0));
		String marcelo = queue.get(0);
		assertEquals("Marcelo",marcelo);
	}
	
	@Test
	public void shouldStubbingListBehaviourUsingBDDStyle() {
		BDDMockito.given(queue.get(0)).willReturn("Marcelo");
		String marcelo = queue.get(0);
		assertEquals("Marcelo",marcelo);
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldThrowExceptionAsExpectedBehaviour(){
		given(queue.get(1));
		queue.get(1);
		fail("Boom");
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldDoThrowExceptionAsExpectedBehaviour(){
		doThrow(exception).when(queue).get(1);
		queue.get(1);
	}
	
	@Test
	public void shouldMockUsingArgumentMatchers(){
		given(queue.get(Mockito.anyInt())).willReturn("nenhum aluno cadastrado");
		assertEquals("nenhum aluno cadastrado",queue.get(1000));
	}
	
	@Test
	public void shouldVerifyMockBehaviourUsingArgumentMatchers(){
		given(queue.get(0)).willReturn("Guilherme");
		given(queue.get(1)).willReturn("Daniel");
		
		assertEquals("Guilherme",queue.get(0));
		assertEquals("Daniel",queue.get(1));
		
		verify(queue,times(2)).get(anyInt());
	}
	
	@Test
	public void shouldMockNeverBeUsed(){
		given(queue.get(0)).willReturn("Marcelo");
		assertEquals("Marcelo",queue.get(0));
		
		verify(queue, never()).get(anyInt());
		verifyZeroInteractions(queue);
	}

}
