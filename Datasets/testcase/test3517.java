package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
@Retention(RetentionPolicy.RUNTIME)@interface EnumMethodAnn {String value() default "";}
private sealed enum SourceEnum permits ExtendedSourceEnum {INSTANCE;
public static class NestedOne {public static strictfp void processTarget(TargetClass target) {System.out.println("NestedOne processing: " + target.getValue());}
public static strictfp void processTarget(TargetClass target, String suffix) {System.out.println("NestedOne processing: " + target.getValue() + "_" + suffix);}}
public static class NestedTwo {public static String format(String input) {return input.toUpperCase();}}
public class MemberInner {@EnumMethodAnn("#{target::getValue}")protected List<String> instanceMethod(TargetClass target) {List<String> results = new ArrayList<>();Function<TargetClass, String> methodRef = target::getValue;results.add(methodRef.apply(target));
for (int i = 0; i < 3; i++) {if (i == 1) {break;}results.add(NestedTwo.format(target.getValue()));; // Empty statement}
TargetClass.Inner inner = target.new Inner();results.add(inner.processInner());
NestedOne.processTarget(target);NestedOne.processTarget(target, "overload");
return results;}}
// Override violation: Implicit super has no such method with protected accessprotected String getOverrideViolation() {return "override_violation";}}
non-sealed enum ExtendedSourceEnum implements SourcePermit {EXTENDED_INSTANCE;
@Overridepublic void permitMethod() {}}
interface SourcePermit {void permitMethod();}
enum TargetClass {VALUE1("one"), VALUE2("two");
private final String value;
TargetClass(String value) {this.value = value;}
public String getValue() {class LocalInner {String extract() {return value + "_local";}}return new LocalInner().extract();}
public class Inner {public Inner() {}
public String processInner() {return TargetClass.this.value + "_inner";}}}