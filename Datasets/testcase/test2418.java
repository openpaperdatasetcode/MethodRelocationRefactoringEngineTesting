package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface OverrideAnnot {}
class ParentSource {protected List<String> process() {return new ArrayList<>();}}
class TargetClass {public String field = "target_field";
public int getValue() {return 5;}}
class OtherClass {protected static int staticMethod(String str) {return str.length();}}
public class SourceClass<T extends TargetClass> extends ParentSource {static class StaticNested {}
class MemberInner {}
@OverrideAnnot@Overrideprotected List<String> process() {T target = (T) new TargetClass();Object varCall = target.field;
// SuperConstructorInvocation featureclass Derived extends TargetClass {private Derived() {super();if (target.field != null) {varCall = target.field;}}}Derived derived = new Derived();
// Super keyword usageList<String> result = super.process();result.add(target.field);
// With bounds demonstrationresult.add(String.valueOf(target.getValue()));
// Method reference in ternary operatorFunction<String, Integer> func = (target.field.isEmpty()) ?String::length : OtherClass::staticMethod;result.add(String.valueOf(func.apply(target.field)));
// Requires try-catchtry {Integer.parseInt(target.field);} catch (NumberFormatException e) {result.add("caught");}
return result;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3083 {@Testpublic void testOverridingMethod() {SourceClass<TargetClass> source = new SourceClass<>();List<String> result = source.process();assertEquals(4, result.size());}}