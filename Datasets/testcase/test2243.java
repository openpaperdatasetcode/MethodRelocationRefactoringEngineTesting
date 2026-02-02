package test;
class ParentClass {public Object method() {return null;}}
public class SourceClass extends ParentClass {static class StaticNested {}
public void localInnerMethod() {class LocalInner {}}
@Overrideprivate Object method() {TargetClass<String> target = new TargetClass<>();TargetClass<String>.MemberInner inner = target.new MemberInner();int field = inner.targetField;
Object[] array = {helperMethod(1)};
do {if (field > 0) {this.method();}} while (field-- > 0);
switch (field) {case 1:super.method();break;default:break;}
try {String str = inner.finalMethod();} catch (Exception e) {// No new exception}
return inner.obj.m1().m2().m3();}
public void helperMethod(int num) {// Helper method implementation}}
protected class TargetClass<T> {class MemberInner {int targetField = 1;SourceClass obj = new SourceClass();
final String finalMethod() {super.toString();return "test";}
public SourceClass m1() { return obj; }public SourceClass m2() { return obj; }public Object m3() { return new Object(); }}}