package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessLog {String value() default "Processed by abstract source class";}
abstract class SourceClass permits SourceConcreteA, SourceConcreteB {protected String protectedField = "AbstractSourceProtectedData";
public class SourceMemberInner {public static int calculateTargetValue(TargetClass target) {try {return target.getLocalProcessor().compute(target.getTargetField().length());} catch (Exception e) {System.err.println("Inner class calculation failed: " + e.getMessage());return -1;}}}
@ProcessLog("Final overriding method in abstract source")public final int processTarget(TargetClass target) {; // Empty statementint result = 0;
new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class: Using protected field - " + protectedField);}}.run();
try {Method targetMethod = TargetClass.class.getMethod("getTargetField");String targetFieldVal = (String) targetMethod.invoke(target);result = SourceMemberInner.calculateTargetValue(target) + targetFieldVal.length();} catch (Exception e) {System.err.println("Reflection access failed: " + e.getMessage());result = -2;}
return result;}
public abstract void abstractMethod();}
class SourceConcreteA extends SourceClass {@Overridepublic void abstractMethod() {System.out.println("Concrete A implementation: " + protectedField);}}
class SourceConcreteB extends SourceClass {@Overridepublic void abstractMethod() {System.out.println("Concrete B implementation: " + protectedField);}}
class TargetClass {private String targetField;
public TargetClass(String targetField) {this.targetField = targetField;}
public LocalProcessor getLocalProcessor() {class LocalProcessor {public int compute(int input) {return input * 2;}}return new LocalProcessor();}
public String getTargetField() {return targetField;}
public void setTargetField(String targetField) {this.targetField = targetField;}
interface LocalProcessor {int compute(int input);}}