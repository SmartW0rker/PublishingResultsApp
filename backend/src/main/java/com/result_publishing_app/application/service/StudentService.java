package com.result_publishing_app.application.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.result_publishing_app.application.exceptions.StudentExistException;
import com.result_publishing_app.application.exceptions.StudentNotFoundException;
import com.result_publishing_app.application.exceptions.SubjectExistException;
import com.result_publishing_app.application.mapper.StudentMapper;
import com.result_publishing_app.application.model.SemesterEnum;
import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.model.student.StudentCommand;
import com.result_publishing_app.application.model.subject.Subject;
import com.result_publishing_app.application.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentMapper studentMapper;
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
            Student student=studentMapper.commandToModel(command);
            return studentRepository.save(student);
        }

    }

    public Student update(StudentCommand command){
        Student student=studentRepository.findByIndex(command.getIndex()).orElseThrow(()->new StudentNotFoundException(String.
                format("Student with index %s does not exist",command.getIndex())));
        studentMapper.updateStudent(command,student);

        return studentRepository.save(student);
    }

    public void delete(String index){
        Student student=studentRepository.findByIndex(index).orElseThrow(()->new StudentNotFoundException(String.
                format("Student with index %s does not exist",index)));

        studentRepository.delete(student);
    }

    @Transactional
    public void importCsv(MultipartFile file) throws IOException, CsvValidationException {
        CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
        String[] line;
        //Read first line because it is data with row names
        csvReader.readNext();
        while ((line = csvReader.readNext()) != null) {
            if (studentRepository.existsByIndex(line[0]))
                throw new StudentExistException(String.format("Student with index %s already exist",line[0]));
            Student student = new Student();
            student.setIndex(line[0]);
            student.setEmail(line[1]);
            studentRepository.save(student);
        }
    }
}
