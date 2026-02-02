package test;
import java.lang.reflect.Field;import java.lang.reflect.Method;
abstract class SourceClass {private String outerPrivate = "private_data";protected String outerField = "outer_data";
// Static nested classpublic static class SourceStaticNested {public static Object getValue(TargetClass.Inner inner) {return inner.data;}}
// Anonymous inner classprivate Runnable anonymous = new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();try {method(target.new Inner("anon_inner"));} catch (Exception e) {}}};
protected Object method(TargetClass.Inner targetInner) {// Variable callObject result = targetInner.data;
// Access outer privateresult = result + "_" + outerPrivate;
// Access instance fieldresult = result + "_" + targetInner.counter;
// Uses outer thisresult = result + "_" + SourceClass.this.outerField;
// Used by reflectiontry {Field field = TargetClass.Inner.class.getField("data");Method method = TargetClass.Inner.class.getMethod("increment");result = result + "" + field.get(targetInner) + "" + method.invoke(targetInner);} catch (Exception e) {}
// Instance code blocks with parent_class overloading methodsclass InstanceBlockHolder {{Object val1 = TargetParent.process(targetInner.data);Object val2 = TargetParent.process(targetInner.data, 2);result = result + "" + val1 + "" + val2;}}new InstanceBlockHolder();
return result;}}
final class TargetClass {// Static nested classpublic static class TargetStaticNested {}
public class Inner {public String data;public int counter = 0;
public Inner(String data) {this.data = data;}
public int increment() {return ++counter;}}}
class TargetParent {public static Object process(String s) {return s.toUpperCase();}
public static Object process(String s, int repeat) {return s.repeat(repeat);}}