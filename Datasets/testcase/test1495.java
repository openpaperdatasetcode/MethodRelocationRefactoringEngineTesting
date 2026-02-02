package test;
import java.util.List;import java.lang.reflect.Method;
public class Source {class FirstInner {class SecondInner {private void process(Target target, String... args) {// Raw type usageTarget rawTarget = new Target();
// Labeled statementprocessLoop:for (String arg : args) {if (arg == null) {break processLoop;}target.addData(arg);}
// Used by reflectiontry {Method method = Target.class.getMethod("getLocalData");List<String> data = (List<String>) method.invoke(target);System.out.println(data);} catch (Exception e) {// No new exception thrown}}}}
public void execute(Target target) {new FirstInner().new SecondInner().process(target, "a", "b", "c");}}
public class Target {private List<String> data;
void addData(String item) {// Local inner classclass LocalDataHandler {void store(String val) {data.add(val);}}new LocalDataHandler().store(item);}
List<String> getLocalData() {return data;}}