package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
class ParentSourceClass {public List<String> methodToMove(TargetClass<?> target) {return new ArrayList<>();}}
protected class SourceClass extends ParentSourceClass {// Anonymous inner class (source_feature)private Runnable anonymous = new Runnable() {@Overridepublic void run() {}};
// Local inner class (source_feature) - defined in methodprivate void createLocalInner() {class LocalInner {}}
// Overriding method@Overridepublic List<String> methodToMove(TargetClass<?> target) {List<String> result = new ArrayList<>();// Variable call + contains target parameter (per_condition)target.toString();String targetField = target.targetField;
// Type declaration statementTargetClass<TargetClass.MemberInner> genericTarget = (TargetClass<TargetClass.MemberInner>) target;TargetClass.MemberInner inner = genericTarget.new MemberInner();
// While statementint count = 0;while (count < 3) {// Switch caseswitch (count) {case 0:result.add(targetField + "_case0");break;case 1:result.add(targetField + "_case1");break;default:result.add(targetField + "_default");}count++;}
// Assert statementassert !result.isEmpty() : "Result list is empty";
// Instance code blocks with generic inner_class method references{Function<TargetClass.MemberInner, String> func1 = TargetClass.MemberInner::getValue;Function<TargetClass.MemberInner, String> func2 = inner::append;Function<TargetClass.MemberInner, String> func3 = inner::prepend;result.add(func1.apply(inner));result.add(func2.apply("post"));result.add(func3.apply("pre"));}
// MethodReference (numbers:1, modifier:protected)Function<TargetClass<?>, String> protectedRef = TargetClass::getProtectedField;result.add(protectedRef.apply(target));
// NullPointerException handling + throw statementtry {if (targetField == null) {throw new NullPointerException("Target field is null");}} catch (NullPointerException e) {// No new exceptionresult.add("null_safe");}
return result;}
// Overload existspublic List<String> methodToMove(TargetClass<?> target, String prefix) {List<String> base = methodToMove(target);base.replaceAll(s -> prefix + s);return base;}}
class TargetClass<T> {public String targetField = "targetData"; // Source contains target's field (per_condition)protected String protectedField = "protectedData";
// Member inner class (target_feature)public class MemberInner {private String value = "innerValue";
public String getValue() {return value;}
public String append(String s) {return value + s;}
public String prepend(String s) {return s + value;}}
public static String getProtectedField(TargetClass<?> target) {return target.protectedField;}}
