import java.util.ArrayList;import java.util.List;
abstract class SourceClass {class SourceInnerClass {@Overridepublic final Object method(TargetClass<String> targetParam) {if (targetParam != null) {transient TargetClass<String> obj1 = new TargetClass<>();obj1.field = "value1";transient TargetClass<String> obj2 = new TargetClass<>();obj2.field = "value2";
Runnable r = new Runnable() {public void run() {variableCall(targetParam);}};r.run();
return defaultMethod(obj1, obj2);} else {return new Object();}}
default List<String> defaultMethod(TargetClass<String> t1, TargetClass<String> t2) {List<String> list = new ArrayList<>();list.add(t1.field);list.add(t2.field);
class InnerClass {void addItems() {list.add("inner");}}new InnerClass().addItems();
TargetClass<String> ref = new TargetClass<>();list.forEach(ref::processItem);return list;}
private void variableCall(TargetClass<String> target) {target.anonymousMethod();}}
{new Runnable() {public void run() {SourceInnerClass inner = new SourceInnerClass();inner.method(new TargetClass<>());}}.run();}}
public abstract class TargetClass<T> {String field;
void processItem(String s) {}
void anonymousMethod() {new Runnable() {public void run() {System.out.println(field);}}.run();}}
class ConstructorCaller {ConstructorCaller() {this(new SourceClass().new SourceInnerClass().callMethod(new TargetClass<>()));}
ConstructorCaller(int value) {}
int callMethod(TargetClass<String> target) {return target.field.length();}}
abstract class SuperClass {public abstract Object method(TargetClass<String> param);}
