package test;
import java.util.ArrayList;import java.util.List;import java.util.stream.Collectors;
public class SourceClass {// First anonymous inner class{new Runnable() {@Overridepublic void run() {try {List<String> result = varargsMethod(new TargetClass(), new TargetClass());System.out.println("Anonymous 1 result: " + result);} catch (NullPointerException e) {e.printStackTrace();}}}.run();}
// Second anonymous inner class{new Thread() {@Overridepublic void run() {TargetClass target = new TargetClass();System.out.println("Anonymous 2 static field: " + target.staticField);}}.start();}
List<String> varargsMethod(TargetClass... targets) {// NullPointerException checkif (targets == null) {throw new NullPointerException("Targets cannot be null");}
// Raw typeList rawList = new ArrayList();
// Instance code blocks with parent class methods{ParentClass parent = new TargetClass();rawList.add(TargetClass.StaticNested.parentMethod1(parent));rawList.add(TargetClass.StaticNested.parentMethod2(parent, 10));rawList.add(TargetClass.StaticNested.parentMethod3(parent, "test"));}
List<String> result = new ArrayList<>();// Enhanced for loop for variable call and access instance fieldfor (TargetClass target : targets) {result.add("Instance field: " + target.instanceField);rawList.add(target.instanceField);}
// Collection operations with inner class static method referenceresult.addAll(rawList.stream().map(TargetClass.StaticNested::convertToString).collect(Collectors.toList()));
return result;}}
/**
Target class with javadoc
Contains static nested class and fields used by source*/class TargetClass extends ParentClass {int instanceField = 20;static int staticField = 5;
/**
Static nested class providing utility methods*/public static class StaticNested {// Static method for method referencepublic static String convertToString(Object obj) {return obj.toString();}
// Parent class instance methods (3 methods)public static Object parentMethod1(ParentClass parent) {return parent.toString();}
public static Object parentMethod2(ParentClass parent, int num) {return parent.hashCode() + num;}
public static Object parentMethod3(ParentClass parent, String str) {return parent.getClass().getSimpleName() + ":" + str;}}}
class ParentClass {}