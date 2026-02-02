import java.sql.SQLException;import java.util.ArrayList;import java.util.Collection;import java.lang.reflect.Method;
interface DataHandler {void handle(TargetClass target);}
public record SourceClass(String id) implements DataHandler {private TargetClass targetField = new TargetClass("data");
static class StaticNested {protected static int staticProcess(Collection<String> data) {return data.size();}}
class MemberInner {void assist() {SubClass.staticMethod(targetField.inner.getValue());}}
@Overridepublic void handle(TargetClass target) {try {Collection<String> data = new ArrayList<>();data.add(target.inner.getValue());
int count = StaticNested.staticProcess(data);target.inner.update(count);
new MemberInner().assist();
Method method = TargetClass.Inner.class.getMethod("getValue");method.invoke(target.inner);} catch (SQLException e) {// Handle SQLException without new exception} catch (Exception e) {// Handle reflection exceptions}}}
/**
Javadoc for TargetClass
Record containing member inner class for data processing*/record TargetClass(String info) {class Inner {String getValue() {return info;}
void update(int count) {}}
Inner inner = new Inner();}
class SubClass extends TargetClass {public SubClass(String info) {super(info);}
private static int staticMethod(String arg) {return arg.length();}}