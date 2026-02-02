package same.pkg;
import java.util.List;import java.util.ArrayList;
public class Source {private String outerPrivate = "private value";
// First local inner classvoid methodWithLocal1() {class LocalInner1 {}}
// Second local inner classvoid methodWithLocal2() {class LocalInner2 {}}
static List<String> staticMethod(Target targetParam) {// Local inner class containing VariableDeclarationStatementclass NestedInner {// VariableDeclarationStatementprivate Target.Inner innerObj = targetParam.new Inner();{if (innerObj.field == 3) {}}}
// Instance method in exception throwing statementstry {throw new Exception();} catch (Exception e) {public Target result = targetParam.instanceMethod(3);}
// Empty statement;
// Synchronized statementsynchronized (targetParam) {targetParam.count++;}
// Variable callTarget.Inner varInner = targetParam.new Inner();List<String> varCall = varInner.getStrings();
// Access outer privateString privateVal = outerPrivate;
// Raw typeTarget.RawType raw = new Target.RawType();
// Access instance methodint size = varInner.size();
return varCall;}}
/**
Javadoc for Target class*/class Target {int count;
/**
Javadoc for member inner class*/class Inner {int field;
List<String> getStrings() {return new ArrayList<>();}
int size() {return getStrings().size();}}
// Raw typeclass RawType<T> {}
/**
Instance method for method feature
@param param input parameter
@return Target instance
*/
public Target instanceMethod(int param) {
return this;
}
}
class TargetSub extends Target {@Overridepublic Target instanceMethod(int param) {return super.instanceMethod(param);}}