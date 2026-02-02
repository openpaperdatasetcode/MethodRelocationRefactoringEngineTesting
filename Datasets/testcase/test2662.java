package test.same;
import java.util.function.Supplier;
private class SourceClass {class MemberInner {final Object varargsMethod(TargetClass... targets) {class TypeDecl {TargetClass.MemberInner createInner(TargetClass target) {return target.new MemberInner();}}TypeDecl typeDecl = new TypeDecl();
TargetClass target = new TargetClass();TargetClass.MemberInner inner = typeDecl.createInner(target);Object var = inner.field;
Supplier<TargetClass> creator1 = TargetClass::new;Supplier<TargetClass.MemberInner> creator2 = target::new MemberInner;inner = creator2.get();
int count = 0;while (count < 2) {var = inner.overloadMethod(count);var = inner.overloadMethod("count: " + count);count++;}
new OtherClass() {{new TargetClass() {{superField = "init";}};}};
return var;}}
Runnable anon = new Runnable() {public void run() {}};}
class TargetClass extends ParentClass implements Runnable {class MemberInner {Object field;
private Object overloadMethod(int num) {return num;}
private Object overloadMethod(String str) {return str;}}
public void run() {}}
class ParentClass {Object superField;}
class OtherClass {}