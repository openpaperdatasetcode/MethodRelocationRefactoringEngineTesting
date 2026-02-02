package test;
import java.sql.SQLException;
// Sealed enum source class with member inner class and static nested classsealed enum SourceEnum permits SourceEnum.PERM1, SourceEnum.PERM2 {PERM1, PERM2;
// Private field of target class (meets per_condition)private TargetEnum targetField = TargetEnum.VALUE1;
// Member inner class (source_class feature)public class MemberInner {}
// Static nested class (source_class feature)public static class StaticNested {}
// Overloading methods (method type: overloading)public void overloadedMethod(TargetEnum target) {// Access outer private fieldTargetEnum localTarget = this.targetField;variableCall(target);
// For loop (pos for constructor feature)for (int i = 0; i < 2; i++) {// Target class constructor invocation (ClassName.methodName(arguments) form)TargetEnum newTarget = TargetEnum.valueOf("VALUE" + (i + 1));}
// Do statementdo {try {if (target == null) {throw new SQLException("Target is null"); // SQLException feature}return; // Return statement} catch (SQLException e) {throw new RuntimeException(e); // Throw statement}} while (false);}
// Overloaded method (overloading feature)public void overloadedMethod(TargetEnum... targets) {for (TargetEnum target : targets) {variableCall(target);}return; // Return statement}
private void variableCall(TargetEnum target) {TargetEnum local = target;local.createLocalInner().process(); // Access target's local inner class}}
// Target enum class extending super class with local inner classenum TargetEnum extends SuperTargetEnum {VALUE1, VALUE2;
// Local inner class (target_class feature)TargetEnum createLocalInner() {class LocalInner {TargetEnum process() {return TargetEnum.this;}}return new LocalInner().process();}
// Public constructor (matches constructor feature)public TargetEnum() {}
// Static method for constructor invocation (ClassName.methodName(arguments))public static TargetEnum valueOf(String name) {return TargetEnum.valueOf(name);}}
// Super class for target enum's extends featureclass SuperTargetEnum {}