package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
class SuperTarget {public void baseMethod(String data) {}}
class Target extends SuperTarget {public String field1;public int field2;public boolean field3;
class MemberInner {void process(String input) {field1 = input;}}}
public class Source {strictfp List<String> handle(Target target) {List<String> result = new ArrayList<>();
// Anonymous inner classes (2 instances)Consumer<String> consumer1 = new Consumer<>() {@Overridepublic void accept(String s) {target.field1 = s;}};
Runnable runner1 = new Runnable() {@Overridepublic void run() {target.field2 = 100;}};runner1.run();
// Object initialization with target instance methods (3 calls)Target.MemberInner inner = target.new MemberInner();inner.process("init1");((SuperTarget) target).baseMethod("init2");target.baseMethod("init3");
// Labeled statementprocessLoop:for (int i = 0; i < 3; i++) {if (i == 2) {break processLoop;}result.add(target.field1 + "_" + i);}
// IfStatement with 3 target object fieldsif (target.field1 != null) {result.add(target.field1);}if (target.field2 > 0) {result.add(String.valueOf(target.field2));}if (target.field3) {result.add("field3 is true");}
// Access instance methodtarget.MemberInner inner2 = target.new MemberInner();inner2.process("updated");result.add(target.field1);
return result;}}