package test;
import java.util.List;
public class SourceClass {@MethodAnnotation(accessorVal1 = super.getSourceAccessor1(),accessorVal2 = super.getSourceAccessor2())private Object varargsMethod(TargetClass... targetParams) {// Raw type usage of target's static nested classTargetClass.StaticNested rawNested = new TargetClass.StaticNested();
// Variable call to target's inner recursive class methodfor (TargetClass target : targetParams) {target.getInnerRec().variableCall();}
// Access source class public accessors (matches accessor feature count=2)List<String> accessorRes1 = getSourceAccessor1();List<String> accessorRes2 = getSourceAccessor2();
// Return statement (raw type instance)return rawNested;}
// Public accessor methods of source class (required by accessor feature)public List<String> getSourceAccessor1() {return List.of("accessor1_data");}
public List<String> getSourceAccessor2() {return List.of("accessor2_data");}}
// Target class (default modifier) with static nested classclass TargetClass {private TargetInnerRec innerRec = new TargetInnerRec();
// Target's static nested class (matches target_feature)public static class StaticNested<T> {}
// Target's inner recursive class (matches target_class=target_inner_rec)public static class TargetInnerRec {public void variableCall() {}}
// Getter for inner recursive class instancepublic TargetInnerRec getInnerRec() {return innerRec;}}
// Annotation for method attribute (pos=the attribute values of annotations)@interface MethodAnnotation {List<String> accessorVal1();List<String> accessorVal2();}