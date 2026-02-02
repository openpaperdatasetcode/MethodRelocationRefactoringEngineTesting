package test;
import java.lang.reflect.Constructor;import java.util.List;
interface TargetInterface {}
abstract class SourceClass {public class SourceInner {void innerMethod() {}}
strictfp TargetClass methodToMove() {try {Constructor<TargetClass> constructor = TargetClass.class.getConstructor(int.class);TargetClass target = constructor.newInstance(1);
for (int i = 0; i < 5; i++) {if (i == target.superField) {continue;}target.targetField = i;}
<T extends List & TargetInterface> T boundedMethod(T input) {return input;}boundedMethod((T) new ArrayList<>());
new Runnable() {@Overridepublic void run() {SourceInner inner = new SourceInner();inner.innerMethod();}};
return target;} catch (Exception e) {return null;}}
@Overridepublic String toString() {return super.toString();}}
private abstract class TargetClass implements TargetInterface {protected int superField = 1;int targetField;
public TargetClass(int value) {this.superField = value;}
public class TargetInner {void process() {}}
@Overridepublic abstract String toString();}
