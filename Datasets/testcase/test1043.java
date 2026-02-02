import java.util.Objects;
// Sealed abstract source class (same package as target)sealed abstract class SourceClass permits SourceClass.SourceSealedSubclass {// Field referencing target class (per_condition: source contains target field)TargetClass targetField;
// Static nested class (source class feature)static class StaticNestedClass {static int staticField = 42; // For depends_on_static_field feature}
// Local inner class (source class feature) - declared within a methodpublic void sourceMethodWithLocalInner() {class LocalInnerClass {void localInnerMethod() {}}new LocalInnerClass().localInnerMethod();}
// Inner class for recursive method position (source_inner_rec)class SourceInnerClass {// Recursive inner class to satisfy source_inner_recclass RecursiveInnerClass {// Method to refactor: instance, base type return, strictfp access, target class = target_innerstrictfp int methodToMove() {// Depends on static field (SourceClass.StaticNestedClass.staticField)int staticVal = StaticNestedClass.staticField;
// Constructor invocationTargetClass.MemberInnerClass innerInstance = new TargetClass().new MemberInnerClass();
// Variable callinnerInstance.innerField = staticVal;
// Try statementtry {// NullPointerException (explicit null access)String nullStr = null;nullStr.length();} catch (NullPointerException e) {// No new exception (no_new_exception feature - rethrow/throw new not allowed)}
// Assert statementassert innerInstance.innerField == 42 : "Static field value mismatch";
// Expression statement (completes variable call flow)targetField = new TargetClass() {};
// Return base type (int)return innerInstance.innerField;}}}
// Sealed subclass (required for sealed modifier)static final class SourceSealedSubclass extends SourceClass {}}
// Public abstract target classpublic abstract class TargetClass {// Member inner class (target_feature)class MemberInnerClass {int innerField;
// Empty constructor for invocationMemberInnerClass() {}}}
