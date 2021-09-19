package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findStudentsByLastNameContaining(String str);
    //JPQL
    @Query("select s from Student  s where  s.id= ?1")
    public Student findByCustomId(Long id);
    @Transactional
    @Modifying
    @Query("update Student  e set e.firstName = ?2 where e.id = ?1")
    public void updateById(Long id, String newFirstName);
    @Transactional
    @Modifying
    @Query("delete from Student e where e.id = ?1")
    public void deleteByIdCustom(Long id);

    //Native
    @Query(value = "select * from Student ", nativeQuery = true)
    public List<Student> findAllStudentsCustom();

    @Transactional
    @Modifying
    @Query(value = "UPDATE Student set first_Name=?1 where id= ?2 ",nativeQuery = true)
    public void updateByIDNative( String newFirstName,Long id);


}
