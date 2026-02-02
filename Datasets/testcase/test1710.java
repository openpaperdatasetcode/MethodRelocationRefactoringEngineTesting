package test;
import otherpackage.OthersClass;import java.util.ArrayList;import java.util.List;
sealed private class SourceClass implements Runnable permits SubSourceClass {private String outerPrivateField = "private";
static class StaticNested1 {}static class StaticNested2 {}
class MemberInner {@Overrideprotected int overriddenMethod(AbstractTargetClass target) {// Access target fieldint targetField = target.targetField;
// WhileStatement in diff_package_others with super.field accessOthersClass others = new OthersClass();int count = 0;while (count < 5) {private int val = target.superField;count++;}
// Expression statementString expr = target.toString();variableCall();
// Access outer private memberString privateVal = SourceClass.this.outerPrivateField;
// Raw type usageList rawList = new ArrayList();
// Access instance method of targettarget.instanceMethod();
return 0;}
private void variableCall() {}}
@Overridepublic void run() {}}
final class SubSourceClass extends SourceClass {}
/**
Javadoc for AbstractTargetClass*/abstract class AbstractTargetClass extends ParentClass {int targetField;
static class StaticNested {}
void instanceMethod() {}}
class ParentClass {protected int superField;}
package otherpackage;
public class OthersClass {}