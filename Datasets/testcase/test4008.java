package test;
import java.io.IOException;
interface TargetInterface {}
strictfp class SourceClass {private String sourceInstanceField = "sourceInstanceVal";static String sourceStaticField = "sourceStaticVal";
@DeprecatedTargetClass instanceMethod(TargetClass target) {protected String[] array = new String[1];array[0] = target.targetField;
Runnable lambda = () -> System.out.println(this.sourceInstanceField);
String varCall = target.getInnerInstance().innerField;String instanceFieldAccess = sourceInstanceField;String staticFieldAccess = sourceStaticField;
try {if (array[0].isEmpty()) {throw new IOException("Array element empty");}} catch (IOException e) {}
return target;}
void localInnerClassDemo() {class SourceLocalInner {}}
void anonymousInnerClassDemo() {Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println(sourceInstanceField);}};}}
protected class TargetClass implements TargetInterface {String targetField = "targetInstanceVal";
class TargetMemberInner {String innerField = "targetInnerVal";}
public TargetMemberInner getInnerInstance() {return new TargetMemberInner();}
@Overridepublic String toString() {return targetField;}}