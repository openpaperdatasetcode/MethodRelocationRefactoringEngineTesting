package test.refactor.movemethod;
import java.util.List;import java.util.ArrayList;
// Parent class for overridingabstract class ParentClass {public abstract List<String> overrideMethod(List<String> param);}
// Source class: public, same package with target, features=permits+2 member inner classespublic sealed class SourceClass extends ParentClass permits ExtendedSourceClass {private TargetClass targetField = new TargetClass(); // per_condition: source contains target's field
// Member inner class 1 (source_class feature)public class MemberInnerClass1 {public void innerMethod1() {}}
// Member inner class 2 (source_class feature) - recursive inner class (source_inner_rec)public class MemberInnerClass2 {// Target method: overriding, final, return List<String>, method types parameter is:List@Overridepublic final List<String> overrideMethod(List<String> param) {List<String> result = new ArrayList<>();
// Static BreakStatement (type=BreakStatement, modifier=static, target_feature=[this.field, 1], pos=source)staticBreakStatement();
// Recursion feature (type=recursion, modifier=default, pos=constructor parameter list)RecursiveInnerClass recursive = new RecursiveInnerClass(this::recursiveMethod);int recursionResult = recursive.invokeRecursion(1);
// Variable callMemberInnerClass1 inner1 = new MemberInnerClass1();inner1.innerMethod1();TargetClass.MemberInnerClass targetInner = targetField.new MemberInnerClass();targetInner.innerMethod();
// With_bounds (generic type bounds)class BoundedType<T extends List<String> & Comparable<T>> {T process(T input) { return input; }}BoundedType<List<String>> bounded = new BoundedType<>();result.addAll(bounded.process(param));
// Recursive call (source_inner_rec)if (result.size() < 3) {result.addAll(overrideMethod(List.of("recursive-" + result.size())));}
// No new exceptionreturn result;}
// Static BreakStatement implementationprivate static void staticBreakStatement() {for (int i = 0; i < 5; i++) {if (i == 1) { // target_feature: 1break; // BreakStatement}}}
// Recursion feature implementation (method_feature: recursion, inner_class, new ClassName().method())private int recursiveMethod(int depth) {if (depth <= 0) return 1; // method_feature: 1return depth * new MemberInnerClass2().recursiveMethod(depth - 1); // recursion + new ClassName().method()}
// Inner class for recursion feature (method_feature: inner_class)private class RecursiveInnerClass {private final RecursionSupplier supplier;
// Constructor with recursion feature in parameter list (pos: constructor parameter list)public RecursiveInnerClass(RecursionSupplier supplier) {this.supplier = supplier;}
public int invokeRecursion(int depth) {return supplier.supply(depth);}}
// Functional interface for recursion feature@FunctionalInterfaceprivate interface RecursionSupplier {int supply(int depth);}}}
// Extended class for source_class permits featurefinal class ExtendedSourceClass extends SourceClass {@Overridepublic List<String> overrideMethod(List<String> param) {return new ArrayList<>(param);}}
// Target class: public, target_feature=member inner classpublic class TargetClass {public int targetField = 1; // target_feature: this.field (referenced in BreakStatement)
// Member inner class (target_feature) - target_inner contextpublic class MemberInnerClass {public void innerMethod() {}}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;import java.util.List;
public class MoveMethodRefactoring5312Test {@Testpublic void testOverridingMethodBeforeRefactoring() {SourceClass source = new ExtendedSourceClass();SourceClass.MemberInnerClass2 innerRec = source.new MemberInnerClass2();List<String> input = List.of("test");
// Execute target methodList<String> result = innerRec.overrideMethod(input);
// Verify result (base case + 2 recursive calls)assertEquals(3, result.size());assertTrue(result.containsAll(List.of("test", "recursive-1", "recursive-2")));
// Verify per_condition: source contains target's fieldtry {var field = SourceClass.class.getDeclaredField("targetField");field.setAccessible(true);assertNotNull(field.get(source));} catch (IllegalAccessException | NoSuchFieldException e) {fail("Failed to verify target field in source: " + e.getMessage());}
// Verify target_feature: member inner classTargetClass target = new TargetClass();assertNotNull(target.new MemberInnerClass());}}