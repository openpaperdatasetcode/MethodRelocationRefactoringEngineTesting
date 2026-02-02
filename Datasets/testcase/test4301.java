package same.pkg;
import java.io.IOException;import java.util.function.Function;
// Source enum with type parameter, static nested class, member inner classenum SourceEnum<T> implements TargetEnum.InnerInterface {INSTANCE;
// Contains target's field (per condition)private TargetEnum targetField = TargetEnum.MAIN;
// Overriding method (implements method from target's inner interface)@Overridepublic TargetEnum overridingMethod(T targetParam) throws IOException {variableCall();
// 3 public TypeMethodReference expressionspublic Function<String, TargetEnum> ref1 = TargetEnum::valueOf;public Function<TargetEnum, String> ref2 = TargetEnum::name;public Function<TargetEnum, Integer> ref3 = TargetEnum::ordinal;
// Static method call (target's static method) in constructor parameter listSourceStaticNested nested = new SourceStaticNested(TargetEnum.staticMethod(targetField));
// Use outer this (SourceEnum instance)if (SourceEnum.this == INSTANCE) {// Call others_class constructor & method in if/elseOthersClass others = new OthersClass();others.othersMethod();} else {new OthersClass().othersMethod();}
return targetField;}
private void variableCall() {String localVar = targetField.getTargetField();}
// Source's static nested classstatic class SourceStaticNested {public SourceStaticNested(Object param) {}}
// Source's member inner classclass SourceMemberInner {void innerMethod() {}}}
// Target enum (final modifier) with local inner classfinal enum TargetEnum {MAIN, SECONDARY;
private String targetField = "targetValue";
// Target's static method for source's static method callpublic static Object staticMethod(TargetEnum target) {return target.targetField;}
String getTargetField() {return targetField;}
// Target's method with local inner classvoid methodWithLocalClass() {class TargetLocalInner {void localMethod() {}}new TargetLocalInner().localMethod();}
// Inner interface for source to implement (enables overriding)interface InnerInterface {TargetEnum overridingMethod<T>(T param) throws IOException;}}
// Others class for call_methodclass OthersClass {// Others_class constructor (default modifier)OthersClass() {}
// Others_class method (default modifier)void othersMethod() {}}