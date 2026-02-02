package test;
import java.util.ArrayList;import java.util.List;
@interface SourceAnnotation {TargetAnnotation targetField();
final int recursiveMethod(int n, TargetAnnotation target) {if (n <= 0) {return 0;}int[] arr = {n};int val = arr[0];
try {List<String> list = new InnerClass().createList();val += list.size();} catch (IllegalStateException e) {throw new IllegalStateException("Error", e);}
for (String s : target.strings()) {val += s.length() + target.count();}
val += SourceAnnotation.this.targetField().count();val += StaticHolder.staticField;
return val + recursiveMethod(n - 1, target);}
class InnerClass {public List<String> createList() {return new ArrayList<>();}}
default void someMethod() {class LocalInner {void callRecursive() {TargetAnnotation target = new TargetAnnotation() {@Overridepublic int count() {return 5;}
@Overridepublic String[] strings() {return new String[]{"test"};}};recursiveMethod(3, target);}}new LocalInner().callRecursive();}
static class StaticHolder {static int staticField = 10;
static {SourceAnnotation ann = new SourceAnnotation() {};ann.someMethod();ann.targetField().strings();}}}
@interface TargetAnnotation {int count();String[] strings();}
class SubTargetAnnotation implements TargetAnnotation {@Overridepublic int count() {return 3;}
@Overridepublic String[] strings() {return new String[]{"sub"};}}