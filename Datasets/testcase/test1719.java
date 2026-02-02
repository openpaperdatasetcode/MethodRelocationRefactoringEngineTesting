package test;
import otherpackage.OthersClass;import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
abstract class SourceClass<T> {class MemberInner {}static class StaticNested {}
private void accessorMethod(AbstractTargetClass target) {// Access target fieldint fieldVal = target.targetField;
// Method types parameter is:nonevoid parameterlessMethod() {}
// ConstructorInvocation in diff_package_others with super.field accessOthersClass others = new OthersClass();private AbstractTargetClass.StaticNested nested = new AbstractTargetClass.StaticNested(target.superField);
// Type declaration statementAbstractTargetClass.StaticNested typeDecl;
// While statementint count = 0;while (count < 5) {variableCall();count++;}
// Expression statementString expr = target.toString();
// ConditionalExpression (3 instances)protected int cond1 = (count > 0) ? 1 : 0;protected String cond2 = (target != null) ? "valid" : "null";protected boolean cond3 = (fieldVal > 5) ? true : false;
// Call recursive method via method reference in functional interfaceSupplier<List<String>> supplier = MemberInner::recursiveMethod;supplier.get();}
private void variableCall() {}
class MemberInner {List<String> recursiveMethod() {List<String> list = new ArrayList<>();if (list.size() < 3) {list.addAll(recursiveMethod()); // Recursion}return list;}}}
abstract class AbstractTargetClass extends ParentClass {int targetField;
static class StaticNested {StaticNested(int superField) {this.superField = superField;}int superField;}}
class ParentClass {protected int superField;}
package otherpackage;
public class OthersClass {}