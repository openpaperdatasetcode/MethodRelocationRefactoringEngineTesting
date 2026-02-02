package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Field;import java.io.IOException;
class SourceClass {private String outerField = "source_outer";
class MemberInner1 {}class MemberInner2 {}
protected List<String> processTarget(TargetClass target) throws IOException {List<String> result = new ArrayList<>();
String [] targetArr = new String [2];targetArr [0] = target.targetField1;targetArr [1] = target.targetField2;result.add ("Array [0]:" + targetArr [0]);result.add ("Array [1]:" + targetArr [1]);
abstract String abstractArr1 = targetArr [0];abstract String abstractArr2 = targetArr [1];result.add ("Abstract Array Val1:" + abstractArr1);result.add ("Abstract Array Val2:" + abstractArr2);
variableCall (target);result.add ("Target Field3:" + target.targetField3);result.add ("Inner Class Res:" + target.createLocalInner ().process ());
try {Field privateField = TargetClass.class.getDeclaredField ("targetField4");privateField.setAccessible (true);result.add ("Reflect Private Field:" + privateField.get (target));} catch (Exception e) {throw new IOException ("Reflection failed", e);}
result.add ("Outer This Field:" + SourceClass.this.outerField);
if (target.targetField1.length () > 5) {result.add ("Field1 exceeds bound:" + target.targetField1);}
return result;}
private void variableCall(TargetClass target) {target.targetField3 += "_updated";target.targetField2 = target.targetField2.toUpperCase();}}
private class TargetClass {String targetField1 = "field1";String targetField2 = "field2";String targetField3 = "field3";private String targetField4 = "private_field4";
TargetClass.LocalInner createLocalInner () {
class LocalInner {String process () {return targetField1 + "-" + targetField2;}}return new LocalInner ();}}