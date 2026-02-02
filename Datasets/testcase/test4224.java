package test;
import java.util.ArrayList;import java.util.List;
public interface TargetInterface<T> {String TARGET_FIELD1 = "target_field1";String TARGET_FIELD2 = "target_field2";String TARGET_FIELD3 = "target_field3";
default T process(T data) {Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};anon.run();return data;}}
interface SourceInterface {static class SourceStaticNested {public static <T> T helper(TargetInterface<T> target, T data) {return target.process(data);}}
class SourceInner {public final int overloadingMethod(TargetInterface<String> target, String data) {List<String> coll = new ArrayList<>();coll.add(data);return processOverload(target, coll);}
public final int overloadingMethod(TargetInterface<Integer> target, Integer data) {List<Integer> coll = new ArrayList<>();coll.add(data);return processOverload(target, coll);}
private <T> int processOverload(TargetInterface<T> target, List<T> coll) {SubClass<T> sub = new SubClass<>();int result = sub.overrideMethod(target, coll);
private void assertTargetFields() {assert TargetInterface.TARGET_FIELD1 != null : "FIELD1 null";assert TargetInterface.TARGET_FIELD2 != null : "FIELD2 null";assert TargetInterface.TARGET_FIELD3 != null : "FIELD3 null";}assertTargetFields();
variableCall(target, coll.get(0));String superVal = super.toString();return result;}
private <T> void variableCall(TargetInterface<T> target, T data) {target.process(data);}}
class SubClass<T> {public int overrideMethod(TargetInterface<T> target, List<T> coll) {T processed = this.overrideMethod(target, coll.get(0));coll.set(0, processed);return coll.size();}
public T overrideMethod(TargetInterface<T> target, T data) {return target.process(data);}}
Runnable sourceAnon = new Runnable() {@Overridepublic void run() {SourceInner inner = new SourceInner();TargetInterface<String> target = new TargetInterface<String>() {};inner.overloadingMethod(target, "test");}};}