package test;
import java.util.ArrayList;import java.util.List;
interface SourceInterface extends java.io.Serializable {static class SourceStaticNested {}
class SourceInner {protected void varargsMethod(TargetInterface... targets) {do {for (TargetInterface target : targets) {TargetInterface.TargetStaticNested nested = target.nestedField;Object obj = nested.publicInstanceMethod(target);
class LocalType {}LocalType typeDecl = new LocalType();
List rawList = new ArrayList();rawList.add(nested.staticField);
switch (target.field) {case "value1":rawList.add("case1");break;case "value2":rawList.add("case2");break;default:rawList.add("default");}}} while (targets.length > 0);}}}
abstract interface TargetInterface {String field = "defaultValue";TargetStaticNested nestedField = new TargetStaticNested();
static class TargetStaticNested {String staticField = "targetStaticData";
public Object publicInstanceMethod(TargetInterface target) {return target.field + staticField;}}}