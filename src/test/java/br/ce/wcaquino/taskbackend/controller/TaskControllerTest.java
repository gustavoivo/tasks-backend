package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {
	
	@Mock
	private TaskRepo taskrepo;
	
	@InjectMocks
	private TaskController controller;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void naoDeveSalvarTarefasemDescricao() {
		Task todo = new Task();
//		todo.setTask("Descrição");
		todo.setDueDate(LocalDate.now());
	   	try {
			controller.save(todo);
			Assert.fail("Não Deveria chegar nesse ponto!");
		} catch (ValidationException e) {
			Assert.assertEquals("Fill the task description", e.getMessage());			
		}
	}
	
	@Test
	public void naoDeveSalvarTarefasemData() {
		Task todo = new Task();
//		todo.setTask("Descrição");
		todo.setDueDate(LocalDate.now());
		try {
			controller.save(todo);
			Assert.fail("Não Deveria chegar nesse ponto!");
		} catch (ValidationException e) {
			Assert.assertEquals("Fill the task description", e.getMessage());			
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefasemDataPassada() {
		Task todo = new Task();
//		todo.setTask("Descrição");
		todo.setDueDate(LocalDate.of(2010, 01, 01));
		try {
			controller.save(todo);
			Assert.fail("Não Deveria chegar nesse ponto!");
		} catch (ValidationException e) {
			Assert.assertEquals("Fill the task description", e.getMessage());			
		}
		
	}
	
	@Test
	public void deveSalvarTarefacomSucesso() throws ValidationException {
		Task todo = new Task();
		todo.setTask("Descrição");
		todo.setDueDate(LocalDate.now());
		controller.save(todo);
		Mockito.verify(taskrepo).save(todo);
		}
}