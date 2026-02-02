package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
/**
Generic source class with required features*/class SourceClass<T> {private TargetClass<T> targetField;private T sourceData;
/**
Processes data using target field and sub-class methods
@param input List of input data
@param flag Control flag for switch case*/@Deprecatedprivate void processData(List<T> input, int flag) {if (targetField == null) {throw new NullPointerException("Target field must not be null");}
outerLoop:for (T item : input) {if (item == null) {continue outerLoop;}
switch (flag) {case 1:TargetClass<T> sub1 = getSubClassInstance1().process(item, "arg1", 100);sub1.execute(sourceData);break;case 2:TargetClass<T> sub2 = getSubClassInstance2().process(item, "arg2", 200);sub2.execute(sourceData);break;case 3:TargetClass<T> sub3 = getSubClassInstance3().process(item, "arg3", 300);sub3.execute(sourceData);break;default:break outerLoop;}}
class LocalInnerClass {void useTargetField() {targetField.execute(sourceData);}}new LocalInnerClass().useTargetField();}
private TargetSubClass1<T> getSubClassInstance1() {return new TargetSubClass1<>();}
private TargetSubClass2<T> getSubClassInstance2() {return new TargetSubClass2<>();}
private TargetSubClass3<T> getSubClassInstance3() {return new TargetSubClass3<>();}
static class StaticNestedSource<T> {private T nestedData;
public void setData(T data) {this.nestedData = data;}}}
/**
Private generic target class with required features*/private class TargetClass<T> implements Processable<T> {private T targetData;
@Overridepublic void execute(T data) {this.targetData = data;}
public TargetClass<T> process(T item, String arg, int num) {return new TargetClass<>();}
static class StaticNestedTarget<T> {private List<T> nestedList = new ArrayList<>();
public void addItem(T item) {nestedList.add(item);}}}
interface Processable<T> {void execute(T data);}
class TargetSubClass1<T> extends TargetClass<T> {}class TargetSubClass2<T> extends TargetClass<T> {}class TargetSubClass3<T> extends TargetClass<T> {}
// Test classpublic class MoveMethodTest5167 {public static void main(String[] args) {SourceClass<String> source = new SourceClass<>();source.targetField = new TargetClass<>();
List<String> input = List.of("data1", "data2", "data3");// Access via reflection since processData is privatetry {var method = SourceClass.class.getDeclaredMethod("processData", List.class, int.class);method.setAccessible(true);method.invoke(source, input, 2);} catch (Exception e) {e.printStackTrace();}}}