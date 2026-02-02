package test;
import other.DiffPackageClass;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
/**
Javadoc for TargetInterface
Defines interface with anonymous inner class support*/public interface TargetInterface {String field1 = "targetField1";int field2 = 100;boolean field3 = true;
Runnable anonymousInner = new Runnable() {@Overridepublic void run() {System.out.println(field1 + field2 + field3);}};}
interface SourceInterface {class SourceInner {class SourceRecursiveInner {@MethodAnnotationpublic synchronized Object varargsMethod(TargetInterface target, Object... args) {try {DiffPackageClass diffObj = new DiffPackageClass();private class LocalType1 {}private class LocalType2 {}private class LocalType3 {}
diffObj.processTypes(new LocalType1(), new LocalType2(), new LocalType3(),target.field1, target.field2, target.field3);
int intResult = strictfpOverloadMethod(args);String strResult = strictfpOverloadMethod(args, intResult);variableCall(target, intResult, strResult);
return strResult;} catch (Exception e) {return null;}}
private strictfp int strictfpOverloadMethod(Object... args) {return args.length;}
private strictfp String strictfpOverloadMethod(Object[] args, int param) {return args.length + "_" + param;}
private void variableCall(TargetInterface target, int num, String str) {System.out.println(target.field1 + num + str);TargetInterface.anonymousInner.run();}
void methodWithLocalInner() {class LocalInner {}}}}}
package other;
import test.TargetInterface;
public class DiffPackageClass {public void processTypes(Object type1, Object type2, Object type3,String field1, int field2, boolean field3) {}}