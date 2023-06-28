package tech.kbtg.fullcrudrestapiwithhibernatejpa.rest;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.kbtg.fullcrudrestapiwithhibernatejpa.entity.Student;
import tech.kbtg.fullcrudrestapiwithhibernatejpa.entity.response.StudentErrorResponse;
import tech.kbtg.fullcrudrestapiwithhibernatejpa.exception.StudentNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ( "/api" )
public class StudentRestController
{
	private List < Student > students;

	@PostConstruct
	public void loadData()
	{
		students = new ArrayList <>();
		students.add( new Student( "Somchai" , "Meeboon" ) );
		students.add( new Student( "Ying" , "Oreo" ) );
		students.add( new Student( "Nut" , "Sangtong" ) );
	}

	@GetMapping ( "/students" )
	public List < Student > getStudents()
	{
		return students;
	}

	@GetMapping ( "/students/{studentId}" )
	public Student getStudentById( @PathVariable int studentId )
	{
		if ( studentId >= students.size() || studentId < 0 )
		{
			throw new StudentNotFoundException( "Student Not Found ID: " + studentId );
		}

		return students.get( studentId );
	}
}
