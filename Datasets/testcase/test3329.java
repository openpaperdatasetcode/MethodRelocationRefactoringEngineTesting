package test;
interface Processable {}
abstract record TargetRecord(int field) {public void targetMethod() {class LocalInner {}new LocalInner();}}
record ParentTargetRecord(int field) {public void parentVarargsMethod(String... args) {}}
protected record SourceRecord(String data) implements Processable {static class StaticNested {}
class MemberInner {}
class InnerClass {/**
Instance method with no type parameters
@param target Abstract target record instance*/private void process(TargetRecord target) {super(); // Super constructor invocation
class LocalType {}new LocalType(); // Type declaration statement
int count = 0;while (count < 1) {variableCall(target);target.targetMethod();
// Varargs method from parent_class in if/else conditionsif (target.field() > 0) {new ParentTargetRecord(target.field()).parentVarargsMethod("arg1", "arg2");} else {new ParentTargetRecord(target.field()).parentVarargsMethod("default");}
count++;}}
private void variableCall(TargetRecord target) {new MemberInner();new StaticNested();}}}