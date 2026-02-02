package test.source;
import java.io.IOException;import test.target.TargetClass;
// Source abstract class (non-sealed modifier, different package from target)abstract non-sealed class SourceClass {private int outerField = 10;
// Static nested class (source_class feature)static class StaticNestedClass {}
// Member inner class (method_position: source_inner)class SourceInnerClass {// Strictfp instance method to be refactored (method.type: instance, access_modifier: strictfp)strictfp void refactorMethod(TargetClass targetParam) throws IOException { // Contains target parameter (per_condition) + requires_throws// Uses outer this (method.features)int outerThisValue = SourceClass.this.outerField;// Constructor invocation (method.features)StaticNestedClass nestedObj = new StaticNestedClass();// Depends on inner class (method.features)SourceClass.AnonymousInner anonymousObj = new SourceClass.AnonymousInner();// Variable call (method.features)String varCall = nestedObj.toString() + outerThisValue;// Throw statement (method.features)if (targetParam == null) {throw new IOException("Target parameter is null");}}}
// Anonymous inner class (source_class feature)static class AnonymousInner {}}
package test.target;
import java.util.concurrent.Callable;
// Target abstract class (public modifier, different package from source)public abstract class TargetClass {// Anonymous inner class (target_class feature)Callable<Void> targetAnonymous = new Callable<>() {@Overridepublic Void call() throws Exception {return null;}};
// Target inner record to receive moved method (target class: target_inner_rec)record TargetInnerRec(int innerField) {}}