import java.util.function.Function;
// Functional interface for accessor position@FunctionalInterfaceinterface TargetAccessor<T extends TargetClass<String>> extends Function<Integer, T> {}
// Source class: generic, protected modifier, same package, anonymous inner class, member inner classprotected class SourceClass<T> {// Member inner class (source class feature)class SourceMemberInnerClass {int innerField = 3; // For accessor method_feature "3"}
// Anonymous inner class (source class feature)SourceMemberInnerClass anonymousInner = new SourceMemberInnerClass() {@Overridepublic String toString() {return "AnonymousInner";}};
/**
Method to refactor: instance, base type return, default access, target class = target
per_condition: contains target parameter*/@SuppressWarnings("unused") // has_annotation featureint methodToMove(TargetClass<String> targetParam) {// Depends on inner class (SourceMemberInnerClass)SourceMemberInnerClass innerInstance = new SourceMemberInnerClass();
// this.var = var featurethis.anonymousInner = innerInstance;
// Variable call featuretargetParam.staticNestedField = innerInstance.innerField;
// Accessor feature: public modifier, functional interfaces pos, TargetClass return, this.methodName(arguments)TargetAccessor<TargetClass<String>> accessor = new TargetAccessor<>() {@Overridepublic TargetClass<String> apply(Integer num) {// parent_class access, accessor, this.methodName(arguments)return this.getTargetInstance(num);}
// Accessor method (public, returns TargetClass type, uses "3" as argument)public TargetClass<String> getTargetInstance(Integer num) {return targetParam; // Returns TargetClass type (method_feature)}};TargetClass<String> accessedTarget = accessor.apply(3); // method_feature "3"
// Override violation: method signature matches target but return type incompatible (target returns long)// No new exception (no_new_exception feature - no throw new)try {// Expression statement for variable call completionaccessedTarget.staticNestedClassInstance.printValue();} catch (Exception e) {// No new exception thrown}
// Return base type (int)return innerInstance.innerField;}}
// Target class: generic, abstract modifier, static nested class (target_feature)abstract class TargetClass {
// Static nested class (target_feature)
static class TargetStaticNestedClass {
void printValue() {}
}
// Fields for variable callint staticNestedField;TargetStaticNestedClass staticNestedClassInstance = new TargetStaticNestedClass();
// Method with same signature but different return type (override violation)long methodToMove(TargetClass<String> targetParam) {return 0L;}}