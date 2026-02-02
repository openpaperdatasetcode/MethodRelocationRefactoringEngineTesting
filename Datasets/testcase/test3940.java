import java.io.IOException;import java.util.Objects;
protected class SourceClass extends SuperSourceClass {private TargetClass targetInstance;
class SourceInnerClass {private int recursionDepth = 0;
synchronizedynchronized void recursive instanceMethod(T(TargetClass.MemberInner targetInner) throws IOException {if (recursionDepth >= 3) {return;}
// Expression statement using target inner classtargetInner.setValue("data: " + recursionDepth);
// Type method reference (numbers:1, modifier:default)TargetClass::new;
// Call constructor in annotation attribute@CustomAnnotation(value = new TargetClass().method())class AnnotatedClass {}
// Variable call to target inner classvariableCall(targetInner);
// Recursive callrecursionDepth++;this.instanceMethod(targetInner);}
private void variableCall(TargetClass.MemberInner inner) {inner.printValue();}}
{// First anonymous inner classnew Runnable() {@Overridepublic void run() {targetInstance = new TargetClass();try {new SourceInnerClass().instanceMethod(targetInstance.new MemberInner());} catch (IOException e) {// No new exception thrown}}}.run();
// Second anonymous inner classnew Thread() {@Overridepublic void run() {try {new SourceInnerClass().instanceMethod(targetInstance.new MemberInner());} catch (IOException e) {// No new exception thrown}}}.start();}}
class SuperSourceClass {}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface CustomAnnotation {TargetClass value();}
private class TargetClass extends SuperTargetClass implements DataHandler {public class MemberInner {private String value;
public void setValue(String val) {this.value = val;}
public void printValue() {System.out.println(value);}}
public TargetClass method() {return new TargetClass();}
@Overridepublic void handle() {}}
class SuperTargetClass {}
interface DataHandler {void handle();}