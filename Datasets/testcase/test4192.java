package test;
import java.util.List;import java.util.ArrayList;
record TargetRecord(String data) {List<String> getLocalData() {class TargetLocalInner {List<String> process() {return List.of(data);}}return new TargetLocalInner().process();}
private static List<String> staticMethod(String param) {return List.of(param);}
TargetRecord.Inner getInner() {return new Inner();}
class Inner {String innerField = data;}}
strictfp record SourceRecord<T>(T value) extends ParentClass {class SourceInner {protected Object instanceMethod1() {return value;}
protected Object instanceMethod1(int param) {return param;}
protected Object instanceMethod1(String param) {return param;}}
synchronized TargetRecord.Inner normalMethod(TargetRecord target) {SourceInner inner = new SourceInner();Object result = inner.instanceMethod1(target.data()) + inner.instanceMethod1(1) + inner.instanceMethod1("str");
TargetRecord.Inner targetInner = target.getInner();switch (targetInner.innerField.length()) {case 3:targetInner.innerField = "abc";break;case 4:targetInner.innerField = "abcd";break;default:targetInner.innerField = "default";}
{List<String> list = target.staticMethod(targetInner.innerField);}
return targetInner;}
{Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(value);}};}}
class ParentClass {}