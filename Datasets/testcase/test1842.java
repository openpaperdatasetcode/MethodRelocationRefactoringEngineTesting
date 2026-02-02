package test;
import java.util.ArrayList;import java.util.List;
public record SourceRecord(String data) {// Static nested classpublic static class SourceStaticNested {}
// Member inner classpublic class SourceMemberInner {String getOuterData() {return SourceRecord.this.data();}}
protected List<String> normalMethod(TargetRecord target) {List<String> result = new ArrayList<>();
// Constructor invocationTargetRecord.StaticNested nested = new TargetRecord.StaticNested();
// Expression statementString combined = data() + target.value();result.add(combined);
// Variable callresult.add("Target field: " + target.value());result.add("Nested value: " + nested.calculate(target.value().length()));
// Labeled statementprocessing: {if (target.value().isEmpty()) {break processing;}result.add("Valid target");}
// Super constructor invocation in anonymous subclassObject obj = new Object() {{super();}};
// Other object processes thisOtherProcessor.process(this);
// Uses outer this in inner classresult.add(new SourceMemberInner().getOuterData());
// Abstract method in switch (sub_class, super call)abstract class AbstractHandler extends TargetSubclass {@Overrideprotected int handle(int code) {return switch (code) {case 1 -> super.handle(code) + 1;default -> super.handle(code);};}}AbstractHandler handler = new AbstractHandler() {};result.add("Handled: " + handler.handle(target.value().length()));
return result;}}
private record TargetRecord(String value) {// Static nested classpublic static class StaticNested {int calculate(int length) {return length * 2;}}}
class TargetSubclass {protected int handle(int code) {return code * 3;}}
class OtherProcessor {static void process(SourceRecord source) {System.out.println("Processing source: " + source.data());}}