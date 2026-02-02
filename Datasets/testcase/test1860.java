package test;
public record SourceRecord<T>(T content) {// First member inner classpublic class FirstInner {T getContent() {return SourceRecord.this.content;}}
// Second member inner classpublic class SecondInner {U convert(T value) {
return (U) value;
}
}
protected int instanceMethod(TargetRecord<String> target) {// Constructor invocationTargetRecord<String>.Inner targetInner = target.new Inner();
// Super constructor invocation in anonymous subclassTargetRecord<String> subTarget = new TargetRecord<>("sub_" + target.value()) {{super(target.value());}};
// Variable callint length = target.value().length();length += targetInner.process(target.value());
// If statementif (length > 10) {length -= 5;}
// Requires try-catchtry {length += Integer.parseInt(new FirstInner().getContent().toString());} catch (NumberFormatException e) {length += 0;}
return length;}}
final record TargetRecord<T>(T value) {// Member inner class with type parameterpublic class Inner {int process(U input) {
return input.toString().length() * 2;
}
}
}
