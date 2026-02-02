package test;
// Target enum class (protected modifier + static nested class)protected enum TargetEnum {INSTANCE;
// Target feature: static nested classpublic static class TargetStaticNested {}}
// Source enum class (public modifier + type parameter + permits)public enum SourceEnum<T extends TargetEnum> permits SourceEnum.SubEnum {MAIN;
// Overloading method 1public TargetEnum methodToMove(T target) {// Super constructor invocation (enum superclass)super.toString();
// Variable callTargetEnum.TargetStaticNested nested = new TargetEnum.TargetStaticNested();
// With_boundsclass BoundedType {}
BoundedType<TargetEnum> bounded = new BoundedType<>();
// Depends_on_inner_classnested.toString();
// No new exception thrownreturn target;}
// Overloading method 2 (different parameter)public TargetEnum methodToMove(T target, String arg) {// Variable callString combined = target.name() + "_" + arg;
// With_boundsclass BoundedType {}
BoundedType<TargetEnum> bounded = new BoundedType<>();
// Depends_on_inner_classTargetEnum.TargetStaticNested nested = new TargetEnum.TargetStaticNested();
// No new exception thrownreturn target;}
// Permitted subclass for "permits" featureenum SubEnum extends SourceEnum<TargetEnum> {SUB_INSTANCE;}}
