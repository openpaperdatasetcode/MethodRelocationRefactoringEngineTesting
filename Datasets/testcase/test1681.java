package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public class SourceClass {private TargetClass targetField;
class MemberInner {private static int staticMethod() {return 0;}}
{new Runnable() {};Runnable r = MemberInner::staticMethod;}
@MyAnnotationprivate List<String> instanceMethod() {targetField = new TargetClass();variableCall();return new ArrayList<>();}
private void variableCall() {}}
private class TargetClass {}
