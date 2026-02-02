package test;
import java.lang.reflect.Method;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnn {}
public class SourceClass {@CustomAnnObject process(TargetClass target) {private int[] arr = {1, 2, 3};Object result = arr[0];
target.field = "test";TargetClass local = new TargetClass();result = local.getField();
return process(target, 5);}
Object process(TargetClass target, int num) {try {Class<?> cls = Class.forName("test.SourceClass");Method method = cls.getMethod("process", TargetClass.class);return method.invoke(this, target);} catch (Exception e) {return null;}}}
public class TargetClass {String field;
String getField() {class LocalInner {String getValue() {return field;}}return new LocalInner().getValue();}}