package test;
import java.util.ArrayList;import java.util.List;
public enum SourceClass<T> {INSTANCE1,INSTANCE2;
class MemberInner1 {T value;}
class MemberInner2 {void process(Target target) {target.count++;}}
private Object method(Target target) {// Raw type usageList rawList = new ArrayList();rawList.add(target.field);
// 3 PrefixExpressions with private modifierprivate int a = ++target.count;private int b = ++target.count;private int c = ++target.count;
// Empty statement;
// Access instance method of targettarget.action();
new MemberInner2().process(target);return rawList;}
private Object method(Target target, String msg) {return target.field + msg;}}
non-sealed enum Target {VALUE1 {@Overridevoid action() {// Anonymous inner class in enum constantRunnable r = new Runnable() {public void run() {field = "processed1";}};r.run();}},VALUE2 {@Overridevoid action() {Runnable r = new Runnable() {public void run() {field = "processed2";}};r.run();}};
String field;int count = 0;
abstract void action();}