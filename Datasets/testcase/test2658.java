package test.same;
import java.util.List;import java.util.ArrayList;import java.util.function.Consumer;
enum SourceClass<T extends CharSequence> {INSTANCE;
{new Runnable() { public void run() {} };new Runnable() { public void run() {} };}
class MemberInner {record InnerRec(TargetClass target) {protected List<String> instanceMethod() {super();TargetClass.LocalInner inner = target.new LocalInner();Consumer<String> consumer = inner::process;
try {if (target.field == null) {throw new RuntimeException();}} catch (RuntimeException e) {consumer.accept(target.field);}
List<String> result = inner.callSuperMethod();Object var = super.toString();var = target.field;
return result;}}}}
protected enum TargetClass {VALUE;
String field = "target_field";
LocalInner new LocalInner() {class LocalInner {void process(String s) {}
public List<String> callSuperMethod() {super.toString();return new ArrayList<>();}}return new LocalInner();}}
