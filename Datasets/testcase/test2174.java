package test;
import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
strictfp class SourceClass {@MyAnnotationpublic static int staticMethod(TargetClass target) {// Local inner classclass LocalInner {}new LocalInner();
// Anonymous inner classnew Object() {};
// Access target fieldint targetField = target.targetField;
// For statementint sum = 0;for (int i = 0; i < targetField; i++) {sum += i;}
// Raw typeArrayList rawList = new ArrayList();rawList.add(target.new MemberInner());
// Call method in exception throwing statementstry {if (sum < 0) {throw new Exception(new SourceClass().instanceMethod());}} catch (Exception e) {}
return sum;}
public String instanceMethod() {return "instanceMethod";}}
protected class TargetClass {int targetField;
class MemberInner {}}