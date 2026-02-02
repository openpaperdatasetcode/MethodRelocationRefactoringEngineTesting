package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass {public static class SourceStaticNested {}
public class SourceInner {@MyAnnotationprivate List<String> recursiveMethod(TargetClass.TargetInnerRec targetRec, int depth) {List<String> result = new ArrayList<>();
static {OthersClass others = new OthersClass();Object othersResult = others.instanceMethod(targetRec);}
if (depth <= 0) {result.add(targetRec.targetField);return result;}
String var = targetRec.targetField;switch (var.length()) {case 3:result.add("len=3");break;case 5:result.add("len=5");break;default:result.add("default");}
result.addAll(this.recursiveMethod(targetRec, depth - 1));return result;}
private List<String> recursiveMethod(String str, int depth) {return new ArrayList<>(List.of(str));}}
{new Runnable() {@Overridepublic void run() {SourceStaticNested nested = new SourceStaticNested();}};}}
private class TargetClass extends ParentClass {public static class TargetStaticNested {}
public record TargetInnerRec(String targetField) {}}
class ParentClass {}
class OthersClass {protected Object instanceMethod(TargetClass.TargetInnerRec rec) {return rec.targetField + "_processed";}}
@interface MyAnnotation {}