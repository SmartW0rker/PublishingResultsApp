package com.result_publishing_app.application.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.result_publishing_app.application.exceptions.ProfessorExistException;
import com.result_publishing_app.application.exceptions.ProfessorNotFoundException;
import com.result_publishing_app.application.mapper.ProfessorMapper;
import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.professor.ProfessorCommand;
import com.result_publishing_app.application.model.professorRole.ProfessorRole;
import com.result_publishing_app.application.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    ProfessorMapper mapper;

    public Professor findById(String id) {
        return professorRepository.findById(id).orElseThrow(() -> new ProfessorNotFoundException(id));
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor findByEmail(String email) {
        return professorRepository.findByEmail(email).orElse(null);
    }

    public Professor createProfessor(ProfessorCommand command) {
        if (professorRepository.existsById(command.getId()))
            throw new ProfessorExistException(String.
                    format("Professor with id %s already exist", command.getId()));

        else if (professorRepository.existsByEmail(command.getEmail()))
            throw new ProfessorExistException(String.
                    format("Professor with email %s already exist", command.getEmail()));
        else {
            Professor professor = mapper.commandToModel(command);
            return professorRepository.save(professor);
        }
    }

    public Professor updateProfessor(ProfessorCommand command) {

        Professor professor = professorRepository.findById(command.getId()).orElseThrow(() -> new ProfessorNotFoundException(command.getId()));

        mapper.updateProfessor(command, professor);

        professorRepository.save(professor);

        return professor;

    }

    public void deleteProfessor(String id) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new ProfessorNotFoundException(id));

        professorRepository.delete(professor);
    }

    @Transactional
    public void importCsv(MultipartFile file) throws IOException, CsvValidationException {
        CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
        String[] line;
        csvReader.readNext();
        while ((line = csvReader.readNext()) != null) {
            if (professorRepository.existsById(line[0]))
                throw new ProfessorExistException(String.format("Professor with id %s already exist",line[0]));
            Professor professor = new Professor();
            professor.setId(line[0]);
            professor.setName(line[1]);
            professor.setEmail(line[2]);
            professor.setRole(ProfessorRole.valueOf(line[3]));
            professorRepository.save(professor);
        }
    }
}
