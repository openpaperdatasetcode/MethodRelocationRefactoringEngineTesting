package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
// Source record (default modifier) with local and member inner classesrecord SourceRecord(String data) {class MemberInner {// Accessor method (position: source_inner)List<String> getTargetData(TargetRecord target) {List<String> result = new ArrayList<>();super.toString(); // Super keywords
// Static code blocks (method_feature position)static {new SourceRecord("static1").processHelper(target);new SourceRecord("static2").processHelper(target);new SourceRecord("static3").processHelper(target);}
// Private SynchronizedStatement (target_feature: super.field=3)private Object lock = new Object();synchronized (lock) {if (target.superField != 3) throw new IllegalArgumentException();variableCall(target);result.add(target.data());}
// Local inner classclass LocalInner {void addToResult() {result.add(String.valueOf(target.superField));}}new LocalInner().addToResult();
// Used by reflectiontry {Method method = TargetRecord.class.getDeclaredMethod("anonymousTask");method.setAccessible(true);method.invoke(target);} catch (Exception e) {}
return result;}
// Overload existsList<String> getTargetData(TargetRecord target, int multiplier) {List<String> result = getTargetData(target);result.add(String.valueOf(target.superField * multiplier));return result;}
private void variableCall(TargetRecord target) {target.process();}
// Normal method for method_featurevoid processHelper(TargetRecord target) {target.data();}}}
/**
Target record with javadoc and anonymous inner class (strictfp modifier)*/strictfp record TargetRecord(String data) extends ParentRecord {// Anonymous inner classprivate final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {superField = 3;}};
public void process() {}}
class ParentRecord {protected int superField = 3; // super.field=3}