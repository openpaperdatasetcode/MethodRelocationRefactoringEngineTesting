package test.refactoring.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.Objects;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
class OtherClass {public void log(String message) {System.out.println("Log: " + message);}
public void log(int value) {System.out.println("Log (int): " + value);}}
class TargetClass {private String data;
public TargetClass(String data) {this.data = data;}
public class TargetInner {private int count;
public TargetInner(int count) {this.count = count;}
public int getCount() {return count;}
public String getCombined() {return data + "_" + count;}}
public String getData() {return data;}}
protected class SourceClass {static class SourceStaticNested1 {}static class SourceStaticNested2 {}
class SourceInner {/**
Processes TargetClass instance and returns combined result
@param target the TargetClass instance to process
@return combined object from target and its inner class*/@ProcessAnnotationpublic final Object process(TargetClass target) {// Variable callObject varCall = target.getData();
// Create inner class instanceTargetClass.TargetInner inner = target.new TargetInner(5);
// Synchronized statementsynchronized (target) {inner = target.new TargetInner(inner.getCount() + 3);}
// Ternary operator with others class overloading methodsOtherClass other = new OtherClass();String resultStr = inner.getCombined();int length = resultStr.length();length > 10 ? other.log(resultStr) : other.log(length);
return new Object() {@Overridepublic String toString() {return "Processed: " + resultStr;}};}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3174 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();TargetClass target = new TargetClass("test_data");
Object result = inner.process(target);assertTrue(result.toString().contains("test_data_8"));}}