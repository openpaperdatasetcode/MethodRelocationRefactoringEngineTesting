package same.pkg;
import com.other.DiffPackageClass;import java.sql.SQLException;import java.util.List;import java.util.ArrayList;
protected enum SourceEnum {INSTANCE;
private TargetEnum<String> targetField = TargetEnum.INSTANCE; // Contains target's field
// Overloading method 1protected List<String> overloadingMethod() throws SQLException {return overloadingMethod(1);}
// Overloading method 2 (refactored method)protected List<String> overloadingMethod(int count) throws SQLException {variableCall();
// 2 ThisExpression instancespublic SourceEnum thisExp1 = this;public SourceEnum thisExp2 = this;
// do statementList<String> result = new ArrayList<>();int doCount = 0;do {result.add(targetField.name());doCount++;} while (doCount < count);
// switch statement with target's inner class method callswitch (count) {case 1:int baseResult = TargetEnum.TargetInner.method(targetField);result.add(String.valueOf(baseResult));break;default:break;}
// ConstructorInvocation in diff_package_others (uses this.field)DiffPackageClass diffObj = new DiffPackageClass(this.targetField);
// Local inner class 1class SourceLocalInner1 {void addToResult(List<String> list) {list.add("LocalInner1");}}new SourceLocalInner1().addToResult(result);
// Local inner class 2class SourceLocalInner2 {void addToResult(List<String> list) {list.add("LocalInner2");}}new SourceLocalInner2().addToResult(result);
return result;}
private void variableCall() {String localVar = targetField.getGenericValue();}}
// Target enum with type parameter and member inner classenum TargetEnum<T> {INSTANCE;
private T genericValue;
T getGenericValue() {return genericValue;}
// Target's member inner classstatic class TargetInner {// Instance method called in switchpublic static int method(TargetEnum<?> target) {return target.ordinal();}}}
// Diff-package class for ConstructorInvocation pospackage com.other;
import same.pkg.TargetEnum;
public class DiffPackageClass {public DiffPackageClass(TargetEnum<?> target) {}}