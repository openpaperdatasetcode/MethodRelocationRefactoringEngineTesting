package samepkg;
import java.sql.SQLException;import java.lang.reflect.Constructor;import java.util.List;import java.util.ArrayList;
private class SourceClass<T> {static class StaticNested {}
class MemberInner {private TargetClass.TargetInner constructorMethod(TargetClass.TargetInner targetParam) throws SQLException {TargetClass.TargetInner inner = targetParam;try {Constructor<?> ctor = TargetClass.TargetInner.class.getConstructor();inner = (TargetClass.TargetInner) ctor.newInstance();} catch (Exception e) {}return inner;}}}
/**
Javadoc for TargetClass
*/
private class TargetClass {
class TargetInner {}
}
class SubClass extends SourceClass<String> {List<String> callMethod() {List<String> list = new ArrayList<>();do {list = (param) -> new ArrayList<>();} while (list.isEmpty());return list;}}