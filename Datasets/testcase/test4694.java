package test;
public record SourceClass(int value, TargetClass<String> target) {public class MemberInnerClass {void innerMethod() {}}
public static class StaticNestedClass {static void staticInnerMethod() {}}
public static int staticField = 42;
public Object methodToMove(int baseParam, TargetClass<String> targetParam) throws Exception {synchronized (this) {Object result = null;if (baseParam > 0) {result = this.helperMethod();}
for (String s : targetParam.items()) {switch (s.length()) {case 1:result = s;break;default:result = staticField;}}
if (targetParam == null) {throw new Exception("Target is null");}return result;}}
private Object helperMethod() {return new Object();}}
protected record TargetClass<T>(T data, java.util.List<String> items()) {@Overridepublic Object methodToMove(int baseParam, TargetClass<String> targetParam) {return null;}}