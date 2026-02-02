package same.pkg;
/**
Parent interface for target data processing
*/
interface DataHandler {
String processData(String data);
}
/**
Parent class providing base data for target
*/
class ParentTargetClass {
protected String baseData = "parent_target_base";
}
/**
Target class with member inner class and recursive capability*/class TargetClass extends ParentTargetClass implements DataHandler {private String targetData;
/**
Creates TargetClass instance with initial data
@param data Initial data for target
*/
public TargetClass(String data) {
this.targetData = data;
}
@Overridepublic String processData(String data) {return baseData + "_" + data;}
/**
Member inner class for recursive data handling
/
class TargetInner {
/*
Recursively processes target data
@param depth Recursion depth
@return Processed data string
*/
public String recursiveProcess(int depth) {
if (depth <= 0) {
return targetData;
}
return processData(recursiveProcess(depth - 1));
}
}
/**
Gets initialized TargetInner instance
@return TargetInner object
*/
public TargetInner getTargetInner() {
return new TargetInner();
}
}
/**
Source class extending parent and containing static nested classes/
public class SourceClass extends ParentSourceClass {
/*
First static nested class for target validation
/
static class TargetValidator {
/*
Validates TargetClass instances
@param targets Varargs of TargetClass to validate
@return True if all targets are non-null, false otherwise
*/
public static boolean validate(TargetClass... targets) {
if (targets == null) {
return false;
}
for (TargetClass target : targets) {
if (target == null) {
return false;
}
}
return true;
}
}
/**
Second static nested class for target data extraction
/
static class TargetDataExtractor {
/*
Extracts recursive data from target's inner class
@param target TargetClass instance
@param depth Recursion depth
@return Processed recursive data
*/
public static String extractRecursiveData(TargetClass target, int depth) {
return target.getTargetInner().recursiveProcess(depth);
}
}
/**
Processes varargs of TargetClass to get recursive inner class result
@param depth Recursion depth for target inner class
@param targets Varargs of TargetClass to process
@return First valid TargetClass instance after processing
@throws NullPointerException If targets are null or all invalid*/public TargetClass processTargets(int depth, TargetClass... targets) {if (!TargetValidator.validate(targets)) {throw new NullPointerException("Targets cannot be null or contain null elements");}
labeledProcessing: {for (TargetClass target : targets) {String recursiveData = TargetDataExtractor.extractRecursiveData(target, depth);System.out.println("Processed recursive data: " + recursiveData);
if (recursiveData.contains(parentSourceField)) {return target;}}break labeledProcessing;}
return targets[0];}}
/**
Parent class for SourceClass providing base field
*/
class ParentSourceClass {
protected String parentSourceField = "source_parent_base";
}