package test;
import java.io.FileNotFoundException;
protected class TargetClass {String targetField;
class TargetInner {record TargetInnerRec(String val) {} // target_inner_rec}}
abstract class SourceClass {static class SourceStaticNested {}
public SourceClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
class SourceInner {/**
Method Javadoc
Processes TargetClass.TargetInner.TargetInnerRec with varargs
@param rec TargetInnerRec instance
@param args Variable length string arguments
@throws FileNotFoundException if required resource is missing*/strictfp void methodToMove(TargetClass.TargetInner.TargetInnerRec rec, String... args) throws FileNotFoundException { // requires_try_catch// Variable callString var = rec.val();TargetClass target = new TargetClass();String fieldVal = target.targetField;
// Required try-catch (checked exception handling)try {if (args.length == 0) {throw new FileNotFoundException("No arguments provided");}} catch (FileNotFoundException e) {throw e; // Propagate checked exception}}}}