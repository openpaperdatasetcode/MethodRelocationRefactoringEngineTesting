package same.pkg;
import java.io.IOException;import java.util.function.Function;
sealed record Source(String sourceField) permits SubSource {class SourceInner {Object instanceMethod(Target<Integer> targetParam) throws IOException {// Varargs method in property assignmentFunction<Object[], Target<Integer>> varargsFunc = (params) -> targetParam.process(params);Target<Integer> targetProp = varargsFunc.apply(new Object[]{1});
// Super constructor invocation in anonymous inner classRunnable anon = new Runnable() {{super();}@Overridepublic void run() {}};
// Variable callTarget.StaticNested nested = new Target.StaticNested();Target.Inner.Rec innerRec = targetParam.new Inner().new Rec();
// With boundsClass<? extends Number> boundedType = targetParam.value().getClass();
// Access instance fieldString fieldVal = sourceField;int targetField = targetParam.value();
// Requires throwsinnerRec.throwException();
return innerRec;}
protected Target<Integer> process(Object... params) {return new Target<>((Integer) params[0]);}}}
record SubSource(String sourceField) implements Source {}
strictfp record Target<T extends Number>(T value) {static class StaticNested {}
class Inner {record Rec() {void throwException() throws IOException {}}}}