package com.tpg.smp.services.registration;

import com.google.common.base.Optional;
import com.tpg.smp.domain.ContactDetailType;
import com.tpg.smp.persistence.entities.ContactDetailsEntity;
import com.tpg.smp.persistence.entities.StudentEntity;
import com.tpg.smp.persistence.entities.UserEntity;
import com.tpg.smp.persistence.entities.embeddables.Name;
import com.tpg.smp.persistence.repositories.ContactsManagementRepository;
import com.tpg.smp.persistence.repositories.PersonManagementRepository;
import com.tpg.smp.persistence.repositories.StudentManagementRepository;
import com.tpg.smp.persistence.repositories.UsersQueryRepository;
import com.tpg.smp.services.ServiceOutcome;
import com.tpg.smp.services.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tpg.smp.domain.ContactDetailType.EmailAddress;
import static com.tpg.smp.domain.ContactDetailType.MobileNumber;
import static com.tpg.smp.domain.ContactDetailType.TelephoneNumber;

@Service
public class StudentRegistrationHandler implements StudentRegistrationService {
    private ContactsManagementRepository contactsManagementRepository;

    private PersonManagementRepository personManagementRepository;

    private StudentManagementRepository studentManagementRepository;

    private UsersQueryRepository usersQueryRepository;

    private StudentNumberGenerator studentNumberGenerator;

    @Autowired
    public StudentRegistrationHandler(UsersQueryRepository usersQueryRepository,
                                      PersonManagementRepository personManagementRepository,
                                      ContactsManagementRepository contactsManagementRepository,
                                      StudentManagementRepository studentManagementRepository,
                                      StudentNumberGenerator studentNumberGenerator) {
        this.usersQueryRepository = usersQueryRepository;
        this.personManagementRepository = personManagementRepository;
        this.contactsManagementRepository = contactsManagementRepository;
        this.studentManagementRepository = studentManagementRepository;
        this.studentNumberGenerator = studentNumberGenerator;
    }

    @Override
    @Transactional
    public ServiceOutcome registerStudent(StudentRegistrationModel studentRegistration) {
        Optional<UserEntity> user = usersQueryRepository.findByUsernameAndSecureToken(studentRegistration.getUserModel().getUsername(),
                studentRegistration.getUserModel().getSecureToken());

        StudentEntity studentEntity = toEntity(user.get(), studentRegistration);

        personManagementRepository.save(studentEntity);
        studentManagementRepository.save(studentEntity);

        contactsManagementRepository.save(toContactDetailsEntity(studentRegistration.getContactDetails().getTelephoneNumber(), TelephoneNumber));
        contactsManagementRepository.save(toContactDetailsEntity(studentRegistration.getContactDetails().getMobileNumber(), MobileNumber));
        contactsManagementRepository.save(toContactDetailsEntity(studentRegistration.getContactDetails().getMainEmailAddress(), EmailAddress));

        return new Success("Student saved.");
    }

    private ContactDetailsEntity toContactDetailsEntity(String contactNo, ContactDetailType contactDetailType) {
        ContactDetailsEntity entity = new ContactDetailsEntity();

        entity.setDetail(contactNo);
        entity.setDetailType(contactDetailType);

        return entity;
    }

    private StudentEntity toEntity(UserEntity user, StudentRegistrationModel studentRegistration) {
        StudentEntity entity = new StudentEntity();

        entity.setUser(user);
        entity.setGender(studentRegistration.getGender());

        Name name = new Name();

        name.setFirstName(studentRegistration.getName().getFirstName());
        name.setLastName(studentRegistration.getName().getLastName());

        entity.setName(name);

        entity.setStudentNumber(studentNumberGenerator.generateStudentNumber());

        return entity;
    }
}
