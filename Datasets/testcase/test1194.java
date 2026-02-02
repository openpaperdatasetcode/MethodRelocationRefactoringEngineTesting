package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
/**
Generic source class with type parameter, static nested class, and member inner class
@param <T> type parameter (source feature)*/protected class SourceClass<T> extends ParentClass {// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Source feature: member inner classpublic class SourceMemberInnerClass {}
/**
Normal method to be refactored (final access, returns List<String>)
@param targetParam target class parameter (per_condition)
@return List<String> result*/public final List<String> refactorTargetMethod(AbstractTargetClass targetParam) {List<String> result = new ArrayList<>();
// Super constructor invocation (explicit via parent class instance)ParentClass parentInstance = new ParentClass();
// Variable callAbstractTargetClass tempTarget = targetParam;AbstractTargetClass.TargetStaticNested targetNested = new AbstractTargetClass.TargetStaticNested();
// Expression statementtargetNested.nestedMethod();tempTarget.toString();
// With_bounds (generic type with upper bounds)List> boundedList = new ArrayList<>();
// Depends_on_static_field (target class static field)result.add(AbstractTargetClass.TARGET_STATIC_FIELD);
// VariableDeclarationStatement in source (type: VariableDeclarationStatement, modifier: volatile)volatile String classNameField = AbstractTargetClass.TARGET_STATIC_FIELD; // ClassName.field (target static field)volatile int count = 1; // "1" in target_feature
// No new exception thrownreturn result;}}
/**
Parent class for source class super constructor invocation
*/
class ParentClass {
// Super constructor for invocation feature
public ParentClass() {}
}
/**
Abstract target class: abstract modifier, target_feature: static nested class (target_inner)*/abstract class AbstractTargetClass {// Target static field for ClassName.field and depends_on_static_field featurespublic static final String TARGET_STATIC_FIELD = "target_static_value";
// Target feature: static nested class (target_inner)public static class TargetStaticNested {public void nestedMethod() {}}
// Constructor for target class instantiationpublic AbstractTargetClass() {}}
// Concrete subclass of abstract target class (for instantiation)class ConcreteTargetClass extends AbstractTargetClass {}
// Container class to access protected SourceClassclass SourceClassContainer {public <T> SourceClass<T> createSourceClass() {return new SourceClass<>();}}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) {SourceClassContainer container = new SourceClassContainer();SourceClass<String> source = container.createSourceClass();AbstractTargetClass target = new ConcreteTargetClass();
List<String> result = source.refactorTargetMethod(target);System.out.println("Refactor result: " + result); // Output: [target_static_value]}}