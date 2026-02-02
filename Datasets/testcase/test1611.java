package test;
import java.util.List;import java.util.ArrayList;
protected enum SourceEnum {INSTANCE;
public static class StaticNested {public void helper(TargetEnum.Inner.Rec rec) {// Anonymous inner classRunnable runner = new Runnable() {@Overridepublic void run() {rec.values.add("helper");}};runner.run();}}
public void process(TargetEnum.Inner.Rec targetRec) {try {// Super constructor invocation in target's anonymous inner classTargetEnum.Inner inner = targetRec.new Inner();
// Access instance field of target inner rectargetRec.count++;
// Variable call - access target inner rec's fieldStaticNested helper = new StaticNested();helper.helper(targetRec);
// Requires try-catch for potential exceptionsif (targetRec.values.isEmpty()) {throw new IllegalStateException("Empty values");}} catch (IllegalStateException e) {// Handle exception}}}
/**
TargetEnum with Javadoc
Contains inner classes and anonymous inner classes*/public enum TargetEnum {VALUE;
public class Inner {public class Rec {public int count;public List<String> values = new ArrayList<>();
public Inner createInner() {// Anonymous inner class in targetreturn new Inner() {{super(); // Super constructor invocation}};}}}}