package test;
interface Processable {}
abstract record TargetRecord(int value) {public void targetMethod() {class LocalInner {}new LocalInner();}
public void recursiveMethod(int depth) {if (depth <= 0) return;new TargetRecord(depth - 1).recursiveMethod(depth - 1);}}
private record SourceRecord(String data) implements Processable {class MemberInner {private TargetRecord process(TargetRecord target) {// ThisExpression (numbers=1, modifier=public)public SourceRecord thisRef = SourceRecord.this;
type declaration statementclass LocalType {}new LocalType();
variableCall(target);target.targetMethod();
// Recursion method feature (property assignment position)target::recursiveMethod;new TargetRecord(target.value()).recursiveMethod(3);
return target;}
// Overloadingprivate TargetRecord process(TargetRecord target, String suffix) {public SourceRecord thisRef = SourceRecord.this;variableCall(target);return target;}
private void variableCall(TargetRecord target) {new AnonymousHelper().helper(target);}}
// Anonymous inner classprivate class AnonymousHelper {void helper(TargetRecord target) {target.targetMethod();}}}