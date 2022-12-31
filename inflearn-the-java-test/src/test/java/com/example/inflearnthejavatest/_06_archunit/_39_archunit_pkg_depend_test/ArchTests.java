package com.example.inflearnthejavatest._06_archunit._39_archunit_pkg_depend_test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

class ArchTests {

  @Test
  void packageDependencyTests() {
    JavaClasses classes = new ClassFileImporter()
        .withImportOption(new ImportOption.DoNotIncludeTests()) // test 패키지 제외
        .importPackages("com.example.inflearnthejavatest");

    ArchRule domainPackageRule = classes().that().resideInAPackage("..domain..")
        .should().onlyBeAccessed().byClassesThat()
        .resideInAnyPackage("..study..", "..member..", "..domain..");
    domainPackageRule.check(classes);

    ArchRule memberPackageRule = noClasses().that().resideInAPackage("..domain..")
        .should().accessClassesThat().resideInAPackage("..member..");
    memberPackageRule.check(classes);

    ArchRule studyPackageRule = noClasses().that().resideOutsideOfPackage("..study..")
        .should().accessClassesThat().resideInAnyPackage("..study..");
    studyPackageRule.check(classes);

    ArchRule freeOfCycles = slices().matching("..inflearnthejavatest.(*)..")
        .should().beFreeOfCycles();
    freeOfCycles.check(classes);
  }
}
