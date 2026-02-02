package test;
import java.io.IOException;
final class SourceClass<T> {private TargetClass<T> targetField = new TargetClass<>();
{Runnable anon1 = new Runnable() {public void run() {}};Runnable anon2 = new Runnable() {public void run() {}};}
class SourceInner {final abstract TargetClass<T> abstractMethod() throws IOException;}
class ConcreteInner extends SourceInner {@Overridefinal TargetClass<T> abstractMethod() throws IOException {assert targetField != null;TargetClass<T>.LocalInner inner;
try {inner = targetField.createLocalInner();synchronized (inner) {inner.variableCall();new SubClass<>(targetField).superMethod();}} catch (Exception e) {throw new IOException();}
String res = (inner != null) ? new SubClass<>(targetField).overload() : new SubClass<>(targetField).overload("");return targetField;}}}
final class TargetClass {
public U field;
public LocalInner createLocalInner() {class LocalInner {public void variableCall() {}}return new LocalInner();}}
final class SubClass<V> extends TargetClass<V> {public SubClass(TargetClass<V> target) {super();}
public String overload() {return super.toString();}
public String overload(String s) {return super.toString() + s;}
public void superMethod() {super.toString();}}