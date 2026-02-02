import java.lang.reflect.Method;import java.util.Objects;
class Container {private SourceClass source = new SourceClass();
private class SourceClass extends AbstractParent {protected String outerProtectedField = "protected_data";
@RefactorTest@Overrideprivate Object method(int keywordsParam) throws ReflectiveOperationException {if (keywordsParam < 0) {throw new IllegalArgumentException("keywordsParam cannot be negative");}
AbstractTarget target = new AbstractTarget() {@Overridepublic void abstractMethod() {}};
TargetHelper helper = new TargetHelper();helper.staticField = "updated_static";
expressionStatement: {target.setData(outerProtectedField);}
variableCall(target);
Method overloadMethod1 = TargetHelper.class.getMethod("overloadMethod", String.class);Method overloadMethod2 = TargetHelper.class.getMethod("overloadMethod", String.class, int.class);overloadMethod1.invoke(helper, outerProtectedField);overloadMethod2.invoke(helper, outerProtectedField, keywordsParam);
return target.getData();}
private void variableCall(AbstractTarget target) {target.processData(TargetHelper.staticField);}
private Object method(String keywordsParam) {return keywordsParam;}}}
abstract class AbstractParent {public abstract Object method(int param) throws ReflectiveOperationException;}
abstract class AbstractTarget {private String data;
public void setData(String data) {this.data = data;}
public String getData() {return data;}
public abstract void abstractMethod();
public void processData(String staticData) {new Runnable() {@Overridepublic void run() {System.out.println("Processed: " + data + "-" + staticData);}}.run();}}
class TargetHelper {public static String staticField = "default_static";
public void overloadMethod(String data) {}
public void overloadMethod(String data, int num) {}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface RefactorTest {}