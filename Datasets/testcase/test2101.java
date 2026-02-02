package test;
import java.lang.reflect.Method;import java.sql.SQLException;import java.util.function.Supplier;
class OtherClass {private Object getValue() {return super.toString();}}
public abstract class SourceClass {class MemberInnerOne {class MemberInnerTwo {final abstract int methodToTest(TargetClass targetParam) throws SQLException;
{super();TargetClass.MemberInner.InnerRecursive innerRec = targetParam.new MemberInner().new InnerRecursive();int fieldVal = innerRec.instanceField;
if (innerRec.superField == null) {throw new SQLException();}
switch (fieldVal) {case 1:targetParam.variableCall();break;default:break;}
try {Method method = TargetClass.MemberInner.class.getMethod("innerMethod");method.invoke(targetParam.new MemberInner());} catch (Exception e) {}
Supplier<Object> supplier = () -> new OtherClass().getValue();supplier.get();}}}}
public class TargetClass {class MemberInner {class InnerRecursive extends SuperInner {int instanceField;}
void innerMethod() {}}
void variableCall() {}}
class SuperInner {protected Object superField;}