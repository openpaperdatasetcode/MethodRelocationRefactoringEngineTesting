package test;
import java.lang.reflect.Method;
public abstract class SourceClass {private String outerField = "sourceOuterField";
public class SourceMemberInner {public String innerField;public SourceMemberInner(String field) {this.innerField = field;}}
@MyCustomAnnotationObject recursiveMethod(TargetClass target, int depth) {if (depth <= 0) {return target.targetField;}
volatile SourceMemberInner innerObj = new SourceMemberInner(SourceClass.this.outerField);
int count = 0;while (count < 2) {int result1 = TargetClass.SuperTarget::targetMethod1;int result2 = TargetClass.SuperTarget::targetMethod2;count++;}
String var = target.targetField + innerObj.innerField;
try {Method targetMethod = TargetClass.class.getMethod("getTargetField");String reflectedVal = (String) targetMethod.invoke(target);var += reflectedVal;} catch (Exception e) {var += "reflectionError";}
return recursiveMethod(target, depth - 1) + var;}
public void sourceMethod() {class SourceLocalInner {void printOuter() {System.out.println(outerField);}}new SourceLocalInner().printOuter();}}
protected abstract class TargetClass extends TargetClass.SuperTarget {public String targetField = "targetInstanceField";
public class TargetMemberInner {public void useTargetField() {System.out.println(targetField);}}
public static class SuperTarget {public int targetMethod1() {return 10;}public int targetMethod2() {return 20;}}
public String getTargetField() {return targetField;}}
@interface MyCustomAnnotation {}