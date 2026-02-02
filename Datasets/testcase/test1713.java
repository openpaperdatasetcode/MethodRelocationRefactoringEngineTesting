package test;
import java.util.function.Consumer;
non-sealed record SourceClass(String data) {static class StaticNested {}
class MemberInner {class InnerRec {private int instanceMethod(TargetRecord param) {super();class LocalInner {}
// Normal method from target using method reference in if/elseConsumer<TargetRecord> consumer;if (param != null) {consumer = param::normalMethod;} else {consumer = TargetRecord.StaticNested::staticMethod;}consumer.accept(param);
// Access target fieldString fieldVal = param.value();
variableCall();
// Break statementfor (int i = 0; i < 5; i++) {if (i == 3) {break;}}
return 0;}
private void variableCall() {}}}}
public record TargetRecord(String value) {static class StaticNested {static void staticMethod(TargetRecord record) {}}
public void normalMethod() {}}