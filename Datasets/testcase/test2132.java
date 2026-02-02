package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
record SourceRecord(String id) {static class StaticNested {}
Runnable anonymous = new Runnable() {public void run() {}};
TargetRecord methodToMove(TargetRecord... targets) {@MyAnnotationint flag = 0;
for (TargetRecord target : targets) {target.variableCall();
if (target.data().length() > 5) {flag = 1;}}
return targets.length > 0 ? targets[0] : null;}}
abstract record TargetRecord(String data) extends SuperRecord {TargetRecord {Runnable r = new Runnable() {public void run() {}};}
abstract void variableCall();}
record SuperRecord() {}
record ConcreteTargetRecord(String data) extends TargetRecord {ConcreteTargetRecord {super(data);}
@Overridevoid variableCall() {}}