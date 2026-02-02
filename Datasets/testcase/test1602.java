package source;
import target.TargetEnum;import java.util.function.Function;
public enum SourceEnum<T> {INSTANCE;
public class MemberInner {// Static method in source_innerstatic <T> TargetEnum<T> process(TargetEnum<T> target) {// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {// Variable call - access target's fieldtarget.data = (T) "initialized";}};initializer.run();
// Instance code block with lambda (call source's private overriding method){Function<TargetEnum<T>, TargetEnum<T>> processor = t -> processTarget(t);target = processor.apply(target);}
// Return statementreturn target;}
// Private overriding method (overrides functional interface)private TargetEnum<T> processTarget(TargetEnum<T> target) {// Super constructor invocation in target's inner classTargetEnum<T>.Inner inner = target.new Inner();inner.setValue(target.data);return target;}}}
package target;
public enum TargetEnum<T> extends BaseEnum {VALUE1, VALUE2;
public T data;
public class Inner {private T value;
public Inner() {super(); // Super constructor invocation}
public void setValue(T val) {this.value = val;}}}
package target;
public class BaseEnum {}
