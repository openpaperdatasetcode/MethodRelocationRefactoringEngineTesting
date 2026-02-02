package test.refactoring.movemethod;
record SourceRecord<T>(TargetRecord<T> targetField, T sourceData) {static class StaticNestedSource {static int helperValue = 10;}
Object process() {class LocalInnerSource {Object useTarget() {int count = 0;while (count < 1) {count++;return this.processInner(targetField.data());}return null;}
private Object processInner(T data) {return sourceData + "" + data + "" + StaticNestedSource.helperValue;}}return new LocalInnerSource().useTarget();}
Object process(int multiplier) {class LocalInnerSourceOverload {Object useTarget() {int count = 0;while (count < 1) {count++;return this.processInner(targetField.data(), multiplier);}return null;}
private Object processInner(T data, int multi) {return (sourceData.toString() + data.toString()).repeat(multi);}}return new LocalInnerSourceOverload().useTarget();}}
private record TargetRecord<T>(T data) {Object getProcessed() {class LocalInnerTarget {T wrapData() {return data;}}return new LocalInnerTarget().wrapData();}}
public class MoveMethodTest5172 {public static void main(String[] args) {TargetRecord<String> target = new TargetRecord<>("targetData");SourceRecord<String> source = new SourceRecord<>(target, "sourceData");
System.out.println(source.process());System.out.println(source.process(2));}}
