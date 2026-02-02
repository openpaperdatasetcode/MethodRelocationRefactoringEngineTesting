package test;
import java.util.function.Consumer;
public class SourceClass {private String outerPrivateField = "private_value";
public class MemberInner {}
{Runnable anonymous = new Runnable() {@Overridepublic void run() {int val = new SourceClass().instanceMethod(new TargetClass());}};}
static {TargetClass target = new TargetClass();Consumer<TargetClass> consumer = target::getInnerClass;}
/**
Method Javadoc: Instance method returning base type
Handles NullPointerException and accesses outer private field
@param targetParam Target class parameter (per condition)
@return int Base type return value*/int instanceMethod(TargetClass targetParam) {if (targetParam == null) {throw new NullPointerException("Target parameter must not be null");}
int sum = 0;for (int i = 0; i < 3; i++) {targetParam.innerClass.variableCall();sum += i;}
String privateVal = outerPrivateField;targetParam.innerClass.process(privateVal);
return sum;}}
/**
TargetClass Javadoc (matches target_feature: javadoc)
Final normal class with member inner class*/final class TargetClass {public TargetInner innerClass = new TargetInner();
/**
Member inner class of TargetClass*/public class TargetInner {public void variableCall() {}
public void process(String data) {System.out.println(data);}}
public TargetInner getInnerClass() {return innerClass;}}