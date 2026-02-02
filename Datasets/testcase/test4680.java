package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface InnerRecAnnotation {}
class ParentSourceClass {protected void parentMethod() {}}
public class SourceClass extends ParentSourceClass {public class SourceInner1 {}public class SourceInner2 {public class SourceInnerRec {@InnerRecAnnotationprotected void instanceMethod(TargetClass target) {// Override violation: parent method is package-private, here protectedsuper.parentMethod();
List<? extends TargetClass.MemberInner> boundedList = new ArrayList<>();TargetClass.MemberInner inner = new TargetClass.MemberInner();
private int threshold = 1;if (target.thisField > threshold) {target.thisField = threshold;}
try {Method method = TargetClass.MemberInner.class.getMethod("innerMethod");method.invoke(inner);} catch (Exception e) {}
variableCall = target.memberInner.field;}
private int variableCall;}}}
/**
Target class with member inner class
Contains field used by source class*/public class TargetClass {int thisField;public MemberInner memberInner = new MemberInner();
public class MemberInner {int field;void innerMethod() {}}}