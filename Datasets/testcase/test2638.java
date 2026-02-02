package test.same;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Constructor;
strictfp class SourceClass {class MemberInner {}
final List<String> instanceMethod(TargetClass target) {class LocalInner {TargetClass.MemberInner.Rec createRec(TargetClass t) {return t.new MemberInner().new Rec();}}LocalInner local = new LocalInner();
TargetClass.MemberInner.Rec rec = local.createRec(target);Object var = rec.field;
try {Constructor<?> ctor = TargetClass.MemberInner.Rec.class.getConstructor();rec = (TargetClass.MemberInner.Rec) ctor.newInstance();rec.superField = 3;} catch (Exception e) {}
target.new MemberInner().overloadMethod(rec);target.new MemberInner().overloadMethod(rec, "additional");
List<String> result = new ArrayList<>();result.add(rec.field.toString());return result;}}
private class TargetClass extends ParentClass {class MemberInner {record Rec() {Object field;Object superField;
Rec() {superField = ParentClass.superField;}}
void overloadMethod(Rec rec) {}void overloadMethod(Rec rec, String str) {}}}
class ParentClass {static Object superField = 3;}