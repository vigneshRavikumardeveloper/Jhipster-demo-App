package org.jhipster.helloapp;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.jhipster.helloapp");

        noClasses()
            .that()
                .resideInAnyPackage("org.jhipster.helloapp.service..")
            .or()
                .resideInAnyPackage("org.jhipster.helloapp.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..org.jhipster.helloapp.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
