package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
// Source class: normal class, protected modifier, same package with targetprotected class SourceClass {// Per_condition: source contains the field of the targetprivate TargetClass targetField = new TargetClass();
// Source feature: static nested classstatic class SourceStaticNestedClass {}
// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class execution");}};anonymous.run();}
// Source inner class (method_position: source_inner_rec)class SourceInnerClass {// Method to be refactored: normal, default access, return List<String>List<String> refactorTargetMethod() {// Type declaration statementclass MethodLocalType {}MethodLocalType localInstance = new MethodLocalType();
// Variable call (target class field and its method)TargetClass tempTarget = targetField;List<String> targetData = tempTarget.getTargetData();
// Depends on target's inner class (target_inner)String innerClassResult = tempTarget.invokeLocalInnerMethod();
// Assemble resultList<String> result = new ArrayList<>(targetData);result.add(innerClassResult);
// No new exception thrownreturn result;}}
// Call method: parent_class type, synchronized modifier, pos in do-while, return intpublic synchronized int callParentChainedMethod(ParentClass parent) {int result = 0;do {// do-while position + obj.m1().m2().m3() chained callresult = parent.m1().m2().m3();} while (result < 10);return result;}}
// Target class: normal class, protected modifier, implements interface + local inner class (target_feature)protected class TargetClass implements TargetInterface {private List<String> targetData = List.of("target_field_data");
@Overridepublic List<String> getTargetData() {return new ArrayList<>(targetData);}
// Target feature: local inner class (target_inner)public String invokeLocalInnerMethod() {class TargetLocalInnerClass {String processData() {return "Local inner class processed: " + targetData.get(0);}}TargetLocalInnerClass localInner = new TargetLocalInnerClass();return localInner.processData();}}
// Interface for target class implements featureinterface TargetInterface {List<String> getTargetData();}
// Parent class for call_method (parent_class type)class ParentClass {// Chained method 1public MiddleClass1 m1() {return new MiddleClass1();}
// Middle class 1 for chained callpublic static class MiddleClass1 {public MiddleClass2 m2() {return new MiddleClass2();}}
// Middle class 2 for chained callpublic static class MiddleClass2 {public int m3() {return (int) (Math.random() * 20);}}}
