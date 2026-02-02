import java.lang.reflect.Constructor;import java.util.Arrays;import java.util.List;
abstract class SourceAbstract {private String outerPrivateField = "private_source_data";
public static class StaticNested {public static void helperMethod(TargetAbstract target) {if (target == null) {throw new NullPointerException("TargetAbstract cannot be null");}target.staticNested.setData("helper_updated_data");}}
strictfp void instanceMethod(TargetAbstract target) {if (target == null) {throw new NullPointerException("Target parameter cannot be null");}
class LocalInner {void processTarget(TargetAbstract t) {variableCall(t.staticNested);System.out.println("Outer private field: " + outerPrivateField);}}LocalInner local = new LocalInner();
try {Constructor<TargetAbstract.StaticNested> nestedConstructor =TargetAbstract.StaticNested.class.getDeclaredConstructor(String.class);nestedConstructor.setAccessible(true);TargetAbstract.StaticNested reflectedNested = nestedConstructor.newInstance(outerPrivateField);target.staticNested = reflectedNested;} catch (Exception e) {}
List<String> dataList = Arrays.asList("data1", "data2", "data3");for (String data : dataList) {local.processTarget(target);target.staticNested.appendData(data);}}
private void variableCall(TargetAbstract.StaticNested nested) {nested.setData(outerPrivateField);}
public abstract void abstractMethod();}
public abstract class TargetAbstract {public StaticNested staticNested = new StaticNested("default_target_data");
public static class StaticNested {private String data;
public StaticNested() {}
public StaticNested(String data) {this.data = data;}
public void setData(String data) {this.data = data;}
public String getData() {return data;}
public void appendData(String suffix) {this.data += "_" + suffix;}}
public abstract void abstractMethod();}
class SourceConcrete extends SourceAbstract {@Overridepublic void abstractMethod() {}}
class TargetConcrete extends TargetAbstract {@Overridepublic void abstractMethod() {}}