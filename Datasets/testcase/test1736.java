package test;
import otherpackage.OthersClass;import java.util.ArrayList;import java.util.List;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
private record SourceClass(int id) extends ParentRecord {class MemberInner {}
{new Runnable() {}; // Anonymous inner class}
@CustomAnnotationList<String> instanceMethod(TargetRecord target) {// Access target field (record component)String targetField = target.value();
// DoStatement (do-while) in diff_package_others with this.field access (1 target)OthersClass others = new OthersClass();static int count = 0;do {others.process(this.id()); // this.field (record component)count++;} while (count < 3);
// Overriding method from others_class in for loop (1 instance)@Overrideprotected List<String> othersMethod() {List<String> list = new ArrayList<>();for (int i = 0; i < 2; i++) {list.addAll(super.othersMethod());}return list;}
// Enhanced for statementList<String> targetList = target.nested().values();for (String val : targetList) {variableCall();}
variableCall();return new ArrayList<>();}
private void variableCall() {}}
private record TargetRecord(String value, Nested nested) {static record Nested(List<String> values) {} // Static nested class (record)}
abstract class ParentRecord {protected List<String> othersMethod() {return new ArrayList<>();}}
package otherpackage;
import java.util.List;
public class OthersClass {public void process(int value) {}
public List<String> othersMethod() {return List.of();}}