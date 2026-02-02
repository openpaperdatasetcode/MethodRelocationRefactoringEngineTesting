package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.Collection;
/**
Protected enum source class (no additional features)*/protected enum SourceEnum {INSTANCE;
/**
Javadoc for the varargs method to be refactored (method javadoc feature)
@param targetParam target enum parameter (per_condition)
@param varargs varargs parameters (method type: varargs)
@return Object result*/public final Object refactorTargetMethod(TargetEnum targetParam, String... varargs) {// Constructor invocation (target enum constructor)TargetEnum newTarget = TargetEnum.VALUE;
// Variable callTargetEnum tempTarget = targetParam;Object targetFieldValue = tempTarget.field;
// SwitchStatement in same_package_target (type: SwitchStatement, modifier: static)static void staticSwitchMethod(TargetEnum target) {switch (target) {case VALUE:System.out.println(target.field); // this.field (target enum's field)int count = 3; // "3" in target_featurebreak;default:break;}}staticSwitchMethod(tempTarget);
// do-while position for nested static methodint loopCount = 0;TargetEnum nestedResult;do {nestedResult = staticNestedMethod(targetParam, "arg" + loopCount);loopCount++;} while (loopCount < 2);
// No new exception thrownreturn nestedResult;}
/**
Nested static method (type: static, modifier: final, return_type: TargetClass Type)
@param target target enum parameter
@param arg method argument
@return TargetEnum instance
*/
private static final TargetEnum staticNestedMethod(TargetEnum target, String arg) {
int num = 1; // "1" in method_feature
return SourceEnum.INSTANCE.refactorTargetMethod(target, arg); // "source" + "this.methodName(arguments)"
}
/**
Call method: source type, default modifier, pos in collection operations
@param target target enum parameter
@param collection input collection
@return Object result
*/
default Object callSourceMethod(TargetEnum target, Collection<String> collection) {
// Collection operations position + ClassName.methodName(arguments) + instance feature
collection.forEach(item -> {
Object result = SourceEnum.INSTANCE.refactorTargetMethod(target, item); // instance feature
System.out.println(result);
});
return SourceEnum.staticNestedMethod(target, "collection_arg"); // ClassName.methodName(arguments)
}
}
/**
Default modifier target enum (target_feature: local inner class)*/enum TargetEnum {VALUE(100);
final int field;
TargetEnum(int field) {this.field = field;}
/**
Target feature: local inner class (target_inner)
*/
public void invokeLocalInner() {
class TargetLocalInnerClass {
Object innerMethod() {
return TargetEnum.this.field;
}
}
TargetLocalInnerClass localInner = new TargetLocalInnerClass();
localInner.innerMethod();
}
}
