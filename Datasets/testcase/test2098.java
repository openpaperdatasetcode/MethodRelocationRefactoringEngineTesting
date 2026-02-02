package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class SuperClass {}
class OtherClass {private static String name;private int value;
public static void setName(String n) {name = n;}
public int getValue() {return value;}}
private class SourceClass<T> extends SuperClass {class InnerClass {@MyAnnotationTargetClass<T> methodToMove(TargetClass<T>... targets) {super();SourceClass.this.toString();;
for (TargetClass<T> target : targets) {try {OtherClass.setName("test");target.setData(OtherClass.getValue());target.variableCall();target.accessInstanceMethod();} catch (Exception e) {}}
return targets[0];}}}
public class TargetClass<T> {T data;
class MemberInner {}
public void setData(int val) {// Set data logic}
void variableCall() {}
void accessInstanceMethod() {}}