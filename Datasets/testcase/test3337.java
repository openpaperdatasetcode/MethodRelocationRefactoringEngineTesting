package test;
interface Processable {}
public record SourceRecord(String data) implements Processable {static class StaticNested {}
// Anonymous inner classprivate final Runnable anonymousRunnable = new Runnable() {@Overridepublic void run() {process(new TargetRecord("test"));}};
private Object process(TargetRecord target) {super.toString(); // Super keywords
// Type declaration statementclass LocalType {}new LocalType();
variableCall(target);return target;}
// Overload existsprivate Object process(TargetRecord target, String suffix) {super.hashCode();variableCall(target);return target.data() + suffix;}
private void variableCall(TargetRecord target) {target.doTask();new StaticNested();}}
public record TargetRecord(String data) {public void doTask() {}
// Corresponding overloadspublic Object process() {return data;}
public Object process(String suffix) {return data + suffix;}}