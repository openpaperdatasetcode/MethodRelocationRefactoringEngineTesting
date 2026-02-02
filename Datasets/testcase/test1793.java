package test;
public abstract class SourceClass permits permits SourceSubclass {public class MemberInner {}public static class StaticNested {}
/**
Processes target class instance and its field
@param target the target class instance
@return processed value based on target field*/public int normalMethod(TargetClass target) {// Type declaration statementTargetClass.LocalInnerAccessor accessor = target.new LocalInnerAccessor();
// Constructor invocationTargetClass newTarget = new TargetClass();
// Expression statement using target fieldint value = target.targetField * 2;
// Variable callvalue += accessor.getLocalInnerValue();
return value;}}
abstract class SourceSubclass extends SourceClass {}
abstract class TargetClass {int targetField = 10;
class LocalInnerAccessor {int getLocalInnerValue() {// Local inner class in targetclass LocalInner {int getValue() {return targetField;}}return new LocalInner().getValue();}}}