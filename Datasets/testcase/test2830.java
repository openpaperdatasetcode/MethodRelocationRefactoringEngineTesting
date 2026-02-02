package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
class SourceClass {static class StaticNestedSource {}
class SourceInner {class SourceInnerRec {@MethodAnnotationprivate List<String> methodToMove(TargetClass target) {List<String> result = new ArrayList<>();TargetClass.StaticNestedTarget staticNested = TargetClass.STATIC_NESTED;
try {if (staticNested.field == 2) {variableCall(target, result);}} catch (Exception e) {// Requires_try_catch handling}
// Anonymous inner classnew Runnable() {@Overridepublic void run() {switch (staticNested.field) {case 2:TargetClass.MemberInner inner = target.new MemberInner();TargetClass resultTarget = inner.callOverridden(target);result.add(resultTarget.toString());break;default:break;}}}.run();
return result;}
private void variableCall(TargetClass target, List<String> list) {TargetClass.MemberInner inner = target.new MemberInner();list.add(String.valueOf(inner.getValue()));}}}}
protected class TargetClass {public static final StaticNestedTarget STATIC_NESTED = new StaticNestedTarget();
public static class StaticNestedTarget {int field = 2;}
public class MemberInner {protected TargetClass callOverridden(TargetClass target) {return target;}
int getValue() {return STATIC_NESTED.field;}}}
class SubTarget extends TargetClass {@Overridepublic class MemberInner {@Overrideprotected TargetClass callOverridden(TargetClass target) {return new TargetClass();}}}