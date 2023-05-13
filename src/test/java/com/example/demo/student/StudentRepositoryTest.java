package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        // for each test we will have a clean state
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmailExists() {
        // given
        Student student = new Student(
                "Jamal",
                "jamal@gmail.com",
                Gender.MALE
        );
        underTest.save(student);

        // when
        boolean expected = underTest.selectExistsEmail(student.getEmail());

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExists() {
        // given
        Student student = new Student(
                "Jamal",
                "jamal@gmail.com",
                Gender.MALE
        );

        // when
        boolean expected = underTest.selectExistsEmail(student.getEmail());

        // then
        assertThat(expected).isFalse();
    }
}