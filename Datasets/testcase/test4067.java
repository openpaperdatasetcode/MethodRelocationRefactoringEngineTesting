package source;
import target.TargetClass;import java.util.List;import java.util.ArrayList;
private class SourceClass {private TargetClass targetField;
class MemberInnerClass {@MyAnnotationprotected Object varargsMethod(String... args) {TargetClass newTarget = new TargetClass(1, 2);Object varCall = newTarget.field1;int val = newTarget.field2;
class LocalInnerClass {void localMethod() {System.out.println(targetField);}}
LocalInnerClass local = new LocalInnerClass();local.localMethod();
return varCall;}}}
package target;
import java.util.List;
interface MyInterface {}
private class TargetClass implements MyInterface {int field1;int field2;
private TargetClass(int f1, int f2) {this.field1 = f1;this.field2 = f2;}}
final class ParentClass {public <T> List<String> genericMethod(T t) {return new ArrayList<>();}}
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class OtherClass {void init() {SourceClass source = new SourceClass();SourceClass.MemberInnerClass inner = source.new MemberInnerClass();Object obj = new ParentClass().genericMethod(inner).stream().toList();}}