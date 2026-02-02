package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public class SourceClass {private String value = "sourceValue";
{Runnable anon = new Runnable() {@Overridepublic void run() {}};}
strictfp List<String> moveMethod(TargetClass target) {class LocalInner {}new LocalInner();
TargetClass.TargetInner inner = new TargetClass.TargetInner();List<String> result = new ArrayList<>();
() -> System.out.println (this.value)Runnable lambda = () -> System.out.println (this.value);lambda.run ();
result.add(inner.process(target.getData()));result.add(inner.calculate(target.getCount()));
try {Method method = TargetClass.TargetInner.class.getMethod("process", String.class);method.invoke(inner, "reflectionData");} catch (Exception e) {}
return result;}}
class TargetClass {private String data = "targetData";private int count = 5;
class TargetInner {public String process(String input) {return input.toUpperCase();}
public String calculate(int num) {return String.valueOf(num * 2);}}
public String getData() {return data;}
public int getCount() {return count;}}