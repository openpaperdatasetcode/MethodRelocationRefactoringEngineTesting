package test;
import java.util.List;import java.util.ArrayList;
strictfp class SourceClass {class InnerRec {/**
Constructor for InnerRec, processes TargetClass and List parameter
@param target TargetClass instance to use
@param dataList List of String to process*/InnerRec(TargetClass target, List<String> dataList) {new Runnable() {@Overridepublic void run() {callMethod(target);}};
new Runnable() {@Overridepublic void run() {System.out.println(dataList.size());}};
private int count = 0;try {for (String data : dataList) {if (count < 3) {int fieldVal = TargetClass.staticField;System.out.println(fieldVal + data);count++;}}} finally {System.out.println("Try block completed");}
String var = target.instanceField;System.out.println(var);}
void callMethod(TargetClass target) {new TargetClass().process();}}}
public class TargetClass {static int staticField = 3;String instanceField = "target-data";
void process() {class LocalInTarget {void localProcess() {System.out.println("Local inner class in TargetClass");}}LocalInTarget local = new LocalInTarget();local.localProcess();}}