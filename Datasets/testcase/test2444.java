package test.refactoring.movemethod;
public enum TargetEnum {VALUE1, VALUE2, VALUE3;
public static class TargetNested {private String data;
public TargetNested(String data) {this.data = data;}
public String getData() {return data;}}
public TargetEnum getNext() {return values()[(ordinal() + 1) % values().length];}}
class SubTargetNested extends TargetEnum.TargetNested {public SubTargetNested(String data) {super(data);}
@Overridepublic String getData() {return "sub_" + super.getData();}}
public enum SourceEnum {INSTANCE;
public static class StaticNested {}
public class SourceInner {public class NestedInner {public static int process(TargetEnum target) {// Constructor invocationTargetEnum.TargetNested nested1 = new TargetEnum.TargetNested("one");TargetEnum.TargetNested nested2 = new SubTargetNested("two");TargetEnum.TargetNested nested3 = new SubTargetNested("three");
// Variable callObject varCall = target.ordinal();
// Overriding methods in object initialization (3 instances)TargetEnum next1 = target.getNext();String data1 = nested1.getData();String data2 = nested2.getData();String data3 = nested3.getData();
return data1.length() + data2.length() + data3.length();}}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3135 {@Testpublic void testStaticMethod() {int result = SourceEnum.INSTANCE.new SourceInner().new NestedInner().process(TargetEnum.VALUE1);assertEquals(3 + 6 + 7, result); // "one".length() + "sub_two".length() + "sub_three".length()}}
