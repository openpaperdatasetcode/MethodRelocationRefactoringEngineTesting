package test;
import java.util.ArrayList;import java.util.List;import other.OthersClass;
public class SourceClass {public class MemberInner {public TargetClass<String> createTarget(String data) {return new TargetClass<>(data);}}
protected List<String> process(TargetClass<String> target) {List<String> result = new ArrayList<>();MemberInner innerCreator = new MemberInner();
// Local inner classclass TargetHandler {void handle(TargetClass<String> t) {result.add(t.getData());}}TargetHandler handler = new TargetHandler();
// Type declaration statementTargetClass<String> newTarget = innerCreator.createTarget("new_data");
// Variable call - access target's fieldresult.add(target.data);
// Ternary operator with others_class's protected constructor callint length = (target.data != null) ? OthersClass.calculateLength(target.data) : 0;result.add("Length: " + length);
// Throw statementif (target.data.isEmpty()) {throw new IllegalArgumentException("Target data cannot be empty");}
handler.handle(newTarget);return result;}}
public class TargetClass<T> {public T data;
public TargetClass(T data) {this.data = data;// Local inner class in target with type parameterclass DataValidator {boolean isValid(T value) {return value != null;}}if (!new DataValidator().isValid(data)) {this.data = (T) "default";}}
public T getData() {return data;}}
package other;
public class OthersClass {// Protected method for call_methodprotected static int calculateLength(String data) {return data.length();}}