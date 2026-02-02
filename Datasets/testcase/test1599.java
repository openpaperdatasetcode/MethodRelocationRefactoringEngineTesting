package test;
import java.util.function.Consumer;
interface Operation {Object execute();}
abstract class ParentSource {protected abstract Object process(TargetClass.Inner.Rec targetInnerRec);}
public abstract class SourceClass extends ParentSource {private String data = "source_data";
// Accessor method (getter)public String getData() {return data;}
// Accessor method (setter)public void setData(String data) {this.data = data;}
@Overrideprotected Object process(TargetClass.Inner.Rec targetInnerRec) {// Type declaration statementTargetClass.Inner inner = targetInnerRec.getOuter();
// Switch statementswitch (inner.getType()) {case 1:targetInnerRec.setValue("Type 1: " + getData());break;case 2:targetInnerRec.setValue("Type 2: " + getData());break;default:targetInnerRec.setValue("Default: " + getData());}
// Accessor in constructor parameter list (obj.m1().m2().m3())TargetClass temp = new TargetClass(inner.getRec().getValue().toString());
// Variable call - access target inner rec fieldString processed = targetInnerRec.getValue() + "_processed";targetInnerRec.setValue(processed);
// Call inner class's private instance method in functional interfaceConsumer<String> consumer = s -> inner.new Handler().handle(s);consumer.accept(processed);
return temp;}}
public abstract class TargetClass implements Operation {public class Inner {private int type;private Rec rec;
public Inner(int type) {this.type = type;this.rec = new Rec();}
public int getType() {return type;}
public Rec getRec() {return rec;}
private class Handler {void handle(String s) {System.out.println("Handled: " + s);}}
public class Rec {private String value;
public String getValue() {return value;}
public void setValue(String value) {this.value = value;}
public Inner getOuter() {return Inner.this;}}}
public TargetClass(String initData) {// Constructor using data from accessor chain}
@Overridepublic abstract Object execute();}