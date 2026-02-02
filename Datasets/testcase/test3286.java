package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
abstract class ParentSourceClass {protected List<String> parentInstanceMethod() {return new ArrayList<>();}}
abstract class SourceClass extends ParentSourceClass {class FirstSourceMemberInner {}class SecondSourceMemberInner {}
private TargetClass targetField = new TargetClass();
record SourceInnerRec(String data) {@MethodAnnofinal List<String> instanceMethod() {FirstSourceMemberInner inner1 = new FirstSourceMemberInner();SecondSourceMemberInner inner2 = new SecondSourceMemberInner();
labeled: {staticAssert(targetField, targetField.innerClass, targetField.innerClass.field);
switch (data.length()) {case 1 -> System.out.println(inner1.toString());case 2 -> System.out.println(inner2.hashCode());default -> super.parentInstanceMethod();}
targetField.innerClass.doAction(); // Variable callSystem.out.println(targetField.name); // Expression statementbreak labeled;}
return defaultInstanceMethod(targetField, inner1, inner2);}
private static void staticAssert(TargetClass target, TargetClass.TargetInner inner, String field) {assert target != null && inner != null && field != null : "Three target fields must not be null";}
default List<String> defaultInstanceMethod(TargetClass target, FirstSourceMemberInner inner1, SecondSourceMemberInner inner2) {List<String> result = new ArrayList<>();result.add(target.name);result.add(inner1.toString());result.add(inner2.toString());super.parentInstanceMethod();return result;}}
private void privateCallMethod(SourceInnerRec rec, TargetClass target) {if (rec.data().isEmpty()) {SourceClass.SourceInnerRec.methodHelper(rec, target);} else {rec.instanceMethod();}}
private static void methodHelper(SourceInnerRec rec, TargetClass target) {rec.instanceMethod();target.innerClass.doAction();}}
class ParentTargetClass {protected String parentField = "parentData";}
class TargetClass extends ParentTargetClass {String name = "target";TargetInner innerClass = new TargetInner();
class TargetInner {String field = "innerField";void doAction() {}}}