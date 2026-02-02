package samepkg;
/**
Source enum class for Move Method refactoring test*/private enum SourceEnum {INSTANCE1, INSTANCE2;
private TargetEnum targetField;
/**
Processes target enum and returns TargetEnum instance
@return TargetEnum instance after processing*/protected TargetEnum processTarget() {// Type declaration statement with boundsClass<? extends TargetEnum> enumClass = TargetEnum.class;
// Local inner class 1class LocalProcessor1 {void validate() {}}
// Constructor invocation (target inner anonymous class)TargetEnum.AnonymousInner inner = new TargetEnum.AnonymousInner() {@Overridevoid execute() {// Local inner class 2 (nested in anonymous inner class)class LocalProcessor2 {void process() {}}new LocalProcessor2().process();}};
// Another constructor invocation with super.field accessTargetEnum targetInstance = TargetEnum.INSTANCE1;Object superFieldVal = targetInstance.getSuperField();
// Variable callnew LocalProcessor1().validate();inner.execute();
return targetInstance;}}
package samepkg;
/**
Target enum class for Move Method refactoring test*/public enum TargetEnum {INSTANCE1, INSTANCE2;
protected Object superField = "targetFieldValue";
/**
Anonymous inner class in target enum
*/
public abstract static class AnonymousInner {
public AnonymousInner() {
// Access super.field (TargetEnum's field)
System.out.println(superField);
}
abstract void execute();
}
public Object getSuperField() {return superField;}}