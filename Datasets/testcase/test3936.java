package source.pkg;
import target.pkg.TargetEnum;
public enum SourceEnum {INSTANCE_ONE, INSTANCE_TWO;
private int sourceInstanceField = 100;
public static class StaticNestedClass {public final int processTarget(TargetEnum target) {return recursiveInnerMethod(target, 3);}
private final int recursiveInnerMethod(TargetEnum target, int depth) {if (depth <= 0) return target.targetField;
class LocalTypeDeclaration {int getCalculatedValue() {return sourceInstanceField * depth;}}LocalTypeDeclaration typeDecl = new LocalTypeDeclaration();
privateExpressionStatement(target);variableCall(target);
SubClass.processViaMethodRef(target);return recursiveInnerMethod(target, depth - 1) + typeDecl.getCalculatedValue();}
private void privateExpressionStatement(TargetEnum target) {target.targetField = 1;}
private void variableCall(TargetEnum target) {target.updateField(sourceInstanceField);}}
/**
Javadoc for enum instance method
@param target TargetEnum instance to process
@return Calculated base type result
*/
public final int enumInstanceMethod(TargetEnum target) {
return new StaticNestedClass().processTarget(target);
}
}
class SubClass {public static void processViaMethodRef(TargetEnum target) {TargetEnum.Processor processor = TargetEnum::updateField;processor.execute(target, 50);}
@FunctionalInterfaceinterface Processor {void execute(TargetEnum target, int value);}}
package target.pkg;
public enum TargetEnum {TARGET_INSTANCE;
int targetField;
public void updateField(int value) {this.targetField = value;
Runnable anonymousInner = new Runnable() {@Overridepublic void run() {System.out.println("Updated target field: " + targetField);}};anonymousInner.run();}
public static void updateField(TargetEnum target, int value) {target.targetField = value;}}