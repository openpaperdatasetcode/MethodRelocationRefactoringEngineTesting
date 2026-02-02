package test;
// Parent class for call_methodclass ParentCaller {public void overloadedCall(SourceEnum.SourceInnerRec innerRec, TargetEnum target) {innerRec.instanceMethod(target);}
public void overloadedCall(SourceEnum.SourceInnerRec innerRec, TargetEnum target, String param) {Object result = innerRec.instanceMethod(target);System.out.println(result + param);}}
// Source enum class (private, same package with target)private enum SourceEnum {INSTANCE;
// Static nested class (source_class feature)static class SourceStaticNested {}
// Member inner class (source_class feature)class SourceInner {// Source inner recursive class (method_position: source_inner_rec)class SourceInnerRec {// Target method to test Move Method refactoringprotected Object instanceMethod(TargetEnum target) {// Synchronized statement (method feature)synchronized (this) {// Accessor: ClassName::methodName (method feature)java.util.function.Supplier<TargetEnum> targetAccessor = TargetEnum::valueOf;TargetEnum accessedTarget = targetAccessor.get();
// Variable call (method feature) - source contains target's fieldString targetFieldVal = target.getTargetField();String staticFieldVal = SourceEnum.SourceStaticNested.class.getSimpleName();
// Depends on static field (method feature)if (staticFieldVal.isEmpty()) {return null;}
// Override violation (method feature) - intentional override of Object's toString@Overridepublic String toString() {return targetFieldVal;}
return accessedTarget;}}}
SourceInnerRec getInnerRec() {return new SourceInnerRec();}}
SourceInner getInner() {return new SourceInner();}}
// Target enum class (default modifier)enum TargetEnum {TARGET_INSTANCE("targetFieldValue");
private final String targetField;
TargetEnum(String targetField) {this.targetField = targetField;}
// Member inner class (target_class feature)class TargetInner {}
// Accessor for target's field (per_condition: source contains target's field)protected String getTargetField() {return targetField;}}
// Caller class with static code block (call_method position)class MethodCaller extends ParentCaller {static {SourceEnum source = SourceEnum.INSTANCE;SourceEnum.SourceInner sourceInner = source.getInner();SourceEnum.SourceInnerRec sourceInnerRec = sourceInner.getInnerRec();TargetEnum target = TargetEnum.TARGET_INSTANCE;
// Instance reference method reference (call_method feature: instanceReference::methodName)java.util.function.BiConsumer<SourceEnum.SourceInnerRec, TargetEnum> callRef = sourceInnerRec::instanceMethod;callRef.accept(sourceInnerRec, target);
// Call overloaded methods from parent class (call_method feature: overloading)ParentCaller caller = new ParentCaller();caller.overloadedCall(sourceInnerRec, target);caller.overloadedCall(sourceInnerRec, target, "extraParam");}}