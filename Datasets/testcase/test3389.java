package test;
import java.util.List;import java.util.ArrayList;
sealed class SourceClass permits SourceSubclass {private class FirstMemberInner {}private class SecondMemberInner {}
@RefactorAnnotationprivate TargetClass instanceMethod(TargetClass targetParam) {if (targetParam == null) {throw new IllegalArgumentException("Target parameter cannot be null");}
FirstMemberInner inner1 = this.new FirstMemberInner();List<String> recursionResult = recursiveMethod(3, inner1, targetParam);
targetParam.staticNested.variableCall();targetParam.setData(recursionResult);
for (int i = 0; i < 5; i++) {if (i == 2) {break;}targetParam.process(i);}
try {this.new SecondMemberInner().doAction();} catch (Exception e) {e.printStackTrace();}
return targetParam;}
private List<String> recursiveMethod(int depth, FirstMemberInner inner, TargetClass target) {List<String> result = new ArrayList<>();if (depth == 0) {result.add(target.getStaticFieldValue());return result;}result.addAll(recursiveMethod(depth - 1, inner, target));return result;}}
final class SourceSubclass extends SourceClass {}
protected class TargetClass {public StaticNested staticNested = new StaticNested();private List<String> data;
public static class StaticNested {public void variableCall() {}}
public static String getStaticFieldValue() {return "static_field_value";}
public void process(int num) {}
public void setData(List<String> data) {this.data = data;}}
@interface RefactorAnnotation {}