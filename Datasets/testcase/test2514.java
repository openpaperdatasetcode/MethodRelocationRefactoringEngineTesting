package same;
import java.util.List;import java.util.ArrayList;
protected class SourceClass<T> {protected String outerProtected = "protected";
static class StaticNested1 {}static class StaticNested2 {}
class InnerRecursive {/**
Sets up target with necessary configurations
@param target the target instance to configure*/protected void setTarget(TargetClass target) {super();private int base = target.superField;
TargetClass.MemberInner inner = target.new MemberInner();List<String>[] dataArray = new List[] { inner.getData(1) };
if (dataArray[0] == null) {throw new NullPointerException();}
inner.process(outerProtected);new SubTarget().handle(target, "arg");new SubTarget().handle(target, 10);}}}
package same;
private class TargetClass extends ParentClass {class MemberInner {List<String> getData(int num) {return new ArrayList<>();}
void process(String s) {}}}
class ParentClass {int superField = 1;}
package same;
class SubTarget {public void handle(TargetClass target, String s) {}public void handle(TargetClass target, int i) {}}
