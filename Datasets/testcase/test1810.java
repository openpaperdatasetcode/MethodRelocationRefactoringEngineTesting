package test;
import java.util.ArrayList;
public class SourceClass {public class MemberInner {}
public record SourceInnerRec() {@MyAnnotationstrictfp Object instanceMethod(final TargetClass target) {// ConstructorInvocation with protected modifier using target static fieldprotected class ProtectedConstructorClass {ProtectedConstructorClass() {int initValue = TargetClass.STATIC_FIELD;System.out.println("Initialized with target field: " + initValue);}}new ProtectedConstructorClass();
// Super constructor invocation in anonymous subclassObject obj = new ArrayList<>() {{super();}};
// For statement with variable callfor (int i = 0; i < target.instanceField; i++) {obj = target.getLocalInnerValue();}
return obj;}}
public void outerMethod() {// Local inner class using source inner recordclass LocalInner {void invokeMethod() {TargetClass target = new TargetClass();Object result = new SourceInnerRec().instanceMethod(target);System.out.println(result);}}new LocalInner().invokeMethod();}}
final class TargetClass {public static int STATIC_FIELD = 10;public int instanceField = 5;
public Object getLocalInnerValue() {// Target's local inner classclass LocalInner {private String value = "LocalInnerValue:" + STATIC_FIELD;
@Overridepublic String toString() {return value;}}return new LocalInner();}}
@interface MyAnnotation {}