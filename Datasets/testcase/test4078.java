package test;
import java.util.Arrays;import java.util.List;
interface SourceInterface {}
interface TargetInterface {}
class ParentTarget {}
public class SourceClass implements SourceInterface {private TargetClass targetField = new TargetClass();
static class StaticNested {}
class MemberInner {}
private void overloadedMethod(int a) {assert a > 0 : "Value must be positive";;
typeDeclaration: {final List<Integer> list = Arrays.asList(1);list.forEach(Integer::intValue);
if (targetField == null) {throw new NullPointerException("Target field is null");}
for (int i = 0; i < a; i++) {;}
int var = targetField.localInnerField;overloadedMethod(var);}}
private void overloadedMethod(String s) {int count = 2;while (count > 0) {int result = targetField.protectedRecursiveMethod(count);count = result;}
for (TargetClass.NestedInner inner : targetField.nestedInners) {if (inner.thisField == 1) {break;}}}}
public class TargetClass extends ParentTarget implements TargetInterface {int localInnerField = 1;List<NestedInner> nestedInners = Arrays.asList(new NestedInner());
class NestedInner {int thisField = 1;}
protected int protectedRecursiveMethod(int n) {class LocalInner {int getValue() { return n - 1; }}LocalInner inner = new LocalInner();int val = inner.getValue();return val > 0 ? protectedRecursiveMethod(val) : 0;}}