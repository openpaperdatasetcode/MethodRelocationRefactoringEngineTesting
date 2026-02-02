package test;
import java.util.ArrayList;import java.util.List;import java.io.IOException;import other.OthersClass;
public class SourceClass {private String outerPrivate = "private_outer_data";
public class MemberInner {public String getValue() {return SourceClass.this.outerPrivate; // OuterClass.this.x}}
List<String> process(TargetClass<String> target) throws IOException {List<String> result = new ArrayList<>();
// Local inner classclass TargetProcessor {void processField(TargetClass<String> t) {// Access outer private fieldt.data = SourceClass.this.outerPrivate;}}TargetProcessor processor = new TargetProcessor();
// Type declaration statementTargetClass.StaticNested<Integer> nested = new TargetClass.StaticNested<>();
// Variable call - access target's fieldresult.add(target.data);
// Access target's static nested class fieldnested.value = 100;result.add(nested.value.toString());
// Switch statementswitch (target.getType()) {case 1:// Overloaded method 1target.setData("type_1");break;case 2:// Overloaded method 2target.setData(2, "type_2");break;default:target.setData("default");}
// Call others_class's protected method in property assignmenttarget.relatedObj = OthersClass.process(target);
processor.processField(target);return result;}}
package other;
import test.TargetClass;
public class OthersClass {// Protected normal methodprotected static Object process(TargetClass<?> target) {return "processed_by_others: " + target.data;}}
package test;
import java.util.List;
public class TargetClass<T> {public T data;public Object relatedObj;private int type;
public static class StaticNested {
public U value;
}
// Overloaded methods (overload_exist)public void setData(T data) {this.data = data;}
public void setData(int code, T data) {this.type = code;this.data = data;}
public int getType() {return type;}}