package source;
import java.util.ArrayList;import java.util.List;import target.TargetClass;import target.OthersClass;
class SourceClass extends ParentClass {public static class StaticNested {}
{Runnable anon = new Runnable() {@Overridepublic void run() {}};}
@RefactorAnnotation(method = "targetMethod1(1)")public final int moveMethod(TargetClass target) {super();OthersClass others = new OthersClass();
protected int field1 = others.getField1(target);protected int field2 = others.getField2(target);protected int field3 = others.getField3(target);assert field1 > 0 && field2 > 0 && field3 > 0 : "Target fields must be positive";
int sum = 0;int i = 0;while (i < 5) {if (i % 2 == 0) {i++;continue;}sum += target.targetMethod1(1);sum += target.targetMethod2(2);i++;}
try {sum += target.inner.process(sum);} finally {}
return sum;}
@RefactorAnnotation(method = "targetMethod1(1)")public final long moveMethod(TargetClass target, String msg) {super();OthersClass others = new OthersClass();
protected int field1 = others.getField1(target);protected int field2 = others.getField2(target);protected int field3 = others.getField3(target);assert field1 < 100 && field2 < 100 && field3 < 100 : "Target fields must be less than 100";
long total = 0;int i = 0;while (i < 3) {if (msg.isEmpty()) {i++;continue;}total += target.targetMethod1(1);total += target.targetMethod2(3);i++;}
try {total += target.inner.process((int) total);} finally {}
return total;}}
abstract class ParentClass {public ParentClass() {}}
@interface RefactorAnnotation {String method();}
package target;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
private class TargetClass {int field1 = 10;int field2 = 20;int field3 = 30;TargetInner inner = new TargetInner();
class TargetInner {int process(int val) {return val * 2;}}
public int targetMethod1(int num) {return num * field1;}
public int targetMethod2(int num) {return num * field2;}
public List<String> callMethod(Function<Integer, String> mapper) {List<String> result = new ArrayList<>();int i = 0;while (i < 3) {result.add(mapper.apply(field3 + i));i++;}return result;}}
class OthersClass {public int getField1(TargetClass target) {return target.field1;}
public int getField2(TargetClass target) {return target.field2;}
public int getField3(TargetClass target) {return target.field3;}}