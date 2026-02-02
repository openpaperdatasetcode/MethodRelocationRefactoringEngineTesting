package test;
import java.util.List;import java.util.ArrayList;
interface MyInterface {}
strictfp enum SourceEnum {ENUM_VALUE;
class SourceInner {private List<String> methodToMove(TargetEnum target) {List<String> result = new ArrayList<>();result.add(target.field);; // Empty statement
super.toString();
return result;}}}
enum TargetEnum implements MyInterface {TARGET_VALUE {{new Object() {};}};
String field;}
For id: 3750
package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class ParentClass {}
class OtherClass {OtherClass m1() { return this; }OtherClass m2() { return this; }int m3() { return 0; }
protected TargetClass methodWithThreeParams(int a, String b, Object c) {return new TargetClass();}}
@MyAnnotationclass SourceClass extends ParentClass {{TargetClass innerResult = new OtherClass().methodWithThreeParams(1, "two", 3);}
List<String> methodToMove(TargetClass target) {new Object() {};new Object() {};
TargetClass instance = new TargetClass(new OtherClass().m1().m2().m3());ArrayList rawList = new ArrayList();rawList.add(target);
if (target != null) {return rawList.stream().map(Object::toString).toList();}return new ArrayList<>();}}
private class TargetClass {class MemberInner {}
TargetClass(int param) {}
// Override violation (assuming parent has different signature/access)@Overridepublic String toString() {return super.toString();}}