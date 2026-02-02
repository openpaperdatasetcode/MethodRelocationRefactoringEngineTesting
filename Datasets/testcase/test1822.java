package test;
record SourceRecord(String data) {public record SourceInnerRec(int code) {int varargsMethod(TargetRecord.NestedRec... innerRecs) {int sum = 0;
// Constructor invocation of target static nested classTargetRecord.NestedStatic nestedStatic = new TargetRecord.NestedStatic();
// Super constructor invocation in anonymous subclass of ObjectObject obj = new Object() {{super();}};
// Synchronized statementsynchronized (this) {for (TargetRecord.NestedRec rec : innerRecs) {// Variable call using target inner record componentssum += rec.id() + nestedStatic.multiply(rec.value());}}
// Local inner class 1class LocalProcessorOne {int process() {return sum * 2;}}
// Local inner class 2class LocalProcessorTwo {int enhance(LocalProcessorOne processor) {return processor.process() + code;}}
return new LocalProcessorTwo().enhance(new LocalProcessorOne());}}}
record TargetRecord(String name) {public static class NestedStatic {int multiply(int value) {return value * 3;}}
public record NestedRec(int id, int value) {}}