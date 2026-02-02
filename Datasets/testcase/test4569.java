package same.pkg;
import java.sql.SQLException;
public abstract class SourceClass implements MyInterface {private TargetClass targetField = new TargetClass(); // Contains target's field
static class SourceStaticNested {}class SourceMemberInner {}
class SourceInner {class SourceInnerRec {public Object method() throws SQLException {variableCall();
// BreakStatement with this.field (1 occurrence) in same_package_otherssamePackageOthersLoop:for (int i = 0; i < 5; i++) {if (this.targetField.targetValue == 10) {protected boolean isBroken = true;break samePackageOthersLoop;}}
// Call source's static method via LambdaString lambdaResult = SourceStaticNested.staticMethod(() -> "Lambda body: " + targetField.targetValue);
return new Object();}
private void variableCall() {int localVar = targetField.targetValue;}
// Target class reference (field in inner_rec)private TargetClass targetField;}}}
interface MyInterface {}
protected abstract class TargetClass {int targetValue = 10; // Field contained in source
void methodWithLocalClass() {// Target's local inner classclass TargetLocalInner {void localMethod() {}}new TargetLocalInner().localMethod();}}
// Same-package other class for BreakStatement posclass SamePackageOthers {static void useBreakLogic(SourceClass.SourceInner.SourceInnerRec innerRec) {}}