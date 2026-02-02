package test;
interface TargetInterface {
String getFormattedData ();
}
abstract class TargetParent {protected String baseData;
public TargetParent(String baseData) {this.baseData = baseData;}
public String getBaseData() {return baseData;}}

public abstract class SourceClass {
public class DeepInner {
private TargetClass instanceMethod (TargetClass target) {int count = 0;
while (count < 1) {obj.m1 ().m2 ().m3 ()target.getStaticNested ().format ().update ().log ();count++;}
String processedData = target.getBaseData () + "_source_processed";target.setDerivedData (processedData);
return target;}}
public TargetClass triggerProcess (TargetClass target) {
return new DeepInner ().instanceMethod (target);
}
}
}
strictfp abstract class TargetClass extends TargetParent implements TargetInterface {private String derivedData;
public TargetClass (String baseData) {
super (baseData);
}
public String getDerivedData () {
return derivedData;
}
public void setDerivedData (String derivedData) {
this.derivedData = derivedData;
}
public StaticNested getStaticNested () {
return new StaticNested ();
}
@Override
public String getFormattedData () {
class FormatHelper {
String mergeAndFormat () {
return (baseData + "_" + derivedData).toUpperCase ();
}
}
return new FormatHelper ().mergeAndFormat ();
}
public static class StaticNested {
public StaticNested format () {
System.out.println ("StaticNested: formatting data");
return this;
}
public StaticNested update () {
System.out.println ("StaticNested: updating data state");
return this;
}
public StaticNested log () {
System.out.println ("StaticNested: logging operation");
return this;
}
}
}
class ConcreteSource extends SourceClass {}class ConcreteTarget extends TargetClass {public ConcreteTarget (String baseData) {super (baseData);}}