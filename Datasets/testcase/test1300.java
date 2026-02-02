package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
// Source enum class (public, with static nested + member inner class)public enum SourceEnum {INSTANCE1, INSTANCE2;
// Static nested class (source feature)public static class StaticNestedClass {public static int staticField = 10;
// Member inner class (source feature) - recursive inner structurepublic class MemberInnerClass {// Method to be refactored: instance, public, base type return, in source_inner_rec@ProcessAnnotationpublic int processTargetField(TargetEnum target) {// Overload exist (overloaded method below)return processTargetField(target, 0);}
// Overload method (overload_exist feature)public int processTargetField(TargetEnum target, int offset) {// Super keywords (call outer class method)super.toString();
// Constructor invocation + raw_type (raw List)List rawList = new ArrayList();rawList.add(new TargetEnum.EnumHelper());
// Variable callint base = target.targetField; // Source contains target's field (per_condition)VariableCaller caller = new VariableCaller();base += caller.calculate(base);
// TryStatement (static modifier, same_package_others pos)try {base += StaticNestedClass.staticField; // ClassName.field (target_feature[0])base += TargetEnum.OTHER_FIELD; // ClassName.field (2nd occurrence - target_feature[1])} catch (IllegalArgumentException e) {// no_new_exception (rethrow without wrapping)throw e;}
return base; // base type return}}}
// Usage of inner class methodpublic int invokeProcess(TargetEnum target) {StaticNestedClass nested = new StaticNestedClass();StaticNestedClass.MemberInnerClass inner = nested.new MemberInnerClass();return inner.processTargetField(target);}}
// Target enum class (default modifier, no target_feature)enum TargetEnum {TARGET1, TARGET2;
// Target field (source contains this field - per_condition)public int targetField = 20;public static final int OTHER_FIELD = 5; // For ClassName.field feature
// Helper class for constructor invocationpublic static class EnumHelper {}}
// Helper class for variable call (same package - same_package_others)class VariableCaller {public int calculate(int value) {return value * 2;}}
// Test class to verify refactoringpublic class MoveMethodTest5225 {public static void main(String[] args) {SourceEnum source = SourceEnum.INSTANCE1;TargetEnum target = TargetEnum.TARGET1;System.out.println(source.invokeProcess(target));}}