package test;
import java.lang.reflect.Method;
public class Source {private String outerField = "outer_value";
class SourceInner {protected int process(Target target) {super.toString();target.data = Source.this.outerField;
int result = target.counter + target.getLocalValue();
try {Method method = Target.class.getMethod("getLocalValue");result += (int) method.invoke(target);} catch (Exception e) {}
return result;}}
public void initialize() {class LocalHandler {String[] getStrings() {return new String[] {new SubTarget().getValue()};}}new LocalHandler().getStrings();}}
public class Target {int counter;String data;
int getLocalValue() {class LocalHelper {int compute() {return counter * 2;}}return new LocalHelper().compute();}}
class SubTarget extends Target {protected String getValue() {return data;}}