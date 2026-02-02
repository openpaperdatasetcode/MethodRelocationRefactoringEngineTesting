package test;
import java.lang.reflect.Method;import java.util.function.Function;
strictfp class SourceClass implements Function<String, Integer> {public class MemberInner {public class InnerRec {private <T> TargetClass process(T input) {class LocalHandler {TargetClass createTarget() {return new TargetClass(TargetClass.staticField1, TargetClass.staticField2);}}
private TargetClass target = new LocalHandler().createTarget();private int count = target.getValue();
new Runnable() {@Overridepublic void run() {target.update(input.toString());}}.run();
try {Class<?> cls = Class.forName("test.SourceClass
MemberInner
InnerRec");Method method = cls.getMethod("process", Object.class);method.invoke(this, input);} catch (Exception e) {// no new exception}
return target;}}}
@Overridepublic Integer apply(String s) {return s.length();}}
class TargetClass {public static String staticField1 = "field1";public static int staticField2 = 2024;private String data;
TargetClass(String s, int num) {class LocalInitializer {String init() {return s + num;}}this.data = new LocalInitializer().init();}
int getValue() {return data.length();}
void update(String newData) {this.data = newData;}}