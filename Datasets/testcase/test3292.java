package test;
import java.util.List;import java.util.ArrayList;
enum SourceEnum {INSTANCE;
static class SourceStaticNested {static String staticField = "staticData";}
class SourceMemberInner {protected abstract <T extends CharSequence> TargetEnum.TargetInner abstractMethod(TargetEnum targetParam);
protected <T extends CharSequence> TargetEnum.TargetInner instanceMethod(TargetEnum targetParam) {new Runnable() {@Overridepublic void run() {targetParam.new TargetInner().doAction();}}.run();
List<TargetEnum.TargetInner> innerList = new ArrayList<>();innerList.add(targetParam.new TargetInner());
for (TargetEnum.TargetInner inner : innerList) {inner.doAction();}
try {TargetEnum.TargetInner[] innerArr = new TargetEnum.TargetInner[]{parentHelperMethod(targetParam)};return innerArr[0];} catch (Exception e) {return targetParam.new TargetInner();}}
protected <T extends CharSequence> TargetEnum.TargetInner instanceMethod(TargetEnum targetParam, T data) {return targetParam.new TargetInner();}
private TargetEnum.TargetInner parentHelperMethod(TargetEnum target) {return target.new TargetInner();}}}
non-sealed enum TargetEnum permits {} {VALUE;
class TargetInner {void doAction() {}}}
class SubClass extends SourceEnum.SourceMemberInner {@Overrideprotected <T extends CharSequence> TargetEnum.TargetInner abstractMethod(TargetEnum targetParam) {TargetEnum.TargetInner inner = null;do {inner = targetParam.new TargetInner();inner.doAction();} while (inner == null);return inner;}}
// Test case for id:774package test;
import java.lang.reflect.Method;import java.util.function.Function;
sealed private class SourceGenericClass<T> permits SourceSubClass<T> {private String outerPrivateField = "privateData";
class SourceMemberInner {protected TargetGenericClass<T>.TargetInner instanceMethod(TargetGenericClass<T> targetParam) {Function<TargetGenericClass<T>.TargetInner, String> methodRef = TargetGenericClass<T>.TargetInner::getContent;
System.out.println(super.toString());
try {Method reflectMethod = TargetGenericClass.TargetInner.class.getDeclaredMethod("getContent");reflectMethod.invoke(targetParam.new TargetInner());} catch (Exception e) {}
TargetGenericClass<T>.TargetInner inner = targetParam.new TargetInner();inner.doAction();inner.getContent();
System.out.println(outerPrivateField);return inner;}}}
class SourceSubClass<T> extends SourceGenericClass<T> {}
abstract class TargetGenericClass {
class TargetInner {
String getContent() {
return "targetContent";
}
void doAction() {}}}