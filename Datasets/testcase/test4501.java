package test;
protected class SourceClass {public static class SourceStaticNested1 {void useSourceMethod() {try {new SourceClass().sourceMethod(new TargetClass());} catch (Exception e) {}}}
public static class SourceStaticNested2 {TargetClass nestedTargetParam;void invoke() {try {TargetClass result = new SourceClass().sourceMethod(nestedTargetParam);} catch (Exception e) {}}}
public TargetClass sourceMethod(TargetClass param) throws Exception {int paramFieldValue = param.targetStaticNested.targetField;if (paramFieldValue < 0) {throw new Exception("Invalid field value");}return param;}}
class TargetClass {public static class TargetStaticNested {int targetField = 15;}TargetStaticNested targetStaticNested = new TargetStaticNested();}