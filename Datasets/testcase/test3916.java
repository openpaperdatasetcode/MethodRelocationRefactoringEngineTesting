package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
// Parent interface for source interface to implementinterface ParentInterface {List<String> process(TargetInterface target) throws IOException;}
// Source interface: default modifier, implements ParentInterface, with two local inner classesinterface SourceInterface extends ParentInterface {// Static field for SynchronizedStatement target_featurestatic final int STATIC_FIELD = 3;
// Overriding method (method type: overriding) - refactored method@Overrideprotected List<String> process(TargetInterface target) throws IOException { // requires_throws featureList<String> result = new ArrayList<>();// Uses outer this (uses_outer_this feature) - interface instance referenceParentInterface outerInterface = this;
// 1st Local inner class (source feature)class LocalInner1 {void addData(List<String> list) {list.add("LocalInner1-data");}}
// 2nd Local inner class (source feature)class LocalInner2 {// Final recursive method (method_feature)public final void recursiveAdd(List<String> list, int depth) {if (depth <= 0) return; // Base caselist.add("Recursion-depth-" + depth);recursiveAdd(list, depth - 1); // Recursive call}}
// SynchronizedStatement (static modifier, pos: source) - target_featuresynchronized (SourceInterface.class) {if (STATIC_FIELD == 3) {variableCall(); // Variable call feature}}
// Synchronized statement (non-static)synchronized (result) {// Depends_on_inner_class featureLocalInner1 inner1 = new LocalInner1();inner1.addData(result);
// Instance code blocks (method_feature pos){LocalInner2 inner2 = new LocalInner2();inner2.recursiveAdd(result, 1); // method_feature "1"}
// Expression statement (method feature)String targetData = target.getTargetData();result.add(targetData);}
return result;}
// Variable call target methodprivate static void variableCall() {}}
// Target interface: sealed modifier, with local inner class (target feature)sealed interface TargetInterface permits TargetImpl {String getTargetData();
default void processList(List<String> list) {// Local inner class (target feature)class TargetLocalInner {void formatList(List<String> l) {for (int i = 0; i < l.size(); i++) {l.set(i, "Formatted-" + l.get(i));}}}new TargetLocalInner().formatList(list);}}
// Permitted implementation for sealed TargetInterfacefinal class TargetImpl implements TargetInterface {@Overridepublic String getTargetData() {return "Target-impl-data";}}