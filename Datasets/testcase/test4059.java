package test;
import java.lang.reflect.Method;
public class SourceClass implements Calculable {// Protected field for "access_outer_protected"protected int outerProtected = 50;// Source contains target field (matches per_condition)private TargetClass targetField = new TargetClass();
// Member inner class (1st layer for "source_inner")class SourceInner {// Recursive inner class (2nd layer for "source_inner_rec")class SourceRecursiveInner {// Instance method to be refactoredstrictfp int calculateSum(TargetClass target) {int sum = 0;
// EnhancedForStatement (static modifier via helper, matches target_feature)int[] data = SourceStaticHelper.getData();for (int num : data) {// Super.field access (target inherits from ParentTarget)sum += num + target.superField;}
// Variable call: access target's local inner class methodTargetClass.SumHelper helper = target.createSumHelper();sum += helper.add(outerProtected); // access_outer_protected
// Used by reflectiontry {Method helperMethod = TargetClass.SumHelper.class.getMethod("getBase", int.class);sum += (int) helperMethod.invoke(helper, 10);} catch (Exception e) {sum = -1;}
return sum;}}}
// call_method (source type, private modifier)private int callCalculate(int caseType) {SourceInner.RecursiveInner inner = new SourceInner().new SourceRecursiveInner();// switch position (matches call_method "pos")switch (caseType) {case 1:// ClassName.methodName(arguments) (overloading 1)return inner.calculateSum(targetField);case 2:// ClassName.methodName(arguments) (overloading 2)return inner.calculateSum(new TargetClass(20));default:return 0;}}
@Overridepublic int compute() {return callCalculate(1);}
// Static helper for EnhancedForStatement's static modifierprivate static class SourceStaticHelper {static int[] getData() {return new int[]{10, 20, 30};}}}
// Parent class for target's "super.field"class ParentTarget {protected int superField = 10;}
// Target class (default modifier)class TargetClass extends ParentTarget {public TargetClass() {}
// Overloaded constructorpublic TargetClass(int superField) {this.superField = superField;}
// Local inner class (target_class feature)public SumHelper createSumHelper() {class TargetLocalSumHelper implements SumHelper {@Overridepublic int add(int val) {return val + TargetClass.this.superField;}
@Overridepublic int getBase(int base) {return base * 2;}}return new TargetLocalSumHelper();}
// Inner interface for local inner classinterface SumHelper {int add(int val);int getBase(int base);}}
// Interface for source class "implements" featureinterface Calculable {int compute();}