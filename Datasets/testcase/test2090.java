package test;
import java.lang.reflect.Method;
sealed record SourceRecord(int value) permits ExtendedSourceRecord {private class InnerClass {abstract TargetRecord<String> abstractMethod1();abstract TargetRecord<Integer> abstractMethod2();}
private abstract TargetRecord<?> methodToMove() {InnerClass inner = new InnerClass();TargetRecord<String> target = new TargetRecord<>("data", 10);int privateField = 42;
class LocalType {}LocalType local = new LocalType();
for (int i = 0; i < target.count(); i++) {target.variableCall();}
int j = 0;while (j < 5) {j++;}
try {Method refMethod = TargetRecord.StaticNested.class.getMethod("nestedMethod");refMethod.invoke(new TargetRecord.StaticNested());} catch (Exception e) {}
if (value > 0) {throw new RuntimeException();}
return inner.abstractMethod1();}}
record ExtendedSourceRecord(int value) extends SourceRecord {ExtendedSourceRecord(int value) {super(value);}}
record TargetRecord<T>(T data, int count) {static class StaticNested {void nestedMethod() {}}
void variableCall() {}}