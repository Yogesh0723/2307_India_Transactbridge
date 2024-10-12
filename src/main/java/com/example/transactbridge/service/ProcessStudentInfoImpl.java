package com.example.transactbridge.service;

import com.example.transactbridge.entity.Studentinfo;
import com.example.transactbridge.repository.StudentInfoRepository; // Make sure to import your repository
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProcessStudentInfoImpl implements ProcessStudentInfo {

    private static final Logger logger = LoggerFactory.getLogger(ProcessStudentInfoImpl.class);

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    @Override
    @Transactional
    public void processStudent(StudentInfoDTO studentInfoDTO) {
        // Create a new Student entity from the DTO
        Studentinfo studentinfo = Studentinfo.builder()
                .studentName(studentInfoDTO.getStudentName())
                .studentLastName(studentInfoDTO.getStudentLastName())
                .studentRollNumber(studentInfoDTO.getStudentRollNumber())
                .address(studentInfoDTO.getAddress())
                .height(studentInfoDTO.getHeight())
                .weight(studentInfoDTO.getWeight())
                .email(studentInfoDTO.getEmail())
                .phoneNumber(studentInfoDTO.getPhoneNumber())
                .dateOfBirth(studentInfoDTO.getDateOfBirth())
                .gender(studentInfoDTO.getGender())
                .course(studentInfoDTO.getCourse())
                .build();

        // Validate required fields
        if (isStudentValid(studentinfo)) {
            // Prepare unique key
            studentinfo.prepareUniqueKey();
            String uniqueKey = studentinfo.getUniqueKey();
            Optional<Studentinfo> existingStudent = Optional.ofNullable(studentInfoRepository.findByUniqueKey(uniqueKey));
            try {
                if (existingStudent.isEmpty()) {
                    studentInfoRepository.save(studentinfo);
                    logger.info("Inserted studentinfo: {}", studentinfo);
                } else {
                    logger.warn("Student with unique key already exists: {}", studentinfo);
                }
            }catch (Exception e) {
                logger.error("Error processing transaction for student: {}", studentinfo, e);
            }
        }

    }

    private boolean isStudentValid(Studentinfo studentinfo) {
        return studentinfo.getStudentName() != null &&
                studentinfo.getStudentLastName() != null &&
                studentinfo.getStudentRollNumber() != null;
    }
}