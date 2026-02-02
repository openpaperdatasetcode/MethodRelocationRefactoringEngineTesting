package test.refactor.movemethod;
import java.util.function.Function;
// Interface for source_class implements featureinterface Processable<T> {T process(TargetClass<T> target, T... args);}
// Source class (public, same package, type parameter + implements + local inner + static nested class)public class SourceClass<T> implements Processable<T> {// Feature: static nested classpublic static class SourceStaticNested {public static U convert(Object obj) {
return (U) obj;
}
}
/**
Method Javadoc (method javadoc feature)
@param targetParam Target class parameter (per_condition)
@param args Varargs parameters
@return TargetClass instance (TargetClass Type return)*/@Overridepublic TargetClass<T> process(TargetClass<T> targetParam, T... args) {// Protected ExpressionMethodReference (numbers:1, modifier:protected, exp:ExpressionMethodReference)protected Function<T, String> exprRef = this::convertToStr;
// Local inner class (source feature)class LocalInnerClass {public void validateArgs() {for (int i = 0; i < args.length; i++) {if (args[i] == null) {continue; // continue statement}// Variable call: use static nested class + target inner classTargetClass.TargetStaticNested.processArg(args[i]);String processed = exprRef.apply(args[i]);targetParam.addData(processed);}}}
try {new LocalInnerClass().validateArgs();// Variable call: target inner class usage (target_inner)TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();nested.updateTarget(targetParam);} catch (IllegalArgumentException e) {// no_new_exception: rethrow without wrappingthrow e;}
return targetParam;}
// Helper method for ExpressionMethodReferenceprotected String convertToStr(T data) {return data.toString() + "_processed";}}
// Target class (default modifier, target_feature: static nested class)class TargetClass<T> {private T data;
// Target_inner: static nested class (target_feature)public static class TargetStaticNested {public static void processArg(U arg) {
System.out.println("Processed arg: " + arg);
}
public void updateTarget(TargetClass target) {
target.data = (U) (target.data + "_updated");
}
}
public void addData(String processedData) {this.data = (T) (this.data + "_" + processedData);}
public T getData() {return data;}}
// Test classpublic class MoveMethodTest5245 {public static void main(String[] args) {SourceClass<String> source = new SourceClass<>();TargetClass<String> target = new TargetClass<>();TargetClass<String> result = source.process(target, "arg1", "arg2", null, "arg3");System.out.println("Refactored result: " + result.getData());}}