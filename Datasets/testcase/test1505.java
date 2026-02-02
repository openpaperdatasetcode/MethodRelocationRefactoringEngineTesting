package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Process {}
class SuperTarget {protected int baseValue = 0;}
protected class Target extends SuperTarget {void processItems(String... items) {class LocalHandler {int count = 0;
void handle(String item) {count++;baseValue += item.length();}}
LocalHandler handler = new LocalHandler();for (String item : items) {handler.handle(item);}}}
class Source {private String outerPrivate = "private_data";
static class StaticNested {static int getLength(String s) {return s.length();}}
class MemberInner {String format(String input) {return input + "_formatted";}}
@Processpublic int handle(Target target, String... args) {// Empty statement;
// Access outer private fieldSystem.out.println(outerPrivate);
MemberInner inner = new MemberInner();int result = 0;
// Expression statementstarget.processItems(args);result = target.baseValue;
// 3 PostfixExpressionsint a = 0;a++;result++;StaticNested.getLength(inner.format("test"))++;
// this.methodName(arguments)this.validate(args);
return result;}
private void validate(String[] args) {if (args == null) {throw new IllegalArgumentException("Args cannot be null");}}}