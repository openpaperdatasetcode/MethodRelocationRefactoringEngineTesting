package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
class SourceClass {public class MemberInner {public <T> void handle(T value) {System.out.println("Handling: " + value);}}
@ProcessAnnotationpublic <T> void process(TargetClass<T> target) {// Local inner classclass LocalProcessor {void processInner(TargetClass<T>.Inner inner) {// Variable call - access target inner class's fieldinner.data = target.baseValue;// Access instance method of target inner classinner.printData();}}LocalProcessor processor = new LocalProcessor();
// Method with generic parameterMemberInner memberInner = new MemberInner();memberInner.handle(target.baseValue);
// Access target's instance methodTargetClass<T>.Inner targetInner = target.createInner();processor.processInner(targetInner);
// Variable call - access target's fieldSystem.out.println("Target base value: " + target.baseValue);}}
strictfp class TargetClass<T> {public T baseValue;
public class Inner {public T data;
public void printData() {System.out.println("Inner data: " + data);}}
public Inner createInner() {return new Inner();}}