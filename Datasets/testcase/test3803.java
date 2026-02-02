package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public class SourceClass extends ParentClass {protected String outerProtectedField = "protectedFieldValue";private TargetClass targetInstance = new TargetClass() {@Overridevoid processLocal() {}};
public static class StaticNestedClass {private List<String> nestedData;
private StaticNestedClass() {this.nestedData = new ArrayList<>();}
List<String> createDataList() {return new SourceClass().new InnerClass().buildList();}}
class InnerClass {List<String> buildList() {boolean useReflection = true;List<String> result = useReflection ? getViaReflection() : new ArrayList<>();
String localType = "declaredType";do {result.add(localType);result.add(SourceClass.this.outerProtectedField);} while (result.size() < 2);
for (String item : result) {System.out.println(item);}
return result;}
private void helperMethod() {}}
private List<String> getViaReflection() {try {Method method = InnerClass.class.getDeclaredMethod("helperMethod");method.setAccessible(true);method.invoke(new InnerClass());} catch (Exception e) {}return new ArrayList<>();}}
abstract class TargetClass {class LocalInnerClass {void useMovedConstructor() {List<String> data = new TargetClass.MovedConstructor().getList();}}
class MovedConstructor {private List<String> dataList;
MovedConstructor() {this.dataList = new ArrayList<>();String superField = SourceClass.super.parentProtectedField;this.dataList.add(superField);}
List<String> getList() {return this.dataList;}}
abstract void processLocal();}
class ParentClass {protected String parentProtectedField = "parentValue";
private List<String> parentMethod() {return new SourceClass.StaticNestedClass().createDataList();}
List<String> executeCall() {boolean useMethodRef = true;return useMethodRef ? ParentClass::parentMethod : new ArrayList<>();}}
class MoveMethodTest {public static void main(String[] args) {TargetClass target = new TargetClass() {@Overridevoid processLocal() {new LocalInnerClass().useMovedConstructor();}};new SourceClass().executeCall();}}