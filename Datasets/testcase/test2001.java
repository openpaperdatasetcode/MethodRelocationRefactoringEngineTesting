package test;
import java.sql.SQLException;
class ParentClass<T> {}
public class SourceClass<T extends Number> extends ParentClass<T> {static class StaticNestedNested<T> {}
class MemberInner {class InnerRec {public Object process(TargetClass<String> target) throws SQLException {TargetClass<Integer>.Inner inner = target.new Inner(5);Object result = inner.getValue();
overloadMethod(target);overloadMethod(10);
return result;}
public void overloadMethod(TargetClass<?> target) {}public void overloadMethod(int num) {}}}}
class TargetClass<T> {class Inner {private T value;
Inner(T val) {this.value = val;}
T getValue() {return value;}}}