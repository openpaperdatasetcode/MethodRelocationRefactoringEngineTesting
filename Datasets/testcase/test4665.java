package test;
import java.io.IOException;import java.lang.reflect.Method;
interface BaseSourceInterface {protected void protectedMethod();}
interface SourceInterface extends BaseSourceInterface {default void createInnerProcessor() {class SourceInner {synchronized int processTarget(TargetInterface<String> target, int count) {if (count <= 0) return 0;
try {TargetInterface.Inner inner = new TargetInterface.Inner() {@Overridepublic void setValue(String val) {this.value = val;}};inner.setValue("Processed: " + count);System.out.println(inner.value);
Method getMethod = TargetInterface.class.getMethod("getValue", String.class);String result = (String) getMethod.invoke(target, inner.value);result = (count % 2 == 0) ? target.getValue(result) : target.getValue();
SourceInterface.this.protectedMethod();return count + processTarget(target, count - 1);} catch (Exception e) {throw new RuntimeException(e);}}}
TargetInterface<String> target = new TargetInterface<String>() {@Overridepublic String getValue() {return "Default Value";}
@Overridepublic String getValue(String input) {return "Overloaded: " + input;}};
SourceInner inner = new SourceInner();inner.processTarget(target, 2);}
@Overridedefault void protectedMethod() {}}
abstract interface TargetInterface<T> {String getValue();String getValue(String input);
class Inner {String value;public abstract void setValue(String val);}}