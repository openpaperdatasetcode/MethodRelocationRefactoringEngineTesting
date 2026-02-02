package test;
import java.lang.reflect.Method;import java.util.List;
private class SourceClass {// Static nested class (source feature)public static class SourceStaticNested {}
// Local inner class (source feature)public void createLocalInner() {class SourceLocalInner {public void localMethod() {}}new SourceLocalInner().localMethod();}
// Member inner classpublic class SourceInner {// Source inner recursive class (method_position: source_inner_rec)public class SourceInnerRec {// Varargs method (private access modifier, returns Object)private Object varargsMethod(FinalTarget... targets) { // per_condition// Empty statement;
// ClassInstanceCreation (numbers=3, modifier=public)public FinalTarget target1 = new FinalTarget();public SourceStaticNested nested1 = new SourceStaticNested();public FinalTarget.TargetInner inner1 = targets[0].new TargetInner();
// Raw typeList rawList;
// Variable callfor (FinalTarget target : targets) {target.targetMethod();target.createAnonymousInner();}
// Depends on inner classinner1.innerMethod();
// Used by reflectiontry {Method method = FinalTarget.class.getMethod("targetMethod");method.invoke(target1);} catch (Exception e) {// No new exception}
return target1;}}}}
final class FinalTarget {public void targetMethod() {}
// Member inner classpublic class TargetInner {public void innerMethod() {}}
// Anonymous inner class (target_feature)public void createAnonymousInner() {Runnable anon = new Runnable() {@Overridepublic void run() {}};anon.run();}}
