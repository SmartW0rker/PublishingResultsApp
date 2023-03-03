package com.result_publishing_app.application.service;

import com.result_publishing_app.application.exceptions.StudentExistException;
import com.result_publishing_app.application.exceptions.StudentNotFoundException;
import com.result_publishing_app.application.mapper.StudentMapper;
import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.model.student.StudentCommand;
import com.result_publishing_app.application.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentMapper studentMapper;

    public Student findById(Long id){
        return studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException(String.
                format("Student with id %d does not exist",id)));
    }
    public Student findByIndex(String index){
        return studentRepository.findByIndex(index).orElseThrow(()->new StudentNotFoundException(String.
                format("Student with index %s does not exist",index)));
    }

    public Student findByEmail(String email){
        return studentRepository.findByEmail(email).orElseThrow(()->new StudentNotFoundException(String.
                format("Student with email %s does not exist",email)));
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student create(StudentCommand command) throws StudentExistException {
        if (studentRepository.existsByEmail(command.getEmail())){
            throw new StudentExistException(String.format("Student with email %s already exist",command.getEmail()));
        }
        else if(studentRepository.existsByIndex(command.getIndex())){
            throw new StudentExistException(String.format("Student with index %s already exist",command.getIndex()));
        }
        else{
            Student student=studentMapper.createStudent(command);
            return studentRepository.save(student);
        }

    }

    public Student update(StudentCommand command){
        Student student=studentRepository.findById(command.getId()).orElseThrow(()->new StudentNotFoundException(String.
                format("Student with id %d does not exist",command.getId())));
        studentMapper.updateStudent(command,student);
        return student;
    }
}
