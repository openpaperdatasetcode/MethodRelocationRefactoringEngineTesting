package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
interface MyInterface {void parentMethod(TargetClass.TargetInner inner);}
private class SourceClass implements MyInterface {private TargetClass.TargetInner outerPrivateInner = new TargetClass.TargetInner("privateInnerData");
static class StaticNested {void useTargetInner(TargetClass.TargetInner inner) {System.out.println(inner.getData());}}
@Overridepublic void parentMethod(TargetClass.TargetInner inner) {throw new RuntimeException("Override violation: Method not implemented");}
public int instanceMethod(TargetClass.TargetInner targetInner) {try {Method reflectMethod = SourceClass.class.getMethod("instanceMethod", TargetClass.TargetInner.class);reflectMethod.invoke(this, targetInner);} catch (Exception e) {e.printStackTrace();}
TargetClass.TargetInner varCall = targetInner;String innerData = varCall.getData();int dataLength = innerData.length();
if (dataLength > 5) {StaticNested nested = new StaticNested();nested.useTargetInner(outerPrivateInner);}
int switchCount = 0;switchCount += switch (innerData.charAt(0)) {case 'a' -> 1;default -> 0;};switchCount += switch (dataLength) {case 3 -> 1;default -> 0;};
List<String> doWhileResult = new ArrayList<>();do {doWhileResult = callParentMethod(varCall);} while (doWhileResult.isEmpty());
int whileResult = 0;while (whileResult < 3) {whileResult = new SourceClass().callSourceMethod(varCall);}
Runnable anon = new Runnable() {@Overridepublic void run() {StaticNested newNested = new StaticNested();newNested.useTargetInner(outerPrivateInner);}};anon.run();
return dataLength + switchCount;}
private List<String> callParentMethod(TargetClass.TargetInner inner) {List<String> result = new ArrayList<>();result.add(inner.getData());return result;}
public final int callSourceMethod(TargetClass.TargetInner inner) {return inner.getData().length();}}
non-sealed class TargetClass {static class TargetInner {private String data;
public TargetInner(String data) {this.data = data;}
public String getData() {return this.data;}
public int getLength() {return this.data.length();}}}