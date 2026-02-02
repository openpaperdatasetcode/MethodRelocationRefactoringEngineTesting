package test;
import java.util.function.Function;
interface SourceInterface {}interface TargetInterface {}
public class SourceClass implements SourceInterface {class MemberInner {}static class StaticNested {}
public Object methodToMove(TargetClass target) {// Assignment expressiontarget.valueField = "assigned_value";
// Generic method feature with lambda in property assignmenttarget.transformer = (Function<String, Object>) s -> target.staticNested.genericMethod(s);
// Variable calltarget.variableCall();
// Override violation (assuming TargetClass's method is final but we try to override)TargetClass violating = new TargetClass() {@Overridepublic void finalMethod() {} // Compile error expected: final method cannot be overridden};
return target.valueField;}}
protected class TargetClass implements TargetInterface {String valueField;Function<String, Object> transformer;static class StaticNested {private static <T> Object genericMethod(T input) {return input.toString();}}
void variableCall() {}
public final void finalMethod() {}}