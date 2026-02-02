package test;
import otherpackage.ExternalClass;import java.util.List;import java.util.ArrayList;
abstract class SourceClass<T extends Number> {protected int outerProtectedField = 42;private String instanceField = "source";
class MemberInner {class InnerRecursive {private Object methodToMove(TargetClass targetParam) {try {ExternalClass extObj = new ExternalClass();extObj.field = "test";
List<String> list = new TargetClass.MemberInner() {@OverrideList<String> createList() {return this.buildList(1);}}.createList();
int i = 0;while (i < 5) {switch (i) {case 2:continue;default:targetParam.variableCall();break;}i++;}
SuperClass superObj = new SuperClass();targetParam.instanceField = "updated";System.out.println(outerProtectedField);System.out.println(instanceField);
return new Object();} catch (Exception e) {return null;}}
private Object methodToMove(TargetClass targetParam, int value) {return null;}}}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}}
class SuperClass {public SuperClass() {}}
public class TargetClass {String instanceField;
class MemberInner {List<String> createList() {return new ArrayList<>();}
List<String> buildList(int count) {List<String> list = new ArrayList<>();for (int i = 0; i < count; i++) {list.add("item");}return list;}}
void variableCall() {}}
package otherpackage;
public class ExternalClass {public String field;}