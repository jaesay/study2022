package com.example.inflearnthejavatest._06_archunit._40_archunit_junit5_intg;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.example.inflearnthejavatest.InflearnTheJavaTestApplication;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

@AnalyzeClasses(
    packagesOf = InflearnTheJavaTestApplication.class,
    importOptions = ImportOption.DoNotIncludeTests.class
)
class ArchTests {

  @ArchTest
  ArchRule domainPackageRule = classes().that().resideInAPackage("..domain..")
      .should().onlyBeAccessed().byClassesThat()
      .resideInAnyPackage("..study..", "..member..", "..domain..");

  @ArchTest
  ArchRule memberPackageRule = noClasses().that().resideInAPackage("..domain..")
      .should().accessClassesThat().resideInAPackage("..member..");

  @ArchTest
  ArchRule studyPackageRule = noClasses().that().resideOutsideOfPackage("..study..")
      .should().accessClassesThat().resideInAnyPackage("..study..");

  @ArchTest
  ArchRule freeOfCycles = slices().matching("..inflearnthejavatest.(*)..")
      .should().beFreeOfCycles();
}
