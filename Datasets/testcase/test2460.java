package test.refactoring.movemethod;
import java.util.function.Supplier;
interface EnumBase {Object getValue();}
sealed enum TargetParent permits TargetEnum {}
non-sealed enum TargetEnum extends TargetParent implements EnumBase {VALUE1("one"),VALUE2("two");
public String label;
TargetEnum(String label) {this.label = label;// Anonymous inner class in targetnew Runnable() {@Overridepublic void run() {System.out.println("TargetEnum initialized: " + label);}}.run();}
public class TargetInner {private int code;
public TargetInner(int code) {this.code = code;}
public int getCode() {return code;}}
@Overridepublic Object getValue() {return label;}}
non-sealed enum SourceEnum<T> permits ExtendedSourceEnum {INSTANCE;
public int process(TargetEnum.TargetInner inner) {// ConstructorInvocation with this.fieldTargetEnum target = TargetEnum.VALUE1;TargetEnum.TargetInner newInner = target.new TargetInner(target.label.length());
// Variable callObject varCall = inner.getCode();
// Assert statementassert newInner.getCode() == 3 : "VALUE1 label length should be 3";
// If statementint result = 0;if (inner.getCode() > 5) {result = inner.getCode() * 2;} else {result = inner.getCode() + newInner.getCode();}
// Anonymous inner classes (2)Runnable r1 = new Runnable() {@Overridepublic void run() {System.out.println("Processing start");}};r1.run();
Supplier<String> s1 = new Supplier<>() {@Overridepublic String get() {return target.label;}};
// Do-while with parent class overriding methodint count = 0;Object parentValue;do {parentValue = EnumBase.methodName(target); // Call parent class overriding methodcount++;} while (count < 1);
return result;}}
enum ExtendedSourceEnum extends SourceEnum<String> {}
// Parent class with overriding methodclass EnumBaseImpl implements EnumBase {public static Object methodName(EnumBase base) {return base.getValue();}
@Overridepublic Object getValue() {return null;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3154 {@Testpublic void testNormalMethod() {TargetEnum target = TargetEnum.VALUE2;TargetEnum.TargetInner inner = target.new TargetInner(4);
int result = SourceEnum.INSTANCE.process(inner);assertEquals(7, result); // 4 (inner code) + 3 (newInner code from VALUE1 label length)}}