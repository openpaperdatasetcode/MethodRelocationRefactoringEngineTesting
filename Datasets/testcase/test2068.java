package test;
import java.sql.SQLException;import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
interface MyInterface {}
strictfp class SourceClass implements MyInterface {// Overload exists (varargs vs single param)protected int methodToMove(TargetClass... targets) throws SQLException {super.toString();int total = 0;
for (TargetClass target : targets) {if (target == null) {throw new NullPointerException();}
// Private ConstructorInvocation with 2 this.fieldsTargetClass.MemberInner inner = target.new MemberInner(target.thisField1, target.thisField2);
// Expression statementtotal += inner.calculate();
// Call method in expression (lambda)Supplier<List<String>> supplier = () -> this.protectedLambdaMethod(target);total += supplier.get().size();}
return total;}
protected int methodToMove(TargetClass singleTarget) throws SQLException {return methodToMove(new TargetClass[]{singleTarget});}
protected List<String> protectedLambdaMethod(TargetClass target) {List<String> result = new ArrayList<>();result.add(target.thisField1.toString());return result;}}
public class TargetClass {Object thisField1;String thisField2;
class MemberInner {// Private constructor (matches ConstructorInvocation modifier)private MemberInner(Object field1, String field2) {thisField1 = field1;thisField2 = field2;}
int calculate() {return thisField2.length();}}}