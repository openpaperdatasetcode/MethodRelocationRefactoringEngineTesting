package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;import java.lang.reflect.Method;import other.OtherClass;
interface DataProcessor {List<String> processData(TargetClass target) throws ReflectiveOperationException;}
class SourceClass implements DataProcessor {protected String sourceField = "source_data";
public class MemberInner {public String getValue() {return sourceField;}}
@Overrideprotected List<String> processData(TargetClass target) throws ReflectiveOperationException {List<String> result = new ArrayList<>();
// EnhancedForStatement with private modifier in other packageOtherClass.process(target, items -> {private enhanced for (String item : items) {result.add(item + target.dataField); // Access target's field}});
// Instance method in functional interfaceSupplier<Object> supplier = () -> {private Object getTargetData() {return target.superMethod(); // super.methodName()}return getTargetData();};result.add(supplier.get().toString());
// Expression statementtarget.dataField = new MemberInner().getValue();
// ThisExpression (1 occurrence, final)final SourceClass thisRef = this;result.add(thisRef.sourceField);
// Super keywordresult.add(super.toString());
// Used by reflectionMethod method = TargetClass.class.getMethod("getInnerData");result.add(method.invoke(target).toString());
// Call source's overriding method in expressionint count = SourceClass.this.countItems(target.dataField);result.add(String.valueOf(count));
return result;}
protected int countItems(String data) {return data.length();}}
package other;
import test.TargetClass;import java.util.List;
public class OtherClass {public static void process(TargetClass target, ItemProcessor processor) {processor.process(target.items);}
@FunctionalInterfaceinterface ItemProcessor {void process(List<String> items);}}
package test;
import java.util.List;import java.util.ArrayList;
class TargetClass {public String dataField;public List<String> items = new ArrayList<>();
public Object superMethod() {return super.toString();}
public String getInnerData() {// Anonymous inner class in targetRunnable task = new Runnable() {@Overridepublic void run() {dataField = "processed_by_anonymous";}};task.run();return dataField;}}