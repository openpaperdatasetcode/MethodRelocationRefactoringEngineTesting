package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;import com.other.OtherProcessor;
public class SourceClass {// Member inner classpublic class SourceInner {// Overloading method 1public Object overloadedMethod(TargetClass target) {// Type declaration statementList<String> results = new ArrayList<>();OtherProcessor processor = new OtherProcessor();
// Constructor invocationTargetClass.Inner targetInner = target.new Inner();
// Expression statementtargetInner.value = "test";
// Variable callresults.add(targetInner.value);
// 2 protected TypeMethodReferenceresults.add(String.valueOf(targetInner::getValue));results.add(String.valueOf(TargetClass.Inner::new));
// Private DoStatement with obj.field (diff package)processor.process(targetInner);
// Annotation with call method (inner_class)@MyAnnotation(handler = () -> target.new Inner().log())class AnnotationHolder {}
return results;}
// Overloading method 2 (overload exists)public Object overloadedMethod(TargetClass target, int flag) {Object base = overloadedMethod(target);
// Continue statementfor (int i = 0; i < 5; i++) {if (i % 2 == 0) continue;((List<String>) base).add("i=" + i);}
return base;}}
// Anonymous inner classprivate Runnable initializer = new Runnable() {@Overridepublic void run() {new SourceInner().overloadedMethod(new TargetClass());}};}
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {Runnable handler();}
// Package: com.otherpackage com.other;
import test.TargetClass;
public class OtherProcessor {public void process(TargetClass.Inner inner) {// Private DoStatement with obj.fieldclass DoProcessor {private void execute() {int i = 0;do {inner.value += "_" + i;i++;} while (i < 3);}}new DoProcessor().execute();}}
// Back to original packagepackage test;
non-sealed class TargetClass extends SealedParent {{// Local inner classclass LocalValidator {boolean isValid(String val) {return val != null && !val.isEmpty();}}new LocalValidator().isValid("init");}
public class Inner {public String value;
public String getValue() {return value;}
private void log() {System.out.println("Logged: " + value);}}}
sealed class SealedParent permits TargetClass {}