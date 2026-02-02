package test;
import java.sql.SQLException;
// Source enum: generic (type parameter), with anonymous & member inner classesprivate enum SourceEnum<T extends CharSequence> {INSTANCE;
// Target enum field (satisfies "source contains the field of the target")private TargetEnum targetField = TargetEnum.INSTANCE;
// Member inner class (source feature)class SourceInnerClass {T processData(T data) {return data;}}
// Anonymous inner class (source feature)private Runnable anonTask = new Runnable() {@Overridepublic void run() {variableCall();}};
/**
Varargs method: returns TargetEnum, private access
@param args Varargs parameters
@return TargetEnum instance
@throws SQLException If SQL-related error occurs*/// Override violation: implements method from OverrideCheck but changes return typeprivate TargetEnum varargsMethod(T... args) throws SQLException {// NullPointerException (method feature)if (targetField == null) {throw new NullPointerException("Target enum field is null");}
// SQLException (method feature)if (args.length == 0) {throw new SQLException("No varargs parameters provided");}
// Variable call (method feature)variableCall();SourceInnerClass innerObj = new SourceInnerClass();innerObj.processData(args[0]);
return targetField;}
// Variable call target methodprivate void variableCall() {}}
// Interface for override violation (method feature)interface OverrideCheck {// Different return type from source method -> override violationString varargsMethod(CharSequence... args) throws SQLException;}
/**
Javadoc for TargetEnum (target feature: javadoc)
Enum class with member inner class*/public enum TargetEnum {INSTANCE;
// Field for "obj.field" & "2" (target_feature)public int targetField = 2;
// Member inner class (target feature)class TargetInnerClass {void useField() {System.out.println("Target field value: " + targetField);}}}
// Diff-package class (for ConstructorInvocation pos: "diff_package_others")package test.other;import test.SourceEnum;import test.TargetEnum;import java.sql.SQLException;
public class DiffPackageClass {public void invokeSourceMethod() throws SQLException {// Get source enum instance (generic type: String)SourceEnum<String> sourceEnum = SourceEnum.INSTANCE;
// ConstructorInvocation with target_feature "obj.field" & "2"TargetEnum.TargetInnerClass targetInner = TargetEnum.INSTANCE.new TargetInnerClass();if (TargetEnum.INSTANCE.targetField == 2) {targetInner.useField();}
// Call source enum's varargs methodTargetEnum result = sourceEnum.varargsMethod("param1", "param2");}}