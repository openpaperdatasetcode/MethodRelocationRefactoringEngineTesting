package test;
import java.io.IOException;
// Functional interface for method overridinginterface Processable {Object process(Object data) throws IOException;}
abstract class SourceClass<T> {// Static nested classpublic static class StaticNested {}
// Member inner class implementing Processablepublic class SourceInner implements Processable {/**
Overriding method to process target inner class instances
Handles target inner fields and requires try-catch block
@param data TargetClass.InnerClass instance
@return Processed result object*/@Override@MyAnnotationpublic Object process(Object data) {// Type declaration statementTargetClass.InnerClass targetInner = (TargetClass.InnerClass) data;
// Expression statementString info = "Inner value: " + targetInner.innerField;
// Variable callObject result = targetInner.compute(targetInner.innerField * 2);
// Requires try-catch blocktry {if (targetInner.innerField < 0) {throw new IOException("Negative value not allowed");}} catch (IOException e) {result = "Error: " + e.getMessage();}
return result;}}}
non-sealed class TargetClass {// Target's member inner classpublic class InnerClass {public int innerField = 15;
public Object compute(int input) {return input * 3;}}}
@interface MyAnnotation {}
