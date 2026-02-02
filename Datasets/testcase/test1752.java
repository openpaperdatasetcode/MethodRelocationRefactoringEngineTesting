package source;
import java.lang.reflect.Method;import target.Target;
abstract class Source {static class StaticNested {class InnerRec {private static int process(Target target, String... keywords) {int result = 0;volatile Target.StaticNestedTarget nested = target.new StaticNestedTarget();
try {for (String kw : keywords) {result += target.count;nested.setValue(kw);}Method method = Target.StaticNestedTarget.class.getMethod("getValue");result += ((String) method.invoke(nested)).length();} catch (Exception e) {result = -1;}
class LocalInner {static int getStatic() {return Target.staticField;}}result += LocalInner.getStatic();
return result;}}}}
package target;
public abstract class Target {int count;static int staticField = 5;
static class StaticNestedTarget {private String value;
void setValue(String val) {this.value = val;}
String getValue() {return value;}}}