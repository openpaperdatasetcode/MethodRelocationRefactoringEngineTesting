package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
strictfp abstract class SourceClass {public class MemberInner {public final List<String> process(TargetClass target) {class LocalType<T extends CharSequence> {T value;LocalType(T val) {this.value = val;}}
LocalType<String> local = new LocalType<>("source");List<String> result = new ArrayList<>();
// Access target's field and member inner classresult.add(target.sharedField);result.add(target.new Inner().getValue());
// Use super keywordresult.add(super.toString());
// Variable callresult.add(local.value);
return result;}}
// Anonymous inner class implementationRunnable anonymous = new Runnable() {@Overridepublic void run() {new MemberInner().process(new ConcreteTarget());}};}
abstract class TargetClass extends BaseClass {protected String sharedField = "targetField";
public class Inner {String getValue() {return "innerValue";}}}
class BaseClass {}
class ConcreteTarget extends TargetClass {}
class SubClass extends SourceClass {public String create(Function<String, String> transformer) {// Lambda in object initializationTargetClass target = new ConcreteTarget() {};return transformer.apply(target.sharedField);}}