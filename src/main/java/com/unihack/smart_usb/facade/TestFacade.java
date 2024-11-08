package com.unihack.smart_usb.facade;

import com.unihack.smart_usb.api.dto.TestDto;
import com.unihack.smart_usb.exception.auth.EntityDoesNotExistException;
import com.unihack.smart_usb.exception.auth.UserDoesNotOwnEntityException;
import com.unihack.smart_usb.persistance.model.Professor;
import com.unihack.smart_usb.persistance.model.Test;
import com.unihack.smart_usb.service.implementations.TestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestFacade {

    private final TestService testService;

    @Transactional
    public TestDto getTestById(Long id, Professor professor) {
        Optional<Test> dbTestOptional = testService.getTestById(id);
        Test dbTest;
        if (dbTestOptional.isPresent()) {
            dbTest = dbTestOptional.get();
            if (dbTest.getProfessor() != null) {
                if (dbTest.getProfessor().getId() == professor.getId()) {
                    return TestDto.builder()
                            .id(dbTest.getId())
                            .duration(dbTest.getDuration())
                            .groupOneTestFileName(dbTest.getGroupOneTestFileName())
                            .groupTwoTestFileName(dbTest.getGroupTwoTestFileName())
                            .groupOneUnitTestFileName(dbTest.getGroupOneUnitTestFileName())
                            .groupTwoUnitTestFileName(dbTest.getGroupTwoUnitTestFileName())
                            .blacklistProcessesFileName(dbTest.getBlacklistProcessesFileName())
                            .professorId(dbTest.getProfessor().getId())
                            .title(dbTest.getTitle())
                            .build();
                } else {
                    throw new UserDoesNotOwnEntityException("The professor does not own the selected test.");
                }
            } else {
                throw new UserDoesNotOwnEntityException("The professor does not own the selected test.");
            }
        } else {
            throw new EntityDoesNotExistException("A test with the given id does not exist.");
        }
    }

    //TODO mozda treba, al verovatno ne, treba by title i prof Id
    public TestDto getTestByTitle(String title, Professor professor) {
        Optional<Test> dbTestOptional = testService.getTestByTitle(title);
        Test dbTest;
        if (dbTestOptional.isPresent()) {
            dbTest = dbTestOptional.get();
            if (dbTest.getProfessor() != null) {
                if (dbTest.getProfessor().getId() == professor.getId()) {
                    return TestDto.builder()
                            .id(dbTest.getId())
                            .duration(dbTest.getDuration())
                            .groupOneTestFileName(dbTest.getGroupOneTestFileName())
                            .groupTwoTestFileName(dbTest.getGroupTwoTestFileName())
                            .groupOneUnitTestFileName(dbTest.getGroupOneUnitTestFileName())
                            .groupTwoUnitTestFileName(dbTest.getGroupTwoUnitTestFileName())
                            .blacklistProcessesFileName(dbTest.getBlacklistProcessesFileName())
                            .professorId(dbTest.getProfessor().getId())
                            .title(dbTest.getTitle())
                            .build();
                } else {
                    throw new UserDoesNotOwnEntityException("The professor does not own the selected test.");
                }
            } else {
                throw new UserDoesNotOwnEntityException("The professor does not own the selected test.");
            }
        } else {
            throw new EntityDoesNotExistException("A test with the given title does not exist.");
        }
    }

    public List<TestDto> getTestByTitleQuery(String titleQuery, Professor professor) {
        List<Test> tests = testService.getTestByTitleQueryAndProfessorId(titleQuery, professor.getId());
        return tests.stream().map(dbTest -> TestDto.builder()
                .id(dbTest.getId())
                .duration(dbTest.getDuration())
                .groupOneTestFileName(dbTest.getGroupOneTestFileName())
                .groupTwoTestFileName(dbTest.getGroupTwoTestFileName())
                .groupOneUnitTestFileName(dbTest.getGroupOneUnitTestFileName())
                .groupTwoUnitTestFileName(dbTest.getGroupTwoUnitTestFileName())
                .blacklistProcessesFileName(dbTest.getBlacklistProcessesFileName())
                .professorId(dbTest.getProfessor().getId())
                .title(dbTest.getTitle())
                .build()).toList();
    }
}
