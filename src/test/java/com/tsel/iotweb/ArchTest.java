package com.tsel.iotweb;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.tsel.iotweb");

        noClasses()
            .that()
            .resideInAnyPackage("com.tsel.iotweb.service..")
            .or()
            .resideInAnyPackage("com.tsel.iotweb.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.tsel.iotweb.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
