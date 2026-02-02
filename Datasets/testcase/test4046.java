package same.pkg;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
@Target(ElementType.METHOD)@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
class ParentEnumClass {private static int parentStaticCounter = 0;
private static void incrementCounter() {parentStaticCounter++;}
private static void resetCounter() {parentStaticCounter = 0;}
private static int getCounter() {return parentStaticCounter;}
protected static void processCounter(int steps) {int i = 0;while (i < steps) {incrementCounter();System.out.println("Current counter: " + getCounter());i++;}resetCounter();}}
public enum TargetEnum extends ParentEnumClass {TARGET_A, TARGET_B, TARGET_C;
class TargetMemberInner {int getTargetValue(TargetEnum target) {return target.ordinal() + 1;}}
public TargetMemberInner getInnerInstance() {return new TargetMemberInner();}}
public enum SourceEnum {INSTANCE;
static class SourceStaticNested {private static int staticField1 = 0;private static int staticField2 = 0;private static int staticField3 = 0;
protected static int updateStaticFields(int val) {staticField1 = val;staticField2 = val * 2;staticField3 = val * 3;return staticField1 + staticField2 + staticField3;}}
static class SourceInnerRec {/**
Processes TargetEnum to calculate base type result using inner class and static methods.
@param target TargetEnum instance to process
@param limit Loop limit for counter processing
@return Sum of processed values as base type (int)*/@ProcessAnnotationprivate int processTarget(TargetEnum target, int limit) {TargetEnum.TargetMemberInner targetInner = target.getInnerInstance();int targetVal = targetInner.getTargetValue(target);int sum = 0;
ParentEnumClass.processCounter(limit);
for (int i = 0; i < 5; i++) {if (i % 2 == 0) {private boolean skip = (i == 2);if (skip) {continue;}}
sum += SourceStaticNested.updateStaticFields(targetVal + i);sum += SourceEnum.INSTANCE.getExtraValue();}
class LocalInnerHelper {int adjustSum(int currentSum) {return currentSum - SourceStaticNested.staticField1;}}sum = new LocalInnerHelper().adjustSum(sum);
return sum;}}
private int getExtraValue() {return 5;}
public int startProcessing(TargetEnum target, int limit) {return new SourceInnerRec().processTarget(target, limit);}}