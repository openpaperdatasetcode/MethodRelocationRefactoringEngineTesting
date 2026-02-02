package test.refactoring.movemethod;
import other.DiffPackageValidator;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface EnumMethodAnnot {}
enum TargetEnum {VALUE1("one"),VALUE2("two");
public String label;
TargetEnum(String label) {this.label = label;// Anonymous inner class in targetRunnable init = new Runnable() {@Overridepublic void run() {System.out.println("TargetEnum initialized: " + label);}};init.run();}
public class TargetInner {public class NestedInner {private int code;
public NestedInner(int code) {this.code = code;}
public int getCode() {return code;}}}}
enum SubTargetEnum extends TargetEnum {VALUE3("three");
SubTargetEnum(String label) {super(label);}
public static void processLabel(TargetEnum target) {target.label = target.label.toUpperCase();}
public String getNestedValue(TargetEnum.TargetInner.NestedInner nested) {return nested.getCode() + "_" + label;}}
enum SourceEnum {INSTANCE;
protected String outerProtected = "protected_data";
class SourceInner {}
@EnumMethodAnnotpublic int process(TargetEnum.TargetInner.NestedInner nested) {// Local inner classclass ValueCalculator {int compute(int code) {return code * 2;}}
// Variable callObject varCall = nested.getCode();
// Access instance fieldint base = nested.code;
// Access outer protectedint protectedLen = outerProtected.length();
// Synchronized statementsynchronized (nested) {base += protectedLen;}
// NullPointerExceptionif (nested == null) {throw new NullPointerException("Nested inner cannot be null");}
// Call sub class method in property assignmentString nestedValue = SubTargetEnum.VALUE3.getNestedValue(nested);System.out.println("Nested value: " + nestedValue);
// Overload existsreturn process(nested, new ValueCalculator());}
// Overloaded methodpublic int process(TargetEnum.TargetInner.NestedInner nested, ValueCalculator calculator) {return calculator.compute(nested.getCode());}
// Instance method in parameter list of constructorpublic SourceEnum() {new SourceInner(SubTargetEnum::processLabel);}
class SourceInner {SourceInner(Runnable initializer) {initializer.run();}}}
package other;
import test.refactoring.movemethod.TargetEnum;
public class DiffPackageValidator {public static void validate(TargetEnum.TargetInner.NestedInner nested) {// AssertStatement with this.field in different packageprivate assert nested.code > 0 : "Nested code must be positive";}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3164 {@Testpublic void testOverloadingMethod() {TargetEnum target = TargetEnum.VALUE1;TargetEnum.TargetInner inner = target.new TargetInner();TargetEnum.TargetInner.NestedInner nested = inner.new NestedInner(5);
other.DiffPackageValidator.validate(nested);int result = SourceEnum.INSTANCE.process(nested);assertEquals(10, result);}}
