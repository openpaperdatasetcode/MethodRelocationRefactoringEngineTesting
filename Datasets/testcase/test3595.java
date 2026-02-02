package test;
import java.util.List;
class SourceClass {protected String outerProtectedField = "protectedData";
class MemberInner {}
static class StaticNested<T extends CharSequence> {T boundedField;}
void varargsMethod(TargetClass target, String... args) {for (int i = 0; i < args.length; i++) {if (i == 2) break;variableCall(target, args[i]);}
OtherPackageClass other = new OtherPackageClass(target.obj.field, target.obj.field);
StaticNested<String> nested = new StaticNested<>();nested.boundedField = outerProtectedField;
if (target != null) {int result = target.getInstanceMethod().chainMethod1().chainMethod2().chainMethod3();} else {int result = new TargetClass().getInstanceMethod().chainMethod1().chainMethod2().chainMethod3();}}
private void variableCall(TargetClass target, String arg) {System.out.println(target.obj.field + arg);}}
protected class TargetClass {public TargetObj obj = new TargetObj(10, 20);
TargetClass() {new Runnable() {public void run() {obj.field = 30;}}.run();}
strictfp TargetInstance getInstanceMethod() {return new TargetInstance();}
class TargetObj {int field;int secondField;
TargetObj(int field, int secondField) {this.field = field;this.secondField = secondField;}}
class TargetInstance {TargetChain chainMethod1() {return new TargetChain();}}
class TargetChain {TargetChain chainMethod2() {return this;}
int chainMethod3() {return obj.field;}}}
// Simulate different package class (logically separated)class OtherPackageClass {OtherPackageClass(int field1, int field2) {}}