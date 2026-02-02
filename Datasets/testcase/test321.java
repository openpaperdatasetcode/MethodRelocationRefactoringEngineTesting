package com.source;
private class SourceClass {private com.target.TargetClass targetField = new com.target.TargetClass();
// Member inner class 1class InnerClassOne {}
// Member inner class 2class InnerClassTwo {}
strictfp Object moveMethodExample(String... varargs) {try {// Type declaration statementInteger localVar;// ConditionalExpression 1localVar = varargs.length > 0 ? Integer.parseInt(varargs[0]) : 0;// ConditionalExpression 2Object result = localVar > 5 ? targetField : new Object();// Variable calltargetField.nestedStaticClassMethod();// Requires try-catch (IOException)new java.io.FileInputStream("test.txt").close();return result;} catch (java.io.IOException | NumberFormatException e) {return null;}}}
package com.target;
class TargetClass extends BaseClass {// Static nested class (target_inner)static class TargetInnerClass {strictfp Object moveMethodExample(String... varargs) {// Placeholder for moved methodreturn null;}}
void nestedStaticClassMethod() {}}
class BaseClass {}