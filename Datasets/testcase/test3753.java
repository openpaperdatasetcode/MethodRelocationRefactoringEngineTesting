package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
private class SourceClass<T> {public static class SourceStaticNested<T> {}
public class SourceInner extends ParentInnerClass {@MyAnnotation@Overridepublic List<String> overrideMethod(TargetClass<T>.TargetInner targetInner) throws IOException {super();List<String> result = new ArrayList<>();
for (int i = 0; i < 3; i++) {privateInstanceMethod();String targetField = targetInner.targetField;result.add(targetField);}
return result;}
private void privateInstanceMethod() {super.toString();}}
public class MemberInner {}}
abstract class ParentInnerClass {public ParentInnerClass() {}public abstract List<String> overrideMethod(Object obj) throws IOException;}
public class TargetClass<T> {public class TargetInner {public String targetField;}
{new Runnable() {@Overridepublic void run() {}};}}
@interface MyAnnotation {}
