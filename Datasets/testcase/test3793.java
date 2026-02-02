import java.lang.annotation.*;import java.util.function.Function;
// Annotation for method's has_annotation feature@Target(ElementType.METHOD)@Retention(RetentionPolicy.RUNTIME)@interface ProcessMethod {}
// Parent class for SourceClass (source_class: extends)class ParentSource {protected String parentField = "parent_base_value";}
// TargetClass: normal, public, with member inner class (meets target_class specs)public class TargetClass {// Member inner class (target_inner_rec: method's target class)public class TargetInner {private int innerCount;// Static field for ConstructorInvocation's ClassName.field featurepublic static final String INNER_STATIC_FIELD = "TARGET_INNER_STATIC";
public TargetInner(int count) {this.innerCount = count;}
// Method for variable callpublic void increment() {innerCount++;}
// Method for variable callpublic int getCount() {return innerCount;}
// Overloaded method 1 (for call_method's overloading feature)public TargetInner merge(TargetInner other) {return new TargetInner(this.innerCount + other.innerCount);}
// Overloaded method 2 (for call_method's overloading feature)public TargetInner merge(int value) {return new TargetInner(this.innerCount + value);}}
// Field for source to referencepublic String targetField = "target_default";}
// SourceClass: sealed normal, private, generic, extends ParentSource, with local inner + static nested class (meets source_class specs)sealed class SourceClass<T extends Number> extends ParentSource permits SourceSubClass {// Static nested class (source_feature: static nested class)public static class SourceStaticNested {// Method to create TargetInner instance (for call_method)public static TargetClass.TargetInner createTargetInner(TargetClass target, U count) {
return target.new TargetInner(count.intValue());
}
}
// Inner class for method_position: source_inner_recpublic class SourceInner {/**
Normal method to process TargetClass.TargetInner
Meets method specs and features
@param target TargetClass instance (per_condition: contains target parameter)
@param count Initial count (with_bounds: T extends Number)
@return int Processed count (base type return)*/@ProcessMethod // has_annotation featurepublic int processTargetInner(TargetClass target, T count) {// ConstructorInvocation: protected modifier (indirect), pos=source, access ClassName.field (1)TargetClass.TargetInner inner = new TargetClass.TargetInner(count.intValue());String staticFieldRef = TargetClass.TargetInner.INNER_STATIC_FIELD; // ClassName.field access
// TypeMethodReference: numbers=1, default modifier, exp=TypeMethodReferenceFunction<TargetClass.TargetInner, Integer> countGetter = TargetClass.TargetInner::getCount;
// Variable call: invoke target inner class methodinner.increment();int currentCount = countGetter.apply(inner);
// Call sub_class method in if/else (call_method specs: pos=if/else conditions)TargetClass.TargetInner mergedInner;if (currentCount > 5) {// call_method: sub_class, private, overloading, outerInstance.new InnerClass().methodName()mergedInner = SourceSubClass.privateMerge(target, inner, 3);} else {TargetClass.TargetInner otherInner = target.new TargetInner(2);mergedInner = SourceSubClass.privateMerge(target, inner, otherInner);}
// Variable call: get final countint finalCount = mergedInner.getCount();
// Local inner class (source_feature: local inner class)class LocalProcessor {int validateCount(int c) {return c < 0 ? 0 : c;}}finalCount = new LocalProcessor().validateCount(finalCount);
return finalCount; // no_new_exception}}
// Abstract method to enforce sub_class implementation (permits)public abstract TargetClass.TargetInner createMergedInner(TargetClass target, T a, T b);}
// SubClass of SourceClass (source_class: permits, call_method: type=sub_class)final class SourceSubClass extends SourceClass<Integer> {/**
Private overloaded method 1 (call_method: overloading)
@param target TargetClass instance
@param inner1 First TargetInner
@param inner2 Second TargetInner
@return TargetClass.TargetInner Merged result (call_method: return_typr=TargetClas Type)
*/
private static TargetClass.TargetInner privateMerge(TargetClass target, TargetClass.TargetInner inner1, TargetClass.TargetInner inner2) {
return inner1.merge(inner2);
}
/**
Private overloaded method 2 (call_method: overloading)
@param target TargetClass instance
@param inner TargetInner instance
@param value Int value to merge
@return TargetClass.TargetInner Merged result (call_method: return_typr=TargetClas Type)
*/
private static TargetClass.TargetInner privateMerge(TargetClass target, TargetClass.TargetInner inner, int value) {
return inner.merge(value);
}
@Overridepublic TargetClass.TargetInner createMergedInner(TargetClass target, Integer a, Integer b) {TargetClass.TargetInner innerA = target.new TargetInner(a);TargetClass.TargetInner innerB = target.new TargetInner(b);return innerA.merge(innerB);}}
// JUnit Test Case for Move Method Refactoring Engineimport org.junit.Test;import static org.junit.Assert.*;
public class SourceClassMoveMethodTest {@Testpublic void testProcessTargetInner_MeetsAllFeatures() {// Initialize dependenciesTargetClass target = new TargetClass();SourceClass<Integer> source = new SourceSubClass();SourceClass.SourceInner sourceInner = source.new SourceInner();
// Execute method to test (contains target parameter: per_condition)int result1 = sourceInner.processTargetInner(target, 3);int result2 = sourceInner.processTargetInner(target, 7);
// Verify results (no_new_exception and logic correctness)assertTrue("Result1 should be valid", result1 >= 0);assertTrue("Result2 should be valid", result2 >= 0);assertEquals("Result1 should be 3+1+2=6", 6, result1);assertEquals("Result2 should be 7+1+3=11", 11, result2);
// Verify annotation presence (has_annotation)try {var method = SourceClass.SourceInner.class.getMethod("processTargetInner", TargetClass.class, Number.class);assertTrue("Method should have @ProcessMethod", method.isAnnotationPresent(ProcessMethod.class));} catch (NoSuchMethodException e) {fail("Target method not found: " + e.getMessage());}}}