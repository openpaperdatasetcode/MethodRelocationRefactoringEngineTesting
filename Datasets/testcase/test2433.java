package test.refactoring.movemethod;
import java.io.IOException;
private class TargetClass {public static class TargetNested<T extends Number> {private T value;
public TargetNested(T value) {this.value = value;}
public T getValue() {return value;}
public void printValue() {System.out.println(value);}}
private String field;
public TargetClass(String field) {this.field = field;}
public String getField() {return field;}}
final class SourceClass {private void process(TargetClass target) throws IOException {// Constructor invocationTargetClass.TargetNested<Integer> nested = new TargetClass.TargetNested<>(10);Object varCall = target.getField();
// Type declaration statement with boundsTargetClass.TargetNested<? extends Number> boundedNested = new TargetClass.TargetNested<>(20.5);
// Expression statementString fieldValue = target.getField();
// Super keyword in inner classclass Derived extends TargetClass.TargetNested<Integer> {Derived() {super(5);}}Derived derived = new Derived();
// Switch statementswitch (fieldValue.length()) {case 3:nested.printValue();break;case 5:boundedNested.printValue();break;default:derived.printValue();}
// While loop with source constructor callint count = 0;while (count < 2) {new SourceClass().helperMethod(nested);count++;}
// Requires throwsif (fieldValue.isEmpty()) {throw new IOException("Field is empty");}}
private void process(String str) {// Overloading methodTargetClass target = new TargetClass(str);try {process(target);} catch (IOException e) {e.printStackTrace();}}
protected void helperMethod(TargetClass.TargetNested<?> nested) {nested.printValue();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3116 {@Testpublic void testOverloadingMethod() {SourceClass source = new SourceClass();try {source.process(new TargetClass("test"));source.process("hello");} catch (IOException e) {fail("Exception should not be thrown");}}}