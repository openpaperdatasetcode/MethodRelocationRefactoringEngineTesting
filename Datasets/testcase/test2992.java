package sourcepkg;
import java.io.IOException;import java.util.List;import java.util.ArrayList;import targetpkg.TargetClass;
// Source interface (for source's implements feature)interface SourceInterface {Object processTarget(TargetClass target, List<String> data) throws IOException;}
// Public source class (implements + local inner class + static nested class)public class SourceClass implements SourceInterface {// Static nested class (source feature)public static class SourceStaticNested {}
// Member inner class (method_position: source_inner)protected class SourceInner {// Instance method (protected access modifier, returns Object, method type parameter: List)public Object innerMethod(TargetClass target, List<String> data) throws IOException { // per_condition + method types parameter:Listif (target == null) {throw new IllegalArgumentException("Target cannot be null"); // throw statement}
// SuperMethodInvocation (numbers=3, modifier=protected)protected String super1 = super.toString();protected int super2 = SourceClass.super.hashCode();protected boolean super3 = SourceClass.this.equals(target);
// Try statementtry {// Do statementint count = 0;do {// Variable calltarget.targetMethod();TargetClass.TargetInner inner = target.new TargetInner();inner.innerMethod();data.add("loop-" + count);count++;} while (count < 2);
// Simulate IOException triggerif (data.size() > 3) {throw new IOException("Data size exceeds limit"); // IOException}} catch (IllegalStateException e) {// No new exception}
// Local inner class (source feature)class SourceLocalInner {public void processData() {data.add("local-inner-processed");}}new SourceLocalInner().processData();
return data;}}
// Implement interface method@Overridepublic Object processTarget(TargetClass target, List<String> data) throws IOException {return new SourceInner().innerMethod(target, data);}}
package targetpkg;
import java.util.List;
/**
Target Class with Javadoc
Target feature: javadoc + static nested class*/strictfp public class TargetClass {public void targetMethod() {}
// Target inner class (target_inner)public class TargetInner {public void innerMethod() {}}
// Static nested class (target_feature)public static class TargetStaticNested {public static void staticMethod(List<String> data) {data.add("static-nested-processed");}}}