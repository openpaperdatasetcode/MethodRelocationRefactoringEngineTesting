package test;
import java.util.ArrayList;import java.util.List;
class SourceClass<T> {protected String outerProtectedField = "source_protected";private TargetClass targetField = new TargetClass();
class MemberInner1 {public List<String> recursiveMethod(int depth) {List<String> result = new ArrayList<>();if (depth <= 0) {return result;}
typeDeclaration: {class LocalTypeDecl {}LocalTypeDecl typeInst = new LocalTypeDecl();
try {int val = MemberInner2.abstractMethodImpl(3);result.add(String.valueOf(val));} catch (Exception e) {result.add("exception_handled");}
String var = SourceClass.this.outerProtectedField;result.add(var);
TargetClass.MemberInner targetInner = targetField.new MemberInner();result.add(targetInner.getInnerVal());
for (String s : new String[]{"a", "b"}) {result.add(s);}
super.toString();}
result.addAll(recursiveMethod(depth - 1));return result;}}
class MemberInner2 {public static int abstractMethodImpl(int num) {abstract class AbstractInner {abstract int compute(int val);}AbstractInner impl = new AbstractInner() {@Overrideint compute(int val) {return val * 2;}};return impl.compute(num);}}}
strictfp class TargetClass {class MemberInner {public String getInnerVal() {return "target_inner_val";}}}
class OthersClass {public Object callMethod(SourceClass.MemberInner1 inner, int depth) {return inner.recursiveMethod(depth);}
public Object callMethod(SourceClass.MemberInner1 inner) {String[] arr = {"init_val"};return callMethod(inner, arr.length);}}