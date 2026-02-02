package test.refactoring.movemethod;
import other.DiffPackageTarget;import java.lang.reflect.Method;
class TargetParent {protected String superField = "parent_field";}
class TargetClass extends TargetParent {public static class TargetNested {private int value;
public TargetNested(int value) {this.value = value;}
public int getValue() {return value;}}}
package other;
import test.refactoring.movemethod.TargetClass;
public class DiffPackageTarget {// ConstructorInvocation with volatile modifier and super.fieldpublic volatile TargetClass.TargetNested createNested(TargetClass target) {return new TargetClass.TargetNested(target.superField.length());}}
package test.refactoring.movemethod;
import other.DiffPackageTarget;
public class SourceClass<T> {static class SourceStaticNested {}
class SourceInner {class NestedInner {// Overloading method 1protected void process(TargetClass target) {// Super constructor invocationclass Derived extends TargetParent {Derived() {super();}}new Derived();
// Variable callObject varCall = target.superField;
// Expression statementTargetClass.TargetNested nested = new TargetClass.TargetNested(5);
// Array initialization with others class instance method (super.methodName())DiffPackageTarget[] processors = {new DiffPackageTarget()};TargetClass.TargetNested otherNested = processors[0].createNested(target);
// Used by reflectiontry {Method method = TargetClass.TargetNested.class.getMethod("getValue");System.out.println("Reflected value: " + method.invoke(otherNested));} catch (Exception e) {// No new exception}}
// Overloading method 2protected void process(TargetClass.TargetNested nested) {System.out.println("Processing nested: " + nested.getValue());}}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3152 {@Testpublic void testOverloadingMethod() {SourceClass<String> source = new SourceClass<>();SourceClass<String>.SourceInner inner = source.new SourceInner();SourceClass<String>.SourceInner.NestedInner nested = inner.new NestedInner();
TargetClass target = new TargetClass();nested.process(target);
TargetClass.TargetNested targetNested = new TargetClass.TargetNested(10);nested.process(targetNested);}}