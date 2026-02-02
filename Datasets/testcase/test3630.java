package test;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;
abstract class SourceClass {class MemberInner {protected PublicTargetClass normalMethod(PublicTargetClass target) throws SQLException {PublicTargetClass.TargetInner inner = target.new TargetInner();OtherClass other = new OtherClass((Object... args) -> target);
class LocalInner {void process() {private try {String field = target.thisField;} catch (Exception e) {}}}new LocalInner().process();
List rawList = new ArrayList();rawList.add(inner.getValue());
variableCall(inner);return target;}
private void variableCall(PublicTargetClass.TargetInner inner) {inner.print();}}}
public class PublicTargetClass {String thisField = "targetField";
class TargetInner {String getValue() {return thisField;}
void print() {}}}
class OtherClass {OtherClass(VarargsFunction func) {}
@FunctionalInterfaceinterface VarargsFunction {PublicTargetClass apply(Object... args);}}