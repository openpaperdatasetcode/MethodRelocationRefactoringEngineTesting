package same;
import java.util.List;import java.util.ArrayList;
class SourceClass implements Runnable {static class StaticNested {}
class Inner {public int calculate(TargetClass target) throws Exception {class LocalInner {List<String> recursiveMethod(int depth) throws Exception {if (depth <= 0) {return new ArrayList<>();}List<String> list = new TargetClass().process(depth - 1);list.addAll(recursiveMethod(depth - 1));return list;}}
TargetClass newTarget = new TargetClass(target.value);int sum = newTarget.value;
sum += new LocalInner().recursiveMethod(1).size();
if (sum > 10) {Object result = HelperClass.staticMethod(super.toString());} else {Object result = HelperClass.staticMethod(target.toString());}
return sum;}}
@Overridepublic void run() {}}
class HelperClass {private static Object staticMethod(String arg) {return arg;}}
package same;
import java.util.List;import java.util.ArrayList;
strictfp class TargetClass {int value;
TargetClass() {super();}
TargetClass(int value) {super();this.value = value;}
List<String> process(int depth) throws Exception {return new ArrayList<>();}}