package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface Ann1 {}
@Retention(RetentionPolicy.RUNTIME)@interface Ann2 {}
class SourceClass {private int outerPrivateField = 10;private TargetClass targetField = new TargetClass();static int staticField = 5;
class MemberInner1 {class InnerRec {Object moveMethod(TargetClass... params) {@Ann1int val1 = 0;@Ann2String val2 = "";
switch (TargetClass.staticField) {case 1:variableCall(params[0]);break;default:break;}
System.out.println(SourceClass.this.outerPrivateField);return staticField + TargetClass.staticField;}
private void variableCall(TargetClass target) {target.innerClass.innerRec.doTask();}}}
class MemberInner2 {}}
public class TargetClass {public static int staticField = 1;
class TargetInner {class TargetInnerRec {void doTask() {}}
TargetInnerRec innerRec = new TargetInnerRec();}
TargetInner innerClass = new TargetInner();}
