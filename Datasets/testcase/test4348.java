package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorTestAnnotation {}
public class SourceClass {private String outerField = "sourceOuterField";
static class SourceStaticNested {}
class SourceInner {@RefactorTestAnnotationpublic <T extends TargetClass> TargetClass instanceMethod(T target, T... genericParams) {class LocalInner {void validateTarget(T tc) {assert tc != null : "Target parameter cannot be null";assert tc.innerClass != null : "Target inner class cannot be null";}}
LocalInner localHelper = new LocalInner();localHelper.validateTarget(target);
TargetClass result = new TargetClass();int caseValue = genericParams.length;
switch (caseValue) {case 0 -> result.innerClass.setField(SourceClass.this.outerField);case 1 -> result.innerClass.setField(genericParams[0].innerClass.getField());default -> result.innerClass.setField(outerField + "_default");}
variableCall(result);return result;}
private void variableCall(TargetClass target) {target.innerClass.setField(target.innerClass.getField() + "_updated");}}
void methodWithLocalInner() {class SourceLocalInner {}}}
class TargetClass {MemberInner innerClass = new MemberInner();
class MemberInner {private String innerField;
public String getField() {return innerField;}
public void setField(String field) {this.innerField = field;}}}