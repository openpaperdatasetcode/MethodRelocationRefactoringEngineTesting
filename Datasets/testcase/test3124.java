package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
// Target interface (for target's implements feature)interface TargetSuperType {TargetClass superTypeMethod(int param);}
// Target class (public modifier + implements + anonymous inner class)public class TargetClass implements TargetSuperType {String value;
public TargetClass() {// Target feature: anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}
// Call_method: protected instance method (superTypeReference.methodName(arguments))@Overrideprotected TargetClass superTypeMethod(int param) {this.value += "_" + param;return this;}}
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnn {}
// Source class (default modifier + two local inner classes)class SourceClass {@MethodAnn // has_annotationTargetClass methodToMove(TargetClass... targets) {if (targets == null || targets.length == 0) {return new TargetClass();}
// Variable callTargetClass target = targets[0];String var = target.value;
// Constructor invocationTargetClass newTarget = new TargetClass();
// For statementfor (TargetClass t : targets) {if (t.value == null) {t.value = "default";}}
// While statement with call_method (superTypeReference.methodName(arguments))int count = 0;while (count < 2) {target = ((TargetSuperType) target).superTypeMethod(count);count++;}
// NullPointerException handlingtry {var = target.value.trim();} catch (NullPointerException e) {// No new exception thrownvar = "null_safe";}
// Source feature: first local inner classclass LocalInner1 {TargetClass process() {target.value = var + "_local1";return target;}}
// Source feature: second local inner classclass LocalInner2 {TargetClass enhance(TargetClass t) {t.value += "_local2";return t;}}
return new LocalInner2().enhance(new LocalInner1().process());}}