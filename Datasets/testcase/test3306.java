package test;
public record SourceRecord(String data) {static int staticField = 100;
class Inner1 {}
class Inner2 {class InnerRec {protected Object getData() {TargetRecord target = new TargetRecord(1);access_instance_field(target);
class LocalType {}new LocalType();
for (int i = 0; i < 1; i++) {if (target.this.field != 1) break;variableCall(target);}
return SourceRecord.staticField + target.staticField;}
protected Object getData(String suffix) {TargetRecord target = new TargetRecord(1);variableCall(target);return target.field + suffix;}
private void variableCall(TargetRecord target) {target.innerClass.doTask();}
private void access_instance_field(TargetRecord target) {System.out.println(target.field);}}}}
public record TargetRecord(int field) {public static int staticField = 50;
class TargetInner {void doTask() {}}
private final TargetInner innerClass = new TargetInner();
{new Runnable() {@Overridepublic void run() {}}.run();}
protected Object getData() {return field;}
protected Object getData(String suffix) {return field + suffix;}}