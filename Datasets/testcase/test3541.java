package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnnotation {String value() default "test";}
class ParentSource {public ParentSource() {}}
class SourceClass extends ParentSource {private TargetClass targetField = new TargetClass();
class MemberInner {}
public void outerMethod() {class LocalInner {}new LocalInner();}
protected void moveMethod() {super();do {class TypeDeclaration {}new TypeDeclaration();
int flag = 1;switch (flag) {case 1:variableCall();targetField.innerClass.doTask();break;default:break;}} while (false);System.out.println(SourceClass.this);}
protected void moveMethod(String param) {super();variableCall();System.out.println(SourceClass.this);}
private void variableCall() {targetField.doTask();}}
class TargetClass {class TargetMemberInner {void doTask() {}}
private TargetMemberInner innerClass = new TargetMemberInner();
public void doTask() {}
protected void moveMethod() {}protected void moveMethod(int param) {}}
class OthersClass {@CallAnnotation(value = OthersClass.callMethod(new SourceClass(), new TargetClass()).toString())public static int callMethod(SourceClass source, TargetClass target) {TargetClass chainObj = new TargetClass();chainObj.innerClass.doTask().toString().hashCode();source.moveMethod();return 0;}}