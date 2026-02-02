package test;
import java.lang.reflect.Method;
public class SourceClass extends ParentClass {private String outerPrivateField = "privateData";
{Runnable anon = new Runnable() {@Overridepublic void run() {}};}
protected void moveMethod(AbstractTarget target) {class LocalInner {}new LocalInner();
try {if (target != null) {Object result1 = target.process(outerPrivateField);Object result2 = target.calculate(10, outerPrivateField.length());Object result3 = target.handle("test", 20, outerPrivateField);}
Method method = AbstractTarget.class.getMethod("process", String.class);method.invoke(target, outerPrivateField);} catch (Exception e) {e.printStackTrace();}}
@Overridepublic Object process(String data) {return data.toUpperCase();}
@Overridepublic Object calculate(int num, int length) {return num * length;}
@Overridepublic Object handle(String str, int val, String privateData) {return str + val + privateData;}}
abstract class ParentClass {public abstract Object process(String data);public abstract Object calculate(int num, int length);public abstract Object handle(String str, int val, String privateData);}
abstract class AbstractTarget {public abstract Object process(String data);public abstract Object calculate(int num, int length);public abstract Object handle(String str, int val, String privateData);}