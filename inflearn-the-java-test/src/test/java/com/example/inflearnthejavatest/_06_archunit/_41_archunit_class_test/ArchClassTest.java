package com.example.inflearnthejavatest._06_archunit._41_archunit_class_test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.example.inflearnthejavatest.InflearnTheJavaTestApplication;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import javax.persistence.Entity;

@AnalyzeClasses(
    packagesOf = InflearnTheJavaTestApplication.class,
    importOptions = ImportOption.DoNotIncludeTests.class
)
public class ArchClassTest {

  @ArchTest
  ArchRule controllerClassRule = classes().that().haveSimpleNameEndingWith("Controller")
      .should().accessClassesThat().haveSimpleNameEndingWith("Service")
      .orShould().accessClassesThat().haveSimpleNameEndingWith("Repository");

  @ArchTest
  ArchRule repositoryClassRule = noClasses().that().haveSimpleNameEndingWith("Repository")
      .should().accessClassesThat().haveSimpleNameEndingWith("Service");

  @ArchTest
  ArchRule studyClassesRule = classes().that().haveSimpleNameStartingWith("Study")
      .and().areNotEnums()
      .and().areNotAnnotatedWith(Entity.class)
      .should().resideInAnyPackage("..study..");
}
