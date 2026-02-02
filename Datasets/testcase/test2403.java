package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
interface Processable {void execute();}
private class TargetClass<T> {private T value;public static int staticField = 3;
public TargetClass(T val) {this.value = val;}
public T getValue() {return value;}
public void setValue(T val) {this.value = val;}}
protected class SourceClass implements Processable {static class StaticNested {}
class SourceInner {List<String> handle(TargetClass<String> target) {List<String> result = new ArrayList<>();Object varCall = target.getValue();
class Base {Base() {}}class Derived extends Base {Derived() {super();}}Derived derived = new Derived();
switch (TargetClass.staticField) {case 3:result.add(target.getValue());break;default:break;}
for (int i = 0; i < 2; i++) {target.setValue("updated_" + i);result.add(target.getValue());if (i == 1) {break;}}
try {Method method = TargetClass.class.getMethod("getValue");result.add(method.invoke(target).toString());} catch (Exception e) {// No new exception thrown}
return result;}
List<String> handle(Integer num) {return new ArrayList<>();}}
@Overridepublic void execute() {SourceInner inner = new SourceInner();inner.handle(new TargetClass<>("initial"));}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3064 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();TargetClass<String> target = new TargetClass<>("test");List<String> result = inner.handle(target);assertEquals(4, result.size());}}
