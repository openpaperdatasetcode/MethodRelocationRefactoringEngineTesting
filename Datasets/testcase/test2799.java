package test;
import java.io.IOException;
private class SourceClass extends ParentSourceClass {class SourceInner {// Overloading method 1default void methodToMove(TargetClass target) throws IOException {// Constructor invocation (target's inner class)TargetClass.TargetInner inner = target.new TargetInner();// Depends on inner classinner.doSomething();
// Type declaration statementTargetClass.TargetInner typeDecl;// Variable call + access target's field (per_condition)typeDecl = inner;String targetField = target.targetField;
// Empty statement;
// Try statementtry {if (targetField == null) throw new IOException("Target field is null");} catch (IOException e) {throw e;}}
// Overloading method 2default void methodToMove(TargetClass target, int num) throws IOException {// Override violation: overrides parent's public method with default access}}
public void createLocalInner() {// Local inner class (source_feature)class LocalInnerSource {}}}
class ParentSourceClass {// Parent method for override violationpublic void methodToMove(TargetClass target, int num) {}}
public class TargetClass implements SomeInterface {public String targetField = "targetFieldValue"; // Source contains target's field
// Member inner class (target_feature)class TargetInner {public void doSomething() {}}
// Another member inner class (target_feature)class AnotherTargetInner {}}
interface SomeInterface {}