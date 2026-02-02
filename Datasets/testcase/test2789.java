package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
private class SourceClass {class MemberInner1 {}class MemberInner2 {}
private List<String> methodToMove(TargetClass target) throws IOException {// EnhancedForStatement with this.field and count 3for (String str : target.stringList) {target.this.field1 = str;target.this.field2 = str;target.this.field3 = str;}
// Instance method from parent class via super.methodName() in collection operationstarget.stringList.forEach(item -> target.parentInstanceMethod());
// Variable calltarget.toString();// Uses outer thisSourceClass.this.toString();
return target.stringList;}}
public class TargetClass extends ParentClass {public List<String> stringList = new ArrayList<>();public String field1;public String field2;public String field3;
public void someMethod() {// Local inner class (target_feature)class LocalInner {}}
@Overridepublic void parentInstanceMethod() {super.parentInstanceMethod();}}
class ParentClass {public void parentInstanceMethod() {}}