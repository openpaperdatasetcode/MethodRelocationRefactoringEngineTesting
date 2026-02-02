package test.refactoring.movemethod;
public record SourceRecord(int sourceField, TargetRecord targetField) {public static class StaticNestedClass1 {}public static class StaticNestedClass2 {}
static {protected Object method(int param) {Object parentInstance = new ParentClass();OuterClass outer = new OuterClass();return outer.new InnerClass().innerMethod();}}
protected Object method(String param) {TargetRecord target = new TargetRecord(10);super();int localVar = sourceField;return targetField.targetField;}
public final Object callMethod() {TargetRecord target = new TargetRecord(20);Runnable runnable = target::method;runnable.run();return target.method("test");}}
private record TargetRecord(int targetField) {class MemberInnerClass {}
protected Object method(int param) {return null;}
protected Object method(String param) {return null;}}
class ParentClass {}
class OuterClass {class InnerClass {Object innerMethod() {return new Object();}}}