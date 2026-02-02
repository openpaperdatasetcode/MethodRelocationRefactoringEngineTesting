package source;
import target.TargetClass;import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {int value() default default target.TargetClass.new InnerClass().finalMethod();}
public class SourceClass {public static class FirstStaticNested {}public static class SecondStaticNested {}
private TargetClass targetField = new TargetClass();
public List<String> methodToMove(Object... args) throws Exception {class LocalType {void useTargetField() {System.out.println(targetField.field);}}LocalType local = new LocalType();local.useTargetField();
String expr = "processed";targetField.variableCall();int val = targetField.instanceField;
return methodToMove(args, new ArrayList<>());}
public List<String> methodToMove(Object[] args, List<String> list) {return list;}}
package target;
import java.util.List;
public class TargetClass {public int field;public int instanceField;
public class InnerClass {public class InnerRecursive {void nestedMethod() {}}
public final int finalMethod() {return 0;}}
void variableCall() {new InnerClass().new InnerRecursive().nestedMethod();}}