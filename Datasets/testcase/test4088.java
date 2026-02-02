package test;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;import java.util.List;import java.util.ArrayList;
@Target({ElementType.METHOD, ElementType.TYPE})@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnno {}
class ParentGeneric<T> {protected int superField = 1;}
private class SourceClass<T> extends ParentGeneric<T> {@RefactorAnnostatic class StaticNested {}
@RefactorAnnoclass MemberInner {public void instanceMethod(TargetClass<List<String>> targetParam) {private void expressionStmt() {int val = SourceClass.super.superField;}expressionStmt();
SourceClass.StaticNested<String> nested = new SourceClass.StaticNested<>();int var = targetParam.targetField;
TargetClass.MemberInner targetInner = targetParam.new MemberInner();int recursiveResult = targetInner.protectedRecursive(1);
List<String> data = new ArrayList<>();data.forEach(targetParam::callMethod);}}}
public class TargetClass<V> {int targetField = 5;
class MemberInner {protected int protectedRecursive(int count) {if (count <= 0) {return 0;}return protectedRecursive(count - 1) + 1;}}
public int callMethod(String item) {return item.length();}}
