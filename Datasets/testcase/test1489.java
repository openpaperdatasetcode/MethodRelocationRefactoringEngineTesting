package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnotation {}
protected class TargetClass<T> {private List<String> targetData = new ArrayList<>();
public TargetClass(List<String> initData) {Runnable anonymous = new Runnable() {@Overridepublic void run() {targetData.addAll(initData);}};anonymous.run();}
public List<String> getTargetData() {return targetData;}}
class TargetSubclass<T> extends TargetClass<T> {public TargetSubclass(List<String> initData) {super(initData);}
@Overridepublic List<String> getTargetData() {return super.getTargetData();}}
final class SourceClass<K, V> {public static class StaticNestedClass {public static <T> List<String> processStaticData(List<String> data) {data.add("static_nested");return data;}}
public class MemberInnerClass {public List<String> getInnerData() {return new ArrayList<>(List.of("member_inner"));}}
@RefactorAnnotationpublic strictfp List<String> refactorMethod(TargetClass<String> targetParam) throws IllegalStateException {// Accessor feature (public, method_feature:1, sub_class, accessor, super.methodName(), pos:constructor parameter list)List<String> initData = new TargetSubclass<>(new ArrayList<>()).getTargetData();TargetClass<String> targetSub = new TargetSubclass<>(initData);List<String> targetAccessorData = targetSub.getTargetData();
// Expression statementMemberInnerClass inner = new MemberInnerClass();List<String> innerData = inner.getInnerData();
// Variable callList<String> staticData = StaticNestedClass.processStaticData(innerData);targetParam.getTargetData().addAll(staticData);
// Per_condition: contains target parameterif (targetParam.getTargetData().isEmpty()) {throw new IllegalStateException("Target data cannot be empty");}
List<String> result = new ArrayList<>();result.addAll(targetAccessorData);result.addAll(targetParam.getTargetData());return result;}}
// Test case classpublic class MoveMethodTest5155 {public static void main(String[] args) {SourceClass<Integer, String> source = new SourceClass<>();TargetClass<String> target = new TargetSubclass<>(new ArrayList<>(List.of("init")));
try {List<String> result = source.refactorMethod(target);
assert result.contains("init") : "Target init data check";assert result.contains("member_inner") : "Member inner class data check";assert result.contains("static_nested") : "Static nested class data check";assert result.size() == 4 : "Result list size check";} catch (IllegalStateException e) {assert false : "Unexpected exception: " + e.getMessage();}}}