package test;
import java.lang.reflect.Method;import java.util.Arrays;
public abstract record SourceRecord(String sourceData) {public static class StaticNested {public static final String STATIC_FIELD = "2";
public void method1(TargetRecord target) {System.out.println("Inner class method1: " + target.data());}
public void method2(TargetRecord target) {System.out.println("Inner class method2: " + target.data());}
public void method3(TargetRecord target) {System.out.println("Inner class method3: " + target.data());}}
public final Object abstractMethod(TargetRecord target) {class LocalInner {void process() {// SynchronizedStatement with ClassName.field = 2private class SyncProcessor {void execute() {synchronized (target) {if (StaticNested.STATIC_FIELD.equals("2")) {System.out.println("Matched static field: " + StaticNested.STATIC_FIELD);}}}}new SyncProcessor().execute();
// Array initialization with 3 inner class instance methodsStaticNested[] nestedArray = {new StaticNested(), new StaticNested(), new StaticNested()};for (int i = 0; i < nestedArray.length; i++) {if (i == 2) break; // Break statementswitch (i) {case 0:nestedArray[i].method1(target);break;case 1:nestedArray[i].method2(target);break;}}
// If statement + expression statementif (target.data() != null) {expressionStmt: System.out.println("Target data: " + target.data());}
// Variable call + access instance methodString targetData = target.data();TargetRecord.StaticNested targetNested = target.new StaticNested();targetNested.format(targetData);
// Used by reflectiontry {Method formatMethod = TargetRecord.StaticNested.class.getMethod("format", String.class);formatMethod.invoke(targetNested, targetData);} catch (Exception e) {e.printStackTrace();}}}
LocalInner local = new LocalInner();local.process();return target.data() + "_processed";}}
non-sealed record TargetRecord(String data) {public static class StaticNested {public void format(String input) {System.out.println("Formatted: " + input.toUpperCase());}}}
// Concrete implementation for abstract recordpublic final class ConcreteSourceRecord extends SourceRecord {public ConcreteSourceRecord(String sourceData) {super(sourceData);}}