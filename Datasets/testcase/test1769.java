package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
strictfp class Target {protected String field = "target_field";
{// Anonymous inner class in targetConsumer<String> consumer = new Consumer<>() {@Overridepublic void accept(String s) {System.out.println(s);}};consumer.accept(field);}}
class OtherClass extends Target {@Overrideprotected String field() { // Overriding with different return type (violation)return "overridden_" + super.field;}}
private class Source {class MemberInner {String process(String input) {return input + "_inner";}}
public final List<String> handle(Target target, String... args) {List<String> result = new ArrayList<>();MemberInner inner = new MemberInner();
// Anonymous inner class in sourceRunnable anonTask = new Runnable() {@Overridepublic void run() {result.add(target.field);}};anonTask.run();
// Labeled statementloop:for (String arg : args) {result.add(inner.process(arg));if (arg.isEmpty()) {break loop;}}
// Constructor parameter with others_class method callOtherClass other = new OtherClass(target.new Consumer<String>() {@Overridepublic void accept(String s) {}}.accept("init"));
result.add(other.field());return result;}}