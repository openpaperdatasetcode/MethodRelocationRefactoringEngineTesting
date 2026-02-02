package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Transactional {}
class SuperSource {protected int protectedValue = 100;}
class SubTarget extends Target {@Overrideprotected void processInner(Target.InnerClass.InnerRec rec) {super.processInner(rec);rec.setValue(protectedValue);}}
private class Source {class MemberInner {int data;}
@Transactionalpublic final <T extends Number> int calculate(Target.InnerClass.InnerRec targetRec) {MemberInner inner = new MemberInner();inner.data = protectedValue;
Runnable anon = new Runnable() {@Overridepublic void run() {targetRec.printValue();}};anon.run();
switch (targetRec.getType()) {case "INT":inner.data += (Integer) targetRec.getValue();break;case "DOUBLE":inner.data += (Double) targetRec.getValue();break;default:inner.data = 0;}
new SubTarget().processInner(targetRec);return inner.data;}
public final String calculate(String input) {return input;}}
strictfp class Target {protected int protectedValue = 50;
class InnerClass {class InnerRec {private Object value;private String type;
void setValue(Object val) {this.value = val;class LocalChecker {void validate() {if (val instanceof Integer) type = "INT";else if (val instanceof Double) type = "DOUBLE";}}new LocalChecker().validate();}
Object getValue() {return value;}
String getType() {return type;}
void printValue() {System.out.println(value);}}}
protected void processInner(InnerClass.InnerRec rec) {}}