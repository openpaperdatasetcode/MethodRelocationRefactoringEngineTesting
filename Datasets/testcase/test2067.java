package test;
import java.sql.SQLException;import java.util.function.Function;
class ParentClass<T> {}
class SourceClass<T extends Number> extends ParentClass<T> {static class StaticNested {}
class SourceInner {final int methodToMove(TargetClass<String> target) throws SQLException {// Anonymous inner classnew Object() {};
// Type declaration statementTargetClass<String>.MemberInner inner = target.new MemberInner();int fieldVal = inner.intField;
// Break statementfor (int i = 0; i < 10; i++) {if (i == fieldVal) {break;}}
// Call method in functional interfaceFunction<String, String> function = (s) -> inner.strictfpOverload(s);String result = function.apply("test");
return result.length();}}}
class TargetClass<V> {class MemberInner {int intField;
strictfp String strictfpOverload(String s) {return s;}
strictfp String strictfpOverload(Integer i) {return i.toString();}}}