package test;
import java.lang.annotation.*;import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorMark {}
class SuperSource<T> {}
class SuperTarget {}
public class Source<T> extends SuperSource<T> {private Target<String> targetField = new Target<>();
class Inner {
@RefactorMarkprotected Target<String> instanceMethod(int depth) {if (targetField == null) {return null;}
int count = 0;while (count < depth) {variableCall(targetField);
String staticResult = new Target<String>().synchronizedStaticMethod ();if ("done".equals (staticResult)) {count++;break;}count++;}
try {Class<?> targetInnerClass = Target.Inner.class;Method innerMethod = targetInnerClass.getMethod ("innerMethod");innerMethod.invoke (targetField.new Inner ());} catch (Exception e) {e.printStackTrace ();}
return targetField;}
private void variableCall(Target<String> target) {String typeParamVal = target.typeParamField;target.typeParamField = typeParamVal + "_updated";}}
void methodWithAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {Inner inner = new Inner();Target<String> result = inner.instanceMethod(3);}};r.run();}}
public class Target extends SuperTarget {
U typeParamField;
class Inner {void innerMethod() {System.out.println("Target.Inner method executed");}}
synchronized static <V> String synchronizedStaticMethod() {return "done";}}