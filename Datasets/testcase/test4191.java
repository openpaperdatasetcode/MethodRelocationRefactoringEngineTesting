package test;
import java.io.IOException;
protected class SourceClass {private String outerPrivateField = "sourcePrivateVal";
private int instanceMethod(TargetClass.TargetInnerRec innerRec) throws IOException {String varCall = innerRec.innerField;String privateAccess = outerPrivateField;
if (varCall.isEmpty() || privateAccess.isEmpty()) {throw new IOException("Field cannot be empty");}
return varCall.length() + privateAccess.length();}}
public class TargetClass {static class TargetInnerRec {String innerField = "targetInnerVal";}}
