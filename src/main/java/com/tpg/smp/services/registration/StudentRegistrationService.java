package com.tpg.smp.services.registration;

import com.tpg.smp.services.ServiceOutcome;

public interface StudentRegistrationService {
    ServiceOutcome registerStudent(StudentRegistrationModel studentRegistration);
}
