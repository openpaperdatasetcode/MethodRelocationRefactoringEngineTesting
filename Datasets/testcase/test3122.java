package test;
import java.lang.reflect.Constructor;import java.lang.reflect.Field;
protected class TargetClass {String targetField;
public TargetClass() {// Target feature: anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}}
strictfp class SourceClass {// Source feature: static nested classstatic class SourceStaticNested {}
// Source feature: anonymous inner classprivate final Runnable anonymous = new Runnable() {@Overridepublic void run() {new SourceClass().methodToMove(new TargetClass());}};
protected TargetClass methodToMove(TargetClass target) {// Super constructor invocationsuper();
// Variable callString var = target.targetField;SourceStaticNested nested = new SourceStaticNested();
// Type declaration statementclass LocalType {TargetClass process(TargetClass t) {t.targetField = t.targetField + "_processed";return t;}}LocalType local = new LocalType();
// Used_by_reflection (first occurrence)try {Constructor<TargetClass> constructor = TargetClass.class.getDeclaredConstructor();TargetClass reflectedTarget = constructor.newInstance();var = reflectedTarget.targetField;} catch (Exception e) {// No new exception thrown}
// Used_by_reflection (second occurrence)try {Field field = TargetClass.class.getDeclaredField("targetField");field.setAccessible(true);field.set(target, var + "_reflected");} catch (Exception e) {// No new exception thrown}
return local.process(target);}}