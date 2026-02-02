package test;
import java.util.List;import java.util.ArrayList;
private class SourceClass {private TargetClass targetField = new TargetClass();
class SourceInner {@MyAnnotationprotected List<String> varargsMethod(List<String> paramList, TargetClass... targets) {List<String> result = new ArrayList<>();
do {List<String> subResult = subMethod(targets[0], "do-while");result.addAll(subResult);} while (result.size() < 3);
Runnable anon1 = new Runnable() {@Overridepublic void run() {result.add(targetField.getName());}};anon1.run();
Runnable anon2 = new Runnable() {@Overridepublic void run() {result.addAll(paramList);}};anon2.run();
String varCall = targets[0].getName();result.add(varCall);
return result;}
protected List<String> subMethod(TargetClass target, String... args) {List<String> subResult = super.subMethod(target);subResult.add(args[0]);return subResult;}}}
public class TargetClass {private String name;
public TargetClass() {new Runnable() {@Overridepublic void run() {name = "TargetInstance";}}.run();}
public String getName() {return name;}
protected List<String> subMethod(TargetClass target) {return new ArrayList<>(List.of(target.getName()));}}
class SubTargetClass extends TargetClass {@Overrideprotected List<String> subMethod(TargetClass target) {List<String> subResult = super.subMethod(target);subResult.add("SubClassAdded");return subResult;}}
class OthersClass {private static int callMethod(TargetClass target, int choice) {switch (choice) {case 1:return new SourceClass.SourceInner().varargsMethod(List.of("test"), target).size();default:return target.getName().length();}}}
@interface MyAnnotation {}