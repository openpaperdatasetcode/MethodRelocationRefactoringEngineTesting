package test;
import java.util.List;
interface Processable {int execute();}
class RecordSuper {protected String baseValue;
public RecordSuper(String baseValue) {this.baseValue = baseValue;}}
/**
Target record with type parameters and local inner class
@param <T> type of value field/
public record Target<T>(T value, String id) extends RecordSuper {
/*
Constructs Target with base value initialization
@param value the main value
@param id the identifier
*/
public Target {
super(id); // Super constructor invocation
}
/**
Processes input list
@param list input list
@return processed count*/public int process(List<T> list) {class LocalProcessor {int count = 0;
int handle(T item) {if (item.equals(value)) {count++;}return count;}}
LocalProcessor processor = new LocalProcessor();for (T item : list) {processor.handle(item);}return processor.count;}}
/**
Source record implementing Processable
@param content the content to process*/public record Source(String content) implements Processable {class MemberInner {String getContent() {return Source.this.content; // Uses outer this}}
/**
Overrides execute method to process Target
@return processing result*/@Overridepublic int execute() {Target<String> target = new Target<>(content, "t1");MemberInner inner = new MemberInner();
// Type declaration statementList<String> data = List.of("a", content, "b");
// Enhanced for statementfor (String item : data) {System.out.println(item);}
// Lambda expressionRunnable printer = () -> System.out.println(this.content);printer.run();
// TryStatement with 2 target fields accesstry {String targetId = target.id(); // target field 1String targetValue = target.value(); // target field 2return target.process(data);} catch (Exception e) {return -1;}}}