package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
final class SourceClass {public class MemberInner {public <T extends CharSequence> T process(TargetClass.StaticNested<T> nested) {return nested.getValue();}}
// Static code block with lambda (call source's default instance method)static {SourceClass source = new SourceClass();Function<TargetClass, TargetClass> processor = t -> source.enhance(t);processor.apply(new TargetClass());}
// Default instance method for call_methodTargetClass enhance(TargetClass target) {target.name = "enhanced_" + target.name;return target;}
public final List<String> process(TargetClass... targets) {List<String> result = new ArrayList<>();MemberInner inner = new MemberInner();
// Anonymous inner classRunnable logger = new Runnable() {@Overridepublic void run() {// Variable call - access target's fieldfor (TargetClass t : targets) {result.add(t.name);}}};
// Super constructor invocation in target's static nested classfor (TargetClass target : targets) {TargetClass.StaticNested<String> nested = new TargetClass.StaticNested<>("default");// With bounds (String extends CharSequence)String value = inner.process(nested);target.name = value;}
logger.run();return result;}}
public class TargetClass {public String name;
public static class StaticNested<T> {private T value;
public StaticNested(T value) {super(); // Super constructor invocationthis.value = value;}
public T getValue() {return value;}}}