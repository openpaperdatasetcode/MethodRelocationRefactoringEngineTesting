package test.same;
import java.util.ArrayList;import java.util.List;
strictfp class SourceClass {private int x = 10;
class MemberInner {private List<String> varargsMethod(TargetClass... targets) {List<String> result = new ArrayList<>();
TargetClass.StaticNested nested = new TargetClass.StaticNested() {{superField = 1;}};
for (TargetClass target : targets) {Object var = target.field;result.add(var.toString());
switch (target.id) {case 1:var = "one";break;case 2:var = "two";break;default:var = "default";}; // Empty statement}
Object outerVar = SourceClass.this.x;
int count = (nested.isValid())? OtherClass.synchronizedMethod(nested): 0;result.add(String.valueOf(count));
return result;}}}
final class TargetClass extends ParentClass {int id;Object field;
static class StaticNested extends ParentStatic {// Inherits superField from ParentStatic}}
class ParentClass {}
class ParentStatic {Object superField;
boolean isValid() {return superField != null;}}
class OtherClass {synchronized static int synchronizedMethod(ParentStatic staticNested) {return 1;}}