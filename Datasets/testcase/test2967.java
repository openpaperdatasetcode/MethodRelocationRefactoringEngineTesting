package test;
final class SourceClass<T> {public class SourceInner {public void process(TargetClass... targets) {
for (int i = 0; i < targets.length; i++) {
TargetClass target = targets[i];
TargetClass.InnerRecursive targetInner = target.new InnerRecursive();
transient TargetClass.InnerRecursive temp = new TargetClass.InnerRecursive();
temp.setField(target.targetField);
switch (target.targetField.hashCode() % 3) {case 0:targetInner.update(target.targetField);continue;case 1:targetInner.log(target.targetField.toString());break;default:targetInner.clear();}
Runnable anon = new Runnable() {@Overridepublic void run() {targetInner.setField(target.targetField);}};anon.run();}}}}
protected class TargetClass<T> extends ParentGenericClass<T> {public T targetField;
public class InnerRecursive {private T field;
public void setField(T value) {this.field = value;}
public void update(T value) {this.field = value;}
public void log(String msg) {System.out.println(msg);}
public void clear() {this.field = null;}}
public TargetClass() {Runnable init = new Runnable() {@Overridepublic void run() {targetField = getDefaultValue();}};init.run();}
@Overrideprivate void finalize() throws Throwable {super.finalize();}}
abstract class ParentGenericClass<T> {protected abstract T getDefaultValue();}