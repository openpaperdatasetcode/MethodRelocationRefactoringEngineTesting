package test.refactoring;
import java.util.ArrayList;import java.util.List;
protected class SourceClass {private TargetClass targetField = new TargetClass();
class SourceInner {private Object innerVar;
public List<String> process() {List<String> result = new ArrayList<>();
new TargetClass().createLocalInner().processInner();
for (int i = 0; i < 4; i++) {if (i % 2 == 0) {continue;}this.innerVar = targetField.getInner().m1().m2().m3();result.add(innerVar.toString());}
variableCall(targetField.getInner(), result);return result;}
private void variableCall(TargetClass.TargetInner inner, List<String> list) {list.add(super.toString());inner.addData("processed");}}
{Runnable anon1 = new Runnable() {@Overridepublic void run() {new SourceInner().process();}};
Runnable anon2 = new Runnable() {@Overridepublic void run() {Object callResult = targetField.getInner().callOverload(1);if (callResult == null) {callResult = targetField.getInner().callOverload("fallback");}}};}}
class TargetClass {private TargetInner inner = new TargetInner();
public TargetInner getInner() {return inner;}
public TargetClass createLocalInner() {class LocalInner {void processInner() {inner.addData("localInnerData");}}new LocalInner().processInner();return this;}
class TargetInner {private List<String> dataList = new ArrayList<>();
public TargetInner m1() {return this;}
public TargetInner m2() {return this;}
public Object m3() {return dataList.size();}
public Object callOverload(int num) {return num * 2;}
public Object callOverload(String str) {return str.toUpperCase();}
public void addData(String data) {dataList.add(data);}}}