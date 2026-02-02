package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;import java.util.stream.Collectors;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorMethodAnno {}
abstract sealed class SourceClass permits SubSourceClass {private String outerPrivateField = "source_private_data";private TargetClass targetField = new TargetClass() {};
@RefactorMethodAnnopublic List<String> overloadedMethod() {typeDeclaration: {class LocalTypeDecl {String getProcessedData() {return SourceClass.this.outerPrivateField + "_processed";}}LocalTypeDecl typeInst = new LocalTypeDecl();
List<String> baseData = new ArrayList<>();baseData.add(typeInst.getProcessedData());baseData.add(privateNormalMethod(1));
targetField.executeAnonymousAction();String var = targetField.getTargetData();baseData.add(var);
return baseData;}}
@RefactorMethodAnnopublic List<String> overloadedMethod(int filterCount) {List<String> fullData = overloadedMethod();return fullData.stream().limit(filterCount).collect(Collectors.toList());}
private String privateNormalMethod(int num) {return "base_type_val_" + num;}
{new Runnable() {@Overridepublic void run() {SourceClass.this.overloadedMethod();}}.run();}
public synchronized int callMethod(List<String> dataList) {int count = 0;for (String data : dataList) {count += SourceClass.processData(data);}return count;}
private static int processData(String data) {return data.length();}}
non-sealed class SubSourceClass extends SourceClass {}
abstract class TargetClass {private String targetData = "target_instance_data";
public void executeAnonymousAction() {Thread actionThread = new Thread() {@Overridepublic void run() {System.out.println("Anonymous inner class action in TargetClass");}};actionThread.start();}
public String getTargetData() {return targetData;}}