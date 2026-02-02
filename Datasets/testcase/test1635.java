package test;
import java.io.IOException;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
class SourceClass<T> {public class MemberInner {public TargetClass<T>.Inner createInner(T data) {return new TargetClass<T>().new Inner(data);}}
private void setValues(TargetClass<T>.Inner targetInner) throws IOException {// Local inner classclass InnerUpdater {void update(TargetClass<T>.Inner inner, T value) {inner.setValue(value);}}InnerUpdater updater = new InnerUpdater();
// Type declaration statementList<T> values = new ArrayList<>();values.add(targetInner.getValue());
// Super constructor invocation in subclassclass SubInner extends TargetClass<T>.Inner {SubInner(T data) {super(data);}}SubInner subInner = new SubInner((T) "sub_data");
// For loop with break statementfor (int i = 0; i < values.size(); i++) {if (values.get(i) == null) {break;}// Variable call - access target inner's fieldupdater.update(targetInner, values.get(i));}
// IOException handlingif (targetInner.getValue() == null) {throw new IOException("Inner value cannot be null");}
// Used by reflectiontry {Method method = TargetClass.Inner.class.getMethod("setValue", Object.class);method.invoke(targetInner, (T) "reflected_value");} catch (Exception e) {// no new exception}}}
protected class TargetClass<T> {public class Inner {private T value;
public Inner(T value) {this.value = value;// Anonymous inner classRunnable validator = new Runnable() {@Overridepublic void run() {if (value == null) {Inner.this.value = (T) "default";}}};validator.run();}
public T getValue() {return value;}
public void setValue(T value) {this.value = value;}}}