package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnn {}
private record SourceRecord<T>(T value) {@MyAnnprotected void overloadingMethod(TargetRecord.TargetInner inner) {variableCall = inner.field;; // empty statement
List<TargetRecord> list = List.of(new TargetRecord(0));list.forEach(t -> new TargetRecord(0).synchronizedMethod());
return;}
protected void overloadingMethod(String str) {}
String variableCall;}
public record TargetRecord(int num) {class TargetInner {String field;}
{new Runnable() {};}
synchronized String synchronizedMethod() {return "";}}