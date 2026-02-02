package test;
import java.io.IOException;import java.lang.reflect.Field;
class ParentSourceClass {public Object processTarget(TargetClass target) {return null;}
protected static String parentStaticMethod(TargetClass target) {return target.targetField + "_parentStatic";}}
strictfp class SourceClass extends ParentSourceClass {/**
Overriding method to process TargetClass with multiple features
@param target TargetClass instance
@return Processed object result*/@Overridepublic Object processTarget(TargetClass target) {super(); // Super constuctor invocation// Variable call + contains target field (per_condition)target.toString();
// VariableDeclarationStatement with 2 obj.field (private modifier, pos: source)private String field1 = target.targetField;private String field2 = target.staticNested.field;
// Depends on static fieldString combined = field1 + field2 + TargetClass.STATIC_FIELD;
// Try statement with IOExceptiontry {if (combined.isEmpty()) {throw new IOException("Empty combined field");}} catch (IOException e) {// No new exceptioncombined = "default_combined";}
// Used by reflectiontry {Field targetField = TargetClass.class.getField("targetField");combined += targetField.get(target);} catch (Exception e) {// No new exception}
// Local inner class (source_feature)class LocalProcessor {public String process() {return combined.toUpperCase();}}combined = new LocalProcessor().process();
// Anonymous inner class (source_feature)Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Processed: " + combined);}};anon.run();
// Ternary operators with parent_class static method call (OuterClass.InnerClass.methodName())String result = combined.length() > 10? ParentSourceClass.parentStaticMethod(target): combined;
return result;}}
class TargetClass {public String targetField = "targetValue"; // Source contains target's field (per_condition)public static final String STATIC_FIELD = "_static"; // Depends on static field
// Static nested class (target_feature)public static class StaticNested {public String field = "nestedField"; // obj.field for VariableDeclarationStatement}
public StaticNested staticNested = new StaticNested();}